/**
 * 
 */
package swing.frame;

import javax.swing.JFrame;

import util.Displayer;

/**
 * ����Frame
 * <p>
 * JFrame�ĳ��÷�����
 * <p>
 * <li>dispose�������ڹرմ��ڣ������մ���������ʹ�õ�ȫ��ϵͳ��Դ��
 * <li>setIconImage�������ڽ�������С��ʱ��ͼ������Ϊָ����image����
 * <li>setResizable��������һ��booleanֵȷ����ܵĴ�С�Ƿ������û��ı䡣
 * <li>setLocation���������ض�λ�����λ�á�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-2-25
 */
public class BasicFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4678152379795505698L;

	public BasicFrame() {
		super();
		initUI();
	}

	public BasicFrame(String title) {
		super(title);
		initUI();
	}

	private void initUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Ĭ��Ϊ������ʾ
	 */
	public void setVisible(boolean b) {
		pack();
		Displayer.setCenter(this);
		super.setVisible(b);
	}
}