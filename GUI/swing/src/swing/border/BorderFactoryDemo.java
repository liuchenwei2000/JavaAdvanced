package swing.border;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

import util.Displayer;

/**
 * BorderFactory演示类
 * <p>
 * BorderFactory提供了创建常用边框的静态方法。
 * <p>
 * 风格如下：</br>
 * 凹斜面、凸斜面、蚀刻、直线、不光滑、空边框、组合边框。
 * <p>
 * 为JComponent设置边框时一般有两种方法：</br>
 * <li>1，使用BorderFactory来创建边框对象
 * <li>2，直接 new 边框对象
 * </br>总的来说，使用BorderFactory能够获得更多样的边框。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-6-25
 */
public class BorderFactoryDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("BorderFactory Demo",
				new BorderFactoryPanel());
	}
}

class BorderFactoryPanel extends JPanel {

	private static final long serialVersionUID = -4932355246858608900L;

	private static final int WIDTH = 600;
	private static final int HEIGHT = 200;

	private JPanel demoPanel;
	private JPanel buttonPanel;
	private ButtonGroup group;

	public BorderFactoryPanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		// 凹斜面：创建一个具有凹入斜面边缘的边框，将组件当前背景色的较亮的色度用于突出显示，较暗的色度用于阴影
		addRadioButton("Lowered bevel", BorderFactory.createLoweredBevelBorder());
		// 凸斜面：创建一个具有凸出斜面边缘的边框，将组件当前背景色的较亮的色度用于突出显示，较暗的色度用于阴影
		addRadioButton("Raised bevel", BorderFactory.createRaisedBevelBorder());
		// 蚀刻：创建一个具有"浮雕化"外观效果的边框，将组件的当前背景色用于突出显示和阴影显示
		addRadioButton("Etched", BorderFactory.createEtchedBorder());
		// 直线：创建一个具有指定颜色的线边框
		addRadioButton("Line", BorderFactory.createLineBorder(Color.BLUE));
		// 不光滑：使用纯色创建一个类似衬边的边框(某个方向的参数为0时即代表那个方向的边框不显示)
		addRadioButton("Matte", BorderFactory.createMatteBorder(10, 1, 10, 0, Color.BLUE));
		// 空边框：创建一个不占用空间的空边框
		addRadioButton("Empty", BorderFactory.createEmptyBorder());
		// 组合边框：创建一个合成边框，指定了用于外部和内部的边框对象
		addRadioButton("Compound", BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.BLUE), BorderFactory
						.createEtchedBorder()));
		
		Border etched = BorderFactory.createEtchedBorder();
		// 向现有边框添加一个标题，并指定了标题文本
		Border titled = BorderFactory.createTitledBorder(etched, "Border types");
		getButtonPanel().setBorder(titled);

		setLayout(new GridLayout(2, 1));
		add(getButtonPanel());
		// 再使用一个panel来加载是为了使得边框的效果更直观
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout());
		panel.add(getDemoPanel());
		add(panel);
	}

	private void addRadioButton(String buttonName, final Border b) {
		JRadioButton button = new JRadioButton(buttonName);
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				getDemoPanel().setBorder(b);
				validate();
			}
		});
		getButtonGroup().add(button);
		getButtonPanel().add(button);
	}

	private JPanel getDemoPanel() {
		if (demoPanel == null) {
			demoPanel = new JPanel();
		}
		return demoPanel;
	}

	private JPanel getButtonPanel() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
		}
		return buttonPanel;
	}

	private ButtonGroup getButtonGroup() {
		if (group == null) {
			group = new ButtonGroup();
		}
		return group;
	}
}
