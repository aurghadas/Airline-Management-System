import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;

public class bookFlight extends JFrame implements ActionListener {

    JTextField userPassportField;
    JLabel userNameLabel, userNationalityLabel, userAddressLabel, userGenderLabel, flightNameLabel,
            flightCodeLabel, travelDateLabel;
    JButton bookFlightButton, fetchFlightButton, fetchUserButton;
    Choice sourceChoice, destinationChoice;
    JSpinner dateSpinner;
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Flight> flightInfo = new ArrayList<>();
    private String userNumber;

    private static class User {
        String name, nationality, address, gender, passport;

        public User(String name, String nationality, String passport, String address, String gender, String number) {
            this.name = name;
            this.nationality = nationality;
            this.passport = passport;
            this.address = address;
            this.gender = gender;
        }
    }

    private static class Flight {
        int flightCode;
        String flightName, source, destination;

        public Flight(int flightCode, String flightName, String source, String destination) {
            this.flightCode = flightCode;
            this.flightName = flightName;
            this.source = source;
            this.destination = destination;
        }
    }

    public bookFlight() {
        getContentPane().setBackground(Color.white);
        setLayout(null);

        // Label for heading
        JLabel heading = new JLabel("Book Flight");
        heading.setBounds(420, 20, 500, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        heading.setForeground(Color.BLUE);
        add(heading);

        // Label for aadhar
        JLabel userPassport = new JLabel("Passport Number");
        userPassport.setBounds(60, 80, 150, 25);
        userPassport.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(userPassport);

        // TextField for aadhar
        userPassportField = new JTextField();
        userPassportField.setBounds(220, 80, 150, 25);
        add(userPassportField);

        fetchUserButton = new JButton("Fetch User");
        fetchUserButton.setBackground(Color.BLACK);
        fetchUserButton.setForeground(Color.WHITE);
        fetchUserButton.setBounds(380, 80, 120, 25);
        fetchUserButton.addActionListener(this);
        add(fetchUserButton);

        // Label for name
        JLabel userName = new JLabel("Name");
        userName.setBounds(60, 130, 150, 25);
        userName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(userName);

        userNameLabel = new JLabel();
        userNameLabel.setBounds(220, 130, 150, 25);
        add(userNameLabel);

        // Label for nationality
        JLabel userNationality = new JLabel("Nationality");
        userNationality.setBounds(60, 180, 150, 25);
        userNationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(userNationality);

        userNationalityLabel = new JLabel();
        userNationalityLabel.setBounds(220, 180, 150, 25);
        add(userNationalityLabel);

        // Label for address
        JLabel userAddress = new JLabel("Address");
        userAddress.setBounds(60, 230, 150, 25);
        userAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(userAddress);

        userAddressLabel = new JLabel();
        userAddressLabel.setBounds(220, 230, 150, 25);
        add(userAddressLabel);

        // Label for gender
        JLabel userGender = new JLabel("Gender");
        userGender.setBounds(60, 280, 150, 25);
        userGender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(userGender);

        // Label for gender
        userGenderLabel = new JLabel();
        userGenderLabel.setBounds(220, 280, 150, 25);
        add(userGenderLabel);

        // Label for source address
        JLabel sourceLabel = new JLabel("Source");
        sourceLabel.setBounds(60, 330, 150, 25);
        sourceLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(sourceLabel);

        sourceChoice = new Choice(); // Dropdown | Alternative of JComboBox
        sourceChoice.setBounds(220, 330, 150, 25);
        add(sourceChoice);

        // Label for destination address
        JLabel destinationLabel = new JLabel("Destination");
        destinationLabel.setBounds(60, 380, 150, 25);
        destinationLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(destinationLabel);

        destinationChoice = new Choice(); // Dropdown | Alternative of JComboBox
        destinationChoice.setBounds(220, 380, 150, 25);
        add(destinationChoice);

        try {

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Button for fetching flights
        fetchFlightButton = new JButton("Fetch Flights");
        fetchFlightButton.setBackground(Color.BLACK);
        fetchFlightButton.setForeground(Color.white);
        fetchFlightButton.setBounds(380, 380, 120, 25);
        fetchFlightButton.addActionListener(this);
        add(fetchFlightButton);

        // Label for Flight name
        JLabel userFlightName = new JLabel("Flight Name");
        userFlightName.setBounds(60, 430, 150, 25);
        userFlightName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(userFlightName);

        flightNameLabel = new JLabel();
        flightNameLabel.setBounds(220, 430, 150, 25);
        add(flightNameLabel);

        // Label for Flight code
        JLabel userFlightCode = new JLabel("Flight Code");
        userFlightCode.setBounds(60, 480, 150, 25);
        userFlightCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(userFlightCode);

        flightCodeLabel = new JLabel();
        flightCodeLabel.setBounds(220, 480, 150, 25);
        add(flightCodeLabel);

        // Label for Travel date
        JLabel userTravelDateLabel = new JLabel("Date of Travel");
        userTravelDateLabel.setBounds(60, 530, 150, 25);
        userTravelDateLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(userTravelDateLabel);

        travelDateLabel = new JLabel();
        travelDateLabel.setBounds(220, 530, 150, 25);
        add(travelDateLabel);

        // Adding Imageicon to the frame
        ImageIcon userIcon = new ImageIcon(ClassLoader.getSystemResource("icons/details.jpg"));
        Image userIconImage = userIcon.getImage().getScaledInstance(450, 320, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(userIconImage);
        JLabel userIconLabel = new JLabel(image);
        userIconLabel.setBounds(550, 80, 500, 410);
        add(userIconLabel);

        // Initialize and add the date spinner for selecting travel date
        Date initialDate = new Date();
        SpinnerDateModel model = new SpinnerDateModel(initialDate, null, null, Calendar.DAY_OF_MONTH);
        dateSpinner = new JSpinner(model);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);
        dateSpinner.setBounds(220, 530, 150, 25);
        add(dateSpinner);

        // Button for booking flight
        bookFlightButton = new JButton("Book Flight");
        bookFlightButton.setBackground(Color.BLACK);
        bookFlightButton.setForeground(Color.white);
        bookFlightButton.setBounds(220, 580, 150, 25);
        bookFlightButton.addActionListener(this);
        add(bookFlightButton);

        loadFlightDetails();

        setSize(1100, 700);
        setLocation(300, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == fetchUserButton) {
            String passport = "\"" + userPassportField.getText().trim() + "\""; // Add quotation marks around the trimmed input

            // Creating the file object
            File file = new File("Files/" + "customerDetails" + ".txt");

            if (file.exists()) {

                try (Scanner reader = new Scanner(file)) {

                    while (reader.hasNextLine()) { // Reading data from the file line by line

                        if (reader.hasNextLine() == false) { // Exiting the loop when the file ends
                            break;
                        }

                        String nameLine = getNextLine(reader);
                        if (nameLine.contains("=") == false) { // Skip when the line does not contain "="
                            continue;
                        }

                        String userName = performSplit(nameLine, "=", 1).trim();

                        String nationalityLine = getNextLine(reader);
                        String userNationality = performSplit(nationalityLine, "=", 1).trim();

                        String PassportLine = getNextLine(reader);
                        String userPassport = performSplit(PassportLine, "=", 1).trim();

                        String sourceLine = getNextLine(reader);
                        String userAddress = performSplit(sourceLine, "=", 1).trim();

                        String numberLine = getNextLine(reader);
                        userNumber = performSplit(numberLine, "=", 1).trim();

                        String genderLine = getNextLine(reader);
                        String userGender = performSplit(genderLine, "=", 1).trim();

                        users.add(new User(userName, userNationality, userPassport, userAddress,
                                userGender, userNumber));

                    }

                } catch (FileNotFoundException e) {
                    System.err.println("File not found: " + e.getMessage());
                }
            }

            boolean found = false;
            for (User user : users) {
                if (user.passport.trim().equalsIgnoreCase(passport)) {

                    userNameLabel.setText(user.name.replace("\"", ""));
                    userNationalityLabel.setText(user.nationality.replace("\"", ""));
                    userAddressLabel.setText(user.address.replace("\"", ""));
                    userGenderLabel.setText(user.gender.replace("\"", ""));

                    found = true;
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(null, "Invalid Passport Number. Please enter the correct one.");
            }

        } else if (ae.getSource() == fetchFlightButton) {
            String src = "\"" + sourceChoice.getSelectedItem() + "\"";
            String dest = "\"" + destinationChoice.getSelectedItem() + "\"";

            File file = new File("Files/" + "flightDetails" + ".txt");

            if (file.exists()) {

                try (Scanner reader = new Scanner(file)) {

                    while (reader.hasNextLine()) { // Reading data from the file line by line

                        if (reader.hasNextLine() == false) { // Exiting the loop when the file ends
                            break;
                        }

                        String flightCodeLine = getNextLine(reader);
                        if (flightCodeLine.contains("=") == false) { // Skip when the line does not contain "="
                            continue;
                        }

                        int userFlightCode = parseToInt(performSplit(flightCodeLine, "=", 1));

                        String flightNameLine = getNextLine(reader);
                        String userFlightName = performSplit(flightNameLine, "=", 1).trim();

                        String sourceLine = getNextLine(reader);
                        String userSource = performSplit(sourceLine, "=", 1).trim();

                        String destinationLine = getNextLine(reader);
                        String userDestination = performSplit(destinationLine, "=", 1).trim();

                        flightInfo.add(new Flight(userFlightCode, userFlightName, userSource, userDestination));

                    }

                } catch (FileNotFoundException e) {
                    System.err.println("File not found: " + e.getMessage());
                }
            }

            boolean found = false;
            for (Flight flight : flightInfo) {
                if (flight.source.trim().equalsIgnoreCase(src.trim())
                        && flight.destination.trim().equalsIgnoreCase(dest.trim())) {

                    flightNameLabel.setText(flight.flightName.replace("\"", ""));
                    flightCodeLabel.setText(Integer.toString(flight.flightCode));

                    found = true;
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(null, "Invalid Source or Destination.");
            }

        } else {

            Random random = new Random();

            int pnr = random.nextInt(1000000);
            int ticketNumber = random.nextInt(10000);
            String name = userNameLabel.getText();
            String nationality = userNationalityLabel.getText();
            String address = userAddressLabel.getText();
            String passport = userPassportField.getText();
            String gender = userGenderLabel.getText();
            String flightName = flightNameLabel.getText();
            String flightCode = flightCodeLabel.getText();
            String src = sourceChoice.getSelectedItem();
            String dest = destinationChoice.getSelectedItem();

            DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
            String[] months = {
                    "Jan", "Feb", "Mar", "Apr", "May", "Jun",
                    "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
            };
            dateFormatSymbols.setShortMonths(months);
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
            dateFormatter.setDateFormatSymbols(dateFormatSymbols);
            String date = dateFormatter.format(((Date)dateSpinner.getValue()));

            try {

                if (!new File("Files").exists()) {
                    new File("Files").mkdir();
                }

                File file = new File("Files/" + "reservation" + ".txt"); // Creating the file object

                try {
                    PrintWriter writer = new PrintWriter(new FileWriter(file, true)); // Creating the PrintWriter object for writing data in
                                                                // the file

                    writer.printf(
                            "PNR = \"PNR-%d\"\nTicket Number = \"TIC-%d\"\nName = \"%s\"\nNationality = \"%s\"\nNumber = %s\nAddress = \"%s\"\nPassport = \"%s\"\nGender = \"%s\"\nFlight Name = \"%s\"\nFlight Code = %s\nSource = \"%s\"\nDestination = \"%s\"\nDate = \"%s\"\n\n",
                            pnr, ticketNumber, name, nationality, userNumber, address, passport, gender, flightName,
                            flightCode,
                            src, dest, date);

                    // If any problem occurs while reading the file, fix the "" issue as it is not
                    // same for all informations. Check (reservation.txt)

                    writer.close(); // Closing the PrintWriter

                } catch (Exception e) {
                    e.printStackTrace();
                }

                JOptionPane.showMessageDialog(null, "Flight Booked Successfully!\nPNR: " + "PNR-" + pnr);
                setVisible(false);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void loadFlightDetails() {
        File file = new File("Files/" + "flightDetails" + ".txt");
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                ArrayList<String> sources = new ArrayList<>();
                ArrayList<String> destinations = new ArrayList<>();
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine().trim();
                    if (line.startsWith("Source")) {
                        String source = line.split("=")[1].trim().replace("\"", ""); // Removing quotes and trimming
                        if (!sources.contains(source)) {
                            sources.add(source);
                        }
                    } else if (line.startsWith("Destination")) {
                        String destination = line.split("=")[1].trim().replace("\"", "");
                        if (!destinations.contains(destination)) {
                            destinations.add(destination);
                        }
                    }
                }
                sources.forEach(sourceChoice::add);
                destinations.forEach(destinationChoice::add);
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + e.getMessage());
            }
        } else {
            System.err.println("Flight details file does not exist.");
        }
    }

    private String getNextLine(Scanner reader) {
        return reader.nextLine();
    }

    private String performSplit(String line, String delimiter, int index) {
        String[] parts = line.split(delimiter);
        return parts[index];
    }

    private int parseToInt(String number) {
        try {
            return Integer.parseInt(number.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static void main(String[] args) {
        new bookFlight();
    }
}
