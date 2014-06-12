/**
 * 
 */
package awt.event.design;

/**
 * Text�ı��¼�
 * 
 * @author ����ΰ
 *
 * �������ڣ�2009-6-18
 */
public class TextChangedEvent implements IEvent{

	// ��Ϊ�¼��ı�ʶ
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