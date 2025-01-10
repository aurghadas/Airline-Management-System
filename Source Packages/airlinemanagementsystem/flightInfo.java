import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class flightInfo extends JFrame {

  ArrayList<Flight> flights = new ArrayList<>();

  private static class Flight {
    int flightCode;
    String flightName;
    String source;
    String destination;

    public Flight(int flightCode, String flightName, String source, String destination) {
        this.flightCode = flightCode;
        this.flightName = flightName;
        this.source = source;
        this.destination = destination;
    }
}

  public flightInfo() {

    getContentPane().setBackground(Color.white);
    setLayout(new BorderLayout());


    File file = new File("Files", "flightDetails" + ".txt");

    if (file.exists()) {
      try (Scanner reader = new Scanner(file)) {
        while (reader.hasNextLine()) {

          String flightCodeLine = getNextLine(reader);
          if (!flightCodeLine.contains("=")) {
            continue;
          }

          int flightCode = Integer.parseInt(performSplit(flightCodeLine, "=", 1).trim());
          String flightNameLine = getNextLine(reader);
          String flightName = performSplit(flightNameLine, "=", 1).trim();
          String sourceLine = getNextLine(reader);
          String source = performSplit(sourceLine, "=", 1).trim();
          String destinationLine = getNextLine(reader);
          String destination = performSplit(destinationLine, "=", 1).trim();
          
          flights.add(new Flight(flightCode, flightName, source, destination));
        }
      } catch (FileNotFoundException e) {
        System.err.println("File not found: " + e.getMessage());
      }

      String[] columnNames = {"Flight Code", "Flight Name", "Source", "Destination"};
        Object[][] data = new Object[flights.size()][4];

        for (int i = 0; i < flights.size(); i++) {
            Flight flight = flights.get(i);
            data[i][0] = flight.flightCode;
            data[i][1] = flight.flightName;
            data[i][2] = flight.source;
            data[i][3] = flight.destination;
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true); 
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    setSize(800, 400);
    setLocationRelativeTo(null);
    setVisible(true);

  }

  private String getNextLine(Scanner reader) {
    return reader.nextLine();
  }

  private String performSplit(String line, String delimiter, int index) {
    String[] parts = line.split(delimiter);
    return parts[index];
  }

  public static void main(String[] args) {
    new flightInfo();
  }
}
