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
 * JTable演示类
 *
 * @author 刘晨伟
 *
 * 创建日期：2009-5-21
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
	// 构造表格的数据
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
	 * 自定义TableModel
	 * <p>
	 * JTable用来控制数据如何显示，而TableModel则控制数据本身。
	 * 所以要创建一个JTable对象，就要先创建一个TableModel。
	 * 虽然可以完整实现TableModel接口，不过从AbstractTableModel继承则更容易。
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
			// 表格数据变更事件通知
			fireTableDataChanged();
		}

		public boolean isCellEditable(int row, int col) {
			return true;
		}
	}
	
	/**
	 * 监听TableModel中更改的对象的类
	 */
	class DataModelListener implements TableModelListener {
		// 单元格、行或列的哪些具体范围发生了更改
		public void tableChanged(TableModelEvent e) {
			getArea().setText("");
			// 在JTextArea中格式化打印当前表格数据
			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[0].length; j++) {
					getArea().append(data[i][j] + " ");
				}
				getArea().append("\n");
			}
		}
	}
}
