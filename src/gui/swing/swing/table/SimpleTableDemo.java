/**
 * 
 */
package swing.table;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import util.Displayer;

/**
 * 通过对象数组创建的JTable 
 * <p>
 * 常用的构造方法： </br>
 * <li>JTable(int numRows, int numColumns) </br>
 * 使用 DefaultTableModel 构造具有空单元格的 numRows 行和 numColumns 列的 
 * <li>JTable JTable(Object[][] rowData, Object[] columnNames) </br>
 * 构造 JTable，用来显示二维数组 rowData 中的值，其列名称为 columnNames 
 * <li>JTable(TableModel dm) </br>
 * 构造 JTable，使用 dm 作为数据模型、默认的列模型和默认的选择模型对其进行初始化 
 * <li>JTable(Vector rowData, Vector columnNames) </br>
 * 构造 JTable，用来显示Vector (rowData) 中的值，其列名称为 columnNames 
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2007-8-17
 */
public class SimpleTableDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("Simple Table Demo", new SimpleTablePanel());
	}
}

class SimpleTablePanel extends JPanel implements ActionListener {	
	
	private static final long serialVersionUID = -1138257311163982620L;
	
	private static Object[][] data = { { "Tom", "M", 20, 03311110, true },
			{ "Ady", "M", 21, 01331110, false },
			{ "Lily", "F", 22, 103311111, true } };
	private static String[] column = { "name", "sex", "age", "id", "alive?" };

	private JTable table;
	private JButton buttonClear;
	private JButton buttonResize;

	public SimpleTablePanel() {
		setLayout(null);
		setPreferredSize(new Dimension(270,400));
		
		table = new JTable(data, column);
		//当调整表的大小时，设置表的自动调整模式
		//常量AUTO_RESIZE_OFF：不自动调整列的宽度而是使用滚动条
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(0, 0, 266, 352);
		add(scroll);

		buttonClear = new JButton();
		buttonClear.setBounds(0, 352, 130, 23);
		buttonClear.setText("clear");
		buttonClear.addActionListener(this);
		add(buttonClear);

		buttonResize = new JButton();
		buttonResize.setText("resize");
		buttonResize.addActionListener(this);
		buttonResize.setBounds(136, 352, 130, 23);
		add(buttonResize);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("clear")) {
			// 取消选中所有已选定的行和列
			table.clearSelection();
		} else if (e.getActionCommand().equals("resize")){
			// 使得第一列再拓宽10
			TableColumn tc = table.getColumnModel().getColumn(1);
			tc.setPreferredWidth(tc.getWidth() + 10);
		}	
	}
}