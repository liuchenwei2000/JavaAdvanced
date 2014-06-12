/**
 * 
 */
package swing.border;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import util.Displayer;

/**
 * �߿���ʾ��
 * <p>
 * JComponent��һ��setBorder()����������Ϊ�κο���������ø��ֱ߿�
 *
 * @author ����ΰ
 *
 * �������ڣ�2009-5-17
 */
public class BorderDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("Border Demo", new BorderPanel());
	}
}

class BorderPanel extends JPanel {

	private static final long serialVersionUID = -6413920779401835747L;

	public BorderPanel() {
		setLayout(getGridLayout());
		// TitledBorderʵ����ָ��λ����ָ�����뷽ʽ��ʾ�ַ������������߿�
		add(makeBorderPanel(new TitledBorder("Title")));
		// EtchedBorderʵ�ּ򵥵ĸ��񻯱߿����ȿ��������̸��񻯱߿�Ҳ���������̸��񻯱߿�
		add(makeBorderPanel(new EtchedBorder()));
		add(makeBorderPanel(new EtchedBorder(EtchedBorder.RAISED)));
		// LineBorderʵ�ֵ�ɫ���������߱߿����
		add(makeBorderPanel(new LineBorder(Color.BLUE)));
		// MatteBorder�ṩ���Ƴıߵı߿򣬳ı�ͼ���ȿ����Ǵ�ɫҲ������ƽ�̵�ͼ��
		add(makeBorderPanel(new MatteBorder(5, 5, 30, 30, Color.GREEN)));
		// BevelBorderʵ�ּ򵥵�˫��б��߿� 
		add(makeBorderPanel(new BevelBorder(BevelBorder.RAISED)));
		// SoftBevelBorderʵ��ĳ��б����࣬����б��Ҫô͹��Ҫô�����ҹս�Բ��
		add(makeBorderPanel(new SoftBevelBorder(BevelBorder.LOWERED)));
		// CompoundBorder�Ǹ���Border�࣬ͨ�����ڲ�Border����Ƕ�׵�
		// �ⲿBorder������ʵ�ֽ�����Border����ϲ�Ϊһ�������߿�
		Border outsideBorder = new EtchedBorder();
		Border insideBorder = new LineBorder(Color.RED);
		add(makeBorderPanel(new CompoundBorder(outsideBorder, insideBorder)));
	}

	private static JPanel makeBorderPanel(Border border) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		String className = border.getClass().getName();
		className = className.substring(className.lastIndexOf('.') + 1);
		panel.add(new JLabel(className, JLabel.CENTER), BorderLayout.CENTER);
		panel.setBorder(border);
		return panel;
	}

	private static LayoutManager getGridLayout() {
		GridLayout layout = new GridLayout(2, 4);
		layout.setHgap(10);
		layout.setVgap(10);
		return layout;
	}
}