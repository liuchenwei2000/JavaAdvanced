/**
 * 
 */
package swing.hotkey;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import swing.frame.BasicFrame;
import util.Displayer;

/**
 * MnemonicKey演示
 * <p>
 * Mnemonic Key(键盘助记符)：</br>
 * 通常是菜单，按钮，文本标签等控件的文本中带下划线得一个字符，
 * 用来提示用户可以同时按下Alt键和这个字符对应得键来触发相应的动作。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2010-12-20
 */
public class MnemonicKeyDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.display(new MnemonicKeyFrame(), 200, 100);
	}
}

class MnemonicKeyFrame extends BasicFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -81876610601190356L;

	private JLabel label;
	private JButton button;
	private JTextField textField;

	public MnemonicKeyFrame() {
		super();
		initUI();
		initMenus();
	}

	private void initUI() {
		getContentPane().add(getLabel(), BorderLayout.WEST);
		getContentPane().add(getTextField(), BorderLayout.CENTER);
		getContentPane().add(getButton(), BorderLayout.EAST);
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("JLabel");
			label.setDisplayedMnemonicIndex(1);// 设置第一个'L'为键盘助记符
		}
		return label;
	}

	private JButton getButton() {
		if (button == null) {
			button = new JButton("JButton");
			button.setMnemonic('B');// 设置'B'为键盘助记符
			// Alt+B 将会触发下面的动作
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					getTextField().setText("JButton clicked.");
				}
			});
		}
		return button;
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
		}

		public void actionPerformed(ActionEvent e) {
			getTextField().setText("Save File");
		}
	}
}
