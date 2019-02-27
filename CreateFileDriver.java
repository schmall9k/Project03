import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class CreateFileDriver {

    public static void main(String[] args) throws IOException {

        ArrayList<Address> listOfAddresses = new ArrayList<Address>();

        for (int i = 0; i < 100; i++)
        {
            String result = "";
            int range = 1000 - 100 + 1;
            int firstRand = new Random().nextInt(range) + 100;
            firstRand = firstRand / 10;
            result = Integer.toString(firstRand) + "0";
            firstRand = Integer.parseInt(result);

            String direction = "";
            int secRand = new Random().nextInt(2);
            if (secRand == 0)
                direction += "South";
            else
                direction += "East";

            int thirdRand = new Random().nextInt(20);
            Address address = new Address(firstRand,direction,thirdRand);
            System.out.println(address);
            listOfAddresses.add(address);
        }

        // writes to the file
        BufferedWriter out = new BufferedWriter(new FileWriter("RandomAddresses.txt"));
        for (int i = 0; i < listOfAddresses.size(); i++)
        {
            out.write(listOfAddresses.get(i).toString());
            out.write("\n");
        }

        out.close();
    }
}
