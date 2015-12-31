/**
 * 
 */
package awt.layout;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import util.Displayer;

/**
 * 边界布局管理器
 * <p>
 * 所有JFrame的内容窗格的默认布局管理器是BorderLayout。<p>
 * 特点：</br>
 * 它允许我们选择每个组件的放置位置，可以选择把组件放置在内容窗格的中、北、南、东或者西。
 * 放置组件时，管理器先放入边缘组件，剩余的可用空间由中间组件占用。
 * 当容器缩放时，边缘组件的厚度不会改变，而中间组件的大小会发生变化。
 * 它会扩大所有组件的尺寸以便填充可用空间(流布局管理器中每个组件都有首选的大小)。
 * 它并不要求所有的位置都要占满，如果不提供任何值，系统默认为CENTER。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2007-8-21
 */
public class BorderLayoutDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("BorderLayout Demo", new BorderLayoutPanel());
	}
}

class BorderLayoutPanel extends JPanel {
	
	private static final long serialVersionUID = 8810146383997197446L;

	public BorderLayoutPanel() {
		super();
		BorderLayout border = new BorderLayout();
		setLayout(border);
		border.setHgap(10);// 设置行距
		border.setVgap(10);// 设置列距
		add(new JButton("one"), BorderLayout.EAST);
		add(new JButton("two"), BorderLayout.WEST);
		add(new JButton("three"), BorderLayout.SOUTH);
		add(new JButton("four"), BorderLayout.NORTH);
		add(new JButton("five"), BorderLayout.CENTER);
	}
}
