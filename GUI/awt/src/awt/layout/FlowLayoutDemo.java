/**
 * 
 */
package awt.layout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import util.Displayer;

/**
 * 流布局管理器
 * <p>
 * 所有面板(JPanel)的默认布局管理器是FlowLayout。<p>
 * 特点：</br>
 * 在一行上水平排列组件，直到没有足够的空间为止，这时才开始新的一行。
 * 当用户缩放容器时，布局管理器自动地调整组件的位置使其填充可用的空间。
 * 还可以选择在每一行上排列组件的方案，默认方式是居中显示(FlowLayout.CENTER)，
 * 另外还有左对齐(FlowLayout.LEFT)和右对齐(FlowLayout.RIGHT)。
 * 需要在FlowLayout对象的构造器中指定对齐方式：</br>
 * new FlowLayout(FlowLayout.LEFT);
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2007-8-21
 */
public class FlowLayoutDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("FlowLayout Demo", new FlowLayoutPanel());
	}
}

class FlowLayoutPanel extends JPanel {
	
	private static final long serialVersionUID = -4692931166665337007L;

	public FlowLayoutPanel() {
		super();
		FlowLayout flow = new FlowLayout();
		setLayout(flow);
		flow.setHgap(10);// 设置行距
		flow.setVgap(10);// 设置列距
		add(new JButton("one"));
		add(new JButton("two"));
		add(new JButton("three"));
		add(new JButton("four"));
		add(new JButton("five"));
	}
}
