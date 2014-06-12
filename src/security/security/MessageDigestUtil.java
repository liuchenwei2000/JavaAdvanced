/**
 * 
 */
package security;

import java.security.MessageDigest;

/**
 * 消息摘要工具类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-2-19
 */
public class MessageDigestUtil {

	/**
	 * 消息摘要算法枚举
	 */
	private enum Algorithm {
		SHA, MD5
	}

	/**
	 * 通过SHA算法计算参数的消息摘要
	 * 
	 * @param content
	 *            内容
	 */
	public static byte[] computeDigestBySHA(String content) {
		return computeDigest(Algorithm.SHA, content);
	}

	/**
	 * 通过MD5算法计算参数的消息摘要
	 * 
	 * @param content
	 *            内容
	 */
	public static byte[] computeDigestByMD5(String content) {
		return computeDigest(Algorithm.MD5, content);
	}

	/**
	 * 根据参数算法计算参数内容的消息摘要
	 * 
	 * @param algorithm
	 *            消息摘要算法
	 * @param content
	 *            内容
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
	 * 通过SHA算法计算参数的消息摘要字符串
	 * 
	 * @param content
	 *            内容
	 */
	public static String computeDigestStringBySHA(String content) {
		return computeDigestString(Algorithm.SHA, content);
	}

	/**
	 * 通过MD5算法计算参数的消息摘要字符串
	 * 
	 * @param content
	 *            内容
	 */
	public static String computeDigestStringByMD5(String content) {
		return computeDigestString(Algorithm.MD5, content);
	}

	/**
	 * 根据参数算法计算参数内容的消息摘要字符串
	 * 
	 * @param algorithm
	 *            消息摘要算法
	 * @param content
	 *            内容
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