/**
 * 
 */
package swing;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import util.Displayer;

/**
 * ѡ�����(Look and Feel)��ʾ��
 * <p>
 * "�ɲ�����"(plaf��Pluggable Look and Feel)ʹ�����ܹ�ģ�²�ͬ�Ĳ���ϵͳ����ۣ�
 * �������Եõ���������Ĺ��ܣ������ڳ��������ڼ䶯̬�ı�������ۡ�</br>
 * ������ͨ��ֻ�������¶�����ѡ��һ����</br>
 * Ҫôѡ��"��ƽ̨"�����(��Swing��"����"���)��Ҫôѡ�����ǰ����ϵͳ����ۡ�</br>
 * ����Java���������ͺ�����Ϊ��ϵͳר����Ƶ�(���ȷ����õ�ѡ�񣬶��ҿ��Ա������û�)��
 * ʵ����������Ϊ�Ĵ��붼�ܼ򵥣�����Ҫȷ���ڴ����κο������֮ǰ�ȵ�����Щ���롣
 * ������Ϊ����Ǹ��ݵ�ǰ����۶������ģ����Ҵ���֮��Ͳ���ı䡣
 * ���Ҫʹ�ÿ�ƽ̨��"����"���(����Swing���������)��ʲô������������Ϊ����ȱʡ��ۡ�</br>
 * ����Ҫ��ʹ�õ�ǰ����ϵͳ����ۣ��������д��뼴��:</br>
 * <p>
 * try {
 * UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
 * } catch(Exception e) 
 * { e.printStackTrace();}
 * <p>
 * ��catch�Ӿ���ʲôҲ����������Ϊ��ȱʡ����£�������ô���ʧ���ˣ�UIManager�����óɿ�ƽ̨����ۡ�
 * <p>
 * һ�����Щ���������main()��ʼ�ĵط�������ҲҪ������κ����֮ǰ��
 * 
 * @author ����ΰ
 *
 * �������ڣ�2009-5-21
 */
public class LookAndFeelDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Object[] selections = { "system", "motif", "cross" };
		// ��ʾһ��Ҫ���û�ѡ��String�ĶԻ���
		Object selection = JOptionPane.showInputDialog(null, "Choose one",
				"Look and Feel", JOptionPane.INFORMATION_MESSAGE, null,
				selections, selections[0]);
		String value = selection == null ? "system" : selection.toString();
		try {
			if (value.equals("cross")) {
				UIManager.setLookAndFeel(UIManager
						.getCrossPlatformLookAndFeelClassName());
			} else if (value.equals("system")) {
				UIManager.setLookAndFeel(UIManager
						.getSystemLookAndFeelClassName());
			} else if (value.equals("motif")) {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ȷ�����κ��������֮ǰ����LookAndFeel
		Displayer.createAndShowGUI("LookAndFeel Demo", new LookAndFeelPanel());
	}
}

class LookAndFeelPanel extends JPanel {

	private static final long serialVersionUID = -5915555538675728354L;

	private String[] choices = { "one", "two", "three", "four" };

	private Component[] comps = { new JButton("JButton"),
			new JTextField("JTextField"), new JLabel("JLabel"),
			new JCheckBox("JCheckBox"), new JRadioButton("JRadioButton"),
			new JComboBox(choices), new JList(choices) };

	public LookAndFeelPanel() {
		for (int i = 0; i < comps.length; i++) {
			this.add(comps[i]);
		}
	}
}