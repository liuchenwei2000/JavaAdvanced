/**
 * 
 */
package annotation.custom;

/**
 * ע��ʹ��ʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014-7-7
 */
public class AnnotationUsed {

	/**
	 * ע���ǵ���һ�����η���ʹ�õģ��������ڱ�ע����֮ǰ���м�û�зֺš�
	 * ÿһ��ע�������ǰ�涼������@���š�
	 * ע�Ȿ���������κ����飬����Ҫ����֧�ֲŻ����á�
	 * ���˷����⣬������ע���ࡢ��Ա�Լ����ر�������Щע����Դ������κο��Է���
	 * һ��public��static���������η��ĵط���
	 */
	// ע����Զ���ɰ���Ԫ�ص���ʽ����ЩԪ�ؿ��Ա��Ķ���Щע��Ĺ���ȥ����
	@AnnotationTest(name = "operate1")
	public void operate1() {
		System.out.println("in AnnotationUsed.operate()");
	}
	
	/**
	 * ����ûʹ��ע�����ͨ����
	 */
	public void operate2() {
		System.out.println("in AnnotationUsed.operate()");
	}
	
	/**
	 * ����ʹ����һ��ע��ķ���
	 */
	// ��Ϊע�����ɱ�������������ģ��������Ԫ��ֵ�����Ǳ����ڳ�����
	// ���Ԫ��ֵ��һ�����飬��ôҪ������ֵ��������������
	@AnnotationTest(name = "operate1")// һ������Ծ��ж��ע�⣬ֻҪ�������ڲ�ͬ�����ͼ��ɡ�
	@AnnotationSytax(args = { "var1", "vra2" })
//	@AnnotationSytax(args = { "var1", "vra2" }) // ��ע��һ���ض����ʱ�򣬲��ܶ��ʹ��ͬһ��ע�����͡�
	public void operate3() {
		System.out.println("in AnnotationUsed.operate()");
	}
}
