package parkings;

import lombok.Builder;
import vehicle.VehicleType;

public class MotorCycleParking extends ParkingSpot {
    @Builder
    public MotorCycleParking(VehicleType type) {
        super(type);
    }
}
