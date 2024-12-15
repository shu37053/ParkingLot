package vehicle;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Builder;
import lombok.Data;

@Data
public abstract class Vehicle {
    private final VehicleType type;
    private String vinNumber;

    Vehicle(final VehicleType type, final String vinNumber) {
        this.type = type;
        this.vinNumber = vinNumber;
    }
}
