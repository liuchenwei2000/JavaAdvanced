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
 * MnemonicKey��ʾ
 * <p>
 * Mnemonic Key(�������Ƿ�)��</br>
 * ͨ���ǲ˵�����ť���ı���ǩ�ȿؼ����ı��д��»��ߵ�һ���ַ���
 * ������ʾ�û�����ͬʱ����Alt��������ַ���Ӧ�ü���������Ӧ�Ķ�����
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2010-12-20
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
			label.setDisplayedMnemonicIndex(1);// ���õ�һ��'L'Ϊ�������Ƿ�
		}
		return label;
	}

	private JButton getButton() {
		if (button == null) {
			button = new JButton("JButton");
			button.setMnemonic('B');// ����'B'Ϊ�������Ƿ�
			// Alt+B ���ᴥ������Ķ���
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
		// ����ͬʱ����Alt+F�ڲ˵�����ѡ��һ������˵�
		menu.setMnemonic('F');// ֻ��ͨ�����ַ�ʽΪ�˵����ü������Ƿ�
		menu.add(createOpenMenuItem());
		menu.add(createSaveMenuItem());
		return menu;
	}

	private JMenuItem createOpenMenuItem() {
		// ֻ���ڲ˵���Ĺ��������趨�������Ƿ���ĸ
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
			// ���԰Ѽ������Ƿ��ļ�ֵ��ӵ�Action������
			putValue(MNEMONIC_KEY, new Integer('S'));
		}

		public void actionPerformed(ActionEvent e) {
			getTextField().setText("Save File");
		}
	}
}