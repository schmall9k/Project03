package Simulation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;

// Kylie wrote this class. Kiersten and Elijah helped determine what to write.
// We implemented the Singleton Pattern together IN CLASS.

public class OrderEvents {
    private static OrderEvents orderEventsInstance;
    private static PriorityQueue<Address> queueOfAddresses;

    private OrderEvents() throws IOException {
        this.queueOfAddresses = new PriorityQueue<>(Neighborhood.NUMBER_OF_ORDERS);
        createQueue();
    }

    public static OrderEvents getOrderEventsInstance() throws IOException {

        if (orderEventsInstance == null)
            orderEventsInstance = new OrderEvents();

        return orderEventsInstance;
    }

    // method that will generate the queue from reading the file of random addresses
    // Kylie created queue, Kiersten modified in Sprint 2 when order times were added.
    public void createQueue() throws IOException {

        File file = new File(Neighborhood.FILENAME);
        BufferedReader in = new BufferedReader(new FileReader(file));

        String line;

        while ((line = in.readLine()) != null) {
            String[] addressArray = line.split(" ");
            int houseNumber = Integer.parseInt(addressArray[0]);
            String direction = addressArray[1];
            int streetNumber = Integer.parseInt(addressArray[2]);
            String streetLabel = addressArray[3]; // don't need
            String deliveryTime = addressArray[4];
            String deliveryAMorPM = addressArray[5];

            Address address = new Address(houseNumber, direction, streetNumber, true, deliveryTime, deliveryAMorPM);

            queueOfAddresses.add(address);
        }

    }

    public static PriorityQueue<Address> getQueueOfAddresses() {
        return queueOfAddresses;
    }

    public static void setQueueOfAddresses(PriorityQueue<Address> queueOfAddresses) {
        OrderEvents.queueOfAddresses = queueOfAddresses;
    }
}
