package lab.view.table;

import java.awt.Dimension;
import java.awt.event.*;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.*;
import lab.model.Passes;
import lab.model.Student;

public class RecordsTable {
	
	private final int SCROLL_PANE_OFFSET_SIZE = 6;
	private final int ROWS_OFFSET_SIZE = 4;
	private final int TABLE_SIZE_X = 1000;
	private final int COMPONENT_SIZE_X = 100;
	private final int COMPONENT_SIZE_Y = 20;

	private RecordsTableModel tableModel;
	private JTable table;
	private JLabel infoLabel = new JLabel("");
	private int rowsPerPage = 10;
	private int currentPage = 1;
	private int allPages = 1;
	
	private JPanel component;
	
	@SuppressWarnings("serial")
	public RecordsTable(Map<Student, Passes> records) {
		tableModel = new RecordsTableModel(records);
		table = new JTable(tableModel) {
	    	protected JTableHeader createDefaultTableHeader() {
	    		return new GroupableTableHeader(columnModel);
	    	}
	    };
	    addSubHeader();
	    component = createPaginationPanel();
	}
	
	public JPanel getComponent() {
		return component;
	}
	
	public void update(Map<Student, Passes> records) {
		tableModel.setRecords(records);
		tableModel.fireTableDataChanged();
		updateInfoLabel();
	}
	
	private void addSubHeader() {
	    TableColumnModel tableColumnModel = table.getColumnModel();
	    ColumnGroup passesColumnGroup = new ColumnGroup("Passes");
	    passesColumnGroup.add(tableColumnModel.getColumn(4));
	    passesColumnGroup.add(tableColumnModel.getColumn(5));
	    passesColumnGroup.add(tableColumnModel.getColumn(6));
	    passesColumnGroup.add(tableColumnModel.getColumn(7));
	    GroupableTableHeader header = (GroupableTableHeader)table.getTableHeader();
	    header.addColumnGroup(passesColumnGroup);
	}
	
	private JPanel createPaginationPanel() {
		JPanel panel = new JPanel();
        JPanel navigation = new JPanel();
        
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        navigation.setLayout(new BoxLayout(navigation, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(table.getPreferredSize().width, table.getRowHeight() * rowsPerPage + SCROLL_PANE_OFFSET_SIZE));
		
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
        textField.setMaximumSize(new Dimension(COMPONENT_SIZE_X, COMPONENT_SIZE_Y));
        JButton rowButton = new JButton("Change rows per page");
        rowButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText().trim();
                if (text.isEmpty()) {
            		JOptionPane.showMessageDialog(new JFrame(), "Empty field error!", "Error", JOptionPane.ERROR_MESSAGE);
            	} else {
                	if (table.getRowCount() >= Integer.parseInt(text)) {
                		rowsPerPage = Integer.parseInt(text);
                		panel.setMaximumSize(new Dimension(TABLE_SIZE_X, table.getRowHeight() * (rowsPerPage + ROWS_OFFSET_SIZE) + ROWS_OFFSET_SIZE));
                		updateInfoLabel();
                	} else {
                		JOptionPane.showMessageDialog(new JFrame(), "Too few rows to change rows per page", "Error", JOptionPane.ERROR_MESSAGE);
                	}
            	}
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
        panel.setMaximumSize(new Dimension(TABLE_SIZE_X, table.getRowHeight() * (rowsPerPage + ROWS_OFFSET_SIZE) + ROWS_OFFSET_SIZE));
        return panel;
	}
	
	private void updateInfoLabel() {
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
}
