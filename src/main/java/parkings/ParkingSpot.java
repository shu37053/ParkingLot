package parkings;

import exceptions.OperationNotAllowed;
import exceptions.OperationNotSupported;
import model.Ticket;
import vehicle.VehicleType;

public abstract class ParkingSpot {
    private final VehicleType type;
    private final String id;
    private Ticket currentTicket;

    public ParkingSpot(final VehicleType type, String id) {
        this.type = type;
        this.id = id;
        currentTicket = null;
    }

    public VehicleType getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public Ticket getCurrentTicket() {
        return currentTicket;
    }

    public void setCurrentTicket(Ticket currentTicket) throws OperationNotSupported {
        if (this.currentTicket != null) {
            throw new OperationNotSupported("Position already occupied");
        }
        this.currentTicket = currentTicket;
    }
}
