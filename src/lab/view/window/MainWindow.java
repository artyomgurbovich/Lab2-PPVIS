package lab.view.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import lab.controller.Controller;
import lab.view.table.RecordsTable;

public class MainWindow {
	
	private final int FRAME_SIZE_X = 1200;
	private final int FRAME_SIZE_Y = 400;
	private final String ADD_STUDENT = "Add Student";
	private final String FIND_BY_GROUP = "Find by Group";
	private final String FIND_BY_PASS_TYPE = "Find by Pass type";
	private final String FIND_BY_PASS_NUMBER = "Find by Pass number";
	private final String REMOVE_BY_GROUP = "Remove by Group";
	private final String REMOVE_BY_PASS_TYPE = "Remove by Pass type";
	private final String REMOVE_BY_PASS_NUMBER = "Remove by Pass number";
	
	private Controller controller;
	private RecordsTable recordsTable;
	
	public MainWindow(Controller controller) {
		this.controller = controller;
	}
	
	public Controller getController() {
		return controller;
	}
	
	public RecordsTable getRecordsTable() {
		return recordsTable;
	}
	
	private JMenu createFileMenu() {
		JMenu fileMenu = new JMenu("File");
		JMenuItem newFile = new JMenuItem("New");
		JMenuItem loadFile = new JMenuItem("Load");
		JMenuItem saveFile = new JMenuItem("Save");
		
		newFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				controller.clearRecords();
				recordsTable.update(controller.getRecords());
            }
        });
		loadFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				try {
					controller.loadRecords();;
				} catch (SAXException | IOException | ParserConfigurationException e1) {
					e1.printStackTrace();
				}
				recordsTable.update(controller.getRecords());
            }
        });
		saveFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
					controller.saveStudents();
				} catch (ParserConfigurationException | TransformerException e1) {
					e1.printStackTrace();
				}
            }
        });
		
		fileMenu.add(newFile);
		fileMenu.add(loadFile);
		fileMenu.add(saveFile);
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
		
		add.addActionListener(controller.addAction);
		findByGroup.addActionListener(controller.findByGroupAction);
		findByPassType.addActionListener(controller.findByPassTypeAction);
		findByPassNumber.addActionListener(controller.findByPassNumberAction);
		removeByGroup.addActionListener(controller.removeByGroupAction);
		removeByPassType.addActionListener(controller.removeByPassTypeAction);
		removeByPassNumber.addActionListener(controller.removeByPassNumberAction);
		return commandsMenu;
	}
	
	public void CreateUI() {
		JFrame frame = new JFrame("Lab2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar(); 
        menuBar.add(createFileMenu());
        menuBar.add(create—ommandsMenu());
		
		JPanel mainPanel = new JPanel();
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		
		recordsTable = new RecordsTable(controller.getRecords());
		
		JButton addButton = new JButton(ADD_STUDENT);
		JButton findByGroupButton = new JButton(FIND_BY_GROUP);
		JButton findByPassTypeButton = new JButton(FIND_BY_PASS_TYPE);
		JButton findByPassNumberButton = new JButton(FIND_BY_PASS_NUMBER);
		JButton removeByGroupButton = new JButton(REMOVE_BY_GROUP);
		JButton removeByPassTypeButton = new JButton(REMOVE_BY_PASS_TYPE);
		JButton removeByPassNumberButton = new JButton(REMOVE_BY_PASS_NUMBER);
		
		addButton.addActionListener(controller.addAction);
		findByGroupButton.addActionListener(controller.findByGroupAction);
		findByPassTypeButton.addActionListener(controller.findByPassTypeAction);
		findByPassNumberButton.addActionListener(controller.findByPassNumberAction);
		removeByGroupButton.addActionListener(controller.removeByGroupAction);
		removeByPassTypeButton.addActionListener(controller.removeByPassTypeAction);
		removeByPassNumberButton.addActionListener(controller.removeByPassNumberAction);
		
		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);
		
		leftPanel.add(recordsTable.getComponent());
		
		rightPanel.add(addButton);
		rightPanel.add(findByGroupButton);
		rightPanel.add(findByPassTypeButton);
		rightPanel.add(findByPassNumberButton);
		rightPanel.add(removeByGroupButton);
		rightPanel.add(removeByPassTypeButton);
		rightPanel.add(removeByPassNumberButton);
		
        frame.setJMenuBar(menuBar);
		frame.add(mainPanel);
		frame.setSize(FRAME_SIZE_X, FRAME_SIZE_Y);
        frame.setVisible(true);
	}
}
