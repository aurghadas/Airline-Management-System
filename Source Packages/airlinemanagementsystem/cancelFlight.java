import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class cancelFlight extends JFrame implements ActionListener {

    JTextField userPNRField;
    JLabel userNameLabel, userNumberLabel, ticketCancellationLabel, userFlightCodeLabel, userTravelDate,
            userTravelDateLabel;
    JButton cancelFlightButton, showButton;
    int cancellationNumber;
    ArrayList<Reservations> allReservations = new ArrayList<>();

    public class Reservations {
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

        public Reservations(String pnr, String ticketNumber, String name, String nationality, String number,
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

    public cancelFlight() {
        getContentPane().setBackground(Color.white);
        setLayout(null);

        Random random = new Random();

        // Label for heading
        JLabel heading = new JLabel("CANCELLATION");
        heading.setBounds(180, 20, 250, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        add(heading);

        // Adding Image to the frame
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/cancel.jpg"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(470, 120, 250, 250);
        add(image);

        // Label for PNR
        JLabel userPNR = new JLabel("PNR Number");
        userPNR.setBounds(60, 80, 150, 25);
        userPNR.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(userPNR);

        // TextField for aadhar
        userPNRField = new JTextField();
        userPNRField.setBounds(220, 80, 150, 25);
        add(userPNRField);

        // Button to show details
        showButton = new JButton("Show details");
        showButton.setBackground(Color.BLACK);
        showButton.setForeground(Color.WHITE);
        showButton.setBounds(380, 80, 120, 25);
        showButton.addActionListener(this);
        add(showButton);

        // Label for name
        JLabel userName = new JLabel("Name");
        userName.setBounds(60, 130, 150, 25);
        userName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(userName);

        userNameLabel = new JLabel();
        userNameLabel.setBounds(220, 130, 150, 25);
        add(userNameLabel);

        // Label for cancellation number
        JLabel ticketCancellation = new JLabel("Cancellation Number");
        ticketCancellation.setBounds(60, 180, 150, 25);
        ticketCancellation.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(ticketCancellation);

        cancellationNumber = random.nextInt(1000000);

        ticketCancellationLabel = new JLabel("" + cancellationNumber);
        ticketCancellationLabel.setBounds(220, 180, 190, 30);
        add(ticketCancellationLabel);

        // Label for flight code
        JLabel userFlightCode = new JLabel("Flight Code");
        userFlightCode.setBounds(60, 230, 150, 25);
        userFlightCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(userFlightCode);

        userFlightCodeLabel = new JLabel();
        userFlightCodeLabel.setBounds(220, 230, 150, 25);
        add(userFlightCodeLabel);

        // Label for date of travel
        JLabel userTraveldate = new JLabel("Date");
        userTraveldate.setBounds(60, 280, 150, 25);
        userTraveldate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(userTraveldate);

        userTravelDateLabel = new JLabel();
        userTravelDateLabel.setBounds(220, 280, 150, 25);
        add(userTravelDateLabel);

        // Button for flight cancellation
        cancelFlightButton = new JButton("Cancel");
        cancelFlightButton.setBackground(Color.BLACK);
        cancelFlightButton.setForeground(Color.white);
        cancelFlightButton.setBounds(220, 330, 120, 25);
        cancelFlightButton.addActionListener(this);
        add(cancelFlightButton);

        setSize(800, 450);
        setLocation(500, 200);
        setVisible(true);
    }


        public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == showButton) {

            boolean flag = false;
            File file = new File("Files", "reservation" + ".txt");
            String enteredPNR = "\"" + userPNRField.getText().trim() + "\""; // Adds quotes around the text field input

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

                        getNextLine(reader);

                        String nameLine = getNextLine(reader);
                        String name = performSplit(nameLine, "=", 1);

                        getNextLine(reader);

                        getNextLine(reader);

                        getNextLine(reader);

                        getNextLine(reader);

                        getNextLine(reader);

                        getNextLine(reader);

                        String flightCodeLine = getNextLine(reader);
                        int flightCode = Integer.parseInt(performSplit(flightCodeLine, "=", 1).trim());

                        getNextLine(reader);

                        getNextLine(reader);

                        String dateLine = getNextLine(reader);
                        String date = performSplit(dateLine, "=", 1);

                        if (pnr.trim().equals(enteredPNR.trim())) {
                            userNameLabel.setText(name.replace("\"", ""));
                            userFlightCodeLabel.setText("" + flightCode);
                            userTravelDateLabel.setText(date.replace("\"", ""));
                            flag = true;
                        }
                    }

                    if (flag == false) {
                        JOptionPane.showMessageDialog(null, "Invalid PNR. Please enter correct one.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        } else if (ae.getSource() == cancelFlightButton) {

            // boolean flag = false;
            File file = new File("Files", "reservation" + ".txt");
            String enteredPNR = "\"" + userPNRField.getText().trim() + "\""; // Adds quotes around the text field input

            if (file.exists()) {

                try {

                    Scanner reader = new Scanner(file);
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

                        allReservations
                                .add(new Reservations(pnr, ticketNumber, name, nationality, number, address, passport,
                                        gender, flightName, flightCode, source, destination, date));

                        for (Reservations reservation : allReservations) {

                            if (reservation.pnr.trim().equals(enteredPNR.trim())) {

                                if (!new File("Files").exists()) {
                                    new File("Files").mkdir();
                                }

                                File file1 = new File("Files/" + "cancellation" + ".txt"); // Creating the file object

                                try {
                                    PrintWriter writer = new PrintWriter(new FileWriter(file1, true)); // Creating the
                                                                                                       // PrintWriter
                                                                                                       // object
                                                                                                       // for
                                    // writing data in the file

                                    writer.printf(
                                            "PNR = %s\nName = %s\nCancellation Number = %d\nFlight Code = %d\nDate = %s\n\n",
                                            reservation.pnr, reservation.name, cancellationNumber,
                                            reservation.flightCode, reservation.date);

                                    writer.close(); // Closing the PrintWriter

                                    JOptionPane.showMessageDialog(null, "Ticket Cancelled Successfully.");
                                    setVisible(false);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        Iterator<Reservations> iterator = allReservations.iterator();
                        while (iterator.hasNext()) {
                            Reservations reservation = iterator.next();
                            if (reservation.pnr.trim().equals(enteredPNR.trim())) {
                                // Perform your cancellation logic here, like writing to the cancellation file
                        
                                iterator.remove(); // Safely remove the reservation from the list
                            }
                        }

                            File file2 = new File("Files/" + "reservation" + ".txt"); // Creating the file object

                            try {
                                PrintWriter writer = new PrintWriter(file2); // Creating the PrintWriter object for
                                                                            // writing data in the file

                                // Loop to iterate through whole investments arraylist
                                for (Reservations reservation : allReservations) {

                                    writer.printf(
                                        "PNR = %s\nTicket Number = %s\nName = %s\nNationality = %s\nNumber = %s\nAddress = %s\nPassport = %s\nGender = %s\nFlight Name = %s\nFlight Code = %s\nSource = %s\nDestination = %s\nDate = %s\n\n",
                                        reservation.pnr, reservation.ticketNumber, reservation.name, reservation.nationality, reservation.number, reservation.address, reservation.passport, reservation.gender, reservation.flightName,
                                        reservation.flightCode,
                                        reservation.source, reservation.destination, reservation.date);
                                    }
                                
                                writer.close(); // Closing the PrintWriter

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    }

    private String getNextLine(Scanner reader) {
        return reader.nextLine();
    }

    private String performSplit(String line, String delimiter, int index) {
        String[] parts = line.split(delimiter);
        return parts[index];
    }

    public static void main(String[] args) {
        new cancelFlight();
    }
}
