package vehicle;

import lombok.Builder;

public class Van extends Vehicle {
    @Builder
    public Van(VehicleType type, String vinNumber) {
        super(type, vinNumber);
    }
}
