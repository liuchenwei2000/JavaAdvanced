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
 * 密码工具类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-2-20
 */
public class CipherUtil {

	/** 密钥对中公钥和密钥的长度 */
	private static final int KEY_SIZE = 512;
	
	/**
	 * 使用AES算法生成随机密钥
	 */
	public static Key generateKey() {
		Key key = null;
		try {
			// KeyGenertaor类提供(对称)密钥生成器的功能 算法有AES、DES等
			KeyGenerator gen = KeyGenerator.getInstance("AES");
			// SecureRandom类提供加密的强随机数生成器
			SecureRandom random = new SecureRandom();
			gen.init(random);// 初始化此密钥生成器
			key = gen.generateKey();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return key;
	}

	/**
	 * 使用RSA算法生成随机密钥对(密钥长度512)
	 */
	public static KeyPair generateKeyPair() {
		KeyPair keyPair = null;
		try {
			// KeyPairGenerator类用于生成公钥和私钥对 算法有RSA等
			KeyPairGenerator pairgen = KeyPairGenerator.getInstance("RSA");
			SecureRandom random = new SecureRandom();
			// 使用给定的随机源初始化确定密钥长度的密钥对生成器 
			pairgen.initialize(KEY_SIZE, random);
			keyPair = pairgen.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return keyPair;
	}
	
	/**
	 * 根据密钥使用AES算法将输入流数据加密，再将密文数据写入到输出流
	 * 
	 * @param in
	 *            明文输入流
	 * @param out
	 *            密文输出流
	 * @param key
	 *            密钥
	 */
	public static void encrypt(InputStream in, OutputStream out, Key key)
			throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		// Cipher对象的初始化方式(加密or解密)
		cipher.init(Cipher.ENCRYPT_MODE, key);
		crypt1(in, out, cipher);
	}

	/**
	 * 根据密钥使用AES算法将输入流数据解密，再将明文数据写入到输出流
	 * 
	 * @param in
	 *            密文输入流
	 * @param out
	 *            明文输出流
	 * @param key
	 *            密钥
	 */
	public static void decrypt(InputStream in, OutputStream out, Key key)
			throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, key);
		crypt1(in, out, cipher);
	}

	/**
	 * 使用密码转换输入流中的字节码并将转换后的字节码传给输出流
	 * 
	 * @param in
	 *            输入流
	 * @param out
	 *            输出流
	 * @param cipher
	 *            密码
	 */
	private static void crypt1(InputStream in, OutputStream out, Cipher cipher)
			throws Exception {
		// 详见Cipher类文档
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
     * JCE库(Java Cryptographic Extension)提供了一组使用非常方便的密码流工具类，
     * 用于对流进行自动加密或解密。
	 */
	
	/**
	 * 根据密钥使用AES算法将输入流数据加密，再将密文数据写入到指定文件
	 * 
	 * @param in
	 *            明文输入流
	 * @param outputFileName
	 *            密文输出文件名
	 * @param key
	 *            密钥
	 */
	public static void encrypt(InputStream in, String outputFileName, Key key)
			throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		// 构建一个输出流，以便将数据写入out，并且使用指定的密码对数据进行加密和解密
		CipherOutputStream cout = new CipherOutputStream(new FileOutputStream(
				outputFileName), cipher);
		crypt2(in, cout, cipher);
		cout.close();
	}

	/**
	 * 根据密钥使用AES算法将密文文件数据解密，再将明文数据写入到输出流
	 * 
	 * @param inputFileName
	 *            密文文件
	 * @param out
	 *            明文输出流
	 * @param key
	 *            密钥
	 */
	public static void decrypt(String inputFileName, OutputStream out, Key key)
			throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, key);
		// 构建一个输入流，以读取in中的数据，并且使用指定的密码对数据进行解密和加密
		CipherInputStream cin = new CipherInputStream(new FileInputStream(
				inputFileName), cipher);
		crypt2(cin, out, cipher);
		cin.close();
	}

	/**
	 * 使用密码转换输入流中的字节码并将转换后的字节码传给输出流。
	 * 内部使用了密码流类进行转换，使得能够透明地处理update和doFinal方法。
	 * 
	 * @param in
	 *            输入流
	 * @param out
	 *            输出流
	 * @param cipher
	 *            密码
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
	 *                           公 共 密 钥
    AES密码是一种对称密码，加密和解密都使用相同的密钥。对称密码的致命缺点在于密码的分发。
如果A给B发送一个加密信息，那么B需要使用与A相同的密钥。
如果A修改了密钥，那么必须在给B发送信息的同时，还要通过安全信道发送新的密钥，
但是也许A并不拥有到达B的安全信道，这就是为什么A必须首先对他发送给B的信息进行加密的原因。
       公共密钥密码技术解决了这个问题。在公共密钥密码中，B拥有一个密钥对，包括一个公共密钥和一个相匹配的私有密钥。
B可以在任何地方公布公共密钥，但是他必须严格保守他的私有密钥。
A只需要使用公共密钥对她发送给B的信息进行加密即可。
公共密钥算法的操作速度比对称密钥算法慢很多，如果将公共密钥密码与快速的对称密码结合起来，这个问题就可以解决：
1，A生成一个随机对称加密密钥，它使用该密钥对明文进行加密。
2，A使用B的公共密钥给对称密钥进行加密。
3，A将加密后的对称密钥和加密后的明文同时发给B。
4，B用他的私有密钥给对称密钥解密。
5，B使用解密后的对称密钥给信息解密。
       除了B之外，其他人无法给对称密钥进行解密，因为只有B拥有解密的私有密钥。 
	 */
	
	/**
	 * 使用公共密钥对参数文件进行加密
	 * 
	 * @param publicKey
	 *            公钥
	 * @param fileName
	 *            待加密文件名
	 * @param encryptedFileName
	 *            加密后的文件包含： 
	 *            1，包装过的密钥的长度 
	 *            2，包装过的密钥字节 
	 *            3，用AES密钥加密的明文
	 */
	public static void encrypt(Key publicKey, String fileName,
			String encryptedFileName) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.WRAP_MODE, publicKey);

		Key key = generateKey();// 加密明文使用的密钥
		byte[] wrappedKey = cipher.wrap(key);// 使用公钥对密钥加密
		DataOutputStream out = new DataOutputStream(new FileOutputStream(
				encryptedFileName));
		out.writeInt(wrappedKey.length);
		out.write(wrappedKey);
		
		encrypt(new FileInputStream(fileName),out,key);
	}

	/**
	 * 使用私有密钥对参数文件进行解密
	 * 
	 * @param privateKey
	 *            私钥
	 * @param encryptedFileName
	 *            密文文件名
	 * @param decryptedFileName
	 *            解密后的文件名
	 */
	public static void decrypt(Key privateKey, String encryptedFileName,
			String decryptedFileName) throws Exception {
		DataInputStream in = new DataInputStream(new FileInputStream(
				encryptedFileName));
		
		int length = in.readInt();// 包装后的密钥长度
		byte[] wrappedKey = new byte[length];
		in.read(wrappedKey, 0, length);// 读取包装后的密钥字节
		// 通过私钥解密 包装后的密钥
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.UNWRAP_MODE, privateKey);
		Key key = cipher.unwrap(wrappedKey, "AES", Cipher.SECRET_KEY);

		decrypt(in, new FileOutputStream(decryptedFileName), key);
	}
}
