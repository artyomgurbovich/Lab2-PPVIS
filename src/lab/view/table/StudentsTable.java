package lab.view.table;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import lab.model.Student;

public class StudentsTable {

	private StudentsTableModel tableModel;
	private JTable table;
	private JLabel infoLabel = new JLabel("");
	private int rowsPerPage = 10;
	private int currentPage = 1;
	private int allPages = 1;
	
	@SuppressWarnings("serial")
	public StudentsTable(JPanel rootPanel, ArrayList<Student> students) {
		tableModel = new StudentsTableModel(students);
		table = new JTable(tableModel) {
	    	protected JTableHeader createDefaultTableHeader() {
	    		return new GroupableTableHeader(columnModel);
	    	}
	    };
	    addSubHeader();
	    rootPanel.add(createPaginationPanel());
	}
	
	private void addSubHeader() {
	    TableColumnModel tableColumnModel = table.getColumnModel();
	    ColumnGroup passesColumnGroup = new ColumnGroup("Passes");
	    passesColumnGroup.add(tableColumnModel.getColumn(2));
	    passesColumnGroup.add(tableColumnModel.getColumn(3));
	    passesColumnGroup.add(tableColumnModel.getColumn(4));
	    passesColumnGroup.add(tableColumnModel.getColumn(5));
	    GroupableTableHeader header = (GroupableTableHeader)table.getTableHeader();
	    header.addColumnGroup(passesColumnGroup);
	}
	
	private JPanel createPaginationPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
        JPanel navigation = new JPanel();
        navigation.setLayout(new BoxLayout(navigation, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(table.getPreferredSize().width, table.getRowHeight() * rowsPerPage + 6));
		
		JButton next = new JButton(">");
        next.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                int height = table.getRowHeight() * rowsPerPage;
                JScrollBar bar = scrollPane.getVerticalScrollBar();
                bar.setValue(bar.getValue() + height);
                if (currentPage < allPages) {
                    currentPage++;
                }
                updateInfoLabel();
            }
        });
        JButton previous = new JButton("<");
        previous.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                int height = table.getRowHeight() * rowsPerPage;
                JScrollBar bar = scrollPane.getVerticalScrollBar();
                bar.setValue(bar.getValue() - height);
                if (currentPage >= 2) {
                    currentPage--;
                }
                updateInfoLabel();
            }
        });
        JButton first = new JButton("<<");
        first.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                int height = table.getRowHeight() * table.getRowCount();
                JScrollBar bar = scrollPane.getVerticalScrollBar();
                bar.setValue(bar.getValue() - height);
                currentPage = 1;
                updateInfoLabel();
            }
        });
        JButton last = new JButton(">>");
        last.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                int height = table.getRowHeight() * table.getRowCount();
                JScrollBar bar = scrollPane.getVerticalScrollBar();
                bar.setValue(bar.getValue() + height);
                currentPage = allPages;
                updateInfoLabel();
            }
        });
        
        JTextField textField = new JTextField();
        textField.setMaximumSize(new Dimension(100, 20));
        JButton rowButton = new JButton("Change rows per page");
        rowButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	rowsPerPage = Integer.parseInt(textField.getText());
            	panel.setMaximumSize(new Dimension(1000, table.getRowHeight() * (rowsPerPage + 4) + 4));
                updateInfoLabel();
            }
        });
        
        navigation.add(first);
        navigation.add(previous);
        navigation.add(next);
        navigation.add(last);
        navigation.add(infoLabel);
        navigation.add(textField);
        navigation.add(rowButton);
        updateInfoLabel();
        panel.add(scrollPane);
        panel.add(navigation);
        panel.setMaximumSize(new Dimension(1000, table.getRowHeight() * (rowsPerPage + 4) + 4));
        return panel;
	}
	
	public void updateInfoLabel() {
		int rowCount = table.getRowCount();
		int rowsInPage = rowsPerPage;
		if (rowCount < rowsInPage) {
			rowsInPage = rowCount;
		}
		allPages = (int)Math.ceil((float)rowCount / rowsInPage);
		if (allPages == 0) allPages = 1;
		if (currentPage > allPages) {
			currentPage = allPages;
		}
		infoLabel.setText("    Rows in page: " + String.valueOf(rowsInPage) +
				          "    Row count: " + String.valueOf(rowCount) +
				          "    All pages: " + String.valueOf(allPages) +
				          "    Current page: " + String.valueOf(currentPage) + "    ");
	}
	
	public void updateTableData(ArrayList<Student> students) {
		tableModel.setStudents(students);
		tableModel.fireTableDataChanged();
		updateInfoLabel();
	}
}
