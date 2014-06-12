package awt.layout;

import java.awt.Button;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import util.Displayer;

/**
 * 自定义布局演示类
 * 
 * @author 刘晨伟
 *
 * 创建日期：2009-7-14
 */
public class CustomLayoutDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("Custom Layout Demo",
				new CircleLayoutPanel());
	}
}

class CircleLayoutPanel extends JPanel {

	private static final long serialVersionUID = 2879108581139523087L;

	public CircleLayoutPanel() {
		setPreferredSize(new Dimension(350, 350));
		this.setLayout(new CircleLayout());
		this.add(new Button("Yes"));
		this.add(new Button("No"));
		this.add(new Button("Ok"));
		this.add(new Button("Cancel"));
		this.add(new Button("Abort"));
		this.add(new Button("Retry"));
	}
}

/**
 * 自定义布局管理器必须要实现LayoutManager接口
 */
class CircleLayout implements LayoutManager {

	private int minWidth = 0;
	private int minHeight = 0;
	private int preferredWidth = 0, preferredHeight = 0;
	private boolean sizesSet = false;
	private int maxComponentWidth = 0;
	private int maxComponentHeight = 0;

	/**
	 * 将组件添加到布局中
	 */
	public void addLayoutComponent(String name, Component comp) {
	}

	/**
	 * 从布局中删除一个组件
	 */
	public void removeLayoutComponent(Component comp) {
	}

	public void setSizes(Container parent) {
		if (sizesSet)
			return;
		int n = parent.getComponentCount();

		preferredWidth = 0;
		preferredHeight = 0;
		minWidth = 0;
		minHeight = 0;
		maxComponentWidth = 0;
		maxComponentHeight = 0;

		for (int i = 0; i < n; i++) {
			Component c = parent.getComponent(i);
			if (c.isVisible()) {
				Dimension d = c.getPreferredSize();
				maxComponentWidth = Math.max(maxComponentWidth, d.width);
				maxComponentHeight = Math.max(maxComponentWidth, d.height);
				preferredHeight += d.height;
			}
		}
		preferredHeight += maxComponentHeight;
		preferredWidth = 2 * maxComponentWidth;
		minHeight = preferredHeight;
		minWidth = preferredWidth;
		sizesSet = true;
	}

	/**
	 * 返回布局中容器的首选尺寸大小
	 */
	public Dimension preferredLayoutSize(Container parent) {
		Dimension dim = new Dimension(0, 0);
		setSizes(parent);
		Insets insets = parent.getInsets();
		dim.width = preferredWidth + insets.left + insets.right;
		dim.height = preferredHeight + insets.top + insets.bottom;
		return dim;
	}

	/**
	 * 返回布局中容器的最小尺寸大小
	 */
	public Dimension minimumLayoutSize(Container parent) {
		Dimension dim = new Dimension(0, 0);
		setSizes(parent);
		Insets insets = parent.getInsets();
		dim.width = minWidth + insets.left + insets.right;
		dim.height = minHeight + insets.top + insets.bottom;
		return dim;
	}

	/**
	 * 布局容器中的组件，真正的实施操作
	 */
	public void layoutContainer(Container parent) {
		Insets insets = parent.getInsets();
		int containerWidth = parent.getSize().width - insets.left
				- insets.right;
		int containerHeight = parent.getSize().height - insets.top
				- insets.bottom;
		int xradius = (containerWidth - maxComponentWidth) / 2;
		int yradius = (containerHeight - maxComponentHeight) / 2;

		setSizes(parent);
		int xcenter = insets.left + containerWidth / 2;
		int ycenter = insets.top + containerHeight / 2;

		int n = parent.getComponentCount();
		for (int i = 0; i < n; i++) {
			Component c = parent.getComponent(i);
			if (c.isVisible()) {
				Dimension d = c.getPreferredSize();
				double angle = 2 * Math.PI * i / n;
				int x = xcenter + (int) (Math.cos(angle) * xradius);
				int y = ycenter + (int) (Math.sin(angle) * yradius);

				c.setBounds(x - d.width / 2, y - d.height / 2, d.width,
						d.height);
			}
		}
	}
}