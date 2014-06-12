/**
 * 
 */
package swing.inside.EDT;

import javax.swing.SwingUtilities;

/**
 * SwingUtilities演示类
 * <p>
 * SwingUtilities类包含一些静态方法帮你同UI组件交互，其中
 * <li>invokeLater方法的意思是：在EDT上执行其Runnable任务，此方法是异步执行的，调用后会立即返回。
 * <li>invokeAndWait方法是阻塞执行的，它在EDT上执行Runnable任务，直到任务执行完了，该方法才返回调用线程。
 * <p>
 * 这两个方法都是在事件派发队列中的所有事件都处理完之后才执行它们的Runnable任务，
 * 也就是说，这两个方法将Runnable任务放在事件队列的末尾。
 * <p>
 * 警告：虽然可以在其他线程上调用invokeLater，也可以在EDT上调用，
 * 但是千万不要在EDT上调用invokeAndWait， 以免造成线程竞争，陷入死锁。
 * <p>
 * SwingUtilities存在的意义：如果想在非EDT线程上访问UI组件，则必须通过该类的invoke方法。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2011-3-29
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