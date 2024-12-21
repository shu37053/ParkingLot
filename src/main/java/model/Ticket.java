package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import parkings.ParkingSpot;

@Getter
public class Ticket {
    private final long entryTime;
    @Setter
    private long exitTime;
    private final String positionName;
    private final ParkingSpot parkingSpot;
    private final Entry entry;
    @Setter
    private Entry exit;

    @Builder
    public Ticket(long entryTime, long exitTime, String positionName, ParkingSpot parkingSpot, Entry entry, Entry exit) {
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.positionName = positionName;
        this.parkingSpot = parkingSpot;
        this.entry = entry;
        this.exit = exit;
    }
}
