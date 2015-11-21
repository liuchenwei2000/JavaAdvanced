/**
 * 
 */
package bean.property;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;

/**
 * Bean的四种属性
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2013-7-3
 */
public class BeanPropertyDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyBean bean = new MyBean();
		// 为bean添加一个PropertyChangeListener
		bean.addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {
				System.out.println(evt.getPropertyName() + "="
						+ evt.getNewValue());
			}
		});
		// 为bean添加一个VetoableChangeListener
		bean.addVetoableChangeListener(new VetoableChangeListener() {
			
			public void vetoableChange(PropertyChangeEvent evt)
					throws PropertyVetoException {
				throw new PropertyVetoException("我就是不让修改",evt);
			}
		});
		
		bean.setSimpleProperty("new simpleProperty");
		bean.setBindingProperty("new bindingProperty");
		try {
			bean.setVetoableProperty("new vetoableProperty");
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}
}

/**
 * 一个JavaBean
 */
class MyBean {
	
	/**
	 *  简单属性
	 *  <p>
	 *  简单属性只具有一个单独的值，它的getter/setter方法实现也比较简单。
	 */
	private String simpleProperty;
	
	public String getSimpleProperty() {
		// do something else
		return simpleProperty;
	}

	public void setSimpleProperty(String simpleProperty) {
		// do something else
		this.simpleProperty = simpleProperty;
	}

	/**
	 *  索引属性
	 *  <p>
	 *  索引属性是可以获取或设置数组的属性，需要提供两对getter/setter方法：一对给数组使用，一对给数组内的单个元素使用。
	 */
	private String[] indexesProperty;
	
	/**
	 * 索引属性getter/setter方法的命名必须遵守下面的模式。
	 */
	
	public String[] getIndexesProperty() {
		return indexesProperty;
	}

	public void setIndexesProperty(String[] indexesProperty) {
		this.indexesProperty = indexesProperty;
	}
	
	public String getIndexesProperty(int index) {
		if (index >= 0 && index < indexesProperty.length - 1) {
			return indexesProperty[index];
		}
		return null;
	}

	public void setIndexesProperty(int index, String indexProperty) {
		if (index >= 0 && index < indexesProperty.length - 1) {
			indexesProperty[index] = indexProperty;
		}
	}

	/**
	 * 绑定属性
	 * <p>
	 * 绑定属性会在属性值发生变化时，通知所有相关的监听器。
	 * <p>为了实现一个绑定属性，必须实现两个机制：
	 * <li>1，只要属性的值发生了变化，该bean必须发送一个PropertyChange事件给所有已注册的监听器。
	 * <li>2，为了使感兴趣的监听器(PropertyChangeListener)能够进行注册，bean必须实现添加和删除监听器的方法。
	 */
	private String bindingProperty;
	
	/**
	 * java.beans.PropertyChangeSupport是一个很实用的类，能够管理PropertyChangeListener，可以避免写一些重复性的代码。
	 */
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	public String getBindingProperty() {
		return bindingProperty;
	}

	public void setBindingProperty(String bindingProperty) {
		String oldValue = this.bindingProperty;
		this.bindingProperty = bindingProperty;
		// 实现第一个机制，该方法的三个参数：属性名、旧的值以及新的值。
		propertyChangeSupport.firePropertyChange("bindingProperty", oldValue, bindingProperty);
	}
	
	/**
	 * 下面两个方法实现第二个机制
	 */
	public void addPropertyChangeListener(PropertyChangeListener l){
		propertyChangeSupport.addPropertyChangeListener(l);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener l){
		propertyChangeSupport.removePropertyChangeListener(l);
	}

	/**
	 * 约束属性
	 * <p>
	 * 约束属性是指受到某种约束，任何监听器都可以否决(veto)所提出的改变，强迫其还原旧的设置。
	 * <p>为了实现一个约束属性，bean必须实现两个机制：
	 * <li>1，只要属性的值发生了变化，该bean必须发送一个VetoableChange事件给所有已注册的监听器。
	 * <li>2，为了使感兴趣的监听器(VetoableChangeListener)能够进行注册，bean必须实现添加和删除监听器的方法。
	 */
	private String vetoableProperty;
	
	/**
	 * java.beans.VetoableChangeSupport是一个很实用的类，能够管理VetoableChangeListener，可以避免写一些重复性的代码。
	 */
	private VetoableChangeSupport vetoableChangeSupport = new VetoableChangeSupport(this);

	public String getVetoableProperty() {
		return vetoableProperty;
	}

	public void setVetoableProperty(String vetoableProperty) throws PropertyVetoException {
		/**
		 * 为了更新一个约束属性，bean要按顺序执行下面三个步骤：
		 */
		String oldValue = this.vetoableProperty;
		// 1,将修改属性值的意图通知所有可否决修改的监听器，一旦有监听器表示否决就会抛出PropertyVetoException
		vetoableChangeSupport.fireVetoableChange("vetoableProperty", oldValue, vetoableProperty);
		// 2,如果所有可否决修改的监听器都没有抛出PropertyVetoException，就更新属性值。（万不可与1顺序颠倒）
		this.vetoableProperty = vetoableProperty;
		// 3,通知监听属性改变的所有监听器，确认一个改变发生。
		propertyChangeSupport.firePropertyChange("vetoableProperty", oldValue, vetoableProperty);
	}
	
	/**
	 * 下面两个方法实现第二个机制
	 */
	public void addVetoableChangeListener(VetoableChangeListener l){
		vetoableChangeSupport.addVetoableChangeListener(l);
	}
	
	public void removeVetoableChangeListener(VetoableChangeListener l){
		vetoableChangeSupport.removeVetoableChangeListener(l);
	}
}
