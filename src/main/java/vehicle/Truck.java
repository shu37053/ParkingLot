package vehicle;

import lombok.Builder;
import model.Ticket;

public class Truck extends Vehicle {
    @Builder
    public Truck(String vinNumber, Ticket ticket) {
        super(VehicleType.TRUCK, vinNumber, ticket);
    }
}
