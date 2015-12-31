/**
 * 
 */
package awt.event.design;

/**
 * Text改变事件
 * 
 * @author 刘晨伟
 *
 * 创建日期：2009-6-18
 */
public class TextChangedEvent implements IEvent{

	// 作为事件的标识
	private static int counter = 0;

	private String oldText;
	private String newText;

	public TextChangedEvent(String oldText, String newText) {
		this.oldText = oldText;
		this.newText = newText;
		counter++;
	}

	public String getOldText() {
		return oldText;
	}

	public String getNewText() {
		return newText;
	}

	public Object getSource() {
		return counter;
	}
}
