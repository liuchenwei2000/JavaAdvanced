/**
 * 
 */
package awt.layout;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import util.Displayer;

/**
 * �߽粼�ֹ�����
 * <p>
 * ����JFrame�����ݴ����Ĭ�ϲ��ֹ�������BorderLayout��<p>
 * �ص㣺</br>
 * ����������ѡ��ÿ������ķ���λ�ã�����ѡ���������������ݴ�����С������ϡ�����������
 * �������ʱ���������ȷ����Ե�����ʣ��Ŀ��ÿռ����м����ռ�á�
 * ����������ʱ����Ե����ĺ�Ȳ���ı䣬���м�����Ĵ�С�ᷢ���仯��
 * ����������������ĳߴ��Ա������ÿռ�(�����ֹ�������ÿ�����������ѡ�Ĵ�С)��
 * ������Ҫ�����е�λ�ö�Ҫռ����������ṩ�κ�ֵ��ϵͳĬ��ΪCENTER��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2007-8-21
 */
public class BorderLayoutDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("BorderLayout Demo", new BorderLayoutPanel());
	}
}

class BorderLayoutPanel extends JPanel {
	
	private static final long serialVersionUID = 8810146383997197446L;

	public BorderLayoutPanel() {
		super();
		BorderLayout border = new BorderLayout();
		setLayout(border);
		border.setHgap(10);// �����о�
		border.setVgap(10);// �����о�
		add(new JButton("one"), BorderLayout.EAST);
		add(new JButton("two"), BorderLayout.WEST);
		add(new JButton("three"), BorderLayout.SOUTH);
		add(new JButton("four"), BorderLayout.NORTH);
		add(new JButton("five"), BorderLayout.CENTER);
	}
}