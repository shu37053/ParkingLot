package model;

import lombok.Data;
import parkings.*;
import vehicle.VehicleType;

import java.util.ArrayList;
import java.util.List;

@Data
public class Parking {
    private List<Position> positions;

    private List<Entry> entryList;

    public Parking(List<Position> positions, List<Entry> entryList) {
        this.positions = positions;
        this.entryList = entryList;
    }

    public Parking(final AppConfig config) {
        this.positions = config.getPositions();
        processPosition(this.positions);
        this.entryList = config.getEntries();
    }

    private void processPosition(List<Position> positions) {
        positions.forEach((position) -> {
            position.setParkingSpots(createParkingSpotList(position.getType(), position.getTotalParkingSpots()));
        });
    }

    private List<ParkingSpot> createParkingSpotList(final VehicleType type, final int totalParkingSpots) {
        List<ParkingSpot> spots = new ArrayList<>();
        for(int i=0;i<totalParkingSpots;i++) {
            switch (type) {
                case MOTORCYCLE: spots.add(MotorCycleParking.builder().build());
                break;
                case VAN: spots.add(VanParking.builder().build());
                break;
                case CAR: spots.add(CarParking.builder().build());
                break;
                case TRUCK: spots.add(TruckParking.builder().build());
                break;
            }
        }
        return spots;
    }

//    private Void
}
