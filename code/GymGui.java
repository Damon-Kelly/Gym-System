// Student Name : 		Damon Kelly
// Student Id Number : 	C00307057
// Date :				12/03/2026
// Purpose : 			The gui for the gym system

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GymGui extends JFrame
{
    private JPanel memberPanel;
    private JPanel trainerPanel;
    private JTextField memberEmailField;
    private JPasswordField memberPasswordField;
    private JButton memberSubmitButton;
    private JButton memberClearButton;
    private JButton memberSignUpButton;
    
    private JTextField trainerEmailField;
    private JPasswordField trainerPasswordField;
    private JButton trainerSubmitButton;
    private JButton trainerClearButton;

    public GymGui()
    {
        super("Gym Login System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 450);
        setLocationRelativeTo(null);
        
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Member Login Tab
        JPanel memberFormPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        memberFormPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        memberEmailField = new JTextField();
        memberPasswordField = new JPasswordField();
        memberSubmitButton = new JButton("Submit");
        memberClearButton = new JButton("Clear");

        memberFormPanel.add(new JLabel("Email:"));
        memberFormPanel.add(memberEmailField);
        memberFormPanel.add(new JLabel("Password:"));
        memberFormPanel.add(memberPasswordField);
        memberFormPanel.add(memberSubmitButton);  
        memberFormPanel.add(memberClearButton);

        // Create member panel with form and sign-up button
        memberPanel = new JPanel(new BorderLayout(10, 10));
        memberPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        memberPanel.add(memberFormPanel, BorderLayout.CENTER);
        
        memberSignUpButton = new JButton("Don't have an account? Sign Up");
        memberPanel.add(memberSignUpButton, BorderLayout.SOUTH);

        TextFieldHandler memberHandler = new TextFieldHandler();
        memberEmailField.addActionListener(memberHandler);
        memberPasswordField.addActionListener(memberHandler);
        memberSubmitButton.addActionListener(memberHandler);
        memberClearButton.addActionListener(memberHandler);
        memberSignUpButton.addActionListener(memberHandler);

        // Trainer Login Tab
        trainerPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        trainerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        trainerEmailField = new JTextField();
        trainerPasswordField = new JPasswordField();
        trainerSubmitButton = new JButton("Submit");
        trainerClearButton = new JButton("Clear");

        trainerPanel.add(new JLabel("Email:"));
        trainerPanel.add(trainerEmailField);
        trainerPanel.add(new JLabel("Password:"));
        trainerPanel.add(trainerPasswordField);
        trainerPanel.add(trainerSubmitButton);
        trainerPanel.add(trainerClearButton);

        TextFieldHandler trainerHandler = new TextFieldHandler();
        trainerEmailField.addActionListener(trainerHandler);
        trainerPasswordField.addActionListener(trainerHandler);
        trainerSubmitButton.addActionListener(trainerHandler);
        trainerClearButton.addActionListener(trainerHandler);

        // Add tabs to tabbed pane
        tabbedPane.addTab("Member Login", memberPanel);
        tabbedPane.addTab("Trainer Login", trainerPanel);

        add(tabbedPane, BorderLayout.CENTER);
    }

    // private inner class for event handling
    private class TextFieldHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if (event.getSource() == memberSubmitButton)
                {
                    try
                        {
                            String email = memberEmailField.getText();
                            Validator.validateEmail(email);
                            String password = new String(memberPasswordField.getPassword());
                            Validator.validateNotEmpty(password, "Password");
                            Member member = Database.authenticateMember(email, password);
                            JOptionPane.showMessageDialog(null, "Login successful! Welcome " + member.getName());
                            dispose(); // Close the login window
                            new MemberDashboard(member.getMemberID(), member.getName());
                        }
                    catch (ValidationException ve)
                        {
                            JOptionPane.showMessageDialog(null, ve.getMessage(), "Input Validation Error", JOptionPane.ERROR_MESSAGE);
                            return; // Stop further processing if validation fails
                        }
                    catch (Exception e)
                        {
                            JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            return; // Stop further processing if an unexpected error occurs
                        }
                }
            
            else if (event.getSource() == memberClearButton)
                {
                    memberEmailField.setText("");
                    memberPasswordField.setText("");
                }
            
            else if (event.getSource() == memberSignUpButton)
                {
                    new SignUpDialog();
                }
            
            else if (event.getSource() == trainerSubmitButton)
                {
                    try
                        {
                            String email = trainerEmailField.getText();
                            Validator.validateEmail(email);
                            String password = new String(trainerPasswordField.getPassword());
                            Validator.validateNotEmpty(password, "Password");
                            Trainer trainer = Database.authenticateTrainer(email, password);
                            JOptionPane.showMessageDialog(null, "Login successful! Welcome " + trainer.getName());
                            dispose(); // Close the login window
                            new TrainerDashboard(trainer.getTrainerID());
                        }
                    catch (ValidationException ve)
                        {
                            JOptionPane.showMessageDialog(null, ve.getMessage(), "Input Validation Error", JOptionPane.ERROR_MESSAGE);
                            return; // Stop further processing if validation fails
                        }
                    catch (Exception e)
                        {
                            JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            return; // Stop further processing if an unexpected error occurs
                        }
                }
            
            else if (event.getSource() == trainerClearButton)
                {
                    trainerEmailField.setText("");
                    trainerPasswordField.setText("");
                }
        }
    } // end private inner class

    // Private inner class for sign-up dialog
    private class SignUpDialog extends JDialog
    {
        private JTextField nameField;
        private JTextField emailField;
        private JTextField phoneField;
        private JTextField dobField;
        private JPasswordField passwordField;
        private JPasswordField confirmPasswordField;
        private JButton registerButton;
        private JButton cancelButton;

        public SignUpDialog()
        {
            super(GymGui.this, "Member Sign Up", true);
            setSize(400, 350);
            setLocationRelativeTo(GymGui.this);
            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            JPanel mainPanel = new JPanel(new GridLayout(7, 2, 10, 10));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            // Create fields
            nameField = new JTextField();
            emailField = new JTextField();
            phoneField = new JTextField();
            dobField = new JTextField();
            passwordField = new JPasswordField();
            confirmPasswordField = new JPasswordField();
            registerButton = new JButton("Register");
            cancelButton = new JButton("Cancel");

            // Add components
            mainPanel.add(new JLabel("Name:"));
            mainPanel.add(nameField);
            mainPanel.add(new JLabel("Email:"));
            mainPanel.add(emailField);
            mainPanel.add(new JLabel("Phone Number:"));
            mainPanel.add(phoneField);
            mainPanel.add(new JLabel("Date of Birth (YYYY-MM-DD):"));
            mainPanel.add(dobField);
            mainPanel.add(new JLabel("Password:"));
            mainPanel.add(passwordField);
            mainPanel.add(new JLabel("Confirm Password:"));
            mainPanel.add(confirmPasswordField);
            mainPanel.add(registerButton);
            mainPanel.add(cancelButton);

            add(mainPanel);

            SignUpHandler handler = new SignUpHandler();
            registerButton.addActionListener(handler);
            cancelButton.addActionListener(handler);

            setVisible(true);
        }

        // Inner class for sign-up event handling
        private class SignUpHandler implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                if (event.getSource() == registerButton)
                    {
                        try
                            {
                                // Get all input values
                                String name = nameField.getText();
                                String email = emailField.getText();
                                String phone = phoneField.getText();
                                String dob = dobField.getText();
                                String password = new String(passwordField.getPassword());
                                String confirmPassword = new String(confirmPasswordField.getPassword());

                                // Validate inputs
                                Validator.validateNotEmpty(name, "Name");
                                Validator.validateEmail(email);
                                Validator.validateNotEmpty(phone, "Phone Number");
                                Validator.validatePhoneNumber(phone);
                                Validator.validateNotEmpty(dob, "Date of Birth");
                                Validator.validateNotEmpty(password, "Password");

                                // Check if passwords match
                                if (!password.equals(confirmPassword))
                                    {
                                        JOptionPane.showMessageDialog(SignUpDialog.this, 
                                            "Passwords do not match.", 
                                            "Password Mismatch", 
                                            JOptionPane.ERROR_MESSAGE);
                                        return;
                                    }

                                // Check if email already exists
                                if (Database.emailExists(email))
                                    {
                                        JOptionPane.showMessageDialog(SignUpDialog.this, 
                                            "This email is already registered. Please use a different email.", 
                                            "Email Already Exists", 
                                            JOptionPane.ERROR_MESSAGE);
                                        return;
                                    }

                                // Register the member
                                Member newMember = Database.registerMember(name, email, phone, dob, password);

                                JOptionPane.showMessageDialog(SignUpDialog.this, 
                                    "Account created successfully! Welcome " + newMember.getName(), 
                                    "Sign Up Successful", 
                                    JOptionPane.INFORMATION_MESSAGE);

                                // Close the sign-up dialog and login window
                                dispose();
                                GymGui.this.dispose();

                                // Auto-login the new member
                                new MemberDashboard(newMember.getMemberID(), newMember.getName());
                            }
                        catch (ValidationException ve)
                            {
                                JOptionPane.showMessageDialog(SignUpDialog.this, 
                                    ve.getMessage(), 
                                    "Input Validation Error", 
                                    JOptionPane.ERROR_MESSAGE);
                            }
                        catch (Exception e)
                            {
                                JOptionPane.showMessageDialog(SignUpDialog.this, 
                                    "An error occurred: " + e.getMessage(), 
                                    "Error", 
                                    JOptionPane.ERROR_MESSAGE);
                            }
                    }
                else if (event.getSource() == cancelButton)
                    {
                        dispose();
                    }
            }
        }
    }

    public static void main(String[] args)
    {
        GymGui application = new GymGui();
        application.setVisible(true);
    }
}