/**
 * 
 */
package swing.inside.swingworker;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextPane;
import javax.swing.SwingWorker;

import util.Displayer;

/**
 * SwingWorker类的基本演示
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2011-4-1
 */
public class SwingWorkerBasicDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("SwingWorkerDemo", new LongTaskPanel());
	}

}

class LongTaskPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton button;
	private JProgressBar progressBar;
	private JTextPane textPane;

	public LongTaskPanel() {
		super();
		initUI();
	}

	/**
	 * 长时间任务
	 * <p>
	 * SwingWorker是在专用线程中执行长时间 GUI交互任务的抽象类。 
	 */
	private class LongTask extends SwingWorker<String, Void> {

		/**
		 * 覆盖这个方法以处理耗时的任务
		 * 
		 * @see javax.swing.SwingWorker#doInBackground()
		 */
		@Override
		protected String doInBackground() throws Exception {
			for (int i = 0; i < 100; i++) {
				Thread.sleep(100);
				// progress属性值范围是从0到100，当任务实例内处理这些信息时，可以调用setProgress来更新这个属性。
				// 当任务属性发生变化时，它通知处理器对象。
				setProgress(i + 1);
			}
			return "Thread.sleep(10s)";
		}

		@Override
		protected void done() {
			try {
				getTextPane().setText(get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void initUI() {
		setPreferredSize(new Dimension(400, 300));
		setLayout(new BorderLayout());
		add(getButton(), BorderLayout.NORTH);
		add(getTextPane(), BorderLayout.CENTER);
		add(getProgressBar(), BorderLayout.SOUTH);
	}

	private JButton getButton() {
		if (button == null) {
			button = new JButton("start");
			button.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    /*
				     * 正确使用 SwingWorker的方法是实例化，如果需要获知线程发生的状态变化通知，
				     * 则要添加属性变化处理器，最后执行(execute)。
				     * execute方法是异步执行，它立即返回到调用者。
				     * 在这里，execute方法执行后，EDT立即进行执行。
				     */
					LongTask longTask = new LongTask();
					longTask.addPropertyChangeListener(new ProgressBarListener(
							getProgressBar()));
					longTask.execute();
				}
			});
		}
		return button;
	}

	private JProgressBar getProgressBar() {
		if (progressBar == null) {
			progressBar = new JProgressBar();
			progressBar.setValue(0);
			progressBar.setIndeterminate(true);
			progressBar.setString("0%");
			progressBar.setStringPainted(true);
		}
		return progressBar;
	}

	private static class ProgressBarListener implements PropertyChangeListener {

		private JProgressBar progressBar;

		public ProgressBarListener(JProgressBar progressBar) {
			this.progressBar = progressBar;
		}

		public void propertyChange(PropertyChangeEvent evt) {
			String propertyName = evt.getPropertyName();
			if ("progress".equals(propertyName)) {
				progressBar.setIndeterminate(false);
				Integer newValue = (Integer) evt.getNewValue();
				progressBar.setValue(newValue);
				progressBar.setString(newValue + "%");
			}
		}
	}

	private JTextPane getTextPane() {
		if (textPane == null) {
			textPane = new JTextPane();
		}
		return textPane;
	}
}