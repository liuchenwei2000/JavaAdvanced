/**
 * 
 */
package swing.inside.EDT;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * ��ʼ���߳���ʾ
 * <p>
 * ÿһ��JavaӦ�ó��򶼿�ʼ�����߳��е�main������
 * ��Swing�����У����̵߳����������Ǻ̵ܶģ�������EDT�̹߳����û��������˳��ˡ�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2011-3-29
 */
public class InitialThreadTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * �����Ǵ��������UI����ķ�������Υ���˲�Ӧ��EDT��������߳�ͬSwing���������ԭ��
		 * �߳�ͬ��������Ȼ�����������ֳ��������ǻ�Ҫע�����������������
		 */
		new JFrame().setVisible(true);
		// ��������ȷ������UI����ķ���
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				new JFrame().setVisible(true);
			}
		});
	}
}
