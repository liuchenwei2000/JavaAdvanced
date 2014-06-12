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
 * 复杂菜单演示类
 * <p>
 * JMenuItem从AbstractButton继承而来，所以它具有类似按钮的行为。
 * 它提供了一个可以单独放置在下拉菜单上的条目。</br>
 * 还有三种类型继承自JMenuItem:</br>
 * <li>JMenu用来持有其它的JMenuItem(这样才能实现层叠式菜单)。
 * <li>JCheckBoxMenuItem提供了一个检查标记，用来表明菜单项是否被选中。
 * <li>JRadioButtonMenuItem包含了一个单选按钮。
 *
 * @author 刘晨伟
 *
 * 创建日期：2009-5-18
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
	// 第一个菜单栏
	private JMenuBar menuBar1 = new JMenuBar();
	// File菜单
	private JMenu fileMenu = new JMenu("File");
	// File菜单持有的菜单项
	private JMenuItem openItem = new JMenuItem("Open");
	private JMenuItem exitItem = new JMenuItem("Exit");
	// Animal菜单
	private JMenu animalMenu = new JMenu("Animal");
	// Safety菜单
	private JMenu safetyMenu = new JMenu("Safety");
	// Safety菜单持有的菜单项
	private JCheckBoxMenuItem readOnlyItem = new JCheckBoxMenuItem("Read-Only");
	private JCheckBoxMenuItem hiddenItem = new JCheckBoxMenuItem("Hidden");
	// Language菜单
	private JMenu languageMenu = new JMenu("Language");
	// Language菜单持有的菜单项
	private JRadioButtonMenuItem englishItem = new JRadioButtonMenuItem("English");
	private JRadioButtonMenuItem chineseItem = new JRadioButtonMenuItem("Chinese");

	// 第二个菜单栏
	private JMenuBar menuBar2 = new JMenuBar();
	// Sport菜单栏
	private JMenu sportMenu = new JMenu("Sport");
	// Sport菜单栏持有的菜单项
	private JMenuItem footballItem = new JMenuItem("Football", KeyEvent.VK_F);
	private JMenuItem basketballItem = new JMenuItem("Basketball",
			KeyEvent.VK_A);
	private JMenuItem volleyballItem = new JMenuItem("Volleyball");

	private JButton button = new JButton("切换菜单栏");

	public MenusFrame(String title) {
		super(title);
		initMenus();
		initMenuBars();
		initUI();
	}

	/**
	 * 初始化界面，加载各个组件
	 */
	private void initUI() {
		// 设置此窗体的菜单栏
		setJMenuBar(menuBar1);
		text.setEditable(false);
		getContentPane().add(text, BorderLayout.CENTER);
		button.addActionListener(new ButtonListener());
		button.setMnemonic(KeyEvent.VK_S);
		getContentPane().add(button, BorderLayout.NORTH);
	}

	/**
	 * 初始化菜单栏
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
	 * 初始化菜单
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
		 * 因为在每种情况下的"动作命令"(action command)与菜单上的标签都完全相同
		 * 为什么不直接使用标签而是这种额外的字符串呢？问题在于对国际化的支持
		 * 如果要把程序以另一种语言发布，最好是希望只改变菜单上的标签，而不用修改代码
		 * 为了使代码能更容易地判断与菜单关联的字符串，可以把"动作命令"作为不变量，而把菜单上的标签作为可变量
		 * 所有的代码在运行时都使用"动作命令"，这样改变菜单标签的时候就不会影响代码
		 */
		readOnlyItem.setActionCommand("Read-Only");
		/* 
		 * Swing支持助记键，或者称为"键盘快捷键"，所以可以使用键盘而不是鼠标
		 * 来选择任何从AbstractButton(按钮，菜单项等等)继承而来的组件
		 * 做到这一点很简单:只要使用重载的构造器，使它的第二个参数接受快捷键的标识符即可
		 * 不过，大多数AbstractButton没有这样的构造器，所以更通用作法的是
		 * 使用setMnemonic()方法，快捷指示符会自动地出现在组件上
		 */
		readOnlyItem.setMnemonic(KeyEvent.VK_R);
		/*
		 * 快捷键是用来从当前打开的菜单中选择一个子菜单或者菜单项
		 * 而加速器是在不打开菜单的情况下选择菜单项的快捷键
		 * 当用户按下加速器组合键时，就自动地选择了一个相应的菜单项
		 * 同时激活一个动作事件，这和手工选择这个菜单项一样
		 * 加速器只能关联到菜单项上，而不能关联到菜单上
		 * 加速器并不实际打开菜单，它只是直接激活菜单关联的动作事件
		 * 但是会在该菜单项后显示加速器对应的字符串(如 Ctrl-R)
		 * setAccelerator方法可以将加速器键关联到一个菜单项 
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
		// 提供关于应该装饰文本中哪一个字符来表示助记符的外观提示
		// 本例中将"animal"的第二个'a'作为提示字符(即其下有下划线)
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
			// 返回此窗体上设置的菜单栏
			JMenuBar currentMenuBar = getJMenuBar();
			// 动态替换菜单栏
			setJMenuBar(currentMenuBar == menuBar1 ? menuBar2 : menuBar1);
			/*
			 * 验证此容器及其所有子组件，使用奔方法会使容器再次布置其子组件
			 * 已经布置容器后，在修改此容器的子组件的时候(添加、移除组件，或更改与布局相关的信息) 
			 * 应该调用本方法
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
						"确定退出？");
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