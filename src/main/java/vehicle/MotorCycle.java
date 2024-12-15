package vehicle;

import lombok.Builder;

public class MotorCycle extends Vehicle {
    @Builder
    public MotorCycle(VehicleType type, String vinNumber) {
        super(type, vinNumber);
    }
}
