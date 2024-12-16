package parkings;

import lombok.Builder;
import vehicle.VehicleType;

public class CarParking extends ParkingSpot {
    @Builder
    public CarParking(final VehicleType type, final String id) {
        super(type, id);
    }
}
