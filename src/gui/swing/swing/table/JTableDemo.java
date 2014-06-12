/**
 * 
 */
package swing.table;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import util.Displayer;

/**
 * JTable��ʾ��
 *
 * @author ����ΰ
 *
 * �������ڣ�2009-5-21
 */
public class JTableDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JTable Demo", new JTablePanel());
	}
}

class JTablePanel extends JPanel {

	private static final long serialVersionUID = -3699963400634725602L;
	// �����������
	private Object[][] data = { 
			{ "one", "two", "three", "four" },
			{ "five", "six", "seven", "eight" },
			{ "nine", "ten", "eleven", "twelve" }, };
	
	private JTable table;
	private JTextArea area;

	public JTablePanel() {
		setLayout(new BorderLayout());
		add(new JScrollPane(getTable()), BorderLayout.CENTER);
		add(getArea(), BorderLayout.SOUTH);
	}

	private JTable getTable() {
		if (table == null) {
			table = new JTable(new DataModel());
		}
		return table;
	}
	
	private JTextArea getArea() {
		if (area == null) {
			area = new JTextArea(4, 20);
		}
		return area;
	}
	
	/**
	 * �Զ���TableModel
	 * <p>
	 * JTable�����������������ʾ����TableModel��������ݱ���
	 * ����Ҫ����һ��JTable���󣬾�Ҫ�ȴ���һ��TableModel��
	 * ��Ȼ��������ʵ��TableModel�ӿڣ�������AbstractTableModel�̳�������ס�
	 */
	class DataModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;

		public DataModel() {
			addTableModelListener(new DataModelListener());
		}

		public int getColumnCount() {
			return data[0].length;
		}

		public int getRowCount() {
			return data.length;
		}

		public Object getValueAt(int row, int col) {
			return data[row][col];
		}

		public void setValueAt(Object val, int row, int col) {
			data[row][col] = val;
			// ������ݱ���¼�֪ͨ
			fireTableDataChanged();
		}

		public boolean isCellEditable(int row, int col) {
			return true;
		}
	}
	
	/**
	 * ����TableModel�и��ĵĶ������
	 */
	class DataModelListener implements TableModelListener {
		// ��Ԫ���л��е���Щ���巶Χ�����˸���
		public void tableChanged(TableModelEvent e) {
			getArea().setText("");
			// ��JTextArea�и�ʽ����ӡ��ǰ�������
			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[0].length; j++) {
					getArea().append(data[i][j] + " ");
				}
				getArea().append("\n");
			}
		}
	}
}