/**
 * 
 */
package util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * �ļ�������
 * <p>
 * �ṩ���ơ����С�ɾ���ļ�(��)�ȹ���
 *
 * @author ����ΰ
 *
 * �������ڣ�2008-10-29
 */
public class FileManager {

	/**
	 * ��Դ�ļ�(��)���Ƶ�Ŀ���ļ�(��)�� 
	 * <p>
	 * <li>���isReplacementΪtrue��Ḳ��Ŀ���ļ������Ѵ��ڵ�ͬ���ļ�
	 * <li>���isReplacementΪfalse��Ŀ���ļ����д���ͬ���ļ�����׳��쳣
	 * 
	 * @param sourceFilePath
	 *            Դ�ļ�(��)����
	 * @param destDirPath
	 *            Ŀ���ļ�������
	 * @param replaceable
	 *            �Ƿ񸲸�Ŀ���ļ������Ѵ��ڵ�ͬ���ļ�
	 */
	public static void copy(String sourceFilePath, String destDirPath,
			boolean replaceable) throws Exception {
		if (isEmpty(sourceFilePath) || isEmpty(destDirPath)) {
			throw new IllegalArgumentException(
					"source and dest file name cannot be null.");
		}
		File sourceFile = new File(sourceFilePath);
		if (!sourceFile.exists())
			throw new RuntimeException("�Ҳ���Դ�ļ�(��): " + sourceFilePath);
		File destDir = new File(destDirPath);
		if (!destDir.exists())
			throw new RuntimeException("�Ҳ���Ŀ���ļ�(��): " + destDirPath);
		if (sourceFile.isDirectory()) {
			copyDir(sourceFilePath, destDirPath, replaceable);
		} else {
			copyFile(sourceFilePath, destDirPath, replaceable);
		}
	}
	
	private static void copyFile(String sourceFilePath, String destDirPath,
			boolean replaceable) throws Exception {
		File sourceFile = new File(sourceFilePath);
		File destDir = new File(destDirPath);
		if (!destDir.exists()) destDir.mkdirs();
		// ���Ƶ�Ŀ���ļ��е��ļ�·��
		String destFileName = destDirPath + File.separator
				+ sourceFile.getName();
		File destFile = new File(destFileName);
		if (destFile.exists() && !replaceable) {
				throw new RuntimeException("Ŀ���ļ���" + destFileName + " �Ѿ����ڡ�");
		}
		copyPrivate(sourceFilePath, destFileName);
		System.out.println("copy file ��" + sourceFilePath + "�� to ��"
				+ destDirPath + "�� successfully.");
	}

	/**
	 * ��Դ�ļ����Ƶ�Ŀ���ļ���
	 * 
	 * @param sourceFileName
	 *            Դ�ļ�ȫ·����
	 * @param destFileName
	 *            Ŀ���ļ�ȫ·����
	 */
	private static void copyPrivate(String sourceFileName, String destFileName)
			throws Exception {
		FileInputStream input = new FileInputStream(sourceFileName);
		FileOutputStream output = new FileOutputStream(destFileName);
		int bytesRead;
		byte[] buffer = new byte[32 * 1024];
		while ((bytesRead = input.read(buffer, 0, buffer.length)) > 0) {
			output.write(buffer, 0, bytesRead);
		}
		input.close();
		output.close();
	}
	
	/**
	 * ��Դ�ļ��и��Ƶ�Ŀ���ļ����� 
	 * 
	 * @param sourceDirPath
	 *            Դ�ļ�������
	 * @param destDirPath
	 *            Ŀ���ļ�������
	 * @param replaceable
	 *            �Ƿ񸲸�Ŀ���ļ��е��Ѵ����ļ�
	 */
	private static void copyDir(String sourceDirPath, String destDirPath,
			boolean replaceable) throws Exception {
		copyDirPrivate(sourceDirPath, destDirPath, replaceable);
		System.out.println("copy dir ��" + sourceDirPath + "�� successfully.");
	}

	/**
	 * ��Դ�ļ��и��Ƶ�Ŀ���ļ�����
	 * 
	 * @param sourceDirPath
	 *            Դ�ļ�������
	 * @param destDirPath
	 *            Ŀ���ļ�������
	 * @param replaceable
	 *            �Ƿ񸲸�Ŀ���ļ��е��Ѵ����ļ�
	 */
	private static void copyDirPrivate(String sourceDirPath,
			String destDirPath, boolean replaceable) throws Exception {
		File sourceDir = new File(sourceDirPath);
		// Դ�ļ��Ǳ�׼�ļ���ֱ�Ӹ���
		if (sourceDir.isFile()) {
			copyFile(sourceDirPath, destDirPath, replaceable);
		} else {
			// ����Դ�ļ�����������ļ�
			copyDirAllFiles(sourceDirPath, destDirPath, replaceable);
		}
	}

	/**
	 * ��Դ�ļ����е������ļ������Ƶ�Ŀ���ļ���
	 * 
	 * @param sourceDirPath
	 *            Դ�ļ�������
	 * @param destDirPath
	 *            Ŀ���ļ�������
	 * @param replaceable
	 *            �Ƿ񸲸�Ŀ���ļ��е��Ѵ����ļ�
	 */
	private static void copyDirAllFiles(String sourceDirPath,
			String destDirPath, boolean replaceable) throws Exception {
		File sourceDir = new File(sourceDirPath);
		String dirName = destDirPath + File.separator + sourceDir.getName();
		String[] fileNames = sourceDir.list();
		// ����һ�����ļ���
		if (fileNames == null) {
			new File(dirName).mkdirs();
			return;
		}
		// ����ÿһ���ļ�
		for (String fileName : fileNames) {
			String sourceFileName = sourceDirPath + File.separator + fileName;
			copyDirPrivate(sourceFileName, dirName, replaceable);
		}
	}
	
	/**
	 * ɾ�������ļ���ָ�����ļ�(��)
	 * <p>
	 * <li>���file�Ǳ�׼�ļ���ֱ��ɾ�� 
	 * <li>���file���ļ�����ɾ�����µ������ļ�
	 * 
	 * @param fileName
	 *            ��ɾ���ļ�(��)��
	 */
	public static void delete(String fileName) throws Exception {
		if (isEmpty(fileName)) {
			throw new IllegalArgumentException("file name cannot be null.");
		}
		File file = new File(fileName);
		if (!file.exists()) return;
		deletePrivate(file);
		System.out.println("delete ��" + fileName + "�� successfully.");
	}

	/**
	 * ɾ���ļ�
	 * 
	 * @param file
	 *            ��ɾ���ļ�
	 */
	private static void deletePrivate(File file) {
		if (file.isFile()) {
			file.delete();
		} else {
			deleteDir(file);
		}
	}

	/**
	 * ɾ���ļ���
	 * <p>
	 * ��ɾ���������е��ļ���ɾ���ļ����Լ�
	 * 
	 * @param dir
	 *            �ļ���
	 */
	private static void deleteDir(File dir) {
		String[] fileNames = dir.list();
		if (fileNames == null) {
			dir.delete();
			return;
		}
		String dirPath = dir.getAbsolutePath();
		for (String fileName : fileNames) {
			File file = new File(dirPath + File.separator + fileName);
			deletePrivate(file);
		}
		dir.delete();
	}
	
	/**
	 * ��Դ�ļ�(��)���е�Ŀ���ļ�(��)�� 
	 * <p>
	 * <li>���isReplacementΪtrue��Ḳ��Ŀ���ļ������Ѵ��ڵ�ͬ���ļ�
	 * <li>���isReplacementΪfalse��Ŀ���ļ����д���ͬ���ļ�����׳��쳣
	 * 
	 * @param sourceFilePath
	 *            Դ�ļ�(��)����
	 * @param destDirPath
	 *            Ŀ���ļ�������
	 * @param replaceable
	 *            �Ƿ񸲸�Ŀ���ļ������Ѵ��ڵ�ͬ���ļ�
	 */
	public static void cut(String sourceFilePath, String destDirPath,
			boolean replaceable) throws Exception {
		// �ڲ�ʵ��û��ʹ��file.renameTo���������ǲ����ȸ�����ɾ��Դ�ļ��Ĳ���
		copy(sourceFilePath, destDirPath, replaceable);
		delete(sourceFilePath);
	}
	
	private static boolean isEmpty(Object object) {
		return object == null || object.toString().trim().length() == 0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			copy("C:/test.txt","D:/a",true);
			copy("C:/test","D:/a",true);
			cut("C:/test.txt","D:/a",true);
			delete("C:/test");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}