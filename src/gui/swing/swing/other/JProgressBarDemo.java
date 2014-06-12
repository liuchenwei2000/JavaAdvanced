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
 * JProgressBar��ʾ��
 * <p>
 * JProgressBar��������Ĭ����ʾ�н���������ֵ�������
 * ������ͨ��ͨ����ʾĳ����������ɰٷֱȣ������Ǵ˰ٷֱȵ�һ���ı���ʾ����������ȡ�
 * Ҫָʾ����ִ��һ��δ֪���ȵ����񣬿��Խ�����������Ϊ��ȷ��ģʽ��
 * ��ȷ��ģʽ�Ľ�������������ʾ��������ʾ�����еĲ�����
 * һ������ȷ�����񳤶Ⱥͽ���������Ӧ�ø��½�������ֵ�������л���ȷ��ģʽ��
 *
 * @author ����ΰ
 *
 * �������ڣ�2009-5-20
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
	// ��ָ���ķ����ָ������Сֵ�����ֵ�Լ���ʼֵ����һ������
	private JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 60);

	public JProgressBarPanel() {
		setLayout(new BorderLayout());
		add(progressBar, BorderLayout.NORTH);
		// ���û���ĵ�ǰֵ
		slider.setValue(0);
		// ȷ���Ƿ��ڻ����ϻ��ƻ���(�̶�)
		slider.setPaintTicks(true);
		// �������̶ȱ�ǵļ��
		slider.setMajorTickSpacing(20);
		// ���ôο̶ȱ�ǵļ��
		slider.setMinorTickSpacing(5);
		slider.setBorder(new TitledBorder("Slide Me"));
		// ���������ϵ��һ��Ĺؼ����������ǹ���һ��ģ��
		// BoundedRangeModel��JSlider��JProgressBar�������ʹ�õ�����ģ��
		progressBar.setModel(slider.getModel());
		add(slider, BorderLayout.SOUTH);
	}
}