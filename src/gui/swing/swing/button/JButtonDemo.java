/**
 * 
 */
package swing.button;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicArrowButton;

import util.Displayer;

/**
 * ��ť��ʾ
 * <p>
 * Swing�ṩ�˺ܶ����͵İ�ť��������ѡ�򡢵�ѡ��ť���˵���ȡ�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-5-16
 */
public class JButtonDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JButton Demo", new JButtonPanel());
	}
}

class JButtonPanel extends JPanel {

	private static final long serialVersionUID = 5961288201474212404L;

	// ��������ͨ��ť
	private JButton jb = new JButton("JButton");
	// ��������״̬(���»��ߵ���)�İ�ť��JToggleButton������JCheckBox��JRadioButton
	private JToggleButton jt = new JToggleButton("JToggleButton");
	// ��ѡ����һ�����Ա�ѡ����ȡ��ѡ�����������״̬��ʾ���û�
	private JCheckBox jc = new JCheckBox("JButton");
	// ��ѡ��ť���˰�ť��ɱ�ѡ���ȡ��ѡ�񣬲���Ϊ�û���ʾ��״̬
	private JRadioButton jr = new JRadioButton("JButton");

	// JButton���������Ƴ�ĳ��������������ż�ͷ
	private BasicArrowButton up = new BasicArrowButton(BasicArrowButton.NORTH);
	private BasicArrowButton down = new BasicArrowButton(BasicArrowButton.SOUTH);
	private BasicArrowButton right = new BasicArrowButton(BasicArrowButton.EAST);
	private BasicArrowButton left = new BasicArrowButton(BasicArrowButton.WEST);

	public JButtonPanel() {
		setLayout(new FlowLayout());
		add(jb);
		add(jt);
		add(jc);
		add(jr);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder("Directions"));
		panel.add(up);
		panel.add(down);
		panel.add(right);
		panel.add(left);

		add(panel);
	}
}