package vehicle;

import lombok.Data;
import model.Ticket;

@Data
public abstract class Vehicle {
    private final VehicleType type;
    private String vinNumber;
    private Ticket ticket;

    Vehicle(final VehicleType type, final String vinNumber, Ticket ticket) {
        this.type = type;
        this.vinNumber = vinNumber;
        this.ticket = ticket;
    }
}
