package swing;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import util.Displayer;

/**
 * �й۶�̬������ʾ��
 * <p>
 * <li>1���ȵ���UIManager.setLookAndFeel()�������øйۡ�
 * <li>2���ٵ���SwingUtilities.updateComponentTreeUI()����ˢ�����������
 * 
 * @author ����ΰ
 *
 * �������ڣ�2009-5-27
 */
public class LookAndFeelDynamicSettingDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("��̬�й�������ʾ",
				new LookAndFeelDynamicSettingPanel());
	}
}

class LookAndFeelDynamicSettingPanel extends JPanel {

	private static final long serialVersionUID = 4788452073977812179L;

	public LookAndFeelDynamicSettingPanel() {
		addButtons();
		setPreferredSize(new Dimension(300, 200));
	}

	private void addButtons() {
		// ���ذ�װ�����ий���
		UIManager.LookAndFeelInfo[] infos = UIManager
				.getInstalledLookAndFeels();
		for (UIManager.LookAndFeelInfo info : infos) {
			this.add(makeButton(info.getName(), info.getClassName()));
		}
	}

	/**
	 * ���ݸй��ഴ����ť
	 * <p>
	 * ��ť�����֮����Զ�ʹ�øй������õ�ǰ�����
	 * 
	 * @param name
	 *            ��ť����
	 * @param plafName
	 *            �й�ȫ����
	 */
	private JButton makeButton(String name, final String plafName) {
		JButton button = new JButton(name);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					// ���øй�
					UIManager.setLookAndFeel(plafName);
					// ˢ��ȫ�������
					SwingUtilities
							.updateComponentTreeUI(LookAndFeelDynamicSettingPanel.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		return button;
	}
}