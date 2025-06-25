package ship.helpz;

import ship.systems.ShipSystem;

public interface SystemFactory<T> {
    ShipSystem<T> createInstance();
}