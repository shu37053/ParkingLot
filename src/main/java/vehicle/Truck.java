package vehicle;

import lombok.Builder;

public class Truck extends Vehicle {
    @Builder
    public Truck(VehicleType type, String vinNumber) {
        super(type, vinNumber);
    }
}
