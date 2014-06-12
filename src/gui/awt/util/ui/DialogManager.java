/**
 * 
 */
package util.ui;

import javax.swing.JOptionPane;

/**
 * �Ի����������
 * 
 * @author ����ΰ
 *
 * ����ʱ�䣺2008-10-9
 */
public class DialogManager {

	private DialogManager() {
		// do nothing and no instance
	}

	/**
	 * ��ʾ������Ϣ�Ի���
	 * 
	 * @param message
	 *            ��Ϣ
	 */
	public static void showErrorDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * ��ʾ��ͨ��Ϣ�Ի���
	 * 
	 * @param message
	 *            ��Ϣ
	 * @param title
	 *            ����
	 */
	public static void showMessageDialog(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title,
				JOptionPane.INFORMATION_MESSAGE);
	}
}