/**
 * 
 */
package swing.inside.EDT;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * 初始化线程演示
 * <p>
 * 每一个Java应用程序都开始于主线程中的main方法。
 * 在Swing程序中，主线程的生命周期是很短的，它启动EDT线程构造用户界面后就退出了。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2011-3-29
 */
public class InitialThreadTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * 下面是错误的启动UI界面的方法，它违反了不应在EDT外的其他线程同Swing组件交互的原则。
		 * 线程同步问题虽然不是马上显现出来，但是还要注意避免这样的做法。
		 */
		new JFrame().setVisible(true);
		// 下面是正确的启动UI界面的方法
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				new JFrame().setVisible(true);
			}
		});
	}
}
