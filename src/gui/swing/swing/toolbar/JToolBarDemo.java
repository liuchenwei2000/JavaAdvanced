package swing.toolbar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import util.Displayer;

/**
 * JToolBar��ʾ��
 * <p>
 * JToolBar���������ڳ������ṩ���ٷ��ʳ�������İ�ť����
 * ��������֮�����ڿ��԰����Ƶ��κεط���������ק����ܵ��ĸ��߽��ϡ�
 * ����������ȫ�����ܣ������Ĺ��������������Լ��Ŀ���С�
 * 
 * @author ����ΰ
 *
 * �������ڣ�2009-6-30
 */
public class JToolBarDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JToolBar Demo", new ToolBarPanel());
	}
}

class ToolBarPanel extends JPanel {
	
	private static final long serialVersionUID = 4469973302494249840L;
	
	// ������Ĭ����ˮƽ�ģ�����ʹ��HORIZONTAL��VERTICAL��ָ��
	private JToolBar bar = new JToolBar("Color");
	private JPanel emptyPanel = new JPanel();

	public ToolBarPanel() {
		this.setLayout(new BorderLayout());
		initToolBar();
		this.add(bar, BorderLayout.NORTH);
		emptyPanel.setPreferredSize(new Dimension(300, 200));
		this.add(emptyPanel, BorderLayout.CENTER);
	}
	
	private void initToolBar() {
		Action blueAction = new ColorAction("Blue", new ImageIcon(
				"images/gui.swing.toolbar/blue-ball.gif"), Color.BLUE);
		Action yellowAction = new ColorAction("Yellow", new ImageIcon(
				"images/gui.swing.toolbar/yellow-ball.gif"), Color.YELLOW);
		Action redAction = new ColorAction("Red", new ImageIcon(
				"images/gui.swing.toolbar/red-ball.gif"), Color.RED);

		Action exitAction = new AbstractAction("Exit", new ImageIcon(
				"images/gui.swing.toolbar/exit.gif")) {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		};
		exitAction.putValue(Action.SHORT_DESCRIPTION, "Exit");
		// ���һ����ť(��ťʹ�ò���Actionȥ����)�����ص��ǰ�ť����
		// ���˿�����Ӱ�ť֮�⣬������������縴ѡ��ȿؼ�
		bar.add(new JCheckBox("M"));
		bar.add(blueAction);
		bar.add(yellowAction);
		bar.add(redAction);
		// ��ӷָ���
		bar.addSeparator();
		bar.add(exitAction);
		// ���ù������Ƿ����ק�ƶ���Ĭ��Ϊtrue
//		bar.setFloatable(false);
	}

	class ColorAction extends AbstractAction {
		
		private static final long serialVersionUID = 1L;

		public ColorAction(String name, Icon icon, Color c) {
			putValue(Action.NAME, name);
			putValue(Action.SMALL_ICON, icon);
			putValue(Action.SHORT_DESCRIPTION, name + " background");
			putValue("Color", c);
		}

		public void actionPerformed(ActionEvent evt) {
			Color c = (Color) getValue("Color");
			emptyPanel.setBackground(c);
		}
	}
}