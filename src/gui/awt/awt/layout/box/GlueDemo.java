/**
 * 
 */
package awt.layout.box;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import util.Displayer;

/**
 * ��ˮ��ʾ��
 * 
 * @author ����ΰ
 *
 * �������ڣ�2009-7-15
 */
public class GlueDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("Glue Demo", new GluePanel());
	}
}

/**
 * ֧���ܹ�������񿪹̶��ľ��룬��ˮ(glue)�����෴��
 * �������ܵؽ�������뿪����������˵����"��ˮ"������˵����"����"��
 */
class GluePanel extends JPanel {

	private static final long serialVersionUID = -2572173658496659347L;

	public GluePanel() {
		Box bv = Box.createVerticalBox();
		bv.add(new JButton("Hello"));
		bv.add(Box.createVerticalGlue());
		bv.add(new JButton("Java"));
		bv.add(Box.createVerticalGlue());
		bv.add(new JButton("World"));
		
		Box bh = Box.createHorizontalBox();
		bh.add(new JButton("Hello"));
		bh.add(Box.createHorizontalGlue());
		bh.add(new JButton("Java"));
		bh.add(Box.createHorizontalGlue());
		bh.add(new JButton("World"));
		
		setLayout(new BorderLayout());
		this.add(BorderLayout.EAST, bv);
		this.add(BorderLayout.SOUTH, bh);
	}
}