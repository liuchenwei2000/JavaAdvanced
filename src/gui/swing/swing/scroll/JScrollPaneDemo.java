/**
 * 
 */
package swing.scroll;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import util.Displayer;
import util.ui.BorderFactory;

/**
 * JScrollPane��ʾ��
 * <p>
 * JScrollPaneȥִ�й������ƣ�����������ȥ��������Щ�������ǿ��õģ�</br>
 * ˮƽ�ġ���ֱ�ġ����߶��û������߶����á�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-5-17
 */
public class JScrollPaneDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JScrollPane Demo", new JScrollPanePanel());
	}
}

class JScrollPanePanel extends JPanel {

	private static final long serialVersionUID = 3477024713392480248L;

	private JLabel label1 = new JLabel("��:�����ã���:������");
	private JLabel label2 = new JLabel("��:���ã���:������");
	private JLabel label3 = new JLabel("��:�����ã���:����");
	private JLabel label4 = new JLabel("��:���ã���:����");
	private JLabel label5 = new JLabel("��:��Ҫʱ���ã���:��Ҫʱ����");
	
	private JTextArea textArea1 = new JTextArea("textArea1", 5, 5);
	private JTextArea textArea2 = new JTextArea("textArea2", 5, 5);
	private JTextArea textArea3 = new JTextArea("textArea3", 5, 5);
	private JTextArea textArea4 = new JTextArea("textArea4", 5, 5);
	private JTextArea textArea5 = new JTextArea("textArea5", 5, 5);
	
	// ����һ������ָ�����������ԵĿ�(�޴��ڵ���ͼ)JScrollPane
	// JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED ��Ҫʱ��ʾ������
    // JScrollPane.HORIZONTAL_SCROLLBAR_NEVER �Ӳ���ʾ������
    // JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS һֱ��ʾ������
	private JScrollPane scrollPane1 = new JScrollPane(textArea1,
			JScrollPane.VERTICAL_SCROLLBAR_NEVER,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JScrollPane scrollPane2 = new JScrollPane(textArea2,
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JScrollPane scrollPane3 = new JScrollPane(textArea3,
			JScrollPane.VERTICAL_SCROLLBAR_NEVER,
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	private JScrollPane scrollPane4 = new JScrollPane(textArea4,
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	private JScrollPane scrollPane5 = new JScrollPane(textArea5,
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	public JScrollPanePanel() {
		setLayout(new FlowLayout());
		add(makeTextAreaPanel(label1,scrollPane1));
		add(makeTextAreaPanel(label2,scrollPane2));
		add(makeTextAreaPanel(label3,scrollPane3));
		add(makeTextAreaPanel(label4,scrollPane4));
		add(makeTextAreaPanel(label5,scrollPane5));
	}

	private static JPanel makeTextAreaPanel(JLabel label,JScrollPane scroll){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		scroll.setBorder(BorderFactory.createLineBorder());
		panel.add(label,BorderLayout.NORTH);
		panel.add(scroll,BorderLayout.CENTER);
		return panel;
	}
}