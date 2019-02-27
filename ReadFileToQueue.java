import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ReadFileToQueue {

    public static void main(String[] args) throws IOException {


        File file = new File("RandomAddresses.txt");
        BufferedReader in = new BufferedReader(new FileReader(file));

        PriorityQueue<ArrayList> pq = new PriorityQueue<>(100);

        String line;
        Address truckLocation = new Address(910,"South", 9);

        while ((line = in.readLine()) != null)
        {
            String[] addressArray = line.split(" ");
            int houseNumber = Integer.parseInt(addressArray[0]);
            String direction = addressArray[1];
            int streetNumber = Integer.parseInt(addressArray[2]);

            Address address = new Address(houseNumber, direction, streetNumber);
            address.compareTo(truckLocation);

            System.out.println(houseNumber + " " + direction + " " + streetNumber);
        }
    }


}
