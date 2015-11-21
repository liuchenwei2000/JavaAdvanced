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
 * JToolBar演示类
 * <p>
 * JToolBar工具栏是在程序中提供快速访问常用命令的按钮栏，
 * 它的特殊之处在于可以把它移到任何地方，可以拖拽到框架的四个边界上。
 * 甚至可以完全脱离框架，这样的工具栏将包含在自己的框架中。
 * 
 * @author 刘晨伟
 *
 * 创建日期：2009-6-30
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
	
	// 工具栏默认是水平的，可以使用HORIZONTAL和VERTICAL来指明
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
		// 添加一个按钮(按钮使用参数Action去构造)，返回的是按钮引用
		// 除了可以添加按钮之外，还可以添加诸如复选框等控件
		bar.add(new JCheckBox("M"));
		bar.add(blueAction);
		bar.add(yellowAction);
		bar.add(redAction);
		// 添加分隔符
		bar.addSeparator();
		bar.add(exitAction);
		// 设置工具栏是否可拖拽移动，默认为true
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
