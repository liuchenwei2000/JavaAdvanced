/**
 * 
 */
package awt.layout.box;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import util.Displayer;

/**
 * �̶�����ʾ��
 * 
 * @author ����ΰ
 *
 * �������ڣ�2009-7-15
 */
public class GridAreaDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("GridArea Demo", new GridAreaPanel());
	}
}

/**
 * ֧��ֻ��һ����������Ч�������̶���(rigid area)���������������Ϲ̶����֮��Ŀ�϶��
 */
class GridAreaPanel extends JPanel {

	private static final long serialVersionUID = -2572173658496659347L;

	public GridAreaPanel() {
		Box bv = Box.createVerticalBox(); 
	    bv.add(new JButton("Top")); 
	    // �̶�������Щ����ģ���Ϊ��ʹ���˾���ֵ
	    bv.add(Box.createRigidArea(new Dimension(120, 90))); 
	    bv.add(new JButton("Bottom")); 
	    
	    Box bh = Box.createHorizontalBox(); 
	    bh.add(new JButton("Left")); 
	    bh.add(Box.createRigidArea(new Dimension(160, 80))); 
	    bh.add(new JButton("Right")); 
	    bv.add(bh); 
		
		setLayout(new BorderLayout());
		this.add(BorderLayout.CENTER, bv);
	}
}