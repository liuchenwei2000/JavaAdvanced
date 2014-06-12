/**
 * 
 */
package awt.event;

import java.awt.Dimension;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EventListener;

import javax.swing.JPanel;

import swing.frame.BasicFrame;

/**
 * 事件源演示类
 * <p>
 * (将JFrame最小化后再打开可以看到现象)
 * <p>
 * 事件源能够生成自己的事件并通知给对此事件感兴趣的监听器。
 * <p>
 * 事件源需要具有下面三个要素：</br>
 * <li>1，事件类型(event type) </br>
 * 可以定义自己的事件类(本例使用JDK提供的PropertyChangeEvent)
 * <li>2，事件监听接口(event listener interface) </br>
 * 可以定义自己的接口(本例使用JDK提供的PropertyChangeListener)
 * <li>3，增加或删除监听器的方法
 * <p>
 * 怎样才能确保事件能够发送到感兴趣的各方呢？</br>
 * 这是事件源的责任，在事件发生的时候，必须构造一个事件对象，并将它传递给被注册的监听器。
 * 事件管理是一项常见的任务，Swing提供了一种很方便的类EventListenerList。
 * 从而可以轻而易举的实现增加、删除监听器和激活事件的方法。
 * 由于有些事件源允许接收多种类型的监听器，所以事件监听器列表中的每个监听器与一个特定的类关联。
 * 
 * @see gui.event.design包的相关类，本类只是基于JDK提供的一些类实现了事件源。
 * 
 * @author 刘晨伟
 *
 * 创建日期：2009-6-24
 */
public class EventSourceDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new EventSourceFrame().setVisible(true);
	}
}

class EventSourceFrame extends BasicFrame {

	private static final long serialVersionUID = 6544479856463330796L;

	public EventSourceFrame() {
		setTitle("EventSourceFrame");
		setPreferredSize(new Dimension(400, 300));
		final PaintCountPanel panel = new PaintCountPanel();
		panel.addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {
				setTitle("EventSourceFrame : " + evt.getNewValue());
			}
		});
		add(panel);
	}
}

class PaintCountPanel extends JPanel {

	private static final long serialVersionUID = -8947755167156202094L;

	private int counter = 0;

	public void paintComponent(Graphics g) {
		int oldValue = counter;
		counter++;
		firePropertyChangeEvent(new PropertyChangeEvent(this, "paint count",
				oldValue, counter));
		super.paintComponent(g);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		listenerList.add(PropertyChangeListener.class, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		listenerList.remove(PropertyChangeListener.class, listener);
	}

	private void firePropertyChangeEvent(PropertyChangeEvent event) {
		// 返回给定类型的所有监听器组成的数组
		EventListener[] listeners = listenerList
				.getListeners(PropertyChangeListener.class);
		for (EventListener l : listeners) {
			((PropertyChangeListener) l).propertyChange(event);
		}
	}
}