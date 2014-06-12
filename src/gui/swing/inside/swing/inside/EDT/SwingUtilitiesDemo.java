/**
 * 
 */
package swing.inside.EDT;

import javax.swing.SwingUtilities;

/**
 * SwingUtilities��ʾ��
 * <p>
 * SwingUtilities�����һЩ��̬��������ͬUI�������������
 * <li>invokeLater��������˼�ǣ���EDT��ִ����Runnable���񣬴˷������첽ִ�еģ����ú���������ء�
 * <li>invokeAndWait����������ִ�еģ�����EDT��ִ��Runnable����ֱ������ִ�����ˣ��÷����ŷ��ص����̡߳�
 * <p>
 * �����������������¼��ɷ������е������¼���������֮���ִ�����ǵ�Runnable����
 * Ҳ����˵��������������Runnable��������¼����е�ĩβ��
 * <p>
 * ���棺��Ȼ�����������߳��ϵ���invokeLater��Ҳ������EDT�ϵ��ã�
 * ����ǧ��Ҫ��EDT�ϵ���invokeAndWait�� ��������߳̾���������������
 * <p>
 * SwingUtilities���ڵ����壺������ڷ�EDT�߳��Ϸ���UI����������ͨ�������invoke������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2011-3-29
 */
public class SwingUtilitiesDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			System.out.println("SwingUtilities.invokeLater...");
			SwingUtilities.invokeLater(new LongTimeTask("invokeLater"));
			System.out.println("SwingUtilities.invokeLater complete...");
			System.out.println("SwingUtilities.invokeAndWait...");
			SwingUtilities.invokeAndWait(new LongTimeTask("invokeAndWait"));
			System.out.println("SwingUtilities.invokeAndWait complete...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class LongTimeTask implements Runnable {

	private String name;
	
	public LongTimeTask(String name){
		this.name = name;
	}
	
	public void run() {
		System.out.println(name + " Long Time Task start...");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(name + " Long Time Task complete...");
	}
}