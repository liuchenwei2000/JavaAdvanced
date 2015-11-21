/**
 * 
 */
package awt.layout.spring;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Spring;
import javax.swing.SpringLayout;

import util.Displayer;

/**
 * 弹簧布局器(SpringLayout)简单演示
 * <p>
 * 使用弹簧布局，可以给每个组件添加弹簧(Spring)。
 * 弹簧是一种控制组件位置的装备，弹簧具有：最小值、首选值、最大值、实际值。
 * 当弹簧在布局阶段被压缩或者扩展时，就设置了实际值。
 * 它的值介于最小值与最大值之间，实际值决定了依附这个弹簧的组件位置。
 * 
 * @author 刘晨伟
 *
 * 创建日期：2009-7-13
 */
public class SpringLayoutSimpleDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("SpringLayout Simple Demo",
				new SpringLayoutSimplePanel());
	}
}

class SpringLayoutSimplePanel extends JPanel {

	private static final long serialVersionUID = -2262833829037414040L;

	private JButton button1 = new JButton("button1");
	private JButton button2 = new JButton("button2");
	private JButton button3 = new JButton("button3");

	public SpringLayoutSimplePanel() {
		setPreferredSize(new Dimension(400, 300));

		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		add(button1);
		add(button2);
		add(button3);
		// 使用给定的最小值、首选值和最大值创建一个弹簧。(弹簧不是常量，它可以被压缩或者扩展)
		Spring spring = Spring.constant(0, 10000, 10000);
		// 添加给定的弹簧，这个弹簧将终止于第一组参数(button1的西边)，起始于第二组参数(面板的西边)
		// 每个边必须具有以下某个值：SpringLayout.NORTH、SOUTH、EAST、WEST
		layout.putConstraint(SpringLayout.WEST, button1, spring,
				SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, this, spring,
				SpringLayout.EAST, button3);
		// 如果想在两个组件间设置一个固定的距离，可以使用支柱。支柱是一种不能被扩展或压缩的弹簧。
		Spring strut = Spring.constant(10);
		layout.putConstraint(SpringLayout.WEST, button2, strut,
				SpringLayout.EAST, button1);
		layout.putConstraint(SpringLayout.WEST, button3, strut,
				SpringLayout.EAST, button2);
	}
}
