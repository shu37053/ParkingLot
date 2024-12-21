import exceptions.InvalidConfigException;
import model.AppConfig;
import model.Entry;
import model.Position;
import model.Ticket;
import parkings.*;
import spotassignment.BalancedAssignmentStrategy;
import spotassignment.SpotAssignmentStrategy;
import vehicle.Vehicle;
import vehicle.VehicleType;

import java.util.*;

public class ParkingManager {
    private final AppConfig appConfig;
    private final Map<String, Position> positionMap;
    private final Map<String, Entry> entryMap;
    private final SpotAssignmentStrategy assignmentStrategy;


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
