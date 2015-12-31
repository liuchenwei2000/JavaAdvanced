/**
 * 
 */
package applet;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Applet示例
 * <p>
 * 网页中的applet通常不能访问本地磁盘。
 *
 * @author 刘晨伟
 *
 * 创建日期：2008-3-18
 */
public class AppletDemo extends JApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6947418109117870067L;

	/**
	 * 被自动调用，用来对applet进行首次初始化，其中包括组件布局，必须总是覆盖此方法。
	 */
	public void init() {
		getContentPane().add(new JLabel("This is a applet!", JLabel.CENTER));
	}

	/**
	 * 每当 applet 进入 Web 浏览器视野的时候，此方法被调用，使得 applet 能开启它的常规功能（尤其是被stop()关闭的功能），
	 * 它在init()之后被调用，当用户从其他页面返回到包含 applet 的页面时，也会调用该方法。
	 * 这就意味着可能会多次调用start方法，而init方法只能调用一次。
	 * 由于这个原因，可以把希望只执行一次的代码放置到init方法中，而不是放在start中。
	 * start 方法经常为 applet 重新启动一个线程。
	 * 如果applet在用户离开当前页面时没有什么需要挂起的，就没有必要实现该方法（或者stop方法）。
	 */
	public void start() {
		super.start();
	}
	
	/**
	 * 每当applet离开Web浏览器视野的时候，此方法被调用，使得 applet 能关闭它的"昂贵"操作（如视频音频播放）。
	 * 它也在destroy()之前被调用。
	 */
	public void stop(){
		super.stop();
	}

	/**
	 * 当 applet不再被使用、要从网页中卸载 applet 以最终释放资源的时候，此方法被调用。
	 */
	public void destroy() {
		super.destroy();
	}
	
	/**
	 * 在Applet中加入了main方法之后使得，它可以同时作为应用程序和 applet 运行。
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
