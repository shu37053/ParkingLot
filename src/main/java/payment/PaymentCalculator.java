package payment;

import model.Ticket;
import vehicle.Vehicle;
import vehicle.VehicleType;

public abstract class PaymentCalculator {
    public PaymentInfo getPayment(final Ticket ticket, final Vehicle vehicle, final PaymentType paymentType) {
        return PaymentInfo.builder()
                .paymentType(paymentType)
                .entryTime(ticket.getEntryTime())
                .exitTime(ticket.getExitTime())
                .total(getTotalAmount(vehicle.getType(), ticket))
                .build();
    }

    protected abstract int getTotalAmount(final VehicleType type, final Ticket ticket);
}
