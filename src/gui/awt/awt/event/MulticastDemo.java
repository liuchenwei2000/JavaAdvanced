package awt.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import util.Displayer;

/**
 * ��㴫��(multicast)��ʾ
 * <p>
 * ���е�AWT�¼�Դ��֧�ּ������Ķ�㴫��ģ�ͣ�����ζ��ͬһ���¼����Է��͸��������������
 * ������ڶ෽���ܶ�һ���¼�����Ȥ����ô����Ҫʹ�ö�㴫�͡�
 * �������������ӵ�һ���¼�Դ�оͿ���ʹ������ע��ļ��������ܹ��л�����Ӧ����¼���
 * <strong>JDK���ܱ�֤һ�������¼�Դע���һ������������¼���˳��</strong>
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-6-24
 */
public class MulticastDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("Multicast Demo", new MulticastPanel());
	}
}

class MulticastPanel extends JPanel {

	private static final long serialVersionUID = 2731950486987918977L;

	public MulticastPanel() {
		JButton newButton = new JButton("New");
		add(newButton);

		final JButton closeAllButton = new JButton("Close all");
		add(closeAllButton);

		newButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				new BlankFrame(closeAllButton).setVisible(true);
			}
		});
	}
}

class BlankFrame extends JFrame {

	private static final long serialVersionUID = -1944465766151986080L;
	
	public static final int DEFAULT_WIDTH = 200;
	public static final int DEFAULT_HEIGHT = 150;
	public static final int SPACING = 40;

	private static int counter = 0;

	private ActionListener closeListener;

	public BlankFrame(final JButton closeButton) {
		counter++;
		setTitle("Frame " + counter);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLocation(SPACING * counter, SPACING * counter);
		// ÿ����һ���µ�BlankFrame����ΪcloseButton���һ��������
		// ��closeButton�����ʱ��ÿһ��BlankFrame������Ӧ��
		// ���Լ��ļ�������closeButton�ļ������б����Ƴ�
		// Ȼ�������Լ�������
		closeListener = new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				closeButton.removeActionListener(closeListener);
				dispose();
			}
		};
		closeButton.addActionListener(closeListener);
	}
}