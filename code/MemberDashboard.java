// Student Name :      Damon Kelly
// Student Id Number : C00307057
// Date :              14/04/2026
// Purpose :           GUI for member dashboard showing welcome, membership status, and booking options.

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MemberDashboard extends JFrame
{
    private JButton myMembershipButton;
    private JButton bookClassButton;
    private JButton myBookingsButton;
    private JButton logoutButton;

    private int memberID;
    private String memberName;

    public MemberDashboard(int memberID, String memberName)
    {
        super("Member Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 550);
        setLocationRelativeTo(null);

        this.memberID = memberID;
        this.memberName = memberName;

        // --- Main panel with better structure ---
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        // --- Header section ---
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setOpaque(false);
        
        JLabel welcomeLabel = new JLabel("Welcome, " + memberName + "!");
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(welcomeLabel);
        
        headerPanel.add(Box.createVerticalStrut(10));

        // Membership status label with better styling
        String[] membership = QueryHelpers.getMembershipByMemberID(memberID);
        JLabel statusLabel = new JLabel();
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        if (membership != null)
        {
            statusLabel.setText("✓ Active Membership: " + membership[0] + " (expires " + membership[2] + ")");
            statusLabel.setForeground(new Color(0, 120, 0));
        }
        else
        {
            statusLabel.setText("⚠ No active membership");
            statusLabel.setForeground(new Color(200, 0, 0));
        }
        headerPanel.add(statusLabel);
        mainPanel.add(headerPanel);
        
        mainPanel.add(Box.createVerticalStrut(30));

        // --- Button section ---
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 0, 15));
        buttonPanel.setOpaque(false);
        buttonPanel.setMaximumSize(new Dimension(300, 250));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        myMembershipButton = createStyledButton("My Membership");
        bookClassButton = createStyledButton("Book a Class");
        myBookingsButton = createStyledButton("View My Bookings");
        logoutButton = createStyledButton("Logout");

        buttonPanel.add(myMembershipButton);
        buttonPanel.add(bookClassButton);
        buttonPanel.add(myBookingsButton);
        buttonPanel.add(logoutButton);

        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalGlue());

        MemberHandler memberHandler = new MemberHandler();
        myMembershipButton.addActionListener(memberHandler);
        bookClassButton.addActionListener(memberHandler);
        myBookingsButton.addActionListener(memberHandler);
        logoutButton.addActionListener(memberHandler);

        add(mainPanel);
        setVisible(true);
    }

    private JButton createStyledButton(String text)
    {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(300, 50));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private class MemberHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if (event.getSource() == logoutButton)
            {
                dispose();
                new GymGui().setVisible(true);
            }
            else if (event.getSource() == myMembershipButton)
            {
                dispose();
                new ManageMembership(memberID, memberName);
            }
            else if (event.getSource() == bookClassButton)
            {
                dispose();
                new BookClass(memberID, memberName);
            }
            else if (event.getSource() == myBookingsButton)
            {
                dispose();
                new ViewMyBookings(memberID, memberName);
            }
        }
    }

} // end class