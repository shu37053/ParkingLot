package exceptions;

import vehicle.VehicleType;

public class NoAvailableParking extends Exception {
    final VehicleType type;

    public NoAvailableParking(VehicleType type) {
        super();
        this.type = type;
    }
}
