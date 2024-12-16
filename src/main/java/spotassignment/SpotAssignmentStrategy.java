package spotassignment;

import model.AppConfig;
import model.Entry;
import model.Position;
import model.Ticket;
import vehicle.VehicleType;

import java.util.List;
import java.util.Map;

public abstract class SpotAssignmentStrategy {
    List<Entry> entryList;
    Map<String, Position> positionMap;

    public SpotAssignmentStrategy(final AppConfig config, final Map<String, Position> positionMap) {
        this.entryList = config.getEntries();
        this.positionMap = positionMap;
    }

    public abstract Ticket getParkingSpot(final VehicleType type, final Entry entry);
}
