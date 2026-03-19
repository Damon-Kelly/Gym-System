// Student Name : 		Damon Kelly
// Student Id Number : 	C00307057
// Date :				19/03/2026
// Purpose : 			The gui for member dashboard

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManageMembership extends JFrame
{
    // declare the elements that will be used
    private JButton newMembership;
    private JButton viewMembership;
    private JButton cancelMembership;
    private JButton renewMembership;
    private JButton backToDashboard;

    private String email;
    private String password;

    public ManageMembership(Member member)
    {
        // default setup
        super("Manage Membership");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        // this centers the page on the screen
        setLocationRelativeTo(null);
        
        // set the parameters for the page layout
        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        // set a border around the page
        panel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

        // welcome the member
        panel.add(new JLabel("Welcome to your manage membership page, " + member.getName() + "!", JLabel.CENTER));

        panel.add(newMembership = new JButton("Start a new Membership"));
        panel.add(viewMembership = new JButton("View your current Membership"));
        panel.add(renewMembership = new JButton("Renew your current Membership"));
        panel.add(cancelMembership = new JButton("Cancel your current Membership"));
        panel.add(backToDashboard = new JButton("Return to Dashboard"));

        MembershipHandler membershipHandler = new MembershipHandler();

        newMembership.addActionListener(membershipHandler);
        viewMembership.addActionListener(membershipHandler);
        renewMembership.addActionListener(membershipHandler);
        cancelMembership.addActionListener(membershipHandler);
        backToDashboard.addActionListener(membershipHandler);

        email = member.getEmail();
        password = member.getPassword();

        add(panel);
        setVisible(true);
    }

    // private inner class for event handling
    private class MembershipHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            // if user chooses logout close current page and reopen the login
            if (event.getSource() == backToDashboard)
                {
                    try
                        {
                            Validator.validateEmail(email);
                            Validator.validatePassword(password);
                            Member member = Database.authenticateMember(email, password);
                            dispose(); // Close the dashboard window
                            MemberDashboard memberDash = new MemberDashboard(member); // Open the login window again
                            memberDash.setVisible(true);
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
        
            else if (event.getSource() == newMembership)
                {
                    JOptionPane.showMessageDialog(null, "New Membership selected.");
                }
            else if (event.getSource() == viewMembership)
                {
                    JOptionPane.showMessageDialog(null, "View Membership selected.");
                }
            else if (event.getSource() == renewMembership)
                {
                    JOptionPane.showMessageDialog(null, "Renew Membership selected.");
                }
            else if (event.getSource() == cancelMembership)
                {
                    JOptionPane.showMessageDialog(null, "Cancel Membership selected.");
                }
        }
    }
}