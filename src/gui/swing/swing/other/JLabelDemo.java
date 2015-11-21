/**
 * 
 */
package swing.other;

import java.awt.GridLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.Displayer;
import util.ui.BorderFactory;

/**
 * JLabel标签演示类
 * <p>
 * <li>可以显示文本、图像或同时显示二者。
 * <li>可以通过设置垂直和水平对齐方式，指定标签显示区中标签内容在何处对齐。
 * <li>默认情况下，标签在其显示区内垂直居中对齐。
 * <li>默认情况下，只显示文本的标签是开始边对齐，而只显示图像的标签则水平居中对齐 。
 * 
 * @author 刘晨伟
 *
 * 创建日期：2009-5-27
 */
public class JLabelDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JLabel Demo", new JLabelPanel());
	}
}

class JLabelPanel extends JPanel {

	private static final long serialVersionUID = 2172877046353384823L;

	public JLabelPanel(){
		setLayout(new GridLayout(6, 1));
		add(makeLabel("LEFT", JLabel.LEFT));
		add(makeLabel("RIGHT", JLabel.RIGHT));
		add(makeLabel("CENTER", JLabel.CENTER));
		add(makeLabel("LEADING", JLabel.LEADING));
		add(makeLabel("TRAILING", JLabel.TRAILING));
		add(makeLabel("Loading", new ImageIcon("images/gui.swing.other/loading.gif")));
	}
	
	/**
	 * 根据参数创建标签
	 * 
	 * @param name
	 *            标签内容
	 * @param horizontalAlignment
	 *            标签内容水平对齐方式
	 * @see javax.swing.SwingConstants.LEFT
	 *      javax.swing.SwingConstants.CENTER
	 *      javax.swing.SwingConstants.RIGHT
	 *      javax.swing.SwingConstants.LEADING
	 *      javax.swing.SwingConstants.TRAILING
	 */
	private static JLabel makeLabel(String name, int horizontalAlignment) {
		JLabel label = new JLabel(name, horizontalAlignment);
		label.setBorder(BorderFactory.createLineBorder());
		return label;
	}
	
	/**
	 * 根据参数创建标签
	 * 
	 * @param name
	 *            标签内容
	 * @param icon
	 *            标签图标
	 */
	private static JLabel makeLabel(String name, Icon icon) {
		JLabel label = new JLabel(name, icon, JLabel.CENTER);
		label.setBorder(BorderFactory.createLineBorder());
		return label;
	}
}
