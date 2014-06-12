/**
 * 
 */
package swing.hotkey;

import java.awt.BorderLayout;
import java.awt.event.*;

import javax.swing.*;

import swing.frame.BasicFrame;
import util.Displayer;

/**
 * AcceleratorKey演示
 * <p>
 * AcceleratorKey(加速键)：就是通过键盘的几个键组合，触发一个Action的执行。
 * <p>
 * 和MnemonicKey的主要区别：</br>
 * <li>1，AcceleratorKey只有菜单才有，MnemonicKey可以应用在菜单、按钮和标签等控件上。
 * <li>2，MnemonicKey用来从当前打开的菜单中选择一个子菜单或者菜单项，
 * 而AcceleratorKey是在不打开菜单的情况下选择菜单项的快捷键。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2010-12-20
 */
public class AcceleratorKeyDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.display(new AcceleratorKeyFrame(), 200, 100);
	}
}

class AcceleratorKeyFrame extends BasicFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -81876610601190356L;

	private JButton button;
	private JTextField textField;

	public AcceleratorKeyFrame() {
		super();
		initUI();
		initMenus();
	}

	private void initUI() {
		getContentPane().add(getTextField(), BorderLayout.CENTER);
		getContentPane().add(getButton(), BorderLayout.EAST);
	}

	private JButton getButton() {
		if (button == null) {
			ButtonAction buttonAction = new ButtonAction();
			button = new JButton(buttonAction);
			
			// 非菜单项的JComponent要通过下面的方式添加快捷键
			InputMap inputMap = getButton().getInputMap(
					JComponent.WHEN_IN_FOCUSED_WINDOW);
			inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_B,
					InputEvent.CTRL_MASK), "button");// Ctrl+B

			ActionMap actionMap = getButton().getActionMap();
			actionMap.put("button", buttonAction);
		}
		return button;
	}
	
	private class ButtonAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		public ButtonAction() {
			super();
			initAction();
		}

		private void initAction() {
			putValue(NAME, "Ctrl+B");
		}

		public void actionPerformed(ActionEvent e) {
			getTextField().setText("JButton clicked.");
		}
	}

	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField(20);
		}
		return textField;
	}

	private void initMenus() {
		setJMenuBar(createMenuBar());
	}

	private JMenuBar createMenuBar() {
		JMenuBar bar = new JMenuBar();
		bar.add(createMenu());
		return bar;
	}

	private JMenu createMenu() {
		JMenu menu = new JMenu("File");
		// 可以同时按下Alt+F在菜单栏中选择一个顶层菜单
		menu.setMnemonic('F');// 只能通过这种方式为菜单设置键盘助记符
		menu.add(createOpenMenuItem());
		menu.add(createSaveMenuItem());
		return menu;
	}

	private JMenuItem createOpenMenuItem() {
		// 只能在菜单项的构造器中设定键盘助记符字母
		JMenuItem menuItem = new JMenuItem("Open File", 'O');
		// 将Ctrl+B设置为加速键，当用户按下加速器组合键时，就自动的选择了相应的菜单项，同时激活一个动作事件
		// 加速器只能关联到菜单项上，而不能关联到菜单上，加速器并不实际打开菜单，只是直接激活菜单关联的动作
		// 当加速器添加到菜单项时，对应的组合键就会自动的显示在该菜单上
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getTextField().setText("Open File");
			}
		});
		return menuItem;
	}

	private JMenuItem createSaveMenuItem() {
		return new JMenuItem(new SaveAction());
	}

	private class SaveAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		public SaveAction() {
			super();
			initAction();
		}

		private void initAction() {
			putValue(NAME, "Save File");
			// 可以把键盘助记符的键值添加到Action对象中
			putValue(MNEMONIC_KEY, new Integer('S'));
			// 可以把加速键的键值(Ctrl+S)添加到Action对象中
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		}

		public void actionPerformed(ActionEvent e) {
			getTextField().setText("Save File");
		}
	}
}