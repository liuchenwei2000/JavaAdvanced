/**
 * 
 */
package swing.tabbedpane;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import util.Displayer;

/**
 * JTabbedPane��ʾ��
 * <p>
 * �����û�ͨ���������и��������(��)ͼ���ѡ�����һ�����֮������л���
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-5-18
 */
public class JTabbedPaneDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JTabbedPane Demo", new JTabbedPanePanel());
	}
}

class JTabbedPanePanel extends JPanel {

	private static final long serialVersionUID = 7603299123532523822L;

	private static final String[] NAMES = { "One", "Two", "Three", "Four",
			"Five", "Six", "Seven" };

	private JTabbedPane tabs = new JTabbedPane();
	private JTextField txt = new JTextField(20);

	public JTabbedPanePanel() {
		setLayout(new BorderLayout());
		for (int i = 0; i < NAMES.length; i++) {
			// ���һ����title��ʾ����û��ͼ������
			tabs.addTab(NAMES[i], makeTabPanel(" in TabbedPane " + i));
		}
		// ��һ��ChangeListener��ӵ���ѡ�������
		tabs.addChangeListener(new ChangeListener() {
			// ѡ�ѡ��ı�ʱ���¼�����
			public void stateChanged(ChangeEvent e) {
				txt.setText("Tab selected: " + tabs.getSelectedIndex());
			}
		});
		txt.setEditable(false);
		add(txt, BorderLayout.SOUTH);
		add(tabs, BorderLayout.CENTER);
	}

	/**
	 * �����ƶ����ƴ���һ��ѡ�ҳǩ
	 */
	private static JPanel makeTabPanel(String name) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JLabel label = new JLabel("JLabel " + name, JLabel.CENTER);
		label.setBorder(new LineBorder(Color.BLACK));
		panel.add(label);
		return panel;
	}
}