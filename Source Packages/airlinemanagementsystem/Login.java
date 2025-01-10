import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Login extends JFrame implements ActionListener {

        String username, password;
        JButton submitButton, resetButton, exitButton;
        JTextField usernameTextField, passwordTextField;

    public Login() {

        getContentPane().setBackground(Color.white);
        // getContentPane().setBackground(new Color(2, 88, 55)); //If I want to change the background color using RGB of the whole screen
        setLayout(null);

        //Label for username
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(40, 20, 100, 30);
        add(usernameLabel);

        //TextField for username input
        usernameTextField = new JTextField();
        usernameTextField.setBounds(150, 20, 150, 30);
        add(usernameTextField);

        //Label for password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(40, 70, 100, 30);
        add(passwordLabel);

        //TextField for password input
        passwordTextField = new JPasswordField();
        passwordTextField.setBounds(150, 70, 150, 30);
        add(passwordTextField);

        //Submit Button setup
        submitButton = new JButton("Submit");
        submitButton.setBounds(80, 120, 100, 30);
        submitButton.addActionListener(this);
        add(submitButton);

        //Reset Button setup
        resetButton = new JButton("Reset");
        resetButton.setBounds(220, 120, 100, 30);
        resetButton.addActionListener(this);
        add(resetButton);

        //Exit Button setup
        exitButton = new JButton("Exit");
        exitButton.setBounds(150, 170, 100, 30);
        exitButton.addActionListener(this);
        add(exitButton);

        //Main Frame setup
        setSize(400, 250);
        setLocation(650, 350);
        setVisible(true);
    }

    //Action Listener to the buttons to make those work    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submitButton){
            username = usernameTextField.getText();
            password = passwordTextField.getText();

            String filePath = "Files/login.txt";

            try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){

                String desiredUsername = reader.readLine();
                String desiredPassword = reader.readLine();

                if(username.equals(desiredUsername) && password.equals(desiredPassword)){
                    setVisible(false);
                    new Home();
                }else{
                    JOptionPane.showMessageDialog(null, "Invalid username or password.");
                }
    
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if (ae.getSource() == resetButton) {
            usernameTextField.setText("");
            passwordTextField.setText("");
        }else if (ae.getSource() == exitButton) {
            setVisible(false);
            System.exit(0);
        }
    }

    public static void main(String[] args) {

        //Create a directory and a file if they don't exist
        String directorypath = "Files/";
        String filepath = "Files/login.txt";

        File directory = new File(directorypath);
        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(filepath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Write username and password to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                            writer.write("aurgha");
                            writer.newLine();
                            writer.write("aurgha123");
                        } catch (IOException e) {
                            System.out.println("Error writing to the file: " + e.getMessage());
                        }
                        
        new Login();
    }
}