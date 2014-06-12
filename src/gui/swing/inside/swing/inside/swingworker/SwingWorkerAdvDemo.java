/**
 * 
 */
package swing.inside.swingworker;

import javax.swing.SwingUtilities;

import util.Displayer;

/**
 * SwingWorker类的高级演示 
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2011-4-25
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