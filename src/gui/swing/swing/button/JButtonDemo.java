/**
 * 
 */
package swing.button;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicArrowButton;

import util.Displayer;

/**
 * 按钮演示
 * <p>
 * Swing提供了很多类型的按钮，包括复选框、单选按钮、菜单项等。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-5-16
 */
public class JButtonDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JButton Demo", new JButtonPanel());
	}
}

class JButtonPanel extends JPanel {

	private static final long serialVersionUID = 5961288201474212404L;

	// 基本的普通按钮
	private JButton jb = new JButton("JButton");
	// 具有两个状态(按下或者弹出)的按钮，JToggleButton子类有JCheckBox、JRadioButton
	private JToggleButton jt = new JToggleButton("JToggleButton");
	// 复选框是一个可以被选定和取消选定的项，它将其状态显示给用户
	private JCheckBox jc = new JCheckBox("JButton");
	// 单选按钮，此按钮项可被选择或取消选择，并可为用户显示其状态
	private JRadioButton jr = new JRadioButton("JButton");

	// JButton对象，它绘制朝某个基本方向的缩放箭头
	private BasicArrowButton up = new BasicArrowButton(BasicArrowButton.NORTH);
	private BasicArrowButton down = new BasicArrowButton(BasicArrowButton.SOUTH);
	private BasicArrowButton right = new BasicArrowButton(BasicArrowButton.EAST);
	private BasicArrowButton left = new BasicArrowButton(BasicArrowButton.WEST);

	public JButtonPanel() {
		setLayout(new FlowLayout());
		add(jb);
		add(jt);
		add(jc);
		add(jr);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder("Directions"));
		panel.add(up);
		panel.add(down);
		panel.add(right);
		panel.add(left);

		add(panel);
	}
}