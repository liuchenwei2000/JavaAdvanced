/**
 * 
 */
package bean.beaninfo;

import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * ��ʡ��Introspectorʾ��
 * <p>
 * JavaBeanģ����ؼ�����֮һ�������ڵ����ѡ�����϶�һ��Bean(���ӻ�Bean)��Ȼ��������õ������ϵ�ʱ��
 * Ӧ�ó��򹹽����߱����ܹ��������Bean(��Ҫ�޲ι�����)��Ȼ���ڲ�����BeanԴ���������³�ȡ�����б�Ҫ��Ϣ���Դ������Ժ��¼����������б�
 * <p>
 * Java�ķ�������ܷ���δ֪������з�����
 * ���ڽ��JavaBean��������⣬���Ǹ������ķ������㲻�����������ӻ������������ʹ���κ����Ը��ӵĹؼ��֡�
 * ʵ���ϣ�Java��������뷴����Ƶ���Ҫԭ��֮һ����Ϊ��֧��JavaBean(���ܷ���Ҳ֧�ֶ������л���Զ�̷�������)��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-6-17
 */
public class IntrospectorDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			introspect(Class.forName("bean.Frog"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ʹ����ʡ����ȡBeanInfo
	 * <p>
	 * Java�������ϣ���ṩһ����׼���ߣ�����ҪʹBean�������򵥣����Ҷ��ڴ��������ӵ�Bean���ܹ��ṩһ����׼������
	 * ������߾���Introspector(��ʡ��)�࣬���������Ҫ�ľ��Ǿ�̬��getBeanInfo()������
	 * �������������һ��Class�������ã����ܹ���ȫ�������࣬Ȼ�󷵻�һ��BeanInfo���󣬿���ͨ���������õ�Bean�����ԡ��������¼���
	 */
	private static void introspect(Class<?> bean) {
		BeanInfo bi = null;
		try {
			/**
			 * �ڸ�����"��"��֮�£���JavaBean(bean����)������ʡ���˽����������Ժ͹����ķ�����
			 * <p> 
			 * �ڶ�����������ָ���ϵ�,��Ϊ����������Object�ķ����������ڽ�������Object�����з���֮ǰֹͣ��ѯ��
			 */
			bi = Introspector.getBeanInfo(bean, Object.class);
		} catch (IntrospectionException e) {
			System.out.println("Couldn't introspect " + bean.getName());
			return;
		}
		introspectProperties(bi);
		introspectMethods(bi);
		introspectEventListeners(bi);
	}

	private static void introspectProperties(BeanInfo bi) {
		// ��ȡBean����������
		PropertyDescriptor[] properties = bi.getPropertyDescriptors();
		for (int i = 0; i < properties.length; i++) {
			Class<?> p = properties[i].getPropertyType();
			if (p == null)
				continue;
			System.out.println("Property type:\n" + p.getSimpleName());
			// �������Եı���(�ӷ����г�ȡ��)
			System.out.println("Property name:\n" + properties[i].getName());
			// �������ԵĶ�����
			Method readMethod = properties[i].getReadMethod();
			if (readMethod != null) {
				System.out.println("Read method:\n" + readMethod);
			}
			// �������Ե�д����
			Method writeMethod = properties[i].getWriteMethod();
			if (writeMethod != null) {
				System.out.println("Write method:\n" + writeMethod);
			}
			System.out.println("====================");
		}
	}

	private static void introspectMethods(BeanInfo bi) {
		System.out.println("Public methods:");
		// ��ȡBean���еĹ�������(�������Է���)
		MethodDescriptor[] methods = bi.getMethodDescriptors();
		for (int i = 0; i < methods.length; i++) {
			System.out.println(methods[i].getMethod().toString());
		}
		System.out.println("======================");
		System.out.println("Event support:");
	}

	private static void introspectEventListeners(BeanInfo bi) {
		// ��ȡBean��֧�ֵ��¼�����
		EventSetDescriptor[] events = bi.getEventSetDescriptors();
		for (int i = 0; i < events.length; i++) {
			System.out.println("Listener type:\n"
					+ events[i].getListenerType().getSimpleName());
			Method[] lm = events[i].getListenerMethods();
			for (int j = 0; j < lm.length; j++) {
				System.out.println("Listener method:\n" + lm[j].getName());
			}
			MethodDescriptor[] lmd = events[i].getListenerMethodDescriptors();
			for (int j = 0; j < lmd.length; j++) {
				System.out
						.println("Method descriptor:\n" + lmd[j].getMethod());
			}
			// ������Ӵ��¼��������ķ���
			Method addListener = events[i].getAddListenerMethod();
			System.out.println("Add Listener Method:\n" + addListener);
			// �����Ƴ����¼��������ķ���
			Method removeListener = events[i].getRemoveListenerMethod();
			System.out.println("Remove Listener Method:\n" + removeListener);
			System.out.println("====================");
		}
	}
}