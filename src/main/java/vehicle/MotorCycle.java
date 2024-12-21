package vehicle;

import lombok.Builder;
import model.Ticket;

public class MotorCycle extends Vehicle {
    @Builder
    public MotorCycle(String vinNumber, Ticket ticket) {
        super(VehicleType.MOTORCYCLE, vinNumber, ticket);
    }
}
