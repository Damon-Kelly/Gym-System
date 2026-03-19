// Student Name : 		Damon Kelly
// Student Id Number : 	C00307057
// Date :				12/03/2026
// Purpose : 			The gui for the gym system

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MemberDashboard extends JFrame
{
    private JButton myMembershipButton;
    private JButton bookClassButton;
    private JButton bookTrainerButton;
    private JButton logoutButton;

    public MemberDashboard(Member member)
    {
        super("Member Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

        panel.add(new JLabel("Welcome, " + member.getName() + "!", JLabel.CENTER));

        panel.add(myMembershipButton = new JButton("My Membership"));
        panel.add(bookClassButton = new JButton("Book a Class"));
        panel.add(bookTrainerButton = new JButton("Book a Trainer"));

        MemberHandler memberHandler = new MemberHandler();
        logoutButton = new JButton("Logout");
        myMembershipButton.addActionListener(memberHandler);
        bookClassButton.addActionListener(memberHandler);
        bookTrainerButton.addActionListener(memberHandler);
        logoutButton.addActionListener(memberHandler);
        panel.add(logoutButton);

        add(panel);
        setVisible(true);
    }

    // private inner class for event handling
    private class MemberHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if (event.getSource() == logoutButton)
                {
                    dispose(); // Close the dashboard window
                    GymGui gymGui = new GymGui(); // Open the login window again
                    gymGui.setVisible(true);
                }
        
            else if (event.getSource() == myMembershipButton)
                {
                    JOptionPane.showMessageDialog(null, "My Membership selected.");
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