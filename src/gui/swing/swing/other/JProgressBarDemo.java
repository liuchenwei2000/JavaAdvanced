/**
 * 
 */
package swing.other;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;

import util.Displayer;

/**
 * JProgressBar演示类
 * <p>
 * JProgressBar进度条是默认显示有界区间整数值的组件。
 * 进度条通常通过显示某个操作的完成百分比，可能是此百分比的一个文本显示来传达其进度。
 * 要指示正在执行一个未知长度的任务，可以将进度条设置为不确定模式。
 * 不确定模式的进度条持续地显示动画来表示正进行的操作。
 * 一旦可以确定任务长度和进度量，则应该更新进度条的值，将其切换回确定模式。
 *
 * @author 刘晨伟
 *
 * 创建日期：2009-5-20
 */
public class JProgressBarDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JProgressBar Demo", new JProgressBarPanel());
	}
}

class JProgressBarPanel extends JPanel {

	private static final long serialVersionUID = 2846187733969733118L;

	private JProgressBar progressBar = new JProgressBar();
	// 用指定的方向和指定的最小值、最大值以及初始值创建一个滑块
	private JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 60);

	public JProgressBarPanel() {
		setLayout(new BorderLayout());
		add(progressBar, BorderLayout.NORTH);
		// 设置滑块的当前值
		slider.setValue(0);
		// 确定是否在滑块上绘制滑道(刻度)
		slider.setPaintTicks(true);
		// 设置主刻度标记的间隔
		slider.setMajorTickSpacing(20);
		// 设置次刻度标记的间隔
		slider.setMinorTickSpacing(5);
		slider.setBorder(new TitledBorder("Slide Me"));
		// 两个组件联系到一起的关键在于让它们共享一个模型
		// BoundedRangeModel是JSlider和JProgressBar这类组件使用的数据模型
		progressBar.setModel(slider.getModel());
		add(slider, BorderLayout.SOUTH);
	}
}