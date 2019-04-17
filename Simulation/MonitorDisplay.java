package Simulation;

import java.io.IOException;
import java.util.ArrayList;

// Kylie wrote this class.

public class MonitorDisplay implements Observer {
    @Override
    public void update(Address currentLocation) throws IOException {
        System.out.println(currentLocation);
        try {
            Thread.sleep(300);
        } catch (Exception ex) {
        }
    }
}
