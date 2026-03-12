// Student Name : 		Damon Kelly
// Student Id Number : 	C00307057
// Date :				12/03/2026
// Purpose : 			The gui for the gym system

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GymGui extends JFrame
{
    private JPanel loginPanel;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton submitButton;
    private JButton clearButton;

    public GymGui()
    {
        super("Gym System Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        loginPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        emailField = new JTextField();
        passwordField = new JPasswordField();
        submitButton = new JButton("Submit");
        clearButton = new JButton("Clear");

        loginPanel.add(new JLabel("Email:"));
        loginPanel.add(emailField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);
        loginPanel.add(submitButton);  
        loginPanel.add(clearButton);

        TextFieldHandler handler = new TextFieldHandler();
        emailField.addActionListener(handler);
        passwordField.addActionListener(handler);
        submitButton.addActionListener(handler);
        clearButton.addActionListener(handler);

        add(loginPanel, BorderLayout.CENTER);
    }

    // private inner class for event handling
    private class TextFieldHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            String string = "";
            if (event.getSource() == emailField)
                    {
                        string = String.format( "emailField : %s", event .getActionCommand());
                    }
            
            else if (event.getSource() == passwordField)
                {
                    string = String.format( "passwordField : %s", event .getActionCommand());
                }
            
            else if (event.getSource() == submitButton)
                {
                    String email = emailField.getText();
                    String password = new String(passwordField.getPassword());
                    
                    try {
                        // Call database validation method
                        Member member = Database.authenticateMember(email, password);
                        if (member != null) 
                            {
                                JOptionPane.showMessageDialog(null, "Login successful! Welcome " + member.getName());
                            } 
                        else 
                            {
                                JOptionPane.showMessageDialog(null, "Invalid email or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                            }
                        } 
                    catch (Exception e) 
                        {
                            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                }
            
            else if (event.getSource() == clearButton)
                {
                    emailField.setText("");
                    passwordField.setText("");
                }
        }
    } // end private inner class
    public static void main(String[] args)
    {
        GymGui application = new GymGui();
        application.setVisible(true);
    }
}