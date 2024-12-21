package payment;

import model.Ticket;
import vehicle.VehicleType;

public class FlatPaymentCalculator extends PaymentCalculator{
    private final int MOTORCYCLE_RATE = 2000;
    private final int CAR_RATE = 4000;
    private final int VAN_RATE = 6000;
    private final int TRUCK_RATE = 8000;

    @Override
    protected int getTotalAmount(VehicleType type, Ticket ticket) {
        int hours = getInHour(ticket.getEntryTime(), ticket.getExitTime());
        return type.accept(new VehicleType.Visitor<Void, Integer>() {
            @Override
            public Integer visitMotorcycle(Void unused) {
                return hours * MOTORCYCLE_RATE;
            }

            @Override
            public Integer visitCar(Void unused) {
                return hours * CAR_RATE;
            }

            @Override
            public Integer visitVan(Void unused) {
                return hours * VAN_RATE;
            }

            @Override
            public Integer visitTruck(Void unused) {
                return hours * TRUCK_RATE;
            }
        }, null);
    }

    private int getInHour(final long entryTime, final long exitTime) {
        int diff = (int)(exitTime - entryTime);
        diff = diff/(1000*60);
        return diff/60 + (diff%60>0?1:0);
    }
}
