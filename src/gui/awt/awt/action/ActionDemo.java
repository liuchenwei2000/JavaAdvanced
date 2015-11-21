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
 * Action演示
 * <p>
 * 激活一个命令可以有多种方式，可以通过菜单、点击按钮或者敲击键盘。
 * 在AWT事件模型中实现这些非常容易：将所有事件连接到同一监听器上。
 * Swing提供了一种非常实用的机制来封装命令，并将它们连接到多个事件源，这就是Action接口。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-6-23
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
		// JButton的相关属性会从Action中抽取
		add(new JButton(yellowAction));
		add(new JButton(blueAction));
		add(new JButton(redAction));
		
		/**
		 * 将动作添加到击键中，以便让用户敲击键盘命令来执行这项动作，击键被传递给拥有焦点的组件。
		 * 这是一个常见的问题，Swing已经有了很便捷的解决方案：
		 * 为了将动作与击键关联起来，首先需要生成KeyStroke对象，
		 * 使用KeyStroke类中的静态getKeyStroke方法，并指定虚拟键码和标记(如SHIFT和CONTROL)。
		 * 
		 * 每个JComponent有三个输入映射(InputMap)，每一个映射的KeyStroke对象都与动作关联。
		 * 三个输入映射对应着三个不同的条件：
		 * 
		 *        标志                                           激活动作
		 * WHEN_FOCUSED                               当该组件拥有键盘焦点时
		 * WHEN_ANCESTOR_OF_FOCUSED_COMPONENT         当该组件包含了拥有键盘焦点的组件时
		 * WHEN_IN_FOCUSED_WINDOW                     当该组件被包含在一个拥有键盘焦点组件的窗口中时
		 * 
		 * 击键处理将按照下列顺序检查这些映射：
		 * 1，检查具有输入焦点组件的WHEN_FOCUSED映射。
		 * 如果这个击键存在，将执行对应的动作。如果动作已启用，则停止处理。
		 * 2，从具有输入焦点的组件开始，检查其父组件的WHEN_ANCESTOR_OF_FOCUSED_COMPONENT映射。
		 * 一旦找到击键对应的映射，就执行对应的动作。如果动作已启用，将停止处理。
		 * 3，查看具有输入焦点的窗口中的所有可视的和启用的组件，这个击键被注册到WHEN_IN_FOCUSED_WINDOW映射中。
		 * 给这些组件一个执行对应动作的机会。一旦第一个启用的动作被执行，就停止处理。
		 */
		// 获取本JPanel包含了拥有键盘焦点的组件时的输入映射
		InputMap inputMap = getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		// KeyStroke对象封装了对应KEY_PRESSED或KEY_RELEASED事件的一个击键
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
				InputEvent.CTRL_MASK), "panel.yellow");
		inputMap.put(KeyStroke.getKeyStroke("ctrl B"), "panel.blue");
		inputMap.put(KeyStroke.getKeyStroke("ctrl R"), "panel.red");

		/*
		 * InputMap不能直接地将KeyStroke对象映射到Action对象，而是先映射到任意对象上
		 * 然后由ActionMap类实现将对象映射到动作上的第二个映射
		 * 这样很容易实现来自不同输入映射的击键共享一个动作的目的
		 * 习惯上，使用字符串"none"表示空动作
		 */

		ActionMap actionMap = getActionMap();
		actionMap.put("panel.yellow", yellowAction);
		actionMap.put("panel.blue", blueAction);
		actionMap.put("panel.red", redAction);
	}

	/**
	 * 动作是一个封装下列内容的对象： 
	 * 1，命令的说明(一个文本字符串和一个可选图标) 2，执行命令所需要的参数
	 * 
	 *                预定义动作 
	 * NAME                        动作名称：显示在按钮和菜单项上 
	 * SMALL_ICON                  存储小图标：显示在按钮、菜单项或工具栏中
	 * SHORT_DESCRIPTION           图标的简要说明：显示在工具提示中 
	 * MNEMONIC_KEY                快捷键缩写：显示在菜单项中
	 * ACCELERATOR_KEY             击键加速器：存储加速击键的地方
	 * 
	 * Action接口扩展自ActionListener接口，putValue/getValue 方法允许存储和检索动作对象中的任意名/值对。
	 * AbstractAction已经有了很多默认实现，子类只需要实现actionPerformed即可。
	 * 这个类存储了所有名/值对，并管理着属性变更监听器。
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
