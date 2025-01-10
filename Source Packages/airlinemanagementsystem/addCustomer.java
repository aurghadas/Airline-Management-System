import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class addCustomer extends JFrame implements ActionListener {

    JTextField userNameField, userNationalityField, userPassportField, userAddressField, userNumberField;
    JRadioButton male, female;
    ArrayList <Customer> customerList = new ArrayList<>(); 

    private static class Customer {
        String name, nationality,address, number, gender, passport;

        public Customer(String name, String nationality, String passport, String address, String number, String gender) {
            this.name = name;
            this.nationality = nationality;
            this.passport = passport;
            this.address = address;
            this.number = number;
            this.gender = gender;
        }

        public String getName() {
            return name;
        }
    
        public String getNationality() {
            return nationality;
        }
    
        public String getPassport() {
            return passport;
        }
    
        public String getAddress() {
            return address;
        }
    
        public String getNumber() {
            return number;
        }
    
        public String getGender() {
            return gender;
        }
    }


    public addCustomer() {
        getContentPane().setBackground(Color.white);
        setLayout(null);

        //Label for heading
        JLabel heading = new JLabel("Add Customer Details");
        heading.setBounds(290, 20, 500, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        heading.setForeground(Color.BLUE);
        add(heading);

        //Label for name
        JLabel userName = new JLabel("Name");
        userName.setBounds(60, 80, 150, 25);
        userName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(userName);

        //TextField for name
        userNameField = new JTextField();
        userNameField.setBounds(220, 80, 150, 25);
        add(userNameField);

        //Label for nationality
        JLabel userNationality = new JLabel("Nationality");
        userNationality.setBounds(60, 130, 150, 25);
        userNationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(userNationality);

        //TextField for nationality
        userNationalityField = new JTextField();
        userNationalityField.setBounds(220, 130, 150, 25);
        add(userNationalityField);

        //Label for aadhar
        JLabel userPassport = new JLabel("Passport Number");
        userPassport.setBounds(60, 180, 150, 25);
        userPassport.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(userPassport);

        //TextField for aadhar
        userPassportField = new JTextField();
        userPassportField.setBounds(220, 180, 150, 25);
        add(userPassportField);

        //Label for address
        JLabel userAddress = new JLabel("Address");
        userAddress.setBounds(60, 230, 150, 25);
        userAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(userAddress);

        //TextField for address
        userAddressField = new JTextField();
        userAddressField.setBounds(220, 230, 150, 25);
        add(userAddressField);

        //Label for gender
        JLabel userGender = new JLabel("Gender");
        userGender.setBounds(60, 280, 150, 25);
        userGender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(userGender);

        //Radio Buttons containing Male and Female
        ButtonGroup gendeGroup = new ButtonGroup();

        //Radio Button for Male
        male = new JRadioButton("Male");
        male.setBounds(220, 280, 70, 25);
        male.setFont(new Font("Tahoma", Font.PLAIN, 16));
        male.setBackground(Color.white);
        add(male);

        //Radio Button for Female
        female = new JRadioButton("Female");
        female.setBounds(300, 280, 80, 25);
        female.setFont(new Font("Tahoma", Font.PLAIN, 16));
        female.setBackground(Color.white);
        add(female);

        //Adding Radio Buttons to ButtonGroup
        gendeGroup.add(male);
        gendeGroup.add(female);

        //Label for number
        JLabel userNumber = new JLabel("Phone Number");
        userNumber.setBounds(60, 330, 150, 25);
        userNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(userNumber);

        //TextField for number
        userNumberField = new JTextField();
        userNumberField.setBounds(220, 330, 150, 25);
        add(userNumberField);

        //Button for save
        JButton saveButton = new JButton("SAVE");
        saveButton.setBounds(220, 380, 150, 30);
        saveButton.setBackground(Color.BLACK);
        saveButton.setForeground(Color.white);
        saveButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        saveButton.addActionListener(this);
        add(saveButton);

        //Adding Imageicon to the frame
        ImageIcon userIcon = new ImageIcon(ClassLoader.getSystemResource("icons/emp.png"));
        JLabel userIconLabel = new JLabel(userIcon);
        userIconLabel.setBounds(500, 80, 280, 400);
        add(userIconLabel);

        setSize(900,600);
        setLocation(400,150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        //Storing data in variables
        String name = userNameField.getText();
        String nationality = userNationalityField.getText();
        String address = userAddressField.getText();
        String number = userNumberField.getText();
        String gender;
        String passport = userPassportField.getText();

        /*try {
            aadhar = userPassportField.getText().trim();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid Aadhar Number format. Please enter a valid number.");
            return;
        }*/
        

        if(male.isSelected()){
            gender = "Male";
        }else{
            gender = "Female";
        }

        customerList.add(new Customer(name, nationality, passport, address, number, gender));

       try{

        if (!new File("Files").exists()) {
            new File("Files").mkdir();
        }

        File file = new File("Files/" + "customerDetails" + ".txt"); //Creating the file object

        try {
            PrintWriter writer = new PrintWriter(new FileWriter(file, true)); // Creating the PrintWriter object for writing data in the file
        
            for (Customer customer : customerList) {
                writer.printf(
                    "Name = \"%s\"\nNationality = \"%s\"\nPassport = \"%s\"\nAddress = \"%s\"\nNumber = \"%s\"\nGender = \"%s\"\n\n",
                    customer.getName(), customer.getNationality(), customer.getPassport(),
                    customer.getAddress(), customer.getNumber(), customer.getGender());
            }
            writer.close(); // Closing the PrintWriter
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        

           JOptionPane.showMessageDialog(null, "Customer Added Successfully");
           setVisible(false);

       }catch(Exception e){
           e.printStackTrace();
       }
    }
    
    public static void main(String[] args) {
        new addCustomer();
    }
}
