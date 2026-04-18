// Student Name :      Damon Kelly
// Student Id Number : C00307057
// Date :              14/04/2026
// Purpose :           GUI for member to manage membership (view, renew, cancel).

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.*;

public class ManageMembership extends JFrame
{
    private JButton newMembership;
    private JButton viewMembership;
    private JButton cancelMembership;
    private JButton renewMembership;
    private JButton backToDashboard;

    private int memberID;
    private String memberName;

    public ManageMembership(int memberID, String memberName)
    {
        super("Manage Membership");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);

        this.memberID = memberID;
        this.memberName = memberName;

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        JLabel titleLabel = new JLabel("Manage your Membership", JLabel.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        panel.add(titleLabel);

        newMembership     = new JButton("Start a new Membership");
        newMembership.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        viewMembership    = new JButton("View your current Membership");
        viewMembership.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        renewMembership   = new JButton("Renew your current Membership");
        renewMembership.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        cancelMembership  = new JButton("Cancel your current Membership");
        cancelMembership.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        backToDashboard   = new JButton("Return to Dashboard");
        backToDashboard.setFont(new Font("SansSerif", Font.PLAIN, 14));

        panel.add(newMembership);
        panel.add(viewMembership);
        panel.add(renewMembership);
        panel.add(cancelMembership);
        panel.add(backToDashboard);

        MembershipHandler membershipHandler = new MembershipHandler();
        newMembership.addActionListener(membershipHandler);
        viewMembership.addActionListener(membershipHandler);
        renewMembership.addActionListener(membershipHandler);
        cancelMembership.addActionListener(membershipHandler);
        backToDashboard.addActionListener(membershipHandler);

        add(panel);
        setVisible(true);
    }

    private class MembershipHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if (event.getSource() == backToDashboard)
            {
                dispose();
                new MemberDashboard(memberID, memberName);
            }
            else if (event.getSource() == newMembership)
            {
                dispose();
                new NewMembership(memberID, memberName);
            }
            else if (event.getSource() == viewMembership)
            {
                dispose();
                new ViewMembership(memberID, memberName);
            }
            else if (event.getSource() == renewMembership)
            {
                handleRenewMembership();
            }
            else if (event.getSource() == cancelMembership)
            {
                handleCancelMembership();
            }
        }
    }

    private void handleRenewMembership()
    {
        String[] membership = QueryHelpers.getMembershipByMemberID(memberID);

        if (membership == null)
        {
            JOptionPane.showMessageDialog(null,
                "No active membership found to renew.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Current membership: [PlanType, StartDate, EndDate, IsActive]
        String planType = membership[0];
        String startDate = membership[1];
        String endDate = membership[2];

        // Pre-fill with one year from today
        LocalDate newEndDate = LocalDate.now().plusYears(1);
        JTextField endDateField = new JTextField(newEndDate.toString());

        Object[] fields =
        {
            "Current Plan Type:", new JLabel(planType),
            "Current End Date:", new JLabel(endDate),
            "New End Date (YYYY-MM-DD):", endDateField
        };

        int result = JOptionPane.showConfirmDialog(null, fields,
            "Renew Membership", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION)
        {
            try
            {
                String newEndDateStr = endDateField.getText().trim();
                Validator.validateNotEmpty(newEndDateStr, "New End Date");
                Validator.validateDate(newEndDateStr);
                Validator.validateStartEndDate(startDate, newEndDateStr);

                UpdateMembership.updateMembership(memberID, planType, startDate, newEndDateStr, 1);

                JOptionPane.showMessageDialog(null,
                    "Membership renewed successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

                dispose();
                new ManageMembership(memberID, memberName);
            }
            catch (ValidationException ve)
            {
                JOptionPane.showMessageDialog(null,
                    "Validation Error: " + ve.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null,
                    "Error renewing membership: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleCancelMembership()
    {
        String[] membership = QueryHelpers.getMembershipByMemberID(memberID);

        if (membership == null)
        {
            JOptionPane.showMessageDialog(null,
                "No active membership found to cancel.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        String planType = membership[0];
        String startDate = membership[1];
        String endDate = membership[2];

        int confirm = JOptionPane.showConfirmDialog(null,
            "Are you sure you want to cancel your membership?\nThis action cannot be undone.",
            "Confirm Cancellation",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION)
        {
            try
            {
                UpdateMembership.updateMembership(memberID, planType, startDate, endDate, 0);

                JOptionPane.showMessageDialog(null,
                    "Membership cancelled successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

                dispose();
                new ManageMembership(memberID, memberName);
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null,
                    "Error cancelling membership: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

} // end class