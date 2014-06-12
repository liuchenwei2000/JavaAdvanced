/**
 * 
 */
package swing.table;

import javax.swing.table.AbstractTableModel;

/**
 * Tableģ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2007-8-17
 */
public class MyTableModel extends AbstractTableModel {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8301130997521360463L;
	
	private Object[][] data = { { "Tom", "M", 19820910, 03311110, false },
			{ "Ady", "M", 19821110, 01331110, false },
			{ "Ady", "M", 19821205, 01331110, false },
			{ "Lily", "F", 19820901, 103311111, true } };
	private String[] column = { "name", "sex", "birth", "id", "alive?" };

	public MyTableModel() {
		super();
	}

	public int getColumnCount() {
		return column.length;
	}

	public int getRowCount() {
		return data.length;
	}

	public Class<?> getColumnClass(int columnIndex) {
		return getValueAt(0, columnIndex).getClass();
	}

	/**
	 * ���ñ��Ԫ�Ƿ���Ա༭
	 * 
	 * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
	 */
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex < 1)
			return false;
		return true;
	}

	public String getColumnName(int column) {
		return this.column[column];
	}
	
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		data[rowIndex][columnIndex] = value;
		// ֪ͨ�������������Ѹ���ָ��λ�õĵ�Ԫ
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return this.data[rowIndex][columnIndex];
	}
}