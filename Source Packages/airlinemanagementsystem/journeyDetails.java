import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.table.DefaultTableModel;


public class journeyDetails extends JFrame implements ActionListener {

    JTable table;
    JTextField pnrTextField;
    JButton showButton;
    ArrayList<Reservation> reservations = new ArrayList<>();
    DefaultTableModel tableModel;
    JScrollPane scrollPane;

    public class Reservation {
        String pnr;
        String ticketNumber;
        String name;
        String nationality;
        String number;
        String address;
        String passport;
        String gender;
        String flightName;
        int flightCode;
        String source;
        String destination;
        String date;

        public Reservation(String pnr, String ticketNumber, String name, String nationality, String number,
                String address,
                String passport, String gender, String flightName, int flightCode, String source, String destination,
                String date) {
            this.pnr = pnr;
            this.ticketNumber = ticketNumber;
            this.name = name;
            this.nationality = nationality;
            this.number = number;
            this.address = address;
            this.passport = passport;
            this.gender = gender;
            this.flightName = flightName;
            this.flightCode = flightCode;
            this.source = source;
            this.destination = destination;
            this.date = date;
        }
    }

    public journeyDetails() {
        getContentPane().setBackground(Color.white);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        JLabel pnrDetailsLabel = new JLabel("PNR Number: ");
        pnrDetailsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        topPanel.add(pnrDetailsLabel);

        pnrTextField = new JTextField(15);
        topPanel.add(pnrTextField);

        showButton = new JButton("Show Details");
        showButton.setBackground(Color.BLACK);
        showButton.setForeground(Color.WHITE);
        showButton.addActionListener(this);
        topPanel.add(showButton);

        add(topPanel, BorderLayout.NORTH);

        String[] columnNames = {"PNR", "TICKET", "passport", "name", "nationality", "flightname", "flightcode", "source", "destination", "date"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(750, table.getRowHeight() * 2 + table.getTableHeader().getHeight()));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Add top margin
        add(scrollPane, BorderLayout.CENTER);

        setSize(1000, 600); // Maintaining the larger JFrame size for aesthetic or other UI components
        setLocationRelativeTo(null);
        setVisible(true);
    }
    

    public void actionPerformed(ActionEvent ae) {

        File file = new File("Files", "reservation" + ".txt");
        String enteredPNR = "\"" + pnrTextField.getText().trim() + "\""; // Adds quotes around the text field input

        if (file.exists()) {

            try (Scanner reader = new Scanner(file)) {

                while (reader.hasNextLine()) { // Reading data from the file line by line

                    if (reader.hasNextLine() == false) { // Exiting the loop when the file ends
                        break;
                    }

                    String pnrLine = getNextLine(reader);
                    if (pnrLine.contains("=") == false) { // Skip when the line does not contain "="
                        continue;
                    }

                    String pnr = performSplit(pnrLine, "=", 1);

                    String ticketNumberLine = getNextLine(reader);
                    String ticketNumber = performSplit(ticketNumberLine, "=", 1);

                    String nameLine = getNextLine(reader);
                    String name = performSplit(nameLine, "=", 1);

                    String nationalityLine = getNextLine(reader);
                    String nationality = performSplit(nationalityLine, "=", 1);

                    String numberLine = getNextLine(reader);
                    String number = performSplit(numberLine, "=", 1);

                    String addressLine = getNextLine(reader);
                    String address = performSplit(addressLine, "=", 1);

                    String PassportLine = getNextLine(reader);
                    String passport = performSplit(PassportLine, "=", 1);

                    String genderLine = getNextLine(reader);
                    String gender = performSplit(genderLine, "=", 1);

                    String flightNameLine = getNextLine(reader);
                    String flightName = performSplit(flightNameLine, "=", 1);

                    String flightCodeLine = getNextLine(reader);
                    int flightCode = Integer.parseInt(performSplit(flightCodeLine, "=", 1).trim());

                    String sourceLine = getNextLine(reader);
                    String source = performSplit(sourceLine, "=", 1);

                    String destinationLine = getNextLine(reader);
                    String destination = performSplit(destinationLine, "=", 1);

                    String dateLine = getNextLine(reader);
                    String date = performSplit(dateLine, "=", 1);

                    reservations
                            .add(new Reservation(pnr, ticketNumber, name, nationality, number, address, passport, gender,
                                    flightName, flightCode, source, destination, date));

                    // Clear previous data
                    tableModel.setRowCount(0);

                   
                }
                boolean matchFound = false;

                // Check each reservation for a PNR match
                for (Reservation reservation : reservations) {
                    if (reservation.pnr.trim().equals(enteredPNR)) {
                        Object[] data = {
                            reservation.pnr,
                            reservation.ticketNumber,
                            reservation.passport,
                            reservation.name,
                            reservation.nationality,
                            reservation.flightName,
                            reservation.flightCode,
                            reservation.source,
                            reservation.destination,
                            reservation.date
                        };
                        tableModel.addRow(data); // Add a row to the table model
                        matchFound = true;
                        break;
                    }
                }

                if (!matchFound) {
                    JOptionPane.showMessageDialog(null, "No matching PNR found.");
                }

            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + e.getMessage());
            }
        }

    }

    private String getNextLine(Scanner reader) {
        if (reader.hasNextLine()) {
            return reader.nextLine();
        } else {
            return "";
        }
    }

    private String performSplit(String line, String regex, int i) {
        String[] parts = line.split(regex);
        if (parts.length > i) {
            return parts[i];
        } else {
            return "";
        }
    }

    public static void main(String[] args) {
        new journeyDetails();
    }
}