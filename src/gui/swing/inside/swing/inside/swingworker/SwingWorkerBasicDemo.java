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
 * SwingWorker��Ļ�����ʾ
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2011-4-1
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
	 * ��ʱ������
	 * <p>
	 * SwingWorker����ר���߳���ִ�г�ʱ�� GUI��������ĳ����ࡣ 
	 */
	private class LongTask extends SwingWorker<String, Void> {

		/**
		 * ������������Դ����ʱ������
		 * 
		 * @see javax.swing.SwingWorker#doInBackground()
		 */
		@Override
		protected String doInBackground() throws Exception {
			for (int i = 0; i < 100; i++) {
				Thread.sleep(100);
				// progress����ֵ��Χ�Ǵ�0��100��������ʵ���ڴ�����Щ��Ϣʱ�����Ե���setProgress������������ԡ�
				// ���������Է����仯ʱ����֪ͨ����������
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
				     * ��ȷʹ�� SwingWorker�ķ�����ʵ�����������Ҫ��֪�̷߳�����״̬�仯֪ͨ��
				     * ��Ҫ������Ա仯�����������ִ��(execute)��
				     * execute�������첽ִ�У����������ص������ߡ�
				     * �����execute����ִ�к�EDT��������ִ�С�
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