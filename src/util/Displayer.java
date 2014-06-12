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
 * ��ʾ��
 * 
 * ����ֱ����JFrame��ʽչ��JPanel
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-2-25
 */
public class Displayer {

	/**
	 * �����������
	 * 
	 * @param comp
	 *            ���
	 */
	public static void setCenter(Component comp) {
		int windowWidth = comp.getWidth(); // ��ô��ڿ�
		int windowHeight = comp.getHeight(); // ��ô��ڸ�
		Toolkit kit = Toolkit.getDefaultToolkit(); // ���幤�߰�
		Dimension screenSize = kit.getScreenSize(); // ��ȡ��Ļ�ĳߴ�
		int screenWidth = screenSize.width; // ��ȡ��Ļ�Ŀ�
		int screenHeight = screenSize.height; // ��ȡ��Ļ�ĸ�
		comp.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2
				- windowHeight / 2);// ���ô��ھ�����ʾ
	}

	/**
	 * ��������չʾ����(ʹ��JFrame)
	 * 
	 * @param title
	 *            ����
	 * @param comp
	 *            չʾ�����
	 */
	public static void createAndShowGUI(String title, JComponent comp) {
		BasicFrame frame = new BasicFrame(title);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(comp, BorderLayout.CENTER);
		frame.setVisible(true);
	}
	
	/**
	 * ��ʾJFrame
	 */
	public static void display(JFrame frame, int width, int height) {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		setCenter(frame);
		frame.setVisible(true);
	}
	
	/**
	 * ��ʾJApplet
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
	 * ��ʾJPanel
	 */
	public static void display(JPanel panel, int width, int height) {
		BasicFrame frame = new BasicFrame(getClassName(panel));
		frame.getContentPane().add(panel);
		frame.setSize(width, height);
		frame.setVisible(true);
	}
	
	/**
	 * ���ز���������������
	 */
	private static String getClassName(Object object){
		if(object == null) return null;
		return object.getClass().getName();
	}
}