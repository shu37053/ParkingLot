package payment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentInfo {
    private final int total;
    private final long entryTime;
    private final long exitTime;
    private final PaymentType paymentType;
}
