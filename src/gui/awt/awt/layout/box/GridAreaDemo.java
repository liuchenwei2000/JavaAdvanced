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
 * 固定区演示类
 * 
 * @author 刘晨伟
 *
 * 创建日期：2009-7-15
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
 * 支架只在一个方向上起效果，但固定区(rigid area)可以在两个方向上固定组件之间的空隙。
 */
class GridAreaPanel extends JPanel {

	private static final long serialVersionUID = -2572173658496659347L;

	public GridAreaPanel() {
		Box bv = Box.createVerticalBox(); 
	    bv.add(new JButton("Top")); 
	    // 固定区是有些争议的，因为它使用了绝对值
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