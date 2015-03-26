/**
 * 
 */
package applet;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Appletʾ��
 * <p>
 * ��ҳ�е�appletͨ�����ܷ��ʱ��ش��̡�
 *
 * @author ����ΰ
 *
 * �������ڣ�2008-3-18
 */
public class AppletDemo extends JApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6947418109117870067L;

	/**
	 * ���Զ����ã�������applet�����״γ�ʼ�������а���������֣��������Ǹ��Ǵ˷�����
	 */
	public void init() {
		getContentPane().add(new JLabel("This is a applet!", JLabel.CENTER));
	}

	/**
	 * ÿ�� applet ���� Web �������Ұ��ʱ�򣬴˷��������ã�ʹ�� applet �ܿ������ĳ��湦�ܣ������Ǳ�stop()�رյĹ��ܣ���
	 * ����init()֮�󱻵��ã����û�������ҳ�淵�ص����� applet ��ҳ��ʱ��Ҳ����ø÷�����
	 * �����ζ�ſ��ܻ��ε���start��������init����ֻ�ܵ���һ�Ρ�
	 * �������ԭ�򣬿��԰�ϣ��ִֻ��һ�εĴ�����õ�init�����У������Ƿ���start�С�
	 * start ��������Ϊ applet ��������һ���̡߳�
	 * ���applet���û��뿪��ǰҳ��ʱû��ʲô��Ҫ����ģ���û�б�Ҫʵ�ָ÷���������stop��������
	 */
	public void start() {
		super.start();
	}
	
	/**
	 * ÿ��applet�뿪Web�������Ұ��ʱ�򣬴˷��������ã�ʹ�� applet �ܹر�����"����"����������Ƶ��Ƶ���ţ���
	 * ��Ҳ��destroy()֮ǰ�����á�
	 */
	public void stop(){
		super.stop();
	}

	/**
	 * �� applet���ٱ�ʹ�á�Ҫ����ҳ��ж�� applet �������ͷ���Դ��ʱ�򣬴˷��������á�
	 */
	public void destroy() {
		super.destroy();
	}
	
	/**
	 * ��Applet�м�����main����֮��ʹ�ã�������ͬʱ��ΪӦ�ó���� applet ���С�
	 */
	public static void main(String[] args) {
		JApplet applet = new AppletDemo();
		JFrame frame = new JFrame("AppletDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(applet);
		frame.setSize(100, 50);
		applet.init();
		applet.start();
		frame.setVisible(true);
	}
}