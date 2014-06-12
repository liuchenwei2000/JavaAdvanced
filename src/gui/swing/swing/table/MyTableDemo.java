/**
 * 
 */
package swing.table;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import util.Displayer;

/**
 * ͨ��TableModel������JTable
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2007-8-17
 */
public class MyTableDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("My Table Demo", new MyTablePanel());
	}
}

class MyTablePanel extends JPanel {
	
	private static final long serialVersionUID = -3643047362414274741L;

	private JTable table;
	
	public MyTablePanel() {
		super(new GridLayout(1, 0));
		setLayout(new BorderLayout());
		table = new JTable(new MyTableModel());
		// �����еı༭��
		TableColumn sexColumn = table.getColumnModel().getColumn(1);
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("M");
		comboBox.addItem("F");
		sexColumn.setCellEditor(new DefaultCellEditor(comboBox));
		// �����е���Ⱦ��
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		// ����Ⱦ��������ͷ��ʾ��
		renderer.setToolTipText("Click for combo box");
		sexColumn.setCellRenderer(renderer);
		table.getTableHeader().setToolTipText("Test");
		// �����û��Ƿ�����϶���ͷ���������������
		table.getTableHeader().setReorderingAllowed(false);
		// �����и�
		// table.setRowHeight(1, 30);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(0, 0, 250, 327);
		// �����п�
		TableColumn tc = null;
		for (int i = 0; i < table.getColumnCount(); i++) {
			tc = table.getColumnModel().getColumn(i);
			tc.setPreferredWidth(150);
		}
		add(scroll, BorderLayout.NORTH);
	}
}