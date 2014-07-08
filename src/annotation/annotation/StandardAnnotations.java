/**
 * 
 */
package annotation;

import javax.annotation.Generated;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * ��׼ע��ʾ��
 * <p>
 * JDK�ṩ��һЩ��׼ע�⹩ʹ�á�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014-7-8
 */
public class StandardAnnotations {

	/**
	 * @deprecated
	 */
	// @Deprecatedע����Ա���ӵ��κβ��ٹ���ʹ�õ����ϣ���������ĵ���ǩ@deprecated����ͬ�ȹ�Ч��
	@Deprecated
	public void deprecatedMethod() {
		// do something
		
		// @SuppressWarnings ע����֪��������ֹ�������͵ľ�����Ϣ��
		@SuppressWarnings("unused")
		int a = 0;
	}
	
	// @Overrideע��ֻ��Ӧ�õ������ϣ������������ע��ķ��������Ǹ���һ������ķ���������б������
	@Override
	public String toString() {
		return "";
	}

	// @Generatedע���Ŀ���ǹ��������ɹ�����ʹ�ã��κ����ɵ�Դ���붼���Ա�ע�⣬�Ӷ������Ա�ṩ�Ĵ������ֿ���
	@Generated(value = "com.google.code", date = "2014-07-08")
	public int hashCode() {
		return 0;
	}
	
	/**
	 * @PostConstruct �� @PreDestroy ע�����ڿ��ƶ����������ڵĻ����У�����Web������Ӧ�÷�������
	 */
	// @PostConstructע��ķ���Ӧ���ڶ��󱻹���֮�����
	@PostConstruct
	private void postInit() {
		// so something
	}

	// @PreDestroyע��ķ���Ӧ���ڶ��󱻹���֮�����
	@PreDestroy
	private void preClose() {
		// so something
	}
}
