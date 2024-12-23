package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import parkings.ParkingSpot;
import vehicle.VehicleType;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

@Data
public class Position {
    @JsonProperty("positionName")
    private final String positionName;
    @JsonProperty("type")
    private final VehicleType type;
    private Queue<ParkingSpot> parkingSpots;
    private final Map<String, ParkingSpot> occupiedSpots;
    private int totalParkingSpots;
    private int availableParkingSpots;

    @JsonCreator
    public Position(@JsonProperty("positionName") String positionName, @JsonProperty("type") VehicleType type) {
        this.type = type;
        this.positionName = positionName;
        this.parkingSpots = new LinkedList<>();
        this.occupiedSpots = new HashMap<>();
    }

    @Builder
    public Position(String positionName, VehicleType type, Queue<ParkingSpot> parkingSpots) {
        this.positionName = positionName;
        this.type = type;
        this.parkingSpots = parkingSpots;
        totalParkingSpots = parkingSpots.size();
        availableParkingSpots = 0;
        this.occupiedSpots = new HashMap<>();
    }

    public boolean hasParkingSpots() {
        return totalParkingSpots != availableParkingSpots;
    }
}
