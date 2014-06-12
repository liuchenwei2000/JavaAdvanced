/**
 * 
 */
package awt.layout;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.Displayer;

/**
 * GridLayout演示类
 * <p>
 * GridLayout像电子数据表格一样，按行列排列所有的组件并且它的每个单元大小都一样。
 * 在实际应用中，小的网格(通常只有一行或者一列)在组织窗口的布局区域时比较有用。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2007-8-21
 */
public class GridLayoutDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("GridLayout Demo", new GridLayoutPanel());
	}
}

class GridLayoutPanel extends JPanel {
	
	private static final long serialVersionUID = 5198825895504264700L;

	public GridLayoutPanel() {
		super();
		JPanel buttonPanel = new JPanel();
		GridLayout grid = new GridLayout(3, 2);
		grid.setHgap(10);// 设置行距
		grid.setVgap(10);// 设置列距
		buttonPanel.setLayout(grid);
		buttonPanel.add(new JButton("one"));
		buttonPanel.add(new JButton("two"));
		buttonPanel.add(new JButton("three"));
		buttonPanel.add(new JButton("four"));
		buttonPanel.add(new JButton("five"));

		JPanel labelPanel = new JPanel();
		// 创建具有指定行数和列数的网格布局。给布局中的所有组件分配相等的大小
		// 行数和列数中的一个可以为零(但不能两者同时为零)，这表示可以将任何数目的对象置于行或列中
		GridLayout grid2 = new GridLayout(0, 1);
		grid.setHgap(10);// 设置行距
		grid.setVgap(10);// 设置列距
		labelPanel.setLayout(grid2);
		labelPanel.add(new JLabel("one"));
		labelPanel.add(new JLabel("two"));
		labelPanel.add(new JLabel("three"));
		labelPanel.add(new JLabel("four"));
		labelPanel.add(new JLabel("five"));
		
		setLayout(new GridLayout(2, 1));
		this.add(buttonPanel);
		this.add(labelPanel);
	}
}