import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener {

        JButton submitButton, resetButton, exitButton;
        JTextField usernameTextField, passwordTextField;

    public Home() {

        setLayout(null);

        //Adding Image to the frame
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/front.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 10, 1920, 1080);
        add(image);

        //Adding Heading to the frame
        JLabel heading = new JLabel("Biman Bangladesh Welcomes You");
        heading.setBounds(530, 20, 1000, 40);
        heading.setForeground(Color.BLUE);
        heading.setFont(new Font("Tahoma", Font.BOLD, 40));
        image.add(heading);

        //Adding Menu Bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //Adding Menu
        JMenu details = new JMenu("Details");
        menuBar.add(details);

        //Adding Menu Items
        JMenuItem flightDetails = new JMenuItem("Flight Details");
        flightDetails.addActionListener(this);
        details.add(flightDetails);

        JMenuItem customerDetails = new JMenuItem("Add Customer Details");
        customerDetails.addActionListener(this);
        details.add(customerDetails);

        JMenuItem bookDetails = new JMenuItem("Book Flight");
        bookDetails.addActionListener(this);
        details.add(bookDetails);

        JMenuItem journeyDetails = new JMenuItem("Journey Details");        
        journeyDetails.addActionListener(this);
        details.add(journeyDetails);

        JMenuItem ticketCancellation = new JMenuItem("Cancel Ticket");
        ticketCancellation.addActionListener(this);
        details.add(ticketCancellation);

        //Adding second Menu
        JMenu ticket = new JMenu("Ticket");
        menuBar.add(ticket);

        //Adding Menu Items To the second menu
        JMenuItem boardingPass = new JMenuItem("Boarding Pass");
        boardingPass.addActionListener(this);
        ticket.add(boardingPass);


        //Main Frame setup
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocation(650, 350);
        setVisible(true);
    }

    //Action Listener to the buttons to make those work    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String name = ae.getActionCommand();

        if(name.equals("Add Customer Details")){
            new addCustomer();
        }else if(name.equals("Flight Details")){
            new flightInfo();
        }else if(name.equals("Book Flight")){
            new bookFlight();
        }else if(name.equals("Journey Details")){
            new journeyDetails();
        }else if(name.equals("Cancel Ticket")){
            new cancelFlight();
        }else if(name.equals("Boarding Pass")){
            new boardingPass();
        }
        
    }

    public static void main(String[] args) {
        new Home();
    }
}