/**
 * 
 */
package swing.other;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import util.Displayer;

/**
 * JSlider(滑块)演示类
 * <p>
 * JSlider是让用户以图形方式在有界区间内通过移动滑块来选择值的组件。
 * 滑块可以显示主刻度标记和次刻度标记。
 * 
 * @see gui.swing.other.JProgressBarDemo
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-1-20
 */
public class CopyOfJSliderDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JSlider Demo", new JSliderPanel2());
	}
}

class JSliderPanel2 extends JPanel {

	private static final long serialVersionUID = 7379083097908629737L;

	private JSlider slider;// 滑块
	private JTextField textField;// 显示文本框

	public JSliderPanel2() {
		super();
		initUI();
	}

	private void initUI() {
		setLayout(new BorderLayout());
		add(getSlider(), BorderLayout.NORTH);
		add(getTextField(), BorderLayout.SOUTH);
	}

	private JSlider getSlider() {
		if (slider == null) {
			// 从0到100，默认在50
			slider = new JSlider(0, 200, 100);
			// 确定是否在滑块上绘制滑道(刻度)
			slider.setPaintTicks(true);
			slider.setPaintLabels(true);
//			slider.setBorder(
//	                BorderFactory.createEmptyBorder(0,0,10,0));
			// 设置主刻度标记的间隔
			slider.setMajorTickSpacing(100);
			// 设置次刻度标记的间隔
			slider.setMinorTickSpacing(10);
			slider.addChangeListener(new ChangeListener() {

				public void stateChanged(ChangeEvent e) {
					getTextField().setText(getSliderValue());
				}
			});
		}
		return slider;
	}

	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setText(getSliderValue());
		}
		return textField;
	}

	private String getSliderValue() {
		return Integer.toString(getSlider().getValue());
	}
}
