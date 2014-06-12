package awt.action;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import util.Displayer;

/**
 * Action��ʾ
 * <p>
 * ����һ����������ж��ַ�ʽ������ͨ���˵��������ť�����û����̡�
 * ��AWT�¼�ģ����ʵ����Щ�ǳ����ף��������¼����ӵ�ͬһ�������ϡ�
 * Swing�ṩ��һ�ַǳ�ʵ�õĻ�������װ��������������ӵ�����¼�Դ�������Action�ӿڡ�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-6-23
 */
public class ActionDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("Action Demo", new ActionPanel());
	}
}

class ActionPanel extends JPanel {

	private static final long serialVersionUID = 6559092100379964582L;

	public ActionPanel() {
		Action yellowAction = new ColorAction("Yellow", new ImageIcon(
				"images/gui.swing.toolbar/yellow-ball.gif"), Color.YELLOW);
		Action blueAction = new ColorAction("Blue", new ImageIcon(
				"images/gui.swing.toolbar/blue-ball.gif"), Color.BLUE);
		Action redAction = new ColorAction("Red", new ImageIcon(
				"images/gui.swing.toolbar/red-ball.gif"), Color.RED);
		// JButton��������Ի��Action�г�ȡ
		add(new JButton(yellowAction));
		add(new JButton(blueAction));
		add(new JButton(redAction));
		
		/**
		 * ��������ӵ������У��Ա����û��û�����������ִ������������������ݸ�ӵ�н���������
		 * ����һ�����������⣬Swing�Ѿ����˺ܱ�ݵĽ��������
		 * Ϊ�˽��������������������������Ҫ����KeyStroke����
		 * ʹ��KeyStroke���еľ�̬getKeyStroke��������ָ���������ͱ��(��SHIFT��CONTROL)��
		 * 
		 * ÿ��JComponent����������ӳ��(InputMap)��ÿһ��ӳ���KeyStroke�����붯��������
		 * ��������ӳ���Ӧ��������ͬ��������
		 * 
		 *        ��־                                           �����
		 * WHEN_FOCUSED                               �������ӵ�м��̽���ʱ
		 * WHEN_ANCESTOR_OF_FOCUSED_COMPONENT         �������������ӵ�м��̽�������ʱ
		 * WHEN_IN_FOCUSED_WINDOW                     ���������������һ��ӵ�м��̽�������Ĵ�����ʱ
		 * 
		 * ����������������˳������Щӳ�䣺
		 * 1�����������뽹�������WHEN_FOCUSEDӳ�䡣
		 * �������������ڣ���ִ�ж�Ӧ�Ķ�����������������ã���ֹͣ����
		 * 2���Ӿ������뽹��������ʼ������丸�����WHEN_ANCESTOR_OF_FOCUSED_COMPONENTӳ�䡣
		 * һ���ҵ�������Ӧ��ӳ�䣬��ִ�ж�Ӧ�Ķ�����������������ã���ֹͣ����
		 * 3���鿴�������뽹��Ĵ����е����п��ӵĺ����õ���������������ע�ᵽWHEN_IN_FOCUSED_WINDOWӳ���С�
		 * ����Щ���һ��ִ�ж�Ӧ�����Ļ��ᡣһ����һ�����õĶ�����ִ�У���ֹͣ����
		 */
		// ��ȡ��JPanel������ӵ�м��̽�������ʱ������ӳ��
		InputMap inputMap = getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		// KeyStroke�����װ�˶�ӦKEY_PRESSED��KEY_RELEASED�¼���һ������
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
				InputEvent.CTRL_MASK), "panel.yellow");
		inputMap.put(KeyStroke.getKeyStroke("ctrl B"), "panel.blue");
		inputMap.put(KeyStroke.getKeyStroke("ctrl R"), "panel.red");

		/*
		 * InputMap����ֱ�ӵؽ�KeyStroke����ӳ�䵽Action���󣬶�����ӳ�䵽���������
		 * Ȼ����ActionMap��ʵ�ֽ�����ӳ�䵽�����ϵĵڶ���ӳ��
		 * ����������ʵ�����Բ�ͬ����ӳ��Ļ�������һ��������Ŀ��
		 * ϰ���ϣ�ʹ���ַ���"none"��ʾ�ն���
		 */

		ActionMap actionMap = getActionMap();
		actionMap.put("panel.yellow", yellowAction);
		actionMap.put("panel.blue", blueAction);
		actionMap.put("panel.red", redAction);
	}

	/**
	 * ������һ����װ�������ݵĶ��� 
	 * 1�������˵��(һ���ı��ַ�����һ����ѡͼ��) 2��ִ����������Ҫ�Ĳ���
	 * 
	 *                Ԥ���嶯�� 
	 * NAME                        �������ƣ���ʾ�ڰ�ť�Ͳ˵����� 
	 * SMALL_ICON                  �洢Сͼ�꣺��ʾ�ڰ�ť���˵���򹤾�����
	 * SHORT_DESCRIPTION           ͼ��ļ�Ҫ˵������ʾ�ڹ�����ʾ�� 
	 * MNEMONIC_KEY                ��ݼ���д����ʾ�ڲ˵�����
	 * ACCELERATOR_KEY             �������������洢���ٻ����ĵط�
	 * 
	 * Action�ӿ���չ��ActionListener�ӿڣ�putValue/getValue ��������洢�ͼ������������е�������/ֵ�ԡ�
	 * AbstractAction�Ѿ����˺ܶ�Ĭ��ʵ�֣�����ֻ��Ҫʵ��actionPerformed���ɡ�
	 * �����洢��������/ֵ�ԣ������������Ա����������
	 */
	private class ColorAction extends AbstractAction {

		private static final long serialVersionUID = -2513063668684630976L;
		
		public ColorAction(String name, Icon icon, Color c) {
			putValue(Action.NAME, name);
			putValue(Action.SMALL_ICON, icon);
			putValue(Action.SHORT_DESCRIPTION, "Set panel color to "
					+ name.toLowerCase());
			putValue("color", c);
		}

		public void actionPerformed(ActionEvent event) {
			Color c = (Color) getValue("color");
			setBackground(c);
		}
	}
}