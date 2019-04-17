package Simulation;

import java.io.IOException;

// Kylie wrote this class.

public interface Observer {
    void update(Address truckCurLocation) throws IOException;
}
