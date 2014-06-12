/**
 * 
 */
package swing.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import util.Displayer;

/**
 * JPopupMenu演示类
 * <p>
 * JPopupMenu弹出式菜单是一个可弹出并显示一系列选项的小窗口 。
 * 用于当用户选择菜单项并激活它时显示的"右拉式(pull-right)"菜单，
 * 还可以在想让菜单显示的任何其他位置使用，例如当用户在指定区域中右击时。
 * <p>
 * 本例是JPopupMenu如何使用的典型用例。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-5-19
 */
public class JPopupMenuDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JPopupMenu Demo", new JPopupMenuPanel());
	}
}

class JPopupMenuPanel extends JPanel {

	private static final long serialVersionUID = 4309517809979556356L;

	private JPopupMenu popupMenu = new JPopupMenu();
	private JTextField text = new JTextField(15);

	public JPopupMenuPanel() {
		initPopupMenu();
		// 设置组件右键点击时的JPopupMenu
		setComponentPopupMenu(popupMenu);
		// 通过监听方式为JTextField添加右键点击的JPopupMenu
		text.addMouseListener(new PopupListener());
		text.setText("Right  click  your  mouse.");
		add(text);
	}

	/**
	 * 初始化JPopupMenu
	 */
	private void initPopupMenu() {
		ActionListener listener = getListener();
		JMenuItem item1 = new JMenuItem("First");
		item1.addActionListener(listener);
		JMenuItem item2 = new JMenuItem("Second");
		item2.addActionListener(listener);
		// 将指定菜单项追加到此菜单的末尾
		popupMenu.add(item1);
		// 将新分隔符追加到菜单的末尾
		popupMenu.addSeparator();
		popupMenu.add(item2);
	}

	private class PopupListener extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			maybeShowPopup(e);
		}

		public void mouseReleased(MouseEvent e) {
			maybeShowPopup(e);
		}

		private void maybeShowPopup(MouseEvent e) {
			// 返回此鼠标事件是否为该平台的弹出菜单触发事件
			if (e.isPopupTrigger()){
				// 在组件调用者的坐标空间中的位置 X、Y 显示弹出菜单
				popupMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}

	private ActionListener getListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text.setText(((JMenuItem) e.getSource()).getText());
			}
		};
	}
}