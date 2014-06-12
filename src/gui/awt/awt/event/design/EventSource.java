/**
 * 
 */
package awt.event.design;

import java.util.ArrayList;
import java.util.List;

/**
 * �¼�Դ
 * 
 * @author ����ΰ
 *
 * �������ڣ�2009-6-18
 */
public class EventSource {
	
	// �¼��������б�
	private List<TextChangedListener> listeners = new ArrayList<TextChangedListener>();
	
	private String text;
	
	public EventSource(){
		this.text = "I am old text";
	}

	public String getText() {
		return this.text;
	}

	/**
	 * �ڽ�����ֵ��ʱ�򷢳��ı��ı���¼�֪ͨ
	 */
	public void setText(String text) {
		String oldText = getText();
		this.text = text;
		fireTextChanged(new TextChangedEvent(oldText,text));
	}
	
	/**
	 * �ı��ı��¼�֪ͨ
	 * <p>
	 * ʵ������˳��ִ���¼��������б��е�ÿһ�����������ı��ı�ʱ�Ĵ�������
	 */
	private void fireTextChanged(TextChangedEvent event) {
		for (TextChangedListener listener : listeners) {
			listener.actionPerformed(event);
		}
	}

	/**
	 * ����ı��ı������
	 */
	public void addTextChangedListener(TextChangedListener l){
		listeners.add(l);
	}
	
	/**
	 * �Ƴ��ı��ı������
	 */
	public void removeTextChangedListener(TextChangedListener l){
		listeners.remove(l);
	}
}