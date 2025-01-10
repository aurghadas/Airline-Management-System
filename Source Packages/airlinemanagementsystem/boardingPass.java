import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class boardingPass extends JFrame implements ActionListener {

    JTextField userPNRField;
    JLabel userNameLabel, userNationalityLabel, userTravelSourceLabel, userTravelDestLabel,
            flightNameLabel, flightCodeLabel, travelDateLabel;
    JButton enterButton;

    public boardingPass() {
        getContentPane().setBackground(Color.white);
        setLayout(null);

        // Label for heading
        JLabel heading = new JLabel("Biman BANGLADESH");
        heading.setBounds(350, 10, 450, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        heading.setForeground(Color.RED);
        add(heading);

        // Label for subheading
        JLabel subHeading = new JLabel("Boarding Pass");
        subHeading.setBounds(420, 50, 300, 30);
        subHeading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(subHeading);

        // Label for PNR
        JLabel userPNR = new JLabel("PNR Details");
        userPNR.setBounds(60, 100, 150, 25);
        userPNR.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(userPNR);

        // TextField for PNR
        userPNRField = new JTextField();
        userPNRField.setBounds(220, 100, 150, 25);
        add(userPNRField);

        // Enter Button
        enterButton = new JButton("Enter");
        enterButton.setBackground(Color.BLACK);
        enterButton.setForeground(Color.WHITE);
        enterButton.setBounds(380, 100, 120, 25);
        enterButton.addActionListener(this);
        add(enterButton);

        // Label for name
        JLabel userName = new JLabel("NAME");
        userName.setBounds(60, 140, 150, 25);
        userName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(userName);

        userNameLabel = new JLabel();
        userNameLabel.setBounds(220, 140, 150, 25);
        add(userNameLabel);

        // Label for nationality
        JLabel userNationality = new JLabel("NATIONALITY");
        userNationality.setBounds(60, 180, 150, 25);
        userNationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(userNationality);

        userNationalityLabel = new JLabel();
        userNationalityLabel.setBounds(220, 180, 150, 25);
        add(userNationalityLabel);

        // Label for Source
        JLabel userTravelSource = new JLabel("SOURCE");
        userTravelSource.setBounds(60, 220, 150, 25);
        userTravelSource.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(userTravelSource);

        userTravelSourceLabel = new JLabel();
        userTravelSourceLabel.setBounds(220, 220, 150, 25);
        add(userTravelSourceLabel);

        // Label for Destination
        JLabel userTravelDestination = new JLabel("DESTINATION");
        userTravelDestination.setBounds(380, 220, 150, 25);
        userTravelDestination.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(userTravelDestination);

        userTravelDestLabel = new JLabel();
        userTravelDestLabel.setBounds(550, 220, 150, 25);
        add(userTravelDestLabel);

        // Label for Flight name
        JLabel userFlightName = new JLabel("Flight Name");
        userFlightName.setBounds(60, 260, 150, 25);
        userFlightName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(userFlightName);

        flightNameLabel = new JLabel();
        flightNameLabel.setBounds(220, 260, 150, 25);
        add(flightNameLabel);

        // Label for Flight code
        JLabel userFlightCode = new JLabel("Flight Code");
        userFlightCode.setBounds(380, 260, 150, 25);
        userFlightCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(userFlightCode);

        flightCodeLabel = new JLabel();
        flightCodeLabel.setBounds(550, 260, 150, 25);
        add(flightCodeLabel);

        // Label for Travel date
        JLabel userTravelDateLabel = new JLabel("Date");
        userTravelDateLabel.setBounds(60, 300, 150, 25);
        userTravelDateLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(userTravelDateLabel);

        travelDateLabel = new JLabel();
        travelDateLabel.setBounds(220, 300, 150, 25);
        add(travelDateLabel);

        // Adding Image to the frame
        ImageIcon userIcon = new ImageIcon(ClassLoader.getSystemResource("icons/bimanBangladesh.png"));
        Image userIconImage = userIcon.getImage().getScaledInstance(300, 165, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(userIconImage);
        JLabel userIconLabel = new JLabel(image);
        userIconLabel.setBounds(600, 40, 300, 300);
        add(userIconLabel);

        setSize(1000, 450);
        setLocation(350, 250);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

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

                    String nationalityLine = getNextLine(reader);
                    String nationality = performSplit(nationalityLine, "=", 1);

                    getNextLine(reader);
                    getNextLine(reader);
                    getNextLine(reader);
                    getNextLine(reader);

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

                    if (pnr.trim().equals(enteredPNR)) {

                        userNameLabel.setText(name.replace("\"", ""));
                        userNationalityLabel.setText(nationality.replace("\"", ""));
                        userTravelSourceLabel.setText(source.replace("\"", ""));
                        userTravelDestLabel.setText(destination.replace("\"", ""));
                        flightNameLabel.setText(flightName.replace("\"", ""));
                        flightCodeLabel.setText(String.valueOf(flightCode).replace("\"", "")); // Assuming flightCode is an int, it won't have quotes, but shown here for consistency if needed.
                        travelDateLabel.setText(date.replace("\"", ""));

                        break;
                    }
                 
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
        new boardingPass();
    }
}
