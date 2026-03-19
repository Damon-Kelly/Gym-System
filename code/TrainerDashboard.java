// Student Name : 		Damon Kelly
// Student Id Number : 	C00307057
// Date :				19/03/2026
// Purpose : 			The gui for the trainer dashboard

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TrainerDashboard extends JFrame
{
    // declare the elements used
    private JButton manageClasses;
    private JButton viewSchedule;
    private JButton logoutButton;

    public TrainerDashboard(Trainer trainer)
    {
        // default page setup
        super("Trainer Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        
        // page setup
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        // add a border
        panel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

        // welcome the trainer
        panel.add(new JLabel("Welcome, " + trainer.getName() + "!", JLabel.CENTER));

        // add the buttons to the panel
        panel.add(manageClasses = new JButton("Manage Classes"));
        panel.add(viewSchedule = new JButton("View Schedule"));

        // use the event handling class
        TrainerHandler TrainerHandler = new TrainerHandler();
        logoutButton = new JButton("Logout");
        manageClasses.addActionListener(TrainerHandler);
        viewSchedule.addActionListener(TrainerHandler);
        logoutButton.addActionListener(TrainerHandler);
        panel.add(logoutButton);

        // this makes it visible
        add(panel);
        setVisible(true);
    }

    // private inner class for event handling
    private class TrainerHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if (event.getSource() == logoutButton)
                {
                    dispose(); // Close the dashboard window
                    GymGui gymGui = new GymGui(); // Open the login window again
                    gymGui.setVisible(true);
                }
        
            else if (event.getSource() == manageClasses)
                {
                    JOptionPane.showMessageDialog(null, "Manage Classes selected.");
                }
            else if (event.getSource() == viewSchedule)
                {
                    JOptionPane.showMessageDialog(null, "View Schedule selected.");
                }
        }
    }
}