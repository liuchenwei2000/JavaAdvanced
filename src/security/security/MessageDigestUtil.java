/**
 * 
 */
package security;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
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
		return toHexString(computeDigestBySHA(content));
	}

	/**
	 * 通过MD5算法计算参数的消息摘要字符串
	 * 
	 * @param content
	 *            内容
	 */
	public static String computeDigestStringByMD5(String content) {
		return toHexString(computeDigestByMD5(content));
	}

	/**
	 * 将 byte 数组转换成十六进制数字形式的字符串
	 */
	private static String toHexString(byte[] hash) {
		BigInteger bi = new BigInteger(1, hash);
		return bi.toString(16).toUpperCase();
	}
	
	/**
	 * 获取指定文件的MD5值
	 */
	public static String getMd5ByFile(File file) throws IOException {
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			MappedByteBuffer byteBuffer = in.getChannel().map(
					FileChannel.MapMode.READ_ONLY, 0, file.length());
			
			MessageDigest md5 = getMessageDigest(Algorithm.MD5);
			md5.update(byteBuffer);
			
			return toHexString(md5.digest());
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}
}
