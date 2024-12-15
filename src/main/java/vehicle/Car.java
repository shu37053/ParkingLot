package vehicle;


import lombok.Builder;

public class Car extends Vehicle {
    @Builder
    Car(final VehicleType type, final String vinNumber) {
        super(type, vinNumber);
    }
}
