package parkings;

import lombok.Builder;
import vehicle.VehicleType;

public abstract class ParkingSpot {
    private final VehicleType type;

    public ParkingSpot(VehicleType type) {
        this.type = type;
    }
}
