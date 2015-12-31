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
 * Swing中使用多线程示例
 * <p>
 * 将线程与Swing一起使用时，必须遵循两个简单的原则：
 * <li>1，如果一个动作需要花费很长时间，在一个独立的工作器线程中做这件事而不要在事件分派线程（Event Dispatcher Thread，EDT）中做。
 * <li>2，除了事件分派线程，不要在任何线程中接触Swing组件。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月3日
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
			button = new JButton("执行耗时任务");
			button.addActionListener(new LongTaskAction());
		}
		return button;
	}

	private class LongTaskAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// 遵循第一个原则，另起一个线程执行耗时操作，这样EDT线程就可以立即返回，界面不会卡住
			new Thread(new LongTask()).start();
		}
	}
	
	private class LongTask implements Runnable {

		@Override
		public void run() {
			// 模拟耗时操作
			double random = Math.random();
			final int totalTime = (int) (5000 * random);
			try {
				Thread.sleep(totalTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// 遵循第二个原则，操作Swing控件要在EDT线程中进行，也就是使用SwingUtilities.invokeXXX类方法
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
				Displayer.createAndShowGUI("线程与Swing示例", new LongTaskUI());
			}
		});
	}
}
