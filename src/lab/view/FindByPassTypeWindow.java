package lab.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import lab.controller.Controller;
import lab.view.table.StudentsTable;

public class FindByPassTypeWindow {
	public FindByPassTypeWindow(Controller controller) {
		JFrame frame = new JFrame("Add Student");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		StudentsTable studentsTable = new StudentsTable(leftPanel, controller.getStudents());
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		
		JLabel surnameLabel = new JLabel("Surname");
		JTextField surnameField = new JTextField();
		surnameField.setMaximumSize(new Dimension(200, 20));

		String[] items = {"disease", "other reason", "without reason", "all"};
		JComboBox<String> comboBox = new JComboBox<String>(items);
		comboBox.setMaximumSize(new Dimension(200, 20));
		
		JButton findButton = new JButton("Find");
		findButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
            	studentsTable.updateTableData(controller.findByPassType(surnameField.getText(), comboBox.getSelectedIndex()));
            }
        });
		
		rightPanel.add(surnameLabel);
		rightPanel.add(surnameField);
		rightPanel.add(comboBox);
		rightPanel.add(findButton);
		
		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);
		
		frame.add(mainPanel);
		frame.setSize(1300, 400);
        frame.setVisible(true);
	}
}
