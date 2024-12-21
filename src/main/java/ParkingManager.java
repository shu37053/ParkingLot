import exceptions.InvalidConfigException;
import exceptions.NoAvailableParking;
import model.AppConfig;
import model.Entry;
import model.Position;
import model.Ticket;
import parkings.*;
import payment.FlatPaymentCalculator;
import payment.PaymentCalculator;
import payment.PaymentInfo;
import payment.PaymentType;
import spotassignment.BalancedAssignmentStrategy;
import spotassignment.SpotAssignmentStrategy;
import vehicle.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ParkingManager {
    private final AppConfig appConfig;
    private final Map<String, Position> positionMap;
    private final Map<String, Entry> entryMap;
    private final SpotAssignmentStrategy assignmentStrategy;
    private final PaymentCalculator paymentCalculator;
    Map<String, Vehicle> parkedVehicle;


    public ParkingManager(AppConfig appConfig) {
        this.appConfig = appConfig;
        this.positionMap = new HashMap<>();
        this.entryMap = new LinkedHashMap<>();
        try {
            processPosition(appConfig.getPositions());
        } catch (InvalidConfigException e) {
            throw new RuntimeException(e);
        }
        assignmentStrategy = new BalancedAssignmentStrategy(appConfig, positionMap);
        paymentCalculator = new FlatPaymentCalculator();
    }

    public Ticket park(final VehicleType type, final String number, final String entryId) throws NoAvailableParking {
        Ticket ticket = assignmentStrategy.getParkingSpot(type, entryMap.get(entryId));
        Vehicle vehicle = getVehicle(number, type);
        vehicle.setTicket(ticket);
        saveVehicle(vehicle);
        return ticket;
    }

    public PaymentInfo exit(final String vinNumber, final String exitId, final PaymentType paymentType) {
        Vehicle vehicle = parkedVehicle.get(vinNumber);
        Ticket ticket = vehicle.getTicket();
        Entry exitGate = entryMap.get(exitId);
        ticket.setExitTime(System.currentTimeMillis());
        ticket.setExit(exitGate);
        PaymentInfo paymentInfo = paymentCalculator.getPayment(ticket, vehicle, paymentType);
        savePaymentInfo(paymentInfo);
        return paymentInfo;
    }

    private void savePaymentInfo(PaymentInfo paymentInfo) {
        //persist payment info
    }

    private void saveVehicle(Vehicle vehicle) {
        parkedVehicle.put(vehicle.getVinNumber(), vehicle);
        //persist vehicle relate information in permanent storage
    }

    private Vehicle getVehicle(final String number, final VehicleType type) {
        return type.accept(new VehicleType.Visitor<String, Vehicle>() {
            @Override
            public Vehicle visitMotorcycle(String s) {
                return MotorCycle.builder()
                        .vinNumber(number)
                        .build();
            }

            @Override
            public Vehicle visitCar(String s) {
                return Car.builder()
                        .vinNumber(number)
                        .build();
            }

            @Override
            public Vehicle visitVan(String s) {
                return Van.builder()
                        .vinNumber(number)
                        .build();
            }

            @Override
            public Vehicle visitTruck(String s) {
                return Truck.builder()
                        .vinNumber(number)
                        .build();
            }
        }, number);
    }

    private void processPosition(List<Position> positionList) throws InvalidConfigException {
        for(Position position : positionList) {
            for(int i=1;i<=position.getTotalParkingSpots();i++) {
                position.getParkingSpots().offer(getParkingSpot(position.getType(), position.getPositionName(), i));
            }
            if (positionMap.put(position.getPositionName(), position) != null) {
                throw new InvalidConfigException("Duplicate position name provided");
            }
        }
    }

    private ParkingSpot getParkingSpot(VehicleType type, String positionName, int count) {
        return type.accept(new VehicleType.Visitor<String, ParkingSpot>() {
            @Override
            public ParkingSpot visitMotorcycle(String s) {
                return MotorCycleParking.builder().id(positionName+"-"+count).type(type).build();
            }

            @Override
            public ParkingSpot visitCar(String s) {
                return CarParking.builder().id(positionName+"-"+count).type(type).build();
            }

            @Override
            public ParkingSpot visitVan(String s) {
                return VanParking.builder().id(positionName+"-"+count).type(type).build();
            }

            @Override
            public ParkingSpot visitTruck(String s) {
                return TruckParking.builder().id(positionName+"-"+count).type(type).build();
            }
        }, positionName);
    }
}
