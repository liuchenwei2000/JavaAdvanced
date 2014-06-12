/**
 * 
 */
package swing.other;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import util.Displayer;

/**
 * JSlider(����)��ʾ��
 * <p>
 * JSlider�����û���ͼ�η�ʽ���н�������ͨ���ƶ�������ѡ��ֵ�������
 * ���������ʾ���̶ȱ�Ǻʹο̶ȱ�ǡ�
 * 
 * @see gui.swing.other.JProgressBarDemo
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-1-20
 */
public class JSliderDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JSlider Demo", new JSliderPanel());
	}
}

class JSliderPanel extends JPanel {

	private static final long serialVersionUID = 7379083097908629737L;

	private JSlider slider;// ����
	private JTextField textField;// ��ʾ�ı���

	public JSliderPanel() {
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
			// ��0��100��Ĭ����50
			slider = new JSlider(0, 100, 50);
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