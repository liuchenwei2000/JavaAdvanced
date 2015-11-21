package awt.layout.gridbag;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import util.Displayer;

/**
 * GridBagLayout演示
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-7-9
 */
public class GridBagLayoutDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("GridBagLayout Demo",
				new GridBagLayoutPanel());
	}
}

/**
 * 网格组布局(GridBagLayout)是所有布局管理器之首，可以认为网格组布局是没有限制的网格布局。
 * 在网格组布局中，行和列的尺寸可以改变。可以通过将相邻的单元合并来适应更大的组件。
 * 组件不需要填充整个单元格区域，并可以指定它们在单元格内的对齐方式。
 * <p>
 * 网格组布局可能相当复杂，但是它最灵活且适用范围最广。
 * 使用布局管理器的目的是使得组建的排列在各种字体以及各种操作系统上都能够正常显示。
 * 当需要在水平和垂直方向上把组件排列整齐时，就应该考虑使用GridBagLayout了。
 * <p>
 * GridBagLayout类是一个灵活的布局管理器，它不要求组件的大小相同即可将组件垂直和水平对齐。
 * 每个GridBagLayout对象维持一个动态的矩形单元网格，每个组件占用一个或多个这样的单元，称为显示区域。
 * 每个由GridBagLayout管理的组件都与GridBagConstraints的实例相关联。
 * Constraints对象指定组件在网格中的显示区域以及组件在其显示区域中的放置方式。
 * 除了Constraints对象之外，GridBagLayout还考虑每个组件的最小和首选大小，以确定组件的大小。
 *  <p>
 * 网格的总体方向取决于容器的ComponentOrientation属性</br>
 * 对于水平的从左到右的方向，网格坐标 (0,0) 位于容器的左上角，其中 X 向右递增，Y 向下递增。
 * 对于水平的从右到左的方向，网格坐标 (0,0) 位于容器的右上角，其中 X 向左递增，Y 向下递增。
 */
class GridBagLayoutPanel extends JPanel {

	private static final long serialVersionUID = 3824355385856418695L;

	private JLabel styleLabel = new JLabel("Font Style：");
	private JLabel sizeLabel = new JLabel("Size：");

	private JComboBox styleBox;
	private JComboBox sizeBox;

	private JCheckBox boldBox;
	private JCheckBox italicBox;

	private JTextArea textArea;

	private ActionListener fontListener = new FontActionListener();

