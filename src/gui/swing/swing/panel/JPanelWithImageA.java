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
 * �����
 * <p>
 * ���Լ��ر���ͼƬ����дpaintComponent()����ʵ�֡�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2007-10-30
 */
public class JPanelWithImageA {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("��дpaintComponent()ʵ����屳��ͼƬ", new ImagePanelA());
	}
}

class ImagePanelA extends JPanel {

	private static final long serialVersionUID = 1L;

	public ImagePanelA() {
		add(new JButton("����"));
		setPreferredSize(new Dimension(400, 300));
	}
	
	/**
	 * ����������ı���ͼƬ
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	protected void paintComponent(Graphics g) {
		// ͼƬ·��
		Image bkgrdImage = new ImageIcon("images/gui.swing.panel/duke.gif").getImage();
		g.drawImage(bkgrdImage, 0, 0, null);
	}
}