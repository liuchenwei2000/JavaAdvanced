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
 * JPopupMenu��ʾ��
 * <p>
 * JPopupMenu����ʽ�˵���һ���ɵ�������ʾһϵ��ѡ���С���� ��
 * ���ڵ��û�ѡ��˵��������ʱ��ʾ��"����ʽ(pull-right)"�˵���
 * �����������ò˵���ʾ���κ�����λ��ʹ�ã����統�û���ָ���������һ�ʱ��
 * <p>
 * ������JPopupMenu���ʹ�õĵ���������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-5-19
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
		// ��������Ҽ����ʱ��JPopupMenu
		setComponentPopupMenu(popupMenu);
		// ͨ��������ʽΪJTextField����Ҽ������JPopupMenu
		text.addMouseListener(new PopupListener());
		text.setText("Right  click  your  mouse.");
		add(text);
	}

	/**
	 * ��ʼ��JPopupMenu
	 */
	private void initPopupMenu() {
		ActionListener listener = getListener();
		JMenuItem item1 = new JMenuItem("First");
		item1.addActionListener(listener);
		JMenuItem item2 = new JMenuItem("Second");
		item2.addActionListener(listener);
		// ��ָ���˵���׷�ӵ��˲˵���ĩβ
		popupMenu.add(item1);
		// ���·ָ���׷�ӵ��˵���ĩβ
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
			// ���ش�����¼��Ƿ�Ϊ��ƽ̨�ĵ����˵������¼�
			if (e.isPopupTrigger()){
				// ����������ߵ�����ռ��е�λ�� X��Y ��ʾ�����˵�
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