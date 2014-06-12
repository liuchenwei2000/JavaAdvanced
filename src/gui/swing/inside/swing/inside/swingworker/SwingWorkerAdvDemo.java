/**
 * 
 */
package swing.inside.swingworker;

import javax.swing.SwingUtilities;

import util.Displayer;

/**
 * SwingWorker��ĸ߼���ʾ 
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2011-4-25
 */
public class SwingWorkerAdvDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				Displayer.createAndShowGUI("Everything", new FileDetectorUI());
			}
		});
	}
}