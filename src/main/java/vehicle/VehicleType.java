package vehicle;

public enum VehicleType {
    MOTORCYCLE {
        @Override
        public <T, R> R accept(Visitor<T, R> visitor, T data) {
            return visitor.visitMotorcycle(data);
        }
    },
    CAR {
        @Override
        public <T, R> R accept(Visitor<T, R> visitor, T data) {
            return visitor.visitCar(data);
        }
    },
    VAN {
        @Override
        public <T, R> R accept(Visitor<T, R> visitor, T data) {
            return visitor.visitVan(data);
        }
    },
    TRUCK {
        @Override
        public <T, R> R accept(Visitor<T, R> visitor, T data) {
            return visitor.visitTruck(data);
        }
    };

    public abstract <T, R> R accept(Visitor<T, R> visitor, T data);

    public interface Visitor<T, R> {
        R visitMotorcycle(T t);

        R visitCar(T t);

        R visitVan(T t);

        R visitTruck(T t);
    }
}
