package parkings;

import lombok.Builder;
import vehicle.VehicleType;

public class MotorCycleParking extends ParkingSpot {
    @Builder
    public MotorCycleParking(final VehicleType type, final String id) {
        super(type, id);
    }
}
