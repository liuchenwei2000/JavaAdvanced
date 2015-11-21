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
 * 支柱演示类
 * 
 * @author 刘晨伟
 *
 * 创建日期：2009-7-15
 */
public class StrutDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("Strut Demo", new StrutPanel());
	}
}

/**
 * 支柱(strut)可以以像素为单位来增加组件之间的空隙。
 * 如果要使用支柱，只需在添加组件的时候，把它加入到要隔开的组件之间即可 。
 */
class StrutPanel extends JPanel {

	private static final long serialVersionUID = -2572173658496659347L;

	public StrutPanel() {
		Box bv = Box.createVerticalBox();
		for (int i = 0; i < 5; i++) {
			bv.add(new JButton("bv " + i));
			bv.add(Box.createVerticalStrut(i * 10));
		}
		Box bh = Box.createHorizontalBox();
		for (int i = 0; i < 5; i++) {
			bh.add(new JButton("bh " + i));
			bh.add(Box.createHorizontalStrut(i * 10));
		}
		setLayout(new BorderLayout());
		this.add(BorderLayout.EAST, bv);
		this.add(BorderLayout.SOUTH, bh);
	}
}
