/**
 * 
 */
package swing.menu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import util.Displayer;

/**
 * ���Ӳ˵���ʾ��
 * <p>
 * JMenuItem��AbstractButton�̳ж������������������ư�ť����Ϊ��
 * ���ṩ��һ�����Ե��������������˵��ϵ���Ŀ��</br>
 * �����������ͼ̳���JMenuItem:</br>
 * <li>JMenu��������������JMenuItem(��������ʵ�ֲ��ʽ�˵�)��
 * <li>JCheckBoxMenuItem�ṩ��һ������ǣ����������˵����Ƿ�ѡ�С�
 * <li>JRadioButtonMenuItem������һ����ѡ��ť��
 *
 * @author ����ΰ
 *
 * �������ڣ�2009-5-18
 */
public class MenusDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.display(new MenusFrame("Menus Demo"), 300, 120);
	}
}

class MenusFrame extends JFrame {

	private static final long serialVersionUID = -6800750827517288227L;

	private static final String[] ANIMALS = { "Tiger", "Lion", "Fish", "Frog",
			"Eagle", "Pigeon" };

	private JTextField text = new JTextField("No animal", 30);
	// ��һ���˵���
	private JMenuBar menuBar1 = new JMenuBar();
	// File�˵�
	private JMenu fileMenu = new JMenu("File");
	// File�˵����еĲ˵���
	private JMenuItem openItem = new JMenuItem("Open");
	private JMenuItem exitItem = new JMenuItem("Exit");
	// Animal�˵�
	private JMenu animalMenu = new JMenu("Animal");
	// Safety�˵�
	private JMenu safetyMenu = new JMenu("Safety");
	// Safety�˵����еĲ˵���
	private JCheckBoxMenuItem readOnlyItem = new JCheckBoxMenuItem("Read-Only");
	private JCheckBoxMenuItem hiddenItem = new JCheckBoxMenuItem("Hidden");
	// Language�˵�
	private JMenu languageMenu = new JMenu("Language");
	// Language�˵����еĲ˵���
	private JRadioButtonMenuItem englishItem = new JRadioButtonMenuItem("English");
	private JRadioButtonMenuItem chineseItem = new JRadioButtonMenuItem("Chinese");

	// �ڶ����˵���
	private JMenuBar menuBar2 = new JMenuBar();
	// Sport�˵���
	private JMenu sportMenu = new JMenu("Sport");
	// Sport�˵������еĲ˵���
	private JMenuItem footballItem = new JMenuItem("Football", KeyEvent.VK_F);
	private JMenuItem basketballItem = new JMenuItem("Basketball",
			KeyEvent.VK_A);
	private JMenuItem volleyballItem = new JMenuItem("Volleyball");

	private JButton button = new JButton("�л��˵���");

	public MenusFrame(String title) {
		super(title);
		initMenus();
		initMenuBars();
		initUI();
	}

	/**
	 * ��ʼ�����棬���ظ������
	 */
	private void initUI() {
		// ���ô˴���Ĳ˵���
		setJMenuBar(menuBar1);
		text.setEditable(false);
		getContentPane().add(text, BorderLayout.CENTER);
		button.addActionListener(new ButtonListener());
		button.setMnemonic(KeyEvent.VK_S);
		getContentPane().add(button, BorderLayout.NORTH);
	}

	/**
	 * ��ʼ���˵���
	 */
	private void initMenuBars() {
		initMenuBar1();
		initMenuBar2();
	}

	private void initMenuBar1() {
		menuBar1.add(fileMenu);
		menuBar1.add(animalMenu);
		menuBar1.add(languageMenu);
	}

	private void initMenuBar2() {
		menuBar2.add(sportMenu);
	}

	/**
	 * ��ʼ���˵�
	 */
	private void initMenus() {
		initSafetyMenu();
		initFileMenu();
		initAnimalMenu();
		initLanguageMenu();
		initSportMenu();
	}

	private void initSafetyMenu() {
		CheckMenuItemListener listener = new CheckMenuItemListener();
		/*
		 * ��Ϊ��ÿ������µ�"��������"(action command)��˵��ϵı�ǩ����ȫ��ͬ
		 * Ϊʲô��ֱ��ʹ�ñ�ǩ�������ֶ�����ַ����أ��������ڶԹ��ʻ���֧��
		 * ���Ҫ�ѳ�������һ�����Է����������ϣ��ֻ�ı�˵��ϵı�ǩ���������޸Ĵ���
		 * Ϊ��ʹ�����ܸ����׵��ж���˵��������ַ��������԰�"��������"��Ϊ�����������Ѳ˵��ϵı�ǩ��Ϊ�ɱ���
		 * ���еĴ���������ʱ��ʹ��"��������"�������ı�˵���ǩ��ʱ��Ͳ���Ӱ�����
		 */
		readOnlyItem.setActionCommand("Read-Only");
		/* 
		 * Swing֧�����Ǽ������߳�Ϊ"���̿�ݼ�"�����Կ���ʹ�ü��̶��������
		 * ��ѡ���κδ�AbstractButton(��ť���˵���ȵ�)�̳ж��������
		 * ������һ��ܼ�:ֻҪʹ�����صĹ�������ʹ���ĵڶ����������ܿ�ݼ��ı�ʶ������
		 * �����������AbstractButtonû�������Ĺ����������Ը�ͨ����������
		 * ʹ��setMnemonic()���������ָʾ�����Զ��س����������
		 */
		readOnlyItem.setMnemonic(KeyEvent.VK_R);
		/*
		 * ��ݼ��������ӵ�ǰ�򿪵Ĳ˵���ѡ��һ���Ӳ˵����߲˵���
		 * �����������ڲ��򿪲˵��������ѡ��˵���Ŀ�ݼ�
		 * ���û����¼�������ϼ�ʱ�����Զ���ѡ����һ����Ӧ�Ĳ˵���
		 * ͬʱ����һ�������¼�������ֹ�ѡ������˵���һ��
		 * ������ֻ�ܹ������˵����ϣ������ܹ������˵���
		 * ����������ʵ�ʴ򿪲˵�����ֻ��ֱ�Ӽ���˵������Ķ����¼�
		 * ���ǻ��ڸò˵������ʾ��������Ӧ���ַ���(�� Ctrl-R)
		 * setAccelerator�������Խ���������������һ���˵��� 
		 */
		readOnlyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
				InputEvent.CTRL_MASK));
		readOnlyItem.addItemListener(listener);
		hiddenItem.setActionCommand("Hidden");
		hiddenItem.setMnemonic(KeyEvent.VK_H);
		hiddenItem.addItemListener(listener);
		safetyMenu.add(readOnlyItem);
		safetyMenu.add(hiddenItem);
		safetyMenu.setMnemonic(KeyEvent.VK_A);
	}

	private void initFileMenu() {
		openItem.addActionListener(new OpenListener());
		exitItem.addActionListener(new ExitListener());
		fileMenu.add(openItem);
		fileMenu.add(safetyMenu);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		fileMenu.setMnemonic(KeyEvent.VK_F);
	}

	private void initAnimalMenu() {
		AnimalListener al = new AnimalListener();
		for (int i = 0; i < ANIMALS.length; i++) {
			JMenuItem item = new JMenuItem(ANIMALS[i]);
			item.addActionListener(al);
			animalMenu.add(item);
			if ((i + 1) % 2 == 0 && (i != ANIMALS.length - 1)) {
				animalMenu.addSeparator();
			}
		}
		animalMenu.setMnemonic(KeyEvent.VK_A);
		// �ṩ����Ӧ��װ���ı�����һ���ַ�����ʾ���Ƿ��������ʾ
		// �����н�"animal"�ĵڶ���'a'��Ϊ��ʾ�ַ�(���������»���)
		animalMenu.setDisplayedMnemonicIndex("animal".lastIndexOf('a'));
	}

	private void initLanguageMenu() {
		ButtonGroup group = new ButtonGroup();
		group.add(englishItem);
		group.add(chineseItem);
		LanguageListener listener = new LanguageListener();
		englishItem.setMnemonic(KeyEvent.VK_E);
		englishItem.addItemListener(listener);
		chineseItem.setMnemonic(KeyEvent.VK_C);
		chineseItem.addItemListener(listener);
		languageMenu.add(englishItem);
		languageMenu.add(chineseItem);
		languageMenu.setMnemonic(KeyEvent.VK_L);
	}
	
	private void initSportMenu() {
		footballItem.addActionListener(new FootballListener());
		basketballItem.addActionListener(new BasketballListener());
		volleyballItem.addActionListener(new VolleyballListener());
		sportMenu.add(footballItem);
		sportMenu.add(basketballItem);
		sportMenu.add(volleyballItem);
		sportMenu.setMnemonic(KeyEvent.VK_B);
	}

	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// ���ش˴��������õĲ˵���
			JMenuBar currentMenuBar = getJMenuBar();
			// ��̬�滻�˵���
			setJMenuBar(currentMenuBar == menuBar1 ? menuBar2 : menuBar1);
			/*
			 * ��֤���������������������ʹ�ñ�������ʹ�����ٴβ����������
			 * �Ѿ��������������޸Ĵ��������������ʱ��(��ӡ��Ƴ������������벼����ص���Ϣ) 
			 * Ӧ�õ��ñ�����
			 */
			validate();
		}
	}

	class OpenListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(openItem)) {
				String s = text.getText();
				boolean chosen = false;
				for (int i = 0; i < ANIMALS.length; i++) {
					if (s.equals(ANIMALS[i])) {
						chosen = true;
					}
				}
				if (!chosen) {
					text.setText("Choose a animal first!");
				} else {
					text.setText("Opening " + s + ". Mmm, mm!");
				}
			}
		}
	}
	
	class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(exitItem)) {
				int result = JOptionPane.showConfirmDialog(MenusFrame.this,
						"ȷ���˳���");
				if (result == JOptionPane.YES_OPTION) {
					MenusFrame.this.dispose();
				}
			}
		}
	}

	class AnimalListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JMenuItem target = (JMenuItem) e.getSource();
			text.setText(target.getText());
		}
	}

	class LanguageListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			JRadioButtonMenuItem target = (JRadioButtonMenuItem) e.getSource();
			if (target.equals(englishItem)) {
				text.setText("Current language is English? " + target.isSelected());
			} else if (target.equals(chineseItem)) {
				text.setText("Current language is Chinese? " + target.isSelected());
			}
		}
	}
	
	class FootballListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			text.setText("Football selected");
		}
	}

	class BasketballListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			text.setText("Basketball selected");
		}
	}

	class VolleyballListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			text.setText("Volleyball selected");
		}
	}

	class CheckMenuItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			JCheckBoxMenuItem target = (JCheckBoxMenuItem) e.getSource();
			if (target.equals(readOnlyItem)) {
				text.setText("Is it Read-Only? " + target.getState());
			} else if (target.equals(hiddenItem)) {
				text.setText("Is it hidden? " + target.getState());
			}
		}
	}
}