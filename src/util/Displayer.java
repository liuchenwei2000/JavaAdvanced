/**
 * 
 */
package util;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JApplet;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import swing.frame.BasicFrame;

/**
 * 显示器
 * 
 * 用于直接以JFrame形式展现JPanel
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-2-25
 */
public class Displayer {

	/**
	 * 设置组件居中
	 * 
	 * @param comp
	 *            组件
	 */
	public static void setCenter(Component comp) {
		int windowWidth = comp.getWidth(); // 获得窗口宽
		int windowHeight = comp.getHeight(); // 获得窗口高
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
		int screenWidth = screenSize.width; // 获取屏幕的宽
		int screenHeight = screenSize.height; // 获取屏幕的高
		comp.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2
				- windowHeight / 2);// 设置窗口居中显示
	}

	/**
	 * 创建并且展示界面(使用JFrame)
	 * 
	 * @param title
	 *            标题
	 * @param comp
	 *            展示的组件
	 */
	public static void createAndShowGUI(String title, JComponent comp) {
		BasicFrame frame = new BasicFrame(title);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(comp, BorderLayout.CENTER);
		frame.setVisible(true);
	}
	
	/**
	 * 显示JFrame
	 */
	public static void display(JFrame frame, int width, int height) {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		setCenter(frame);
		frame.setVisible(true);
	}
	
	/**
	 * 显示JApplet
	 */
	public static void display(JApplet applet, int width, int height) {
		BasicFrame frame = new BasicFrame(getClassName(applet));
		frame.getContentPane().add(applet);
		frame.setSize(width, height);
		applet.init();
		applet.start();
		frame.setVisible(true);
	}
	
	/**
	 * 显示JPanel
	 */
	public static void display(JPanel panel, int width, int height) {
		BasicFrame frame = new BasicFrame(getClassName(panel));
		frame.getContentPane().add(panel);
		frame.setSize(width, height);
		frame.setVisible(true);
	}
	
	/**
	 * 返回参数对象所属类名
	 */
	private static String getClassName(Object object){
		if(object == null) return null;
		return object.getClass().getName();
	}
}