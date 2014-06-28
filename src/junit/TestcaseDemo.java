/**
 * 
 */
package junit;

import junit.framework.TestCase;

/**
 * 测试用例演示类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2007-9-25
 */
public class TestcaseDemo extends TestCase {

	/**
	 * setUp()方法创建对象的通用集合并将其初始化，这些集合在所有的测试中都将被用到。
	 * <p>
	 * 它与构造方法的区别是：setUp()是在测试之前直接被调用的。
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/**
	 * 如果每次在测试后需要执行清理工作，就要写一个tearDown()方法。
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * 每个测试方法都是public void的，不接受任何参数，并且具有一个以"test"开头的方法名。
	 * JUnit的反射会把这些方法标识为单个的测试，然后一次一个的创建和运行它们，
	 * 并且采取措施以避免这些测试之间的负面影响。
	 */
	public void testMethod() {
		try {
			assertEquals(1, 2);
		} catch (Exception e) {
			assertEquals(1, 1);
		}
	}
}