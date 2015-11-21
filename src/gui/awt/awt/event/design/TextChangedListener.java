/**
 * 
 */
package awt.event.design;

/**
 * Text改变监听器接口
 * 
 * @author 刘晨伟
 *
 * 创建日期：2009-6-18
 */
public interface TextChangedListener extends IEventListener {

	/**
	 * Text改变时的处理
	 * 
	 * @param event
	 *            Text改变事件
	 */
	public void actionPerformed(TextChangedEvent event);
}