	/**
	 * 建立一个网格组布局的方法：<p>
	 * <li>1，在纸上画出组件布局草图；
	 * <li>2，找出一种网格，小组件可以容纳在一个单元格内，大组件将占用多个单元格；
	 * <li>3，使用0，1，2...标识网格的行和列，就可以读取出gridx、gridy、gridwidth和gridweight的值；
	 * <li>4，对于每个组件，需要考虑是否需要水平或垂直填充它所在的单元格？如果不需要又如何排列？也就是确定fill和anchor参数；
	 * <li>5，将所有的增量设置为100，如果需要某一行或一列保持默认的尺寸，就将该行或该列中的所有组件的weightx和weighty设置为0；
	 * <li>6，编译运行，不断地调整代码。
	 */
	public GridBagLayoutPanel() {
		setPreferredSize(new Dimension(300, 200));
		/*
		 * 要想使用网格组布局管理器进行布局，必须经过下列过程：
		 * 1，建立GridBagLayout类型的对象，不需要指定网格的行数和列数，布局管理器会根据后面所给的信息猜测出来
		 * 2，把GridBagLayout对象设置成组件的布局管理器
		 * 3，为每个组件建立一个GridBagConstraints类型的对象，设置GridBagConstraints对象的字段值以便制定组件在网格组中的布局方案
		 * 4，最后通过下面的调用添加组件的约束：add(component,constraints);
		 */
		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);
		/*
		 * 如何设置GridBagConstraints对象的状态是非常重要的：
		 * 1，gridx、gridy、gridwidth和gridheight参数
		 * 这些约束定义了组件在网格中的位置
		 * 
		 * gridx和gridy指定了被添加组件左上角的行和列的位置
		 * 指定包含组件显示区域的前导角的单元，在此显示区域中，位于网格原点的单元地址是gridx = 0、gridy = 0
		 * 对于水平的从左到右的布局，组件的前导角是其左上角。对于水平的从右到左的布局，组件的前导角是其右上角
		 * 使用GridBagConstraints.RELATIVE(默认值)指定将组件置于添加此组件前刚刚添加到容器组件的后面(沿gridx的X轴或gridy的Y轴)
		 * 
		 * gridwidth和gridheight值决定了组件占用的行数和列数
		 * 指定组件的显示区域中行(针对gridwidth)或列(针对gridheight)中的单元数，默认值为1
		 * 使用GridBagConstraints.REMAINDER指定组件的显示区域为从gridx到该行(针对gridwidth)中的最后一个单元
		 * 或者从gridy到该列(针对gridheight)中的最后一个单元
		 * 使用GridBagConstraints.RELATIVE指定组件的显示区域为从gridx到其所在行(针对gridwidth)的倒数第二个单元
		 * 或者从gridy到其所在列(针对gridheight)的倒数第二个单元
		 * 
		 * 2，增量字段weightx和weighty
		 * 在网格组布局中需要为每个区域设置增量字段，如果增量字段为0，则这个区域永远为初始尺寸(即当窗口扩大或缩小时组件的大小始终保持不变)
		 * 如果想让一行或一列的大小保持不变，就需要把该行、该列的所有组件的增量设置为0
		 * 增量并不实际给出列的相对大小。当容器超过它的首选大小时，增量表示分配给每个区域的扩展比例值
		 * 建议把所有的增量设置为100，运行程序查看一下布局情况
		 * 缩放对话框查看一下行和列是如何调整的，如果发现某行或某列不应该扩大，就把所有的组件增量设置为0
		 * 
		 * 3，fill和anchor参数
		 * 当组件的显示区域大于组件的所需大小时，就需要设置fill约束，用于确定是否(以及如何)调整组件
		 * 可能的值为GridBagConstraints.NONE(默认值)、HORIZONTAL(加宽组件直到它足以在水平方向上填满其显示区域，但不更改其高度)、
		 * VERTICAL(加高组件直到它足以在垂直方向上填满其显示区域，但不更改其宽度)和 BOTH(使组件完全填满其显示区域) 
		 * 
		 * 如果组件没有填充整个区域，可以通过设置anchor字段来指定其位置：
		 * GridBagConstraints.NORTH、SOUTH、WEST、EAST、NORTHWEST、NORTHEAST、SOUTHWEST、SOUTHEAST、CENTER(默认)
		 * 
		 * 4，填塞
		 * 通过设置GridBagLayout的insets字段来在组件周围增加附加的空白区域
		 * 通过设置insets对象的left、top、right和bottom值来设置组件周围的空间量，这被称作外部填塞
		 * 用ipadx和ipady值设置内部填塞，这两个值将被添加到组件的最小宽度和最小高度上，这样可以保证组件不会收缩至最小尺寸之下
		 */
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 100;
		gbc.weighty = 100;
		add(styleLabel, gbc, 0, 0, 1, 1);
		add(sizeLabel, gbc, 0, 1, 1, 1);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0;
		gbc.weighty = 100;
		add(getStyleBox(), gbc, 1, 0, 1, 1);
		add(getSizeBox(), gbc, 1, 1, 1, 1);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = 100;
		add(getBoldBox(), gbc, 1, 2, 1, 1);
		add(getItalicBox(), gbc, 1, 3, 1, 1);
//		gbc.fill = GridBagConstraints.BOTH;
//		gbc.weightx = 100;
//		gbc.weighty = 100;
//		add(getTextArea(), gbc, 2, 0, 1, 4);
		// 使用了辅助类GBC设置Constraints，效果如上注释部分
		add(getTextArea(), new GBC(2, 0, 1, 4).setFill(GridBagConstraints.BOTH)
				.setWeight(100, 100));
	}

	private JComboBox getStyleBox() {
		if (styleBox == null) {
			styleBox = new JComboBox(new String[] { "Serif", "SansSerif",
					"Monospaced", "Dialog", "DialogInput" });
			styleBox.addActionListener(fontListener);
			styleBox.setSelectedIndex(0);
		}
		return styleBox;
	}

	private JComboBox getSizeBox() {
		if (sizeBox == null) {
			sizeBox = new JComboBox(new String[] { "14", "12", "10", "8" });
			sizeBox.addActionListener(fontListener);
			sizeBox.setSelectedIndex(0);
		}
		return sizeBox;
	}

	private JCheckBox getBoldBox() {
		if (boldBox == null) {
			boldBox = new JCheckBox("Bold");
			boldBox.addActionListener(fontListener);
		}
		return boldBox;
	}

	private JCheckBox getItalicBox() {
		if (italicBox == null) {
			italicBox = new JCheckBox("Italic");
			italicBox.addActionListener(fontListener);
		}
		return italicBox;
	}

	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea(20, 15);
			textArea.setText("The quick brown fox jumps over the lazy dog.");
			textArea.setEditable(false);
			textArea.setLineWrap(true);
			textArea.setBorder(BorderFactory.createEtchedBorder());
		}
		return textArea;
	}

	private void add(Component c, GridBagConstraints gbc, int x, int y, int w,
			int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		add(c, gbc);
	}

	private void updateFont() {
		String name = getStyleBox().getSelectedItem().toString();
		int bold = getBoldBox().isSelected() ? Font.BOLD : Font.PLAIN;
		int italic = getItalicBox().isSelected() ? Font.ITALIC : Font.PLAIN;
		int style = bold + italic;
		int size = Integer.parseInt(getSizeBox().getSelectedItem().toString());
		Font font = new Font(name, style, size);
		getTextArea().setFont(font);
		repaint();
	}

	private class FontActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			updateFont();
		}

	}
}

/**
 * 网格组约束帮助类
 * <p>
 * 网格组布局最乏味的工作就是为设置约束编写代码，所以可以使用帮助类来协助管理约束。<p>
 * 这个帮助类有如下特性：</br>
 * <li>1，名字简短：GBC替代GridBagConstraints
 * <li>2，扩展于GridBagConstraints，因此可以使用约束的缩写，如GBC.EAST
 * <li>3，添加组件和约束时，可以使用GBC对象，如：add(component,new GBC(1,2));
 * <li>4，有两个构造器可以设置最通常的参数：gridx和girdy或者gridx、girdy、gridwidth和gridheight
 * <li>5，字段有方便的setter，x/y值对：add(component,new GBC(1,2).setWeight(100,100));
 * <li>6，setter方法将返回this，所以可以链接它们：</br>
 * add(component,new GBC(1,2).setAnchor(GBC.EAST).setWight(100,100));
 * <li>7，setInsets方法将构造一个Inset对象，要想获取一个像素的insets，可以调用：</br>
 * add(component,new GBC(1,2).setInsets(1));
 */
class GBC extends GridBagConstraints {

	private static final long serialVersionUID = 8541774806056282157L;

	public GBC(int gridx, int gridy) {
		this.gridx = gridx;
		this.gridy = gridy;
	}

	public GBC(int gridx, int gridy, int gridwidth, int gridheight) {
		this.gridx = gridx;
		this.gridy = gridy;
		this.gridwidth = gridwidth;
		this.gridheight = gridheight;
	}

	public GBC setAnchor(int anchor) {
		this.anchor = anchor;
		return this;
	}

	public GBC setFill(int fill) {
		this.fill = fill;
		return this;
	}

	public GBC setWeight(double weightx, double weighty) {
		this.weightx = weightx;
		this.weighty = weighty;
		return this;
	}

	public GBC setInsets(int distance) {
		this.insets = new Insets(distance, distance, distance, distance);
		return this;
	}

	public GBC setIpad(int ipadx, int ipady) {
		this.ipadx = ipadx;
		this.ipady = ipady;
		return this;
	}
}
