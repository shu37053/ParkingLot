package parkings;

import lombok.Builder;
import vehicle.VehicleType;

public class VanParking extends ParkingSpot {
    @Builder
    public VanParking(VehicleType type) {
        super(type);
    }
}
