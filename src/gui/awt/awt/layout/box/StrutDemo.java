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
 * ֧����ʾ��
 * 
 * @author ����ΰ
 *
 * �������ڣ�2009-7-15
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
 * ֧��(strut)����������Ϊ��λ���������֮��Ŀ�϶��
 * ���Ҫʹ��֧����ֻ������������ʱ�򣬰������뵽Ҫ���������֮�伴�� ��
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