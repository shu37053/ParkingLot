package parkings;

import lombok.Builder;
import vehicle.VehicleType;

public class VanParking extends ParkingSpot {
    @Builder
    public VanParking(final VehicleType type, final String id) {
        super(type, id);
    }
}
