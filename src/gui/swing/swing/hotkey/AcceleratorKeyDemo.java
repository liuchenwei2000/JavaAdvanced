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
 * AcceleratorKey��ʾ
 * <p>
 * AcceleratorKey(���ټ�)������ͨ�����̵ļ�������ϣ�����һ��Action��ִ�С�
 * <p>
 * ��MnemonicKey����Ҫ����</br>
 * <li>1��AcceleratorKeyֻ�в˵����У�MnemonicKey����Ӧ���ڲ˵�����ť�ͱ�ǩ�ȿؼ��ϡ�
 * <li>2��MnemonicKey�����ӵ�ǰ�򿪵Ĳ˵���ѡ��һ���Ӳ˵����߲˵��
 * ��AcceleratorKey���ڲ��򿪲˵��������ѡ��˵���Ŀ�ݼ���
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2010-12-20
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
			
			// �ǲ˵����JComponentҪͨ������ķ�ʽ��ӿ�ݼ�
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
		// ����ͬʱ����Alt+F�ڲ˵�����ѡ��һ������˵�
		menu.setMnemonic('F');// ֻ��ͨ�����ַ�ʽΪ�˵����ü������Ƿ�
		menu.add(createOpenMenuItem());
		menu.add(createSaveMenuItem());
		return menu;
	}

	private JMenuItem createOpenMenuItem() {
		// ֻ���ڲ˵���Ĺ��������趨�������Ƿ���ĸ
		JMenuItem menuItem = new JMenuItem("Open File", 'O');
		// ��Ctrl+B����Ϊ���ټ������û����¼�������ϼ�ʱ�����Զ���ѡ������Ӧ�Ĳ˵��ͬʱ����һ�������¼�
		// ������ֻ�ܹ������˵����ϣ������ܹ������˵��ϣ�����������ʵ�ʴ򿪲˵���ֻ��ֱ�Ӽ���˵������Ķ���
		// ����������ӵ��˵���ʱ����Ӧ����ϼ��ͻ��Զ�����ʾ�ڸò˵���
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
			// ���԰Ѽ������Ƿ��ļ�ֵ��ӵ�Action������
			putValue(MNEMONIC_KEY, new Integer('S'));
			// ���԰Ѽ��ټ��ļ�ֵ(Ctrl+S)��ӵ�Action������
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		}

		public void actionPerformed(ActionEvent e) {
			getTextField().setText("Save File");
		}
	}
}