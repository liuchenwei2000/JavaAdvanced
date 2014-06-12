/**
 * 
 */
package swing.border;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import util.Displayer;

/**
 * 边框演示类
 * <p>
 * JComponent有一个setBorder()方法，允许为任何可视组件设置各种边框。
 *
 * @author 刘晨伟
 *
 * 创建日期：2009-5-17
 */
public class BorderDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("Border Demo", new BorderPanel());
	}
}

class BorderPanel extends JPanel {

	private static final long serialVersionUID = -6413920779401835747L;

	public BorderPanel() {
		setLayout(getGridLayout());
		// TitledBorder实现在指定位置以指定对齐方式显示字符串标题的任意边框
		add(makeBorderPanel(new TitledBorder("Title")));
		// EtchedBorder实现简单的浮雕化边框，它既可以是阴刻浮雕化边框，也可以是阳刻浮雕化边框
		add(makeBorderPanel(new EtchedBorder()));
		add(makeBorderPanel(new EtchedBorder(EtchedBorder.RAISED)));
		// LineBorder实现单色、任意厚度线边框的类
		add(makeBorderPanel(new LineBorder(Color.BLUE)));
		// MatteBorder提供类似衬边的边框，衬边图案既可以是纯色也可以是平铺的图标
		add(makeBorderPanel(new MatteBorder(5, 5, 30, 30, Color.GREEN)));
		// BevelBorder实现简单的双线斜面边框。 
		add(makeBorderPanel(new BevelBorder(BevelBorder.RAISED)));
		// SoftBevelBorder实现某种斜面的类，这种斜面要么凸出要么凹入且拐角圆滑
		add(makeBorderPanel(new SoftBevelBorder(BevelBorder.LOWERED)));
		// CompoundBorder是复合Border类，通过将内部Border对象嵌套到
		// 外部Border对象中实现将两个Border对象合并为一个单个边框
		Border outsideBorder = new EtchedBorder();
		Border insideBorder = new LineBorder(Color.RED);
		add(makeBorderPanel(new CompoundBorder(outsideBorder, insideBorder)));
	}

	private static JPanel makeBorderPanel(Border border) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		String className = border.getClass().getName();
		className = className.substring(className.lastIndexOf('.') + 1);
		panel.add(new JLabel(className, JLabel.CENTER), BorderLayout.CENTER);
		panel.setBorder(border);
		return panel;
	}

	private static LayoutManager getGridLayout() {
		GridLayout layout = new GridLayout(2, 4);
		layout.setHgap(10);
		layout.setVgap(10);
		return layout;
	}
}