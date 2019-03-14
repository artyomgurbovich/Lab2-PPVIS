package lab.view;

import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import lab.controller.Controller;
import lab.view.table.StudentsTable;

public class View {
	
	private final String ADD_STUDENT = "Add Student";
	private final String FIND_BY_GROUP = "Find by Group";
	private final String FIND_BY_PASS_TYPE = "Find by Pass type";
	private final String FIND_BY_PASS_NUMBER = "Find by Pass number";
	private final String REMOVE_BY_GROUP = "Remove by Group";
	private final String REMOVE_BY_PASS_TYPE = "Remove by Pass type";
	private final String REMOVE_BY_PASS_NUMBER = "Remove by Pass number";
	
	private Controller controller;
	private StudentsTable studentsTable;
	
	public View(Controller controller) {
		this.controller = controller;
	}
	
	private JMenu createFileMenu() {
		JMenu fileMenu = new JMenu("File");
		JMenuItem newFile = new JMenuItem("New");
		JMenuItem loadFile = new JMenuItem("Load");
		JMenuItem saveFile = new JMenuItem("Save");
		fileMenu.add(newFile);
		fileMenu.add(loadFile);
		fileMenu.add(saveFile);
		newFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				controller.clearStudents();
            	studentsTable.updateTableData(controller.getStudents());
            }
        });
		loadFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				try {
					controller.LoadStudents();
				} catch (SAXException | IOException | ParserConfigurationException e1) {
					e1.printStackTrace();
				}
            	studentsTable.updateTableData(controller.getStudents());
            }
        });
		saveFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
					controller.SaveStudents();
				} catch (ParserConfigurationException | TransformerException e1) {
					e1.printStackTrace();
				}
            }
        });
		return fileMenu;
	}
	
	private JMenu create—ommandsMenu() {
		JMenu commandsMenu = new JMenu("—ommands");
		JMenuItem add = new JMenuItem(ADD_STUDENT);
		JMenuItem findByGroup = new JMenuItem(FIND_BY_GROUP);
		JMenuItem findByPassType = new JMenuItem(FIND_BY_PASS_TYPE);
		JMenuItem findByPassNumber = new JMenuItem(FIND_BY_PASS_NUMBER);
		JMenuItem removeByGroup = new JMenuItem(REMOVE_BY_GROUP);
		JMenuItem removeByPassType = new JMenuItem(REMOVE_BY_PASS_TYPE);
		JMenuItem removeByPassNumber = new JMenuItem(REMOVE_BY_PASS_NUMBER);
		
		commandsMenu.add(add);
		commandsMenu.add(findByGroup);
		commandsMenu.add(findByPassType);
		commandsMenu.add(findByPassNumber);
		commandsMenu.add(removeByGroup);
		commandsMenu.add(removeByPassType);
		commandsMenu.add(removeByPassNumber);
		
		add.addActionListener(addAction);
		findByGroup.addActionListener(findByGroupAction);
		findByPassType.addActionListener(findByPassTypeAction);
		findByPassNumber.addActionListener(findByPassNumberAction);
		removeByGroup.addActionListener(removeByGroupAction);
		removeByPassType.addActionListener(removeByPassTypeAction);
		removeByPassNumber.addActionListener(removeByPassNumberAction);
		
		return commandsMenu;
	}
	
	public void begin() {
		
		JFrame frame = new JFrame("Lab2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar(); 
        menuBar.add(createFileMenu());
        menuBar.add(create—ommandsMenu());
        frame.setJMenuBar(menuBar);
		
		JPanel mainPanel = new JPanel();
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		
		studentsTable = new StudentsTable(leftPanel, controller.getStudents());
		
		JButton addButton = new JButton(ADD_STUDENT);
		JButton findByGroupButton = new JButton(FIND_BY_GROUP);
		JButton findByPassTypeButton = new JButton(FIND_BY_PASS_TYPE);
		JButton findByPassNumberButton = new JButton(FIND_BY_PASS_NUMBER);
		JButton removeByGroupButton = new JButton(REMOVE_BY_GROUP);
		JButton removeByPassTypeButton = new JButton(REMOVE_BY_PASS_TYPE);
		JButton removeByPassNumberButton = new JButton(REMOVE_BY_PASS_NUMBER);

		rightPanel.add(addButton);
		rightPanel.add(findByGroupButton);
		rightPanel.add(findByPassTypeButton);
		rightPanel.add(findByPassNumberButton);
		rightPanel.add(removeByGroupButton);
		rightPanel.add(removeByPassTypeButton);
		rightPanel.add(removeByPassNumberButton);
		
		addButton.addActionListener(addAction);
		findByGroupButton.addActionListener(findByGroupAction);
		findByPassTypeButton.addActionListener(findByPassTypeAction);
		findByPassNumberButton.addActionListener(findByPassNumberAction);
		removeByGroupButton.addActionListener(removeByGroupAction);
		removeByPassTypeButton.addActionListener(removeByPassTypeAction);
		removeByPassNumberButton.addActionListener(removeByPassNumberAction);
		
		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);
		
		frame.add(mainPanel);
		frame.setSize(1200, 400);
        frame.setVisible(true);
	}
	
	ActionListener addAction = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	new AddWindow(controller, studentsTable);
        }
    };
    
	ActionListener findByGroupAction = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	new FindByGroupWindow(controller);
        }
    };
    
	ActionListener findByPassTypeAction = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	new FindByPassTypeWindow(controller);
        }
    };
    
	ActionListener findByPassNumberAction = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	new FindByPassNumberWindow(controller);
        }
    };
    
	ActionListener removeByGroupAction = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	new RemoveByGroupWindow(controller, studentsTable);
        }
    };
    
	ActionListener removeByPassTypeAction = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	new RemoveByPassTypeWindow(controller, studentsTable);
        }
    };
    
	ActionListener removeByPassNumberAction = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	new RemoveByPassNumberWindow(controller, studentsTable);
        }
    };
}