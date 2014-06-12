/**
 * 
 */
package swing.list;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import util.Displayer;

/**
 * ListModel��ʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2007-8-29
 */
public class ListModelDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("List Model Demo", new ListModelPanel());
	}
}

class ListModelPanel extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5546410946145193007L;

	private DefaultListModel model = new DefaultListModel();
	private JList list = new JList(model);
	private JButton deleteButton = new JButton("delete");
	private JButton addButton = new JButton("add");
	private JTextField text = new JTextField("show selection!");
	
	public ListModelPanel(){
		setLayout(new BorderLayout());
		// ��ָ��ֵ��ӵ���ģ�͵�ĩβ
		model.addElement("One");
		model.addElement("Two");
		model.addElement("Three");
		model.addElement("Four");
		model.addElement("Five");

		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setVisibleRowCount(3);
		
		JScrollPane scroll = new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(150, 80));
		add(scroll, BorderLayout.CENTER);
		
		deleteButton.addActionListener(this);	
		add(deleteButton, BorderLayout.EAST);	
		
		addButton.addActionListener(this);	
		add(addButton, BorderLayout.WEST);
		
		text.setBounds(0, 0, 150, 20);
		text.setEditable(false);
		add(text, BorderLayout.NORTH);		
	}

	/**
	 * ��ť������¼�����
	 */
	public void actionPerformed(ActionEvent e) {
		// ʵ���Ϸ��صľ��� ��Ա���� model
		DefaultListModel listModel = (DefaultListModel) list.getModel();
		// delete
		if (e.getSource().equals(deleteButton)) {
			// ��ǰѡ���������
			int index = list.getSelectedIndex();
			text.setText("delete " + listModel.getElementAt(index));
			// �������������ͬ�Ĳ���
			// text.setText("delete" + getList().getSelectedValue());
			// ���б�ģ����ɾ�����ѡ��(�б�����ģ�����»���)
			listModel.remove(index);
			// ���б����Ƴ���������ĵ�һ��(������С)��ƥ����
			// listModel.removeElement(list.getSelectedValue());
			// ����Ĭ��ѡ����
			if (index < listModel.getSize()) {
				// ɾ���Ĳ������һ��ѡ��������ѡ��
				list.setSelectedIndex(index);
			} else {
				// ɾ���������һ��ѡ��������ѡ��
				list.setSelectedIndex(index - 1);
			}
			// ȫ��ɾ�����֮��ť�û�
			if (listModel.getSize() == 0) {
				deleteButton.setEnabled(false);
			}
		} 
		// add
		else if (e.getSource().equals(addButton)) {
			// ���б�ģ��������һ��ѡ��
			text.setText("add " + "new" + listModel.getSize());
			listModel.add(listModel.getSize(), "new" + listModel.getSize());
			list.setSelectedIndex(listModel.getSize() - 1);
			// ���б�ģ�͵�ĩ�����һ���µĶ���
			// listModel.addElement("new" + listModel.getSize());
			// û��ѡ����������ɾ����ť����
			if (listModel.getSize() > 0) {
				deleteButton.setEnabled(true);
			}
		}
	}
}