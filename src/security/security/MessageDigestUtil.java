/**
 * 
 */
package security;

import java.security.MessageDigest;

/**
 * ��ϢժҪ������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-2-19
 */
public class MessageDigestUtil {

	/**
	 * ��ϢժҪ�㷨ö��
	 */
	private enum Algorithm {
		SHA, MD5
	}

	/**
	 * ͨ��SHA�㷨�����������ϢժҪ
	 * 
	 * @param content
	 *            ����
	 */
	public static byte[] computeDigestBySHA(String content) {
		return computeDigest(Algorithm.SHA, content);
	}

	/**
	 * ͨ��MD5�㷨�����������ϢժҪ
	 * 
	 * @param content
	 *            ����
	 */
	public static byte[] computeDigestByMD5(String content) {
		return computeDigest(Algorithm.MD5, content);
	}

	/**
	 * ���ݲ����㷨����������ݵ���ϢժҪ
	 * 
	 * @param algorithm
	 *            ��ϢժҪ�㷨
	 * @param content
	 *            ����
	 */
	private static byte[] computeDigest(Algorithm algorithm, String content) {
		if (content == null)
			throw new IllegalArgumentException("content cannot be null.");
		byte[] bytes = content.getBytes();
		return getMessageDigest(algorithm).digest(bytes);
	}

	private static MessageDigest getMessageDigest(Algorithm algorithm) {
		try {
			switch (algorithm) {
			case SHA:
				return MessageDigest.getInstance("SHA");
			case MD5:
				return MessageDigest.getInstance("MD5");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * ͨ��SHA�㷨�����������ϢժҪ�ַ���
	 * 
	 * @param content
	 *            ����
	 */
	public static String computeDigestStringBySHA(String content) {
		return computeDigestString(Algorithm.SHA, content);
	}

	/**
	 * ͨ��MD5�㷨�����������ϢժҪ�ַ���
	 * 
	 * @param content
	 *            ����
	 */
	public static String computeDigestStringByMD5(String content) {
		return computeDigestString(Algorithm.MD5, content);
	}

	/**
	 * ���ݲ����㷨����������ݵ���ϢժҪ�ַ���
	 * 
	 * @param algorithm
	 *            ��ϢժҪ�㷨
	 * @param content
	 *            ����
	 */
	private static String computeDigestString(Algorithm algorithm,
			String content) {
		byte[] hash = computeDigest(algorithm, content);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < hash.length; i++) {
			int v = hash[i] & 0xFF;
			if (v < 16)
				sb.append("0");
			sb.append(Integer.toString(v, 16).toUpperCase());
			sb.append(" ");
		}
		return sb.toString();
	}
}