/**
 * 
 */
package junit;

import junit.framework.TestCase;

/**
 * ����������ʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2007-9-25
 */
public class TestcaseDemo extends TestCase {

	/**
	 * setUp()�������������ͨ�ü��ϲ������ʼ������Щ���������еĲ����ж������õ���
	 * <p>
	 * ���빹�췽���������ǣ�setUp()���ڲ���֮ǰֱ�ӱ����õġ�
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/**
	 * ���ÿ���ڲ��Ժ���Ҫִ������������Ҫдһ��tearDown()������
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * ÿ�����Է�������public void�ģ��������κβ��������Ҿ���һ����"test"��ͷ�ķ�������
	 * JUnit�ķ�������Щ������ʶΪ�����Ĳ��ԣ�Ȼ��һ��һ���Ĵ������������ǣ�
	 * ���Ҳ�ȡ��ʩ�Ա�����Щ����֮��ĸ���Ӱ�졣
	 */
	public void testMethod() {
		try {
			assertEquals(1, 2);
		} catch (Exception e) {
			assertEquals(1, 1);
		}
	}
}