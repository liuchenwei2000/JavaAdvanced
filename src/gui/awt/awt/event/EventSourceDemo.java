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
 * �¼�Դ��ʾ��
 * <p>
 * (��JFrame��С�����ٴ򿪿��Կ�������)
 * <p>
 * �¼�Դ�ܹ������Լ����¼���֪ͨ���Դ��¼�����Ȥ�ļ�������
 * <p>
 * �¼�Դ��Ҫ������������Ҫ�أ�</br>
 * <li>1���¼�����(event type) </br>
 * ���Զ����Լ����¼���(����ʹ��JDK�ṩ��PropertyChangeEvent)
 * <li>2���¼������ӿ�(event listener interface) </br>
 * ���Զ����Լ��Ľӿ�(����ʹ��JDK�ṩ��PropertyChangeListener)
 * <li>3�����ӻ�ɾ���������ķ���
 * <p>
 * ��������ȷ���¼��ܹ����͵�����Ȥ�ĸ����أ�</br>
 * �����¼�Դ�����Σ����¼�������ʱ�򣬱��빹��һ���¼����󣬲��������ݸ���ע��ļ�������
 * �¼�������һ���������Swing�ṩ��һ�ֺܷ������EventListenerList��
 * �Ӷ���������׾ٵ�ʵ�����ӡ�ɾ���������ͼ����¼��ķ�����
 * ������Щ�¼�Դ������ն������͵ļ������������¼��������б��е�ÿ����������һ���ض����������
 * 
 * @see gui.event.design��������࣬����ֻ�ǻ���JDK�ṩ��һЩ��ʵ�����¼�Դ��
 * 
 * @author ����ΰ
 *
 * �������ڣ�2009-6-24
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
		// ���ظ������͵����м�������ɵ�����
		EventListener[] listeners = listenerList
				.getListeners(PropertyChangeListener.class);
		for (EventListener l : listeners) {
			((PropertyChangeListener) l).propertyChange(event);
		}
	}
}