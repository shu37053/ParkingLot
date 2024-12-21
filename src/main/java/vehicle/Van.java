package vehicle;

import lombok.Builder;
import model.Ticket;

public class Van extends Vehicle {
    @Builder
    public Van(String vinNumber, Ticket ticket) {
        super(VehicleType.VAN, vinNumber, ticket);
    }
}
