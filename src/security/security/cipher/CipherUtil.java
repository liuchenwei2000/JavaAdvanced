/**
 * 
 */
package security.cipher;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;

/**
 * ���빤����
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-2-20
 */
public class CipherUtil {

	/** ��Կ���й�Կ����Կ�ĳ��� */
	private static final int KEY_SIZE = 512;
	
	/**
	 * ʹ��AES�㷨���������Կ
	 */
	public static Key generateKey() {
		Key key = null;
		try {
			// KeyGenertaor���ṩ(�Գ�)��Կ�������Ĺ��� �㷨��AES��DES��
			KeyGenerator gen = KeyGenerator.getInstance("AES");
			// SecureRandom���ṩ���ܵ�ǿ�����������
			SecureRandom random = new SecureRandom();
			gen.init(random);// ��ʼ������Կ������
			key = gen.generateKey();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return key;
	}

	/**
	 * ʹ��RSA�㷨���������Կ��(��Կ����512)
	 */
	public static KeyPair generateKeyPair() {
		KeyPair keyPair = null;
		try {
			// KeyPairGenerator���������ɹ�Կ��˽Կ�� �㷨��RSA��
			KeyPairGenerator pairgen = KeyPairGenerator.getInstance("RSA");
			SecureRandom random = new SecureRandom();
			// ʹ�ø��������Դ��ʼ��ȷ����Կ���ȵ���Կ�������� 
			pairgen.initialize(KEY_SIZE, random);
			keyPair = pairgen.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return keyPair;
	}
	
	/**
	 * ������Կʹ��AES�㷨�����������ݼ��ܣ��ٽ���������д�뵽�����
	 * 
	 * @param in
	 *            ����������
	 * @param out
	 *            ���������
	 * @param key
	 *            ��Կ
	 */
	public static void encrypt(InputStream in, OutputStream out, Key key)
			throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		// Cipher����ĳ�ʼ����ʽ(����or����)
		cipher.init(Cipher.ENCRYPT_MODE, key);
		crypt1(in, out, cipher);
	}

	/**
	 * ������Կʹ��AES�㷨�����������ݽ��ܣ��ٽ���������д�뵽�����
	 * 
	 * @param in
	 *            ����������
	 * @param out
	 *            ���������
	 * @param key
	 *            ��Կ
	 */
	public static void decrypt(InputStream in, OutputStream out, Key key)
			throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, key);
		crypt1(in, out, cipher);
	}

	/**
	 * ʹ������ת���������е��ֽ��벢��ת������ֽ��봫�������
	 * 
	 * @param in
	 *            ������
	 * @param out
	 *            �����
	 * @param cipher
	 *            ����
	 */
	private static void crypt1(InputStream in, OutputStream out, Cipher cipher)
			throws Exception {
		// ���Cipher���ĵ�
		int blockSize = cipher.getBlockSize();
		int outputSize = cipher.getOutputSize(blockSize);

		byte[] inBytes = new byte[blockSize];
		byte[] outBytes = new byte[outputSize];

		int inLength = 0;
		boolean more = true;
		while (more) {
			inLength = in.read(inBytes);
			if (inLength == blockSize) {
				int outlength = cipher.update(inBytes, 0, blockSize, outBytes);
				out.write(outBytes, 0, outlength);
			} else
				more = false;
		}
		if (inLength > 0)
			outBytes = cipher.doFinal(inBytes, 0, inLength);
		else
			outBytes = cipher.doFinal();
		out.write(outBytes);
		in.close();
		out.close();
	}
	
	/**
     * JCE��(Java Cryptographic Extension)�ṩ��һ��ʹ�÷ǳ�����������������࣬
     * ���ڶ��������Զ����ܻ���ܡ�
	 */
	
	/**
	 * ������Կʹ��AES�㷨�����������ݼ��ܣ��ٽ���������д�뵽ָ���ļ�
	 * 
	 * @param in
	 *            ����������
	 * @param outputFileName
	 *            ��������ļ���
	 * @param key
	 *            ��Կ
	 */
	public static void encrypt(InputStream in, String outputFileName, Key key)
			throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		// ����һ����������Ա㽫����д��out������ʹ��ָ������������ݽ��м��ܺͽ���
		CipherOutputStream cout = new CipherOutputStream(new FileOutputStream(
				outputFileName), cipher);
		crypt2(in, cout, cipher);
		cout.close();
	}

	/**
	 * ������Կʹ��AES�㷨�������ļ����ݽ��ܣ��ٽ���������д�뵽�����
	 * 
	 * @param inputFileName
	 *            �����ļ�
	 * @param out
	 *            ���������
	 * @param key
	 *            ��Կ
	 */
	public static void decrypt(String inputFileName, OutputStream out, Key key)
			throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, key);
		// ����һ�����������Զ�ȡin�е����ݣ�����ʹ��ָ������������ݽ��н��ܺͼ���
		CipherInputStream cin = new CipherInputStream(new FileInputStream(
				inputFileName), cipher);
		crypt2(cin, out, cipher);
		cin.close();
	}

	/**
	 * ʹ������ת���������е��ֽ��벢��ת������ֽ��봫���������
	 * �ڲ�ʹ���������������ת����ʹ���ܹ�͸���ش���update��doFinal������
	 * 
	 * @param in
	 *            ������
	 * @param out
	 *            �����
	 * @param cipher
	 *            ����
	 */
	private static void crypt2(InputStream in, OutputStream out, Cipher cipher)
			throws Exception {
		int blockSize = cipher.getBlockSize();
		byte[] inBytes = new byte[blockSize];
		int inLength = in.read(inBytes);
		while (inLength != -1) {
			out.write(inBytes, 0, inLength);
			inLength = in.read(inBytes);
		}
		out.flush();
		in.close();
		out.close();
	}
	
	/**
	 *                           �� �� �� Կ
    AES������һ�ֶԳ����룬���ܺͽ��ܶ�ʹ����ͬ����Կ���Գ����������ȱ����������ķַ���
���A��B����һ��������Ϣ����ôB��Ҫʹ����A��ͬ����Կ��
���A�޸�����Կ����ô�����ڸ�B������Ϣ��ͬʱ����Ҫͨ����ȫ�ŵ������µ���Կ��
����Ҳ��A����ӵ�е���B�İ�ȫ�ŵ��������ΪʲôA�������ȶ������͸�B����Ϣ���м��ܵ�ԭ��
       ������Կ���뼼�������������⡣�ڹ�����Կ�����У�Bӵ��һ����Կ�ԣ�����һ��������Կ��һ����ƥ���˽����Կ��
B�������κεط�����������Կ�������������ϸ�������˽����Կ��
Aֻ��Ҫʹ�ù�����Կ�������͸�B����Ϣ���м��ܼ��ɡ�
������Կ�㷨�Ĳ����ٶȱȶԳ���Կ�㷨���ܶ࣬�����������Կ��������ٵĶԳ��������������������Ϳ��Խ����
1��A����һ������ԳƼ�����Կ����ʹ�ø���Կ�����Ľ��м��ܡ�
2��Aʹ��B�Ĺ�����Կ���Գ���Կ���м��ܡ�
3��A�����ܺ�ĶԳ���Կ�ͼ��ܺ������ͬʱ����B��
4��B������˽����Կ���Գ���Կ���ܡ�
5��Bʹ�ý��ܺ�ĶԳ���Կ����Ϣ���ܡ�
       ����B֮�⣬�������޷����Գ���Կ���н��ܣ���Ϊֻ��Bӵ�н��ܵ�˽����Կ�� 
	 */
	
	/**
	 * ʹ�ù�����Կ�Բ����ļ����м���
	 * 
	 * @param publicKey
	 *            ��Կ
	 * @param fileName
	 *            �������ļ���
	 * @param encryptedFileName
	 *            ���ܺ���ļ������� 
	 *            1����װ������Կ�ĳ��� 
	 *            2����װ������Կ�ֽ� 
	 *            3����AES��Կ���ܵ�����
	 */
	public static void encrypt(Key publicKey, String fileName,
			String encryptedFileName) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.WRAP_MODE, publicKey);

		Key key = generateKey();// ��������ʹ�õ���Կ
		byte[] wrappedKey = cipher.wrap(key);// ʹ�ù�Կ����Կ����
		DataOutputStream out = new DataOutputStream(new FileOutputStream(
				encryptedFileName));
		out.writeInt(wrappedKey.length);
		out.write(wrappedKey);
		
		encrypt(new FileInputStream(fileName),out,key);
	}

	/**
	 * ʹ��˽����Կ�Բ����ļ����н���
	 * 
	 * @param privateKey
	 *            ˽Կ
	 * @param encryptedFileName
	 *            �����ļ���
	 * @param decryptedFileName
	 *            ���ܺ���ļ���
	 */
	public static void decrypt(Key privateKey, String encryptedFileName,
			String decryptedFileName) throws Exception {
		DataInputStream in = new DataInputStream(new FileInputStream(
				encryptedFileName));
		
		int length = in.readInt();// ��װ�����Կ����
		byte[] wrappedKey = new byte[length];
		in.read(wrappedKey, 0, length);// ��ȡ��װ�����Կ�ֽ�
		// ͨ��˽Կ���� ��װ�����Կ
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.UNWRAP_MODE, privateKey);
		Key key = cipher.unwrap(wrappedKey, "AES", Cipher.SECRET_KEY);

		decrypt(in, new FileOutputStream(decryptedFileName), key);
	}
}