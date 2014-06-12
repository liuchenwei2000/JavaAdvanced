/**
 * 
 */
package awt.event.design;

/**
 * 事件机制演示
 * 
 * @author 刘晨伟
 *
 * 创建日期：2009-6-18
 */
public class EventDispatchDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int n = 4;
		EventSource source = new EventSource();

		source.addTextChangedListener(new TextChangedListener() {
			public void actionPerformed(TextChangedEvent event) {
				System.out.println("event " + event.getSource());
			}
		});

		source.addTextChangedListener(new TextChangedListener() {
			public void actionPerformed(TextChangedEvent event) {
				System.out.println("EventSource old Text : "
						+ event.getOldText());
			}
		});

		source.addTextChangedListener(new TextChangedListener() {
			public void actionPerformed(TextChangedEvent event) {
				System.out.println("EventSource new Text : "
						+ event.getNewText());
			}
		});

		TextChangedListener tl = new TextChangedListener() {
			public void actionPerformed(TextChangedEvent event) {
				System.out.println("=============================");
			}
		};
		source.addTextChangedListener(tl);

		for (int i = 1; i < n; i++) {
			source.setText("I am new text" + i);
		}

		source.removeTextChangedListener(tl);

		for (int i = n; i < 2 * n; i++) {
			source.setText("I am new text" + i);
		}
	}
}