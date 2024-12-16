package parkings;

import lombok.Builder;
import vehicle.VehicleType;

public class TruckParking extends ParkingSpot {
    @Builder
    public TruckParking(final VehicleType type, final String id) {
        super(type, id);
    }
}
