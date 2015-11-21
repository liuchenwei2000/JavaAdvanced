/**
 * 
 */
package swing.panel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import util.Displayer;

/**
 * 面板类
 * <p>
 * 可以加载背景图片，重写paintComponent()方法实现。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2007-10-30
 */
public class JPanelWithImageA {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("重写paintComponent()实现面板背景图片", new ImagePanelA());
	}
}

class ImagePanelA extends JPanel {

	private static final long serialVersionUID = 1L;

	public ImagePanelA() {
		add(new JButton("背景"));
		setPreferredSize(new Dimension(400, 300));
	}
	
	/**
	 * 用于描绘面板的背景图片
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	protected void paintComponent(Graphics g) {
		// 图片路径
		Image bkgrdImage = new ImageIcon("images/gui.swing.panel/duke.gif").getImage();
		g.drawImage(bkgrdImage, 0, 0, null);
	}
}
