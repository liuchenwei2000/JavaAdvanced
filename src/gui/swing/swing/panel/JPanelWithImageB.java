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
 * �����
 * <p>
 * ���Լ��ر���ͼƬ��ʹ��JLabel����ʵ�֡�
 * 
 * @author ����ΰ
 *
 * �������ڣ�2007-10-31
 */
public class JPanelWithImageB {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JLabelʵ����屳��ͼƬ", new ImagePanelB());
	}
}

class ImagePanelB extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JButton button;
	private JLabel label;
	
	public ImagePanelB() {
		setLayout(null);
		button = new JButton("����");
		button.setBounds(250, 50, 180, 30);
		add(button);
		ImageIcon image = new ImageIcon("images/gui.swing.panel/duke.gif");
		label = new JLabel(image);
		// ע��߿������
		label.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
		add(label);
		setPreferredSize(new Dimension(400, 300));
	}
}