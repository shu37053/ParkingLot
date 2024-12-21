package vehicle;


import lombok.Builder;
import model.Ticket;

public class Car extends Vehicle {
    @Builder
    Car(final String vinNumber, final Ticket ticket) {
        super(VehicleType.CAR, vinNumber, ticket);
    }
}
