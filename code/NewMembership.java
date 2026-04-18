import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.swing.*;

/**
 * NewMembership.java - Form for creating a new membership for a member.
 */
public class NewMembership extends JFrame
{
    private int memberID;
    private String memberName;

    private JComboBox<String> planTypeCombo;
    private JTextField startDateField;
    private JTextField endDateField;
    private JButton submitButton;
    private JButton backButton;

    public NewMembership(int memberID, String memberName)
    {
        this.memberID = memberID;
        this.memberName = memberName;

        setTitle("New Membership");
        setSize(420, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // --- Main panel ---
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // Plan type dropdown
        String[] plans = { "Monthly", "Quarterly", "Annual" };
        planTypeCombo = new JComboBox<>(plans);

        // Date fields
        startDateField = new JTextField();
        endDateField   = new JTextField();

        // Buttons
        submitButton = new JButton("Create Membership");
        backButton   = new JButton("Back");

        // Add rows to panel
        panel.add(new JLabel("Plan Type:"));
        panel.add(planTypeCombo);

        panel.add(new JLabel("Start Date (YYYY-MM-DD):"));
        panel.add(startDateField);

        panel.add(new JLabel("End Date (YYYY-MM-DD):"));
        panel.add(endDateField);

        panel.add(backButton);
        panel.add(submitButton);

        add(panel);

        // --- Auto-populate dates when plan type changes ---
        planTypeCombo.addActionListener(e -> updateDates());

        // --- Event Listeners ---
        submitButton.addActionListener(new SubmitHandler());
        backButton.addActionListener(new BackHandler());

        // --- Initial date population ---
        updateDates();

        setVisible(true);

    } // end constructor


    /**
     * Auto-populate start and end dates based on selected plan type.
     */
    private void updateDates()
    {
        LocalDate today = LocalDate.now();
        LocalDate endDate;
        
        String planType = (String) planTypeCombo.getSelectedItem();
        
        endDate = switch (planType) {
            case "Monthly" -> today.plus(1, ChronoUnit.MONTHS);
            case "Quarterly" -> today.plus(3, ChronoUnit.MONTHS);
            case "Annual" -> today.plus(1, ChronoUnit.YEARS);
            default -> today.plus(1, ChronoUnit.MONTHS);
        };
        
        startDateField.setText(today.toString());
        endDateField.setText(endDate.toString());
    }


    // --- Submit Handler ---
    class SubmitHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String planType  = (String) planTypeCombo.getSelectedItem();
            String startDate = startDateField.getText().trim();
            String endDate   = endDateField.getText().trim();

            try
            {
                // Validate inputs using Validator class
                Validator.validateNotEmpty(planType, "Plan Type");
                Validator.validateNotEmpty(startDate, "Start Date");
                Validator.validateNotEmpty(endDate, "End Date");
                Validator.validateDate(startDate);
                Validator.validateDate(endDate);
                Validator.validateStartEndDate(startDate, endDate);

                // Insert membership — IsActive set to 1 (active) by default
                InsertMembership.insertMembership(planType, startDate, endDate, 1, memberID);

                JOptionPane.showMessageDialog(null,
                    "Membership created successfully!",
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
                    "Error creating membership: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }

        } // end actionPerformed

    } // end SubmitHandler


    // --- Back Handler ---
    class BackHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            dispose();
            new ManageMembership(memberID, memberName);
        }

    } // end BackHandler

} // end class
