package lab.view.window;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.*;

import lab.controller.Controller;
import lab.model.Passes;
import lab.model.Student;
import lab.view.table.RecordsTable;

public class FindByPassNumberWindow {
	
	private final int WINDOW_SIZE_X = 1300;
	private final int WINDOW_SIZE_Y = 400;
	private final int COMPONENT_SIZE_X = 200;
	private final int COMPONENT_SIZE_Y = 20;
	
	public FindByPassNumberWindow(MainWindow mainWindow, boolean removeAfterSearch) {
		
		String actionType = "Find";
		if (removeAfterSearch) {
			actionType = "Remove";
		}
		
		Controller controller = mainWindow.getController();
		
		JFrame frame = new JFrame(actionType + " Records");
		JPanel mainPanel = new JPanel();
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		
		RecordsTable recordsTable = new RecordsTable(controller.getRecords());
		
		JLabel lastNameLabel = new JLabel("Last name");
		JTextField lastNameField = new JTextField();
		String[] items = {"disease", "other reason", "without reason", "all"};
		JComboBox<String> comboBox = new JComboBox<String>(items);
		JLabel minLabel = new JLabel("Min");
		JTextField minField = new JTextField();
		JLabel maxLabel = new JLabel("Max");
		JTextField maxField = new JTextField();
		
		lastNameField.setMaximumSize(new Dimension(COMPONENT_SIZE_X, COMPONENT_SIZE_Y));
		comboBox.setMaximumSize(new Dimension(COMPONENT_SIZE_X, COMPONENT_SIZE_Y));
		minField.setMaximumSize(new Dimension(COMPONENT_SIZE_X, COMPONENT_SIZE_Y));
		maxField.setMaximumSize(new Dimension(COMPONENT_SIZE_X, COMPONENT_SIZE_Y));
		
		JButton findButton = new JButton(actionType);
		findButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
            	String lastName = lastNameField.getText().trim();
            	String min = minField.getText().trim();
            	String max = maxField.getText().trim();
            	if (Integer.parseInt(min) > Integer.parseInt(max)) {
            		JOptionPane.showMessageDialog(new JFrame(), "Min greater max!", "Error", JOptionPane.ERROR_MESSAGE);
            	} else if (min.isEmpty() || max.isEmpty()) {
            		JOptionPane.showMessageDialog(new JFrame(), "Empty field error!", "Error", JOptionPane.ERROR_MESSAGE);
            	} else {
            		Map<Student, Passes> result = controller.findByPassNumber(lastName, comboBox.getSelectedIndex(), Integer.parseInt(min), Integer.parseInt(max));
    				if (!removeAfterSearch) {
                		recordsTable.update(result);
    				} else {
                		int deletedNumber = controller.removeSelected(result);
                		recordsTable.update(controller.getRecords());
                    	mainWindow.getRecordsTable().update(controller.getRecords());
                    	JOptionPane.showMessageDialog(new JFrame(), "Deleted " + String.valueOf(deletedNumber), "Info", JOptionPane.WARNING_MESSAGE);
                	}
            	}
            }
        });
		
		leftPanel.add(recordsTable.getComponent());
		rightPanel.add(lastNameLabel);
		rightPanel.add(lastNameField);
		rightPanel.add(comboBox);
		rightPanel.add(minLabel);
		rightPanel.add(minField);
		rightPanel.add(maxLabel);
		rightPanel.add(maxField);
		rightPanel.add(findButton);
		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);
		frame.add(mainPanel);
		frame.setSize(WINDOW_SIZE_X, WINDOW_SIZE_Y);
        frame.setVisible(true);
	}
}
