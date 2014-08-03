/**
 * 
 */
package swing.inside.EDT;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import util.Displayer;

/**
 * Swing��ʹ�ö��߳�ʾ��
 * <p>
 * ���߳���Swingһ��ʹ��ʱ��������ѭ�����򵥵�ԭ��
 * <li>1�����һ��������Ҫ���Ѻܳ�ʱ�䣬��һ�������Ĺ������߳���������¶���Ҫ���¼������̣߳�Event Dispatcher Thread��EDT��������
 * <li>2�������¼������̣߳���Ҫ���κ��߳��нӴ�Swing�����
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��3��
 */
public class LongTaskUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3677153560847087887L;
	
	private JTextArea textArea;
	private JButton button;

	public LongTaskUI() {
		super();
		initUI();
	}

	private void initUI() {
		setPreferredSize(new Dimension(400, 300));
		setLayout(new BorderLayout());
		add(getTextArea(), BorderLayout.CENTER);
		add(getButton(), BorderLayout.SOUTH);
	}

	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
		}
		return textArea;
	}

	private JButton getButton() {
		if (button == null) {
			button = new JButton("ִ�к�ʱ����");
			button.addActionListener(new LongTaskAction());
		}
		return button;
	}

	private class LongTaskAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// ��ѭ��һ��ԭ������һ���߳�ִ�к�ʱ����������EDT�߳̾Ϳ����������أ����治�Ῠס
			new Thread(new LongTask()).start();
		}
	}
	
	private class LongTask implements Runnable {

		@Override
		public void run() {
			// ģ���ʱ����
			double random = Math.random();
			final int totalTime = (int) (5000 * random);
			try {
				Thread.sleep(totalTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// ��ѭ�ڶ���ԭ�򣬲���Swing�ؼ�Ҫ��EDT�߳��н��У�Ҳ����ʹ��SwingUtilities.invokeXXX�෽��
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					getTextArea().append("Long task costs " + (totalTime/1000.0) + " s.\n");
				}
			});
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				Displayer.createAndShowGUI("�߳���Swingʾ��", new LongTaskUI());
			}
		});
	}
}
