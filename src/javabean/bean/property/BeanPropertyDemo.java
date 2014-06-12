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
 * Bean����������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2013-7-3
 */
public class BeanPropertyDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyBean bean = new MyBean();
		// Ϊbean���һ��PropertyChangeListener
		bean.addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {
				System.out.println(evt.getPropertyName() + "="
						+ evt.getNewValue());
			}
		});
		// Ϊbean���һ��VetoableChangeListener
		bean.addVetoableChangeListener(new VetoableChangeListener() {
			
			public void vetoableChange(PropertyChangeEvent evt)
					throws PropertyVetoException {
				throw new PropertyVetoException("�Ҿ��ǲ����޸�",evt);
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
 * һ��JavaBean
 */
class MyBean {
	
	/**
	 *  ������
	 *  <p>
	 *  ������ֻ����һ��������ֵ������getter/setter����ʵ��Ҳ�Ƚϼ򵥡�
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
	 *  ��������
	 *  <p>
	 *  ���������ǿ��Ի�ȡ��������������ԣ���Ҫ�ṩ����getter/setter������һ�Ը�����ʹ�ã�һ�Ը������ڵĵ���Ԫ��ʹ�á�
	 */
	private String[] indexesProperty;
	
	/**
	 * ��������getter/setter�����������������������ģʽ��
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
	 * ������
	 * <p>
	 * �����Ի�������ֵ�����仯ʱ��֪ͨ������صļ�������
	 * <p>Ϊ��ʵ��һ�������ԣ�����ʵ���������ƣ�
	 * <li>1��ֻҪ���Ե�ֵ�����˱仯����bean���뷢��һ��PropertyChange�¼���������ע��ļ�������
	 * <li>2��Ϊ��ʹ����Ȥ�ļ�����(PropertyChangeListener)�ܹ�����ע�ᣬbean����ʵ����Ӻ�ɾ���������ķ�����
	 */
	private String bindingProperty;
	
	/**
	 * java.beans.PropertyChangeSupport��һ����ʵ�õ��࣬�ܹ�����PropertyChangeListener�����Ա���дһЩ�ظ��ԵĴ��롣
	 */
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	public String getBindingProperty() {
		return bindingProperty;
	}

	public void setBindingProperty(String bindingProperty) {
		String oldValue = this.bindingProperty;
		this.bindingProperty = bindingProperty;
		// ʵ�ֵ�һ�����ƣ��÷������������������������ɵ�ֵ�Լ��µ�ֵ��
		propertyChangeSupport.firePropertyChange("bindingProperty", oldValue, bindingProperty);
	}
	
	/**
	 * ������������ʵ�ֵڶ�������
	 */
	public void addPropertyChangeListener(PropertyChangeListener l){
		propertyChangeSupport.addPropertyChangeListener(l);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener l){
		propertyChangeSupport.removePropertyChangeListener(l);
	}

	/**
	 * Լ������
	 * <p>
	 * Լ��������ָ�ܵ�ĳ��Լ�����κμ����������Է��(veto)������ĸı䣬ǿ���仹ԭ�ɵ����á�
	 * <p>Ϊ��ʵ��һ��Լ�����ԣ�bean����ʵ���������ƣ�
	 * <li>1��ֻҪ���Ե�ֵ�����˱仯����bean���뷢��һ��VetoableChange�¼���������ע��ļ�������
	 * <li>2��Ϊ��ʹ����Ȥ�ļ�����(VetoableChangeListener)�ܹ�����ע�ᣬbean����ʵ����Ӻ�ɾ���������ķ�����
	 */
	private String vetoableProperty;
	
	/**
	 * java.beans.VetoableChangeSupport��һ����ʵ�õ��࣬�ܹ�����VetoableChangeListener�����Ա���дһЩ�ظ��ԵĴ��롣
	 */
	private VetoableChangeSupport vetoableChangeSupport = new VetoableChangeSupport(this);

	public String getVetoableProperty() {
		return vetoableProperty;
	}

	public void setVetoableProperty(String vetoableProperty) throws PropertyVetoException {
		/**
		 * Ϊ�˸���һ��Լ�����ԣ�beanҪ��˳��ִ�������������裺
		 */
		String oldValue = this.vetoableProperty;
		// 1,���޸�����ֵ����ͼ֪ͨ���пɷ���޸ĵļ�������һ���м�������ʾ����ͻ��׳�PropertyVetoException
		vetoableChangeSupport.fireVetoableChange("vetoableProperty", oldValue, vetoableProperty);
		// 2,������пɷ���޸ĵļ�������û���׳�PropertyVetoException���͸�������ֵ�����򲻿���1˳��ߵ���
		this.vetoableProperty = vetoableProperty;
		// 3,֪ͨ�������Ըı�����м�������ȷ��һ���ı䷢����
		propertyChangeSupport.firePropertyChange("vetoableProperty", oldValue, vetoableProperty);
	}
	
	/**
	 * ������������ʵ�ֵڶ�������
	 */
	public void addVetoableChangeListener(VetoableChangeListener l){
		vetoableChangeSupport.addVetoableChangeListener(l);
	}
	
	public void removeVetoableChangeListener(VetoableChangeListener l){
		vetoableChangeSupport.removeVetoableChangeListener(l);
	}
}