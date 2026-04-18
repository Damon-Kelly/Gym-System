import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * ViewMembership.java - Displays the active membership details for a member.
 */
public class ViewMembership extends JFrame
{
    private int memberID;
    private String memberName;
    private JButton backButton;

    public ViewMembership(int memberID, String memberName)
    {
        this.memberID = memberID;
        this.memberName = memberName;

        setTitle("My Membership");
        setSize(420, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // --- Fetch membership data ---
        // Returns: [PlanType, StartDate, EndDate, IsActive]
        // or null if no active membership found
        String[] membership = QueryHelpers.getMembershipByMemberID(memberID);

        // --- Main panel ---
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        if (membership == null)
        {
            // No active membership found
            JPanel noDataPanel = new JPanel(new BorderLayout());
            noDataPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

            JLabel noDataLabel = new JLabel("No active membership found.", SwingConstants.CENTER);
            backButton = new JButton("Back");

            noDataPanel.add(noDataLabel, BorderLayout.CENTER);
            noDataPanel.add(backButton, BorderLayout.SOUTH);
            add(noDataPanel);
        }
        else
        {
            // Display membership fields
            panel.add(new JLabel("Plan Type:"));
            panel.add(new JLabel(membership[0]));

            panel.add(new JLabel("Start Date:"));
            panel.add(new JLabel(membership[1]));

            panel.add(new JLabel("End Date:"));
            panel.add(new JLabel(membership[2]));

            panel.add(new JLabel("Status:"));
            String statusText = membership[3].equals("1") ? "Active" : "Inactive";
            JLabel statusLabel = new JLabel(statusText);
            statusLabel.setForeground(membership[3].equals("1") ? Color.GREEN.darker() : Color.RED);
            panel.add(statusLabel);

            // Spacer row
            panel.add(new JLabel(""));
            panel.add(new JLabel(""));

            backButton = new JButton("Back");
            panel.add(new JLabel(""));
            panel.add(backButton);

            add(panel);
        }

        // --- Event Listener ---
        backButton.addActionListener(new BackHandler());

        setVisible(true);

    } // end constructor


    // --- Back Handler ---
    class BackHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            dispose();
            new ManageMembership(memberID, memberName);
        }

    } // end BackHandler

} // end class
