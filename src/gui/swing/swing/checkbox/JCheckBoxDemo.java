/**
 * 
 */
package swing.checkbox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import util.Displayer;

/**
 * JCheckBox��ѡ����ʾ��
 * <p>
 * ��ѡ����һ�����Ա�ѡ����ȡ��ѡ�����������״̬��ʾ���û���
 * ���ṩһ�����Խ��п�/��ѡ��ķ�ʽ����������һ��С�����һ����ǩ(����ѡ����ı�)��
 * ���������ͨ������һ��"x"���(���������ܱ�����ѡ�еı��)����Ϊ�գ���ȡ���ڼ����Ƿ�ѡ�С�
 * ͨ����ʹ�ý��ܱ�ǩ��Ϊ�����Ĺ�����������JCheckBox�����Ի�ȡ������״̬��Ҳ���Ի�ȡ���������ǩ��
 * ����������JCheckBox�����Ѿ�����֮��ı��ǩ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-5-17
 */
public class JCheckBoxDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JCheckBox Demo", new JCheckBoxPanel());
	}
}

class JCheckBoxPanel extends JPanel {

	private static final long serialVersionUID = -5349217288538114962L;

	private JTextArea textArea = new JTextArea(6, 20);
	// ʹ��ָ���ı�������ѡ��
	private JCheckBox checkBox1 = new JCheckBox("CheckBox 1");
	private JCheckBox checkBox2 = new JCheckBox("CheckBox 2");
	private JCheckBox checkBox3 = new JCheckBox("CheckBox 3");

	{
		textArea.setEditable(false);
		checkBox1.addActionListener(getCheckBoxListener());
		checkBox2.addActionListener(getCheckBoxListener());
		checkBox3.addActionListener(getCheckBoxListener());
	}

	public JCheckBoxPanel() {
		add(new JScrollPane(textArea));
		add(checkBox1);
		add(checkBox2);
		add(checkBox3);
	}

	private void printCheckBox(JCheckBox checkBox) {
		// ��ѡ���Ƿ�ѡ��
		if (checkBox.isSelected()) {
			// �������ı�׷�ӵ��ĵ���β(��ͬ���ڽ�����ʾ����)
			textArea.append(checkBox.getText() + " is selected.\n");
		} else {
			textArea.append(checkBox.getText() + " clears selection.\n");
		}
	}

	private ActionListener getCheckBoxListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printCheckBox((JCheckBox) e.getSource());
			}
		};
	}
}