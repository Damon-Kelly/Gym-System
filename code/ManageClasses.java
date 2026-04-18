// Student Name :      Damon Kelly
// Student Id Number : C00307057
// Date :              14/04/2026
// Purpose :           GUI for trainer to manage gym classes with full CRUD operations.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class ManageClasses extends JFrame
{
    private int trainerID;

    private JTable classTable;
    private DefaultTableModel tableModel;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton backButton;

    public ManageClasses(int trainerID)
    {
        this.trainerID = trainerID;

        setTitle("Manage Classes");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // --- Table setup ---
        String[] columns = { "Class ID", "Title", "Schedule", "Capacity" };
        tableModel = new DefaultTableModel(columns, 0)
        {
            public boolean isCellEditable(int row, int column) { return false; }
        };

        classTable = new JTable(tableModel);
        classTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        classTable.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(classTable);

        // --- Load data ---
        loadClassData();

        // --- Button panel ---
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        addButton    = new JButton("Add Class");
        editButton   = new JButton("Edit Class");
        deleteButton = new JButton("Delete Class");
        backButton   = new JButton("Back");

        buttonPanel.add(backButton);
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        // --- Layout ---
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // --- Event Listeners ---
        addButton.addActionListener(new AddHandler());
        editButton.addActionListener(new EditHandler());
        deleteButton.addActionListener(new DeleteHandler());
        backButton.addActionListener(new BackHandler());

        setVisible(true);

    } // end constructor


    /**
     * Clears and reloads the class table from the database.
     * Called on load and after any CRUD operation.
     */
    private void loadClassData()
    {
        tableModel.setRowCount(0);

        // Each String[]: [ClassID, Title, Schedule, Capacity]
        String[][] classes = QueryHelpers.getAllClasses();

        for (String[] row : classes)
        {
            tableModel.addRow(row);
        }

    } // end loadClassData


    /**
     * Validates the schedule string (format: YYYY-MM-DD HH:MM).
     * Throws ValidationException if the date or time part is invalid.
     */
    private void validateSchedule(String schedule) throws ValidationException
    {
        if (schedule == null || schedule.length() != 16 || schedule.charAt(10) != ' ')
        {
            throw new ValidationException("Schedule must be in the format YYYY-MM-DD HH:MM.");
        }
        Validator.validateDate(schedule.substring(0, 10));
        Validator.validateClassTime(schedule.substring(11));
    }


    // --- Add Handler: opens a dialog to create a new class ---
    class AddHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JTextField titleField    = new JTextField();
            JTextField scheduleField = new JTextField("YYYY-MM-DD HH:MM");
            JTextField capacityField = new JTextField();

            Object[] fields = {
                "Title:",              titleField,
                "Schedule (YYYY-MM-DD HH:MM):", scheduleField,
                "Capacity:",           capacityField
            };

            int result = JOptionPane.showConfirmDialog(null,
                fields,
                "Add New Class",
                JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION)
            {
                try
                {
                    String title    = titleField.getText().trim();
                    String schedule = scheduleField.getText().trim();
                    String capacity = capacityField.getText().trim();

                    // Fixed: validate all fields including schedule format and capacity range
                    Validator.validateNotEmpty(title,    "Title");
                    Validator.validateNotEmpty(schedule, "Schedule");
                    validateSchedule(schedule);
                    Validator.validateNotEmpty(capacity, "Capacity");

                    int capacityInt = Integer.parseInt(capacity);
                    Validator.validateClassCapacity(capacityInt);

                    InsertClass.insertClass(title, schedule, capacityInt);

                    JOptionPane.showMessageDialog(null, "Class added successfully!");

                    loadClassData();
                }
                catch (ValidationException ve)
                {
                    JOptionPane.showMessageDialog(null,
                        "Validation Error: " + ve.getMessage(),
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
                }
                catch (NumberFormatException nfe)
                {
                    JOptionPane.showMessageDialog(null,
                        "Capacity must be a valid integer.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null,
                        "Error adding class: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }

        } // end actionPerformed

    } // end AddHandler


    // --- Edit Handler: pre-fills dialog with selected class data ---
    class EditHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int selectedRow = classTable.getSelectedRow();

            if (selectedRow == -1)
            {
                JOptionPane.showMessageDialog(null,
                    "Please select a class to edit.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            int    classID  = Integer.parseInt((String) tableModel.getValueAt(selectedRow, 0));
            String title    = (String) tableModel.getValueAt(selectedRow, 1);
            String schedule = (String) tableModel.getValueAt(selectedRow, 2);
            String capacity = (String) tableModel.getValueAt(selectedRow, 3);

            JTextField titleField    = new JTextField(title);
            JTextField scheduleField = new JTextField(schedule);
            JTextField capacityField = new JTextField(capacity);

            Object[] fields = {
                "Title:",              titleField,
                "Schedule (YYYY-MM-DD HH:MM):", scheduleField,
                "Capacity:",           capacityField
            };

            int result = JOptionPane.showConfirmDialog(null,
                fields,
                "Edit Class",
                JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION)
            {
                try
                {
                    String newTitle    = titleField.getText().trim();
                    String newSchedule = scheduleField.getText().trim();
                    String newCapacity = capacityField.getText().trim();

                    // Fixed: validate all fields including schedule format and capacity range
                    Validator.validateNotEmpty(newTitle,    "Title");
                    Validator.validateNotEmpty(newSchedule, "Schedule");
                    validateSchedule(newSchedule);
                    Validator.validateNotEmpty(newCapacity, "Capacity");

                    int capacityInt = Integer.parseInt(newCapacity);
                    Validator.validateClassCapacity(capacityInt);

                    UpdateClass.updateClass(classID, newTitle, newSchedule, capacityInt);

                    JOptionPane.showMessageDialog(null, "Class updated successfully!");

                    loadClassData();
                }
                catch (ValidationException ve)
                {
                    JOptionPane.showMessageDialog(null,
                        "Validation Error: " + ve.getMessage(),
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
                }
                catch (NumberFormatException nfe)
                {
                    JOptionPane.showMessageDialog(null,
                        "Capacity must be a valid integer.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null,
                        "Error updating class: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }

        } // end actionPerformed

    } // end EditHandler


    // --- Delete Handler: soft deletes selected class ---
    class DeleteHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int selectedRow = classTable.getSelectedRow();

            if (selectedRow == -1)
            {
                JOptionPane.showMessageDialog(null,
                    "Please select a class to delete.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            String classTitle = (String) tableModel.getValueAt(selectedRow, 1);
            int classID = Integer.parseInt((String) tableModel.getValueAt(selectedRow, 0));

            int confirm = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to delete: " + classTitle + "?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION)
            {
                try
                {
                    DeleteClass.deleteClass(classID);

                    JOptionPane.showMessageDialog(null, "Class deleted successfully!");

                    loadClassData();
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null,
                        "Error deleting class: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }

        } // end actionPerformed

    } // end DeleteHandler


    // --- Back Handler ---
    class BackHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            dispose();
            new TrainerDashboard(trainerID);
        }

    } // end BackHandler

} // end class
