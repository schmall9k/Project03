package Simulation;

public class OrderEvents {
    private static OrderEvents orderEventsInstance;

    private OrderEvents() {}

    public static OrderEvents getOrderEventsInstance() {

        if (orderEventsInstance == null)
            orderEventsInstance = new OrderEvents();

        return orderEventsInstance;
    }
}
