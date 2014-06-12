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
 * 胶水演示类
 * 
 * @author 刘晨伟
 *
 * 创建日期：2009-7-15
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
 * 支架能够把组件格开固定的距离，胶水(glue)正好相反。
 * 它尽可能地将组件分离开，所以与其说它是"胶水"，不如说它是"弹簧"。
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