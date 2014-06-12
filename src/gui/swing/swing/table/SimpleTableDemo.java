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
 * ͨ���������鴴����JTable 
 * <p>
 * ���õĹ��췽���� </br>
 * <li>JTable(int numRows, int numColumns) </br>
 * ʹ�� DefaultTableModel ������пյ�Ԫ��� numRows �к� numColumns �е� 
 * <li>JTable JTable(Object[][] rowData, Object[] columnNames) </br>
 * ���� JTable��������ʾ��ά���� rowData �е�ֵ����������Ϊ columnNames 
 * <li>JTable(TableModel dm) </br>
 * ���� JTable��ʹ�� dm ��Ϊ����ģ�͡�Ĭ�ϵ���ģ�ͺ�Ĭ�ϵ�ѡ��ģ�Ͷ�����г�ʼ�� 
 * <li>JTable(Vector rowData, Vector columnNames) </br>
 * ���� JTable��������ʾVector (rowData) �е�ֵ����������Ϊ columnNames 
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2007-8-17
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
		//��������Ĵ�Сʱ�����ñ���Զ�����ģʽ
		//����AUTO_RESIZE_OFF�����Զ������еĿ�ȶ���ʹ�ù�����
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
			// ȡ��ѡ��������ѡ�����к���
			table.clearSelection();
		} else if (e.getActionCommand().equals("resize")){
			// ʹ�õ�һ�����ؿ�10
			TableColumn tc = table.getColumnModel().getColumn(1);
			tc.setPreferredWidth(tc.getWidth() + 10);
		}	
	}
}