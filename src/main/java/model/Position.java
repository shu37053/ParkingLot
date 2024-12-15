package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import parkings.ParkingSpot;
import vehicle.VehicleType;

import java.util.List;

@Data
public class Position {
    @JsonProperty("positionName")
    private final String positionName;
    @JsonProperty("type")
    private final VehicleType type;
    private List<ParkingSpot> parkingSpots;
    private int totalParkingSpots;
    private int emptyParkingSpots;

    @JsonCreator
    public Position(@JsonProperty("positionName") String positionName, @JsonProperty("type") VehicleType type) {
        this.type = type;
        this.positionName = positionName;
    }

    @Builder
    public Position(String positionName, VehicleType type, List<ParkingSpot> parkingSpots) {
        this.positionName = positionName;
        this.type = type;
        this.parkingSpots = parkingSpots;
        totalParkingSpots = parkingSpots.size();
        emptyParkingSpots = 0;
    }
}
