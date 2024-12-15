package parkings;

import lombok.Builder;
import vehicle.VehicleType;

public class TruckParking extends ParkingSpot {
    @Builder
    public TruckParking(VehicleType type) {
        super(type);
    }
}
