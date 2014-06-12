/**
 * 
 */
package swing.list;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import util.Displayer;

/**
 * JList��ʾ��
 * <p>
 * ����������û����б���ѡ��һ����������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2007-8-29
 */
public class JListDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JList Demo", new JListPanel());
	}
}

class JListPanel extends JPanel implements ListSelectionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4203723178063162218L;

	private static final String[] VALUES = { "Andy", "Sany", "Tom", "Peter",
			"Mosie", "Mosie2", "Mosie3" };
	// ���������Ƕ������顢������ListModel
	private JList list = new JList(VALUES);
	private JTextField text = new JTextField("�б�ѡ����ʾ��");

	public JListPanel() {
		setLayout(new BorderLayout());
		/*
		 * ����������ѡ��(SINGLE_SELECTION)���Ƕ���ѡ��(SINGLE_INTERVAL_SELECTION)Ĭ���Ƕ���ѡ��
		 * ��ס"Ctrl"���������ڶ����Ŀ�ϵ�������ôԭ�ȱ�ѡ�е���Ŀ�Ծɱ���ѡ��״̬�����Կ���ѡ����������Ŀ��
		 * ���ѡ����ĳ����Ŀ���κΰ�ס"Shift"����������һ����Ŀ����ô��������Ŀ֮���������Ŀ������ѡ�С�
		 * Ҫ��ѡ�е���Ŀ����ȥ��һ�������԰�ס"Ctrl"���ڴ���Ŀ�ϵ�����
		 */
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		/* 
		 * ���ò����б�Ԫ�ķ�ʽ
		 * VERTICAL ָʾĬ�ϲ��֣�һ�е�Ԫ
		 * HORIZONTAL_WRAP ��Ԫ���Ⱥ�������򲼾�
		 * VERTICAL_WRAP ��Ԫ�����������򲼾�
		 */
		list.setLayoutOrientation(JList.VERTICAL);
		// ���ò�ʹ�ù������������б�����ʾ����ѡ����
		// ��һ���������JViewport����(�����)ȷ��
		// ������JScrollPane��viewport������������������û��ʵ������
		list.setVisibleRowCount(1);
		// ʵ��ListSelectionListener�ӿ�����Ӧѡ����仯���¼�
		list.addListSelectionListener(this);
		
		JScrollPane scroll = new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(250, 80));
		add(scroll, BorderLayout.NORTH);

		text.setEditable(false);
		add(text, BorderLayout.SOUTH);
	}

	/**
	 * Listѡ����仯���¼�����
	 */
	public void valueChanged(ListSelectionEvent e) {
		/*
		 * ���ǵ�ѡ����getSelectedValue()���ض���
		 * ���Ƕ�ѡ����getSelectedValues()���ض������顣
		 */
		text.setText(list.getSelectedValue().toString());
	}
}