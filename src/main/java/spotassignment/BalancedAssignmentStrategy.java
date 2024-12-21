package spotassignment;

import exceptions.NoAvailableParking;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import model.AppConfig;
import model.Entry;
import model.Position;
import model.Ticket;
import vehicle.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BalancedAssignmentStrategy extends SpotAssignmentStrategy{
    Map<VehicleType, PositionStatus> allPositions;
    public BalancedAssignmentStrategy(AppConfig config, Map<String, Position> positionMap) {
        super(config, positionMap);
        for(VehicleType type : VehicleType.values()) {
            allPositions.put(type, PositionStatus.builder().vehicleType(type).positions(new ArrayList<>()).build());
        }
        for(Position position : config.getPositions()) {
            allPositions.get(position.getType()).getPositions().add(position);
        }
    }

    @Override
    public Ticket getParkingSpot(VehicleType type, Entry entry) throws NoAvailableParking {
        PositionStatus positionStatus = allPositions.get(type);
        Position position = positionStatus.positions.get(positionIndex(positionStatus));
        long currTime = System.currentTimeMillis();
        return Ticket.builder()
                .entryTime(currTime)
                .positionName(position.getPositionName())
                .parkingSpot(position.getParkingSpots().poll())
                .build();
    }

    private int positionIndex(PositionStatus status) throws NoAvailableParking {
        int start = (status.pointer+1)%status.positions.size();
        int curr = start;
        do {
            curr = (curr + 1) % status.positions.size();
            if(status.positions.get(curr).hasParkingSpots()) {
                return curr;
            }
        } while(curr != start);
        throw new NoAvailableParking(status.vehicleType);
    }

    @Getter
    private class PositionStatus {
        private final VehicleType vehicleType;
        private final List<Position> positions;
        @Setter
        int pointer;

        @Builder
        public PositionStatus(VehicleType vehicleType, List<Position> positions) {
            this.vehicleType = vehicleType;
            this.positions = positions;
            pointer = -1;
        }
    }
}
