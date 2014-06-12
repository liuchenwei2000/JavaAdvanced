/**
 * 
 */
package security;

/**
 * ��ϢժҪ��ʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-2-19
 */
public class MessageDigestUtilDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String content1 = "this is content1.";
		String content2 = "this is content2.";
		printMessageDigest(content1);
		printMessageDigest(content2);
	}

	/**
	 * ��ӡ�����ַ�����ժҪ��Ϣ
	 * 
	 * @param content
	 */
	private static void printMessageDigest(String content) {
		String sha = MessageDigestUtil.computeDigestStringBySHA(content);
		System.out.println(content + " SHA : " + sha);
		String md5 = MessageDigestUtil.computeDigestStringByMD5(content);
		System.out.println(content + " MD5 : " + md5);
	}
}