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
 * GridLayout��ʾ��
 * <p>
 * GridLayout��������ݱ��һ�����������������е������������ÿ����Ԫ��С��һ����
 * ��ʵ��Ӧ���У�С������(ͨ��ֻ��һ�л���һ��)����֯���ڵĲ�������ʱ�Ƚ����á�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2007-8-21
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
		grid.setHgap(10);// �����о�
		grid.setVgap(10);// �����о�
		buttonPanel.setLayout(grid);
		buttonPanel.add(new JButton("one"));
		buttonPanel.add(new JButton("two"));
		buttonPanel.add(new JButton("three"));
		buttonPanel.add(new JButton("four"));
		buttonPanel.add(new JButton("five"));

		JPanel labelPanel = new JPanel();
		// ��������ָ�����������������񲼾֡��������е��������������ȵĴ�С
		// �����������е�һ������Ϊ��(����������ͬʱΪ��)�����ʾ���Խ��κ���Ŀ�Ķ��������л�����
		GridLayout grid2 = new GridLayout(0, 1);
		grid.setHgap(10);// �����о�
		grid.setVgap(10);// �����о�
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