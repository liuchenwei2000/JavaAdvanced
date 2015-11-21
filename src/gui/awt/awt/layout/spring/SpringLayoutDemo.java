package awt.layout.spring;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Spring;
import javax.swing.SpringLayout;

import util.Displayer;

/**
 * 弹簧布局器演示
 * <p>
 * 建议对于复杂的布局还是应该使用网格组布局。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-7-13
 */
public class SpringLayoutDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("SpringLayout Demo", new SpringLayoutPanel());
	}
}

class SpringLayoutPanel extends JPanel {

	private static final long serialVersionUID = -1284238951065109154L;

	private JLabel styleLabel = new JLabel("Font Style：");
	private JLabel sizeLabel = new JLabel("Size：");

	private JComboBox styleBox;
	private JComboBox sizeBox;

	public SpringLayoutPanel() {
		setPreferredSize(new Dimension(300, 200));

		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		this.add(styleLabel);
		this.add(sizeLabel);
		this.add(getStyleBox());
		this.add(getSizeBox());
		// 支柱，用来隔离标签和下拉框
		Spring strut = Spring.constant(10);
		// getConstraints方法将创建一个SpringLayout.Constraints类型的对象
		// 可以将这个对象想象成一个矩形，只是想x、y、宽度和高度的值是弹簧，而不是数字
		// 通过getWidth()得到贯穿组件的宽度
		Spring styleWidth = layout.getConstraints(styleLabel).getWidth();
		Spring sizeWidth = layout.getConstraints(sizeLabel).getWidth();
		// max得到两个弹簧的最大值，其结果是两个弹簧中较长的那个弹簧
		Spring maxWidth = Spring.max(styleWidth, sizeWidth);
		// sum得到两个弹簧的和
		Spring labelsEast = Spring.sum(strut, maxWidth);
		// 使用同一个弹簧能使得两个标签都是右对齐，并且两个标签也是对齐的
		layout.putConstraint(SpringLayout.EAST, styleLabel, labelsEast,
				SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, sizeLabel, labelsEast,
				SpringLayout.WEST, this);
		// 使用同一个弹簧使得标签和下拉框的NORTH边能保持对齐
		layout.putConstraint(SpringLayout.NORTH, styleLabel, strut,
				SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, getStyleBox(), strut,
				SpringLayout.NORTH, this);
		// getConstraint创建一个组件指定边界到达其父级的顶边或左边的弹簧
		Spring styleLabelSouth = layout.getConstraint(SpringLayout.SOUTH,
				styleLabel);
		Spring styleBoxSouth = layout.getConstraint(SpringLayout.SOUTH,
				getStyleBox());
		Spring secondRowNorth = Spring.sum(strut, Spring.max(styleLabelSouth,
				styleBoxSouth));

		layout.putConstraint(SpringLayout.NORTH, sizeLabel, secondRowNorth,
				SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, getSizeBox(), secondRowNorth,
				SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, getStyleBox(), strut,
				SpringLayout.EAST, styleLabel);
		layout.putConstraint(SpringLayout.WEST, getSizeBox(), strut,
				SpringLayout.EAST, sizeLabel);
	}

	private JComboBox getStyleBox() {
		if (styleBox == null) {
			styleBox = new JComboBox(new String[] { "Serif", "SansSerif",
					"Monospaced", "Dialog", "DialogInput" });
			styleBox.setSelectedIndex(0);
		}
		return styleBox;
	}

	private JComboBox getSizeBox() {
		if (sizeBox == null) {
			sizeBox = new JComboBox(new String[] { "14", "12", "10", "8" });
			sizeBox.setSelectedIndex(0);
		}
		return sizeBox;
	}
}
