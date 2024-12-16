package parkings;

import vehicle.VehicleType;

public abstract class ParkingSpot {
    private final VehicleType type;
    private final String id;

    public ParkingSpot(final VehicleType type, String id) {
        this.type = type;
        this.id = id;
    }
}
