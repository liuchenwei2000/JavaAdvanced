/**
 * 
 */
package swing.panel;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.Displayer;

/**
 * 面板类
 * <p>
 * 可以加载背景图片，使用JLabel方法实现。
 * 
 * @author 刘晨伟
 *
 * 创建日期：2007-10-31
 */
public class JPanelWithImageB {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JLabel实现面板背景图片", new ImagePanelB());
	}
}

class ImagePanelB extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JButton button;
	private JLabel label;
	
	public ImagePanelB() {
		setLayout(null);
		button = new JButton("背景");
		button.setBounds(250, 50, 180, 30);
		add(button);
		ImageIcon image = new ImageIcon("images/gui.swing.panel/duke.gif");
		label = new JLabel(image);
		// 注意高宽的设置
		label.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
		add(label);
		setPreferredSize(new Dimension(400, 300));
	}
}