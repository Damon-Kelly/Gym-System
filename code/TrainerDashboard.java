// Student Name :      Damon Kelly
// Student Id Number : C00307057
// Date :              11/04/2026
// Purpose :           GUI for the trainer dashboard with menu options.

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TrainerDashboard extends JFrame
{
    private JButton manageClasses;
    private JButton viewSchedule;
    private JButton logoutButton;

    private int trainerID; // changed from Trainer object

    public TrainerDashboard(int trainerID) // changed from Trainer trainer
    {
        super("Trainer Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        this.trainerID = trainerID;

        // Fixed: was GridLayout(5, 1) but only 4 components were added,
        // leaving an ugly empty row at the bottom of the panel.
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

        panel.add(new JLabel("Welcome, Trainer!", JLabel.CENTER));
        panel.add(manageClasses = new JButton("Manage Classes"));
        panel.add(viewSchedule  = new JButton("View Schedule"));
        panel.add(logoutButton  = new JButton("Logout"));

        TrainerHandler trainerHandler = new TrainerHandler();
        manageClasses.addActionListener(trainerHandler);
        viewSchedule.addActionListener(trainerHandler);
        logoutButton.addActionListener(trainerHandler);

        add(panel);
        setVisible(true);
    }

    private class TrainerHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if (event.getSource() == logoutButton)
            {
                dispose();
                new GymGui().setVisible(true);
            }
            else if (event.getSource() == manageClasses)
            {
                dispose();
                new ManageClasses(trainerID);
            }
            else if (event.getSource() == viewSchedule)
            {
                dispose();
                new ViewSchedule(trainerID);
            }
        }
    }

} // end class