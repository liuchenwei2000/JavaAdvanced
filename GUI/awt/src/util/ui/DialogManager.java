/**
 * 
 */
package util.ui;

import javax.swing.JOptionPane;

/**
 * 对话框管理器类
 * 
 * @author 刘晨伟
 *
 * 创建时间：2008-10-9
 */
public class DialogManager {

	private DialogManager() {
		// do nothing and no instance
	}

	/**
	 * 显示错误信息对话框
	 * 
	 * @param message
	 *            信息
	 */
	public static void showErrorDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * 显示普通信息对话框
	 * 
	 * @param message
	 *            信息
	 * @param title
	 *            标题
	 */
	public static void showMessageDialog(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title,
				JOptionPane.INFORMATION_MESSAGE);
	}
}
