/**
 * 
 */
package swing.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import util.Displayer;

/**
 * 简单菜单演示类
 * <p>
 * 每个组件都能够持有一个菜单，包括JApplet，JFrame，JDialog以及它们的子类。
 * 它们的setJMenuBar()方法接受一个JMenuBar对象(每个组件只能持有一个JMenuBar对象)作为参数。
 * 先把JMenu对象添加到JMenuBar中，然后把JMenuItem添加到JMenu中。
 * 每个JMenuItem都能有一个相关联的ActionListener，用来捕获菜单项被选中时触发的事件。
 *
 * @author 刘晨伟
 *
 * 创建日期：2009-5-18
 */
public class SimpleMenusDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.display(new SimpleMenusPanel("SimpleMenus Demo"), 200, 85);
	}
}

class SimpleMenusPanel extends JFrame {

	private static final long serialVersionUID = -5316049677104076415L;

	private JTextField text = new JTextField(15);
	// 菜单 数组
	private JMenu fileMenu = new JMenu("File");
	private JMenu editMenu = new JMenu("Edit");
	private JMenu helpMenu = new JMenu("Help");

	private JMenu[] menus = { fileMenu, editMenu, helpMenu };

	private ActionListener listener;// 菜单项监听器

	public SimpleMenusPanel(String title) {
		super(title);
		initMenus();
		initListeners();
		// 菜单栏
		JMenuBar menuBar = new JMenuBar();
		for (JMenu menu : menus) {
			menuBar.add(menu);
		}
		// 设置此Frame的菜单栏
		setJMenuBar(menuBar);
		text.setEditable(false);
		add(text);
	}

	private void initMenus() {
		initFileMenu();
		initEditMenu();
		initHelpMenu();
	}

	private void initFileMenu() {
		fileMenu.add(new JMenuItem("New"));
		fileMenu.add(new JMenuItem("Open"));
		fileMenu.addSeparator();
		fileMenu.add(new JMenuItem("Exit"));
	}

	private void initEditMenu() {
		editMenu.add(new JMenuItem("Cut"));
		editMenu.add(new JMenuItem("Copy"));
		editMenu.add(new JMenuItem("Paste"));
		// 带图标的菜单项
		JMenuItem deleteItem = new JMenuItem("Delete", new ImageIcon(
				"images/gui.swing.menu/delete.gif"));
		editMenu.add(deleteItem);
		JMenuItem deleteItem2 = new JMenuItem("Delete", new ImageIcon(
				"images/gui.swing.menu/delete.gif"));
		// 设置文本的水平显示位置(相对图标)
		deleteItem2.setHorizontalTextPosition(JMenuItem.LEFT);
		editMenu.add(deleteItem2);
	}

	private void initHelpMenu() {
		helpMenu.add(new JMenuItem("Help Contents"));
		helpMenu.add(new JMenuItem("Update"));
		helpMenu.add(new JMenuItem("About"));
	}

	private void initListeners() {
		for (JMenu menu : menus) {
			for (int i = 0; i < menu.getItemCount(); i++) {
				// 排除separator的情况
				if (menu.getItem(i) != null) {
					menu.getItem(i).addActionListener(getListener());
				}
			}
		}
	}

	private ActionListener getListener() {
		if (listener == null) {
			listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					text.setText(((JMenuItem) e.getSource()).getText());
				}
			};
		}
		return listener;
	}
}
