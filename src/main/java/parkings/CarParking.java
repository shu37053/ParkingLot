package parkings;

import lombok.Builder;
import vehicle.VehicleType;

public class CarParking extends ParkingSpot {
    @Builder
    public CarParking(VehicleType type) {
        super(type);
    }
}
