// Student Name :      Damon Kelly
// Student Id Number : C00307057
// Date :              14/04/2026
// Purpose :           GUI for member to view their membership details and duration.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * ViewMyMembership.java
 * Member screen showing their current membership plan and details.
 * Uses QueryHelpers.getMembershipByMemberID() and QueryHelpers.getActiveMembership().
 */
public class ViewMyMembership extends JFrame
{
    private int memberID;
    private String memberName;

    private JLabel nameLabel;
    private JLabel statusLabel;
    private JLabel typeLabel;
    private JLabel startDateLabel;
    private JLabel endDateLabel;
    private JTextArea descriptionArea;

    private JButton renewButton;
    private JButton backButton;

    public ViewMyMembership(int memberID, String memberName)
    {
        this.memberID = memberID;
        this.memberName = memberName;

        setTitle("My Membership");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // --- Main panel with padding ---
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // --- Title ---
        JLabel titleLabel = new JLabel("My Membership");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(15));

        // --- Info panel ---
        JPanel infoPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        infoPanel.add(new JLabel("Member Name:"));
        nameLabel = new JLabel(memberName);
        nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        infoPanel.add(nameLabel);

        infoPanel.add(new JLabel("Status:"));
        statusLabel = new JLabel();
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        infoPanel.add(statusLabel);

        infoPanel.add(new JLabel("Membership Type:"));
        typeLabel = new JLabel();
        typeLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        infoPanel.add(typeLabel);

        infoPanel.add(new JLabel("Start Date:"));
        startDateLabel = new JLabel();
        startDateLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        infoPanel.add(startDateLabel);

        infoPanel.add(new JLabel("End Date:"));
        endDateLabel = new JLabel();
        endDateLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        infoPanel.add(endDateLabel);

        mainPanel.add(infoPanel);
        mainPanel.add(Box.createVerticalStrut(15));

        // --- Description ---
        JLabel descLabel = new JLabel("Description:");
        descLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(descLabel);

        descriptionArea = new JTextArea(5, 50);
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setFont(new Font("SansSerif", Font.PLAIN, 12));
        JScrollPane descScroll = new JScrollPane(descriptionArea);
        mainPanel.add(descScroll);

        mainPanel.add(Box.createVerticalStrut(15));

        // --- Button panel ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));

        backButton = new JButton("Back");
        backButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        backButton.setPreferredSize(new Dimension(100, 35));

        renewButton = new JButton("Renew Membership");
        renewButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        renewButton.setPreferredSize(new Dimension(150, 35));

        buttonPanel.add(backButton);
        buttonPanel.add(renewButton);
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        mainPanel.add(buttonPanel);

        // --- Load data ---
        loadMembershipData();

        add(new JScrollPane(mainPanel));

        // --- Event Listeners ---
        renewButton.addActionListener(new RenewHandler());
        backButton.addActionListener(new BackHandler());

        setVisible(true);

    } // end constructor


    /**
     * Loads membership data for this member.
     */
    private void loadMembershipData()
    {
        String[] membership = QueryHelpers.getMembershipByMemberID(memberID);

        if (membership == null)
        {
            statusLabel.setText("Inactive");
            typeLabel.setText("None");
            startDateLabel.setText("-");
            endDateLabel.setText("-");
            descriptionArea.setText("You do not have an active membership.");
            renewButton.setEnabled(true);
            return;
        }

        typeLabel.setText(membership[0]);  // Plan type
        startDateLabel.setText(membership[1]); // Start date
        endDateLabel.setText(membership[2]); // End date
        descriptionArea.setText("Plan: " + membership[0]); // Use plan as description since QueryHelpers doesn't return description

        // Set status as Active since getMembershipByMemberID only returns active memberships
        statusLabel.setText("Active");
        renewButton.setEnabled(true);

    }


    // --- Renew Handler ---
    class RenewHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            dispose();
            new NewMembership(memberID, memberName);
        }

    } // end RenewHandler


    // --- Back Handler ---
    class BackHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            dispose();
            new MemberDashboard(memberID, memberName);
        }

    } // end BackHandler

} // end class
