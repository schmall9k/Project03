package Simulation;

import java.io.*;
import java.util.Scanner;

public class Order {

    public void addOrder() {
        System.out.print("What is your order?");
        Scanner order= new Scanner(System.in);
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("Orders.txt"));
            out.write(String.valueOf(order));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editOrder(String lineToEdit, String newOrder) throws IOException {
        File inputFile = new File("Order.txt");
        File tempFile = new File("TempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;

        while((currentLine = reader.readLine()) != null) {

            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(lineToEdit)) continue;
            writer.write(currentLine + System.getProperty(newOrder));
        }
        writer.close();
        reader.close();
        tempFile.renameTo(inputFile);
    }


    public void deleteOrder(String lineToRemove ) throws IOException {
        File inputFile = new File("Order.txt");
        File tempFile = new File("TempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        
        String currentLine;

        while((currentLine = reader.readLine()) != null) {

            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(lineToRemove)) continue;
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        tempFile.renameTo(inputFile);
    }
}
