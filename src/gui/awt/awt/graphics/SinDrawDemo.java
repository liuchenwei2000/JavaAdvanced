/**
 * 
 */
package awt.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import util.Displayer;

/**
 * GUI��ܻ�ͼ(��������)��ʾ��
 * <p>
 * �����κλ�ͼ�������������ھ�����ͼλ�õļ���ͨ���ȶԻ�ͼ���ܵĵ���Ҫ���ӵöࡣ
 * ������Щ������򳣳����ͼ�������һ�����Կ���������Ľӿڱ�ʵ����Ҫ��Ҫ�����ӡ�
 * �����κ�ʱ��JComponent�϶����Ի�ͼ����������Ϊ��ˣ����԰����ǵ���������canvas����
 * ���ǣ�Ҫ��ֻ������һ������ֱ�ӻ�ͼ��ƽ��Ļ������͵������Ǵ�JPanel�̳С�
 * Ψһ��Ҫ���صķ�������paintComponent()��������������뱻���»��Ƶ�ʱ�򱻵��á�
 * ͨ������Ϊ�˵��ģ���Ϊ��ʱ������Swing������
 * ���˷���������ʱ��Swing������һ��Graphics����Ȼ��Ϳ���ʹ���������������ϻ����ˡ�
 *
 * @author ����ΰ
 *
 * �������ڣ�2009-5-19
 */
public class SinDrawDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("SinDraw Demo", new SinDrawPanel());
	}
}

class SinDrawPanel extends JPanel {

	private static final long serialVersionUID = 7851869875200638464L;

	private SinDraw sines = new SinDraw();
	private JSlider adjustCycles = new JSlider(1, 30, 5);

	public SinDrawPanel() {
		setLayout(new BorderLayout());
		add(sines, BorderLayout.CENTER);
		adjustCycles.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				sines.setCycles(((JSlider) e.getSource()).getValue());
			}
		});
		add(adjustCycles, BorderLayout.SOUTH);
	}
}

class SinDraw extends JPanel {

	private static final long serialVersionUID = 0L;

	private static final int SCALEFACTOR = 200;
	private int cycles;
	private int points;
	private double[] sines;
	private int[] pts;

	public SinDraw() {
		setCycles(5);
		setPreferredSize(new Dimension(700, 400));
	}

	public void setCycles(int newCycles) {
		cycles = newCycles;
		points = SCALEFACTOR * cycles * 2;
		sines = new double[points];
		for (int i = 0; i < points; i++) {
			double radians = (Math.PI / SCALEFACTOR) * i;
			sines[i] = Math.sin(radians);
		}
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int maxWidth = getWidth();
		double hstep = (double) maxWidth / (double) points;
		int maxHeight = getHeight();
		pts = new int[points];
		for (int i = 0; i < points; i++)
			pts[i] = (int) (sines[i] * maxHeight / 2 * 0.95 + maxHeight / 2);
		g.setColor(Color.BLUE);

		for (int i = 1; i < points; i++) {
			int x1 = (int) ((i - 1) * hstep);
			int x2 = (int) (i * hstep);
			int y1 = pts[i - 1];
			int y2 = pts[i];
			g.drawLine(x1, y1, x2, y2);
		}
	}
}