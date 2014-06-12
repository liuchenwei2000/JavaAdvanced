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
 * GUI框架绘图(正弦曲线)演示类
 * <p>
 * 对于任何绘图程序，其问题在于决定绘图位置的计算通常比对绘图功能的调用要复杂得多。
 * 并且这些计算程序常常与绘图程序混在一起，所以看起来程序的接口比实际需要的要更复杂。
 * 尽管任何时候JComponent上都可以绘图，而且正因为如此，可以把它们当作画布（canvas）。
 * 但是，要是只是想有一个可以直接绘图的平面的话，典型的做法是从JPanel继承。
 * 唯一需要重载的方法就是paintComponent()，它将在组件必须被重新绘制的时候被调用。
 * 通常不必为此担心，因为何时调用由Swing决定。
 * 当此方法被调用时，Swing将传入一个Graphics对象，然后就可以使用这个对象并在组件上绘制了。
 *
 * @author 刘晨伟
 *
 * 创建日期：2009-5-19
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