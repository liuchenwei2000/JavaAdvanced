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
		return toHexString(computeDigestBySHA(content));
	}

	/**
	 * ͨ��MD5�㷨�����������ϢժҪ�ַ���
	 * 
	 * @param content
	 *            ����
	 */
	public static String computeDigestStringByMD5(String content) {
		return toHexString(computeDigestByMD5(content));
	}

	/**
	 * �� byte ����ת����ʮ������������ʽ���ַ���
	 */
	private static String toHexString(byte[] hash) {
		BigInteger bi = new BigInteger(1, hash);
		return bi.toString(16).toUpperCase();
	}
	
	/**
	 * ��ȡָ���ļ���MD5ֵ
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