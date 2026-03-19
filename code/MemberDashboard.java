// Student Name : 		Damon Kelly
// Student Id Number : 	C00307057
// Date :				12/03/2026
// Purpose : 			The gui for member dashboard

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MemberDashboard extends JFrame
{
    // declare the elements that will be used
    private JButton myMembershipButton;
    private JButton bookClassButton;
    private JButton bookTrainerButton;
    private JButton logoutButton;

    private String email;
    private String password;

    public MemberDashboard(Member member)
    {
        // default setup
        super("Member Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        // this centers the page on the screen
        setLocationRelativeTo(null);
        
        // set the parameters for the page layout
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        // set a border around the page
        panel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

        // welcome the member
        panel.add(new JLabel("Welcome, " + member.getName() + "!", JLabel.CENTER));

        // add the buttons with text
        panel.add(myMembershipButton = new JButton("My Membership"));
        panel.add(bookClassButton = new JButton("Book a Class"));
        panel.add(bookTrainerButton = new JButton("Book a Trainer"));

        // use the event handling class
        MemberHandler memberHandler = new MemberHandler();
        logoutButton = new JButton("Logout");
        myMembershipButton.addActionListener(memberHandler);
        bookClassButton.addActionListener(memberHandler);
        bookTrainerButton.addActionListener(memberHandler);
        logoutButton.addActionListener(memberHandler);
        panel.add(logoutButton);

        email = member.getEmail();
        password = member.getPassword();

        add(panel);
        setVisible(true);
    }

    // private inner class for event handling
    private class MemberHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            // if user chooses logout close current page and reopen the login
            if (event.getSource() == logoutButton)
                {
                    dispose(); // Close the dashboard window
                    GymGui gymGui = new GymGui(); // Open the login window again
                    gymGui.setVisible(true);
                }
        
            else if (event.getSource() == myMembershipButton)
                {
                    try
                        {
                            Validator.validateEmail(email);
                            Validator.validatePassword(password);
                            Member member = Database.authenticateMember(email, password);
                            dispose(); // Close the dashboard window
                            ManageMembership manageMembership = new ManageMembership(member); // Open the login window again
                            manageMembership.setVisible(true);
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
            else if (event.getSource() == bookClassButton)
                {
                    JOptionPane.showMessageDialog(null, "Book a Class selected.");
                }
            else if (event.getSource() == bookTrainerButton)
                {
                    JOptionPane.showMessageDialog(null, "Book a Trainer selected.");
                }
        }
    }
}