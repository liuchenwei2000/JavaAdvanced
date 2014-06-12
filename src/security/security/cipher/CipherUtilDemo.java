/**
 * 
 */
package security.cipher;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyPair;

/**
 * 密码工具类演示
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-2-20
 */
public class CipherUtilDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Key key = CipherUtil.generateKey();
			KeyPair keyPair = CipherUtil.generateKeyPair();

			String fileName = "files/security.cipher/encrypt.txt";
			String encryptedFileName = "files/security.cipher/cryptograph.txt";
			String decryptedFileName = "files/security.cipher/decrypt.txt";

			InputStream in1 = new FileInputStream(fileName);
			OutputStream out1 = new FileOutputStream(encryptedFileName);
			InputStream in2 = new FileInputStream(encryptedFileName);
			OutputStream out2 = new FileOutputStream(decryptedFileName);

			// 加密文件 生成密文 cryptograph.txt
			CipherUtil.encrypt(in1, out1, key);
			// 解密 cryptograph.txt 文件
			CipherUtil.decrypt(in2, out2, key);

//			// 加密文件 生成密文 cryptograph.txt
//			CipherUtil.encrypt(in1, encryptedFileName, key);
//			// 解密 cryptograph.txt 文件
//			CipherUtil.decrypt(encryptedFileName, out2, key);

			CipherUtil.encrypt(keyPair.getPublic(), fileName, encryptedFileName);
			CipherUtil.decrypt(keyPair.getPrivate(), encryptedFileName,
					decryptedFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}