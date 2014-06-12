/**
 * 
 */
package awt.event.design;

import java.util.ArrayList;
import java.util.List;

/**
 * 事件源
 * 
 * @author 刘晨伟
 *
 * 创建日期：2009-6-18
 */
public class EventSource {
	
	// 事件监听器列表
	private List<TextChangedListener> listeners = new ArrayList<TextChangedListener>();
	
	private String text;
	
	public EventSource(){
		this.text = "I am old text";
	}

	public String getText() {
		return this.text;
	}

	/**
	 * 在进行设值的时候发出文本改变的事件通知
	 */
	public void setText(String text) {
		String oldText = getText();
		this.text = text;
		fireTextChanged(new TextChangedEvent(oldText,text));
	}
	
	/**
	 * 文本改变事件通知
	 * <p>
	 * 实际上是顺序执行事件监听器列表中的每一个监听器在文本改变时的处理动作。
	 */
	private void fireTextChanged(TextChangedEvent event) {
		for (TextChangedListener listener : listeners) {
			listener.actionPerformed(event);
		}
	}

	/**
	 * 添加文本改变监听器
	 */
	public void addTextChangedListener(TextChangedListener l){
		listeners.add(l);
	}
	
	/**
	 * 移除文本改变监听器
	 */
	public void removeTextChangedListener(TextChangedListener l){
		listeners.remove(l);
	}
}