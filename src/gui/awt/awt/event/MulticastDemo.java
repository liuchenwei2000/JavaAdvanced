package awt.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import util.Displayer;

/**
 * 多点传送(multicast)演示
 * <p>
 * 所有的AWT事件源都支持监听器的多点传送模型，这意味着同一个事件可以发送给多个监听器对象。
 * 如果存在多方可能对一个事件感兴趣，那么就需要使用多点传送。
 * 将多个监听器添加到一个事件源中就可以使得所有注册的监听器都能够有机会相应这个事件。
 * <strong>JDK不能保证一个给定事件源注册的一组监听器传送事件的顺序。</strong>
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-6-24
 */
public class MulticastDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("Multicast Demo", new MulticastPanel());
	}
}

class MulticastPanel extends JPanel {

	private static final long serialVersionUID = 2731950486987918977L;

	public MulticastPanel() {
		JButton newButton = new JButton("New");
		add(newButton);

		final JButton closeAllButton = new JButton("Close all");
		add(closeAllButton);

		newButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				new BlankFrame(closeAllButton).setVisible(true);
			}
		});
	}
}

class BlankFrame extends JFrame {

	private static final long serialVersionUID = -1944465766151986080L;
	
	public static final int DEFAULT_WIDTH = 200;
	public static final int DEFAULT_HEIGHT = 150;
	public static final int SPACING = 40;

	private static int counter = 0;

	private ActionListener closeListener;

	public BlankFrame(final JButton closeButton) {
		counter++;
		setTitle("Frame " + counter);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLocation(SPACING * counter, SPACING * counter);
		// 每创建一个新的BlankFrame都会为closeButton添加一个监听器
		// 当closeButton被点击时，每一个BlankFrame都会响应：
		// 将自己的监听器从closeButton的监听器列表中移除
		// 然后隐藏自己并销毁
		closeListener = new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				closeButton.removeActionListener(closeListener);
				dispose();
			}
		};
		closeButton.addActionListener(closeListener);
	}
}