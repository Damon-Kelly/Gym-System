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
    
    private JTextField trainerEmailField;
    private JPasswordField trainerPasswordField;
    private JButton trainerSubmitButton;
    private JButton trainerClearButton;

    public GymGui()
    {
        super("Gym Login System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);
        
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Member Login Tab
        memberPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        memberPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        memberEmailField = new JTextField();
        memberPasswordField = new JPasswordField();
        memberSubmitButton = new JButton("Submit");
        memberClearButton = new JButton("Clear");

        memberPanel.add(new JLabel("Email:"));
        memberPanel.add(memberEmailField);
        memberPanel.add(new JLabel("Password:"));
        memberPanel.add(memberPasswordField);
        memberPanel.add(memberSubmitButton);  
        memberPanel.add(memberClearButton);

        TextFieldHandler memberHandler = new TextFieldHandler();
        memberEmailField.addActionListener(memberHandler);
        memberPasswordField.addActionListener(memberHandler);
        memberSubmitButton.addActionListener(memberHandler);
        memberClearButton.addActionListener(memberHandler);

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
    public static void main(String[] args)
    {
        GymGui application = new GymGui();
        application.setVisible(true);
    }
}