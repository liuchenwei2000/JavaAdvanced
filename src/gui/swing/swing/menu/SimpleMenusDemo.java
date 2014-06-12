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
 * �򵥲˵���ʾ��
 * <p>
 * ÿ��������ܹ�����һ���˵�������JApplet��JFrame��JDialog�Լ����ǵ����ࡣ
 * ���ǵ�setJMenuBar()��������һ��JMenuBar����(ÿ�����ֻ�ܳ���һ��JMenuBar����)��Ϊ������
 * �Ȱ�JMenu������ӵ�JMenuBar�У�Ȼ���JMenuItem��ӵ�JMenu�С�
 * ÿ��JMenuItem������һ���������ActionListener����������˵��ѡ��ʱ�������¼���
 *
 * @author ����ΰ
 *
 * �������ڣ�2009-5-18
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
	// �˵� ����
	private JMenu fileMenu = new JMenu("File");
	private JMenu editMenu = new JMenu("Edit");
	private JMenu helpMenu = new JMenu("Help");

	private JMenu[] menus = { fileMenu, editMenu, helpMenu };

	private ActionListener listener;// �˵��������

	public SimpleMenusPanel(String title) {
		super(title);
		initMenus();
		initListeners();
		// �˵���
		JMenuBar menuBar = new JMenuBar();
		for (JMenu menu : menus) {
			menuBar.add(menu);
		}
		// ���ô�Frame�Ĳ˵���
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
		// ��ͼ��Ĳ˵���
		JMenuItem deleteItem = new JMenuItem("Delete", new ImageIcon(
				"images/gui.swing.menu/delete.gif"));
		editMenu.add(deleteItem);
		JMenuItem deleteItem2 = new JMenuItem("Delete", new ImageIcon(
				"images/gui.swing.menu/delete.gif"));
		// �����ı���ˮƽ��ʾλ��(���ͼ��)
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
				// �ų�separator�����
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