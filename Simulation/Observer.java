/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & Elijah Ives

The observer component class.

 */

// Kylie wrote this class.

package Simulation;

import java.io.IOException;

public interface Observer {
    void update(Address truckCurLocation) throws IOException;
}
