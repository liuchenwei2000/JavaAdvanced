/**
 * 
 */
package util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 文件管理器
 * <p>
 * 提供复制、剪切、删除文件(夹)等功能
 *
 * @author 刘晨伟
 *
 * 创建日期：2008-10-29
 */
public class FileManager {

	/**
	 * 将源文件(夹)复制到目标文件(夹)中 
	 * <p>
	 * <li>如果isReplacement为true则会覆盖目标文件夹中已存在的同名文件
	 * <li>如果isReplacement为false若目标文件夹中存在同名文件则会抛出异常
	 * 
	 * @param sourceFilePath
	 *            源文件(夹)名称
	 * @param destDirPath
	 *            目标文件夹名称
	 * @param replaceable
	 *            是否覆盖目标文件夹中已存在的同名文件
	 */
	public static void copy(String sourceFilePath, String destDirPath,
			boolean replaceable) throws Exception {
		if (isEmpty(sourceFilePath) || isEmpty(destDirPath)) {
			throw new IllegalArgumentException(
					"source and dest file name cannot be null.");
		}
		File sourceFile = new File(sourceFilePath);
		if (!sourceFile.exists())
			throw new RuntimeException("找不到源文件(夹): " + sourceFilePath);
		File destDir = new File(destDirPath);
		if (!destDir.exists())
			throw new RuntimeException("找不到目标文件(夹): " + destDirPath);
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
		// 复制到目标文件夹的文件路径
		String destFileName = destDirPath + File.separator
				+ sourceFile.getName();
		File destFile = new File(destFileName);
		if (destFile.exists() && !replaceable) {
				throw new RuntimeException("目标文件：" + destFileName + " 已经存在。");
		}
		copyPrivate(sourceFilePath, destFileName);
		System.out.println("copy file 【" + sourceFilePath + "】 to 【"
				+ destDirPath + "】 successfully.");
	}

	/**
	 * 将源文件复制到目标文件中
	 * 
	 * @param sourceFileName
	 *            源文件全路径名
	 * @param destFileName
	 *            目标文件全路径名
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
	 * 将源文件夹复制到目标文件夹中 
	 * 
	 * @param sourceDirPath
	 *            源文件夹名称
	 * @param destDirPath
	 *            目标文件夹名称
	 * @param replaceable
	 *            是否覆盖目标文件夹的已存在文件
	 */
	private static void copyDir(String sourceDirPath, String destDirPath,
			boolean replaceable) throws Exception {
		copyDirPrivate(sourceDirPath, destDirPath, replaceable);
		System.out.println("copy dir 【" + sourceDirPath + "】 successfully.");
	}

	/**
	 * 将源文件夹复制到目标文件夹中
	 * 
	 * @param sourceDirPath
	 *            源文件夹名称
	 * @param destDirPath
	 *            目标文件夹名称
	 * @param replaceable
	 *            是否覆盖目标文件夹的已存在文件
	 */
	private static void copyDirPrivate(String sourceDirPath,
			String destDirPath, boolean replaceable) throws Exception {
		File sourceDir = new File(sourceDirPath);
		// 源文件是标准文件则直接复制
		if (sourceDir.isFile()) {
			copyFile(sourceDirPath, destDirPath, replaceable);
		} else {
			// 复制源文件夹里的所有文件
			copyDirAllFiles(sourceDirPath, destDirPath, replaceable);
		}
	}

	/**
	 * 将源文件夹中的所有文件都复制到目标文件夹
	 * 
	 * @param sourceDirPath
	 *            源文件夹名称
	 * @param destDirPath
	 *            目标文件夹名称
	 * @param replaceable
	 *            是否覆盖目标文件夹的已存在文件
	 */
	private static void copyDirAllFiles(String sourceDirPath,
			String destDirPath, boolean replaceable) throws Exception {
		File sourceDir = new File(sourceDirPath);
		String dirName = destDirPath + File.separator + sourceDir.getName();
		String[] fileNames = sourceDir.list();
		// 复制一个空文件夹
		if (fileNames == null) {
			new File(dirName).mkdirs();
			return;
		}
		// 复制每一个文件
		for (String fileName : fileNames) {
			String sourceFileName = sourceDirPath + File.separator + fileName;
			copyDirPrivate(sourceFileName, dirName, replaceable);
		}
	}
	
	/**
	 * 删除参数文件名指定的文件(夹)
	 * <p>
	 * <li>如果file是标准文件则直接删除 
	 * <li>如果file是文件夹则删除其下的所有文件
	 * 
	 * @param fileName
	 *            待删除文件(夹)名
	 */
	public static void delete(String fileName) throws Exception {
		if (isEmpty(fileName)) {
			throw new IllegalArgumentException("file name cannot be null.");
		}
		File file = new File(fileName);
		if (!file.exists()) return;
		deletePrivate(file);
		System.out.println("delete 【" + fileName + "】 successfully.");
	}

	/**
	 * 删除文件
	 * 
	 * @param file
	 *            待删除文件
	 */
	private static void deletePrivate(File file) {
		if (file.isFile()) {
			file.delete();
		} else {
			deleteDir(file);
		}
	}

	/**
	 * 删除文件夹
	 * <p>
	 * 先删除其下所有的文件再删除文件夹自己
	 * 
	 * @param dir
	 *            文件夹
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
	 * 将源文件(夹)剪切到目标文件(夹)中 
	 * <p>
	 * <li>如果isReplacement为true则会覆盖目标文件夹中已存在的同名文件
	 * <li>如果isReplacement为false若目标文件夹中存在同名文件则会抛出异常
	 * 
	 * @param sourceFilePath
	 *            源文件(夹)名称
	 * @param destDirPath
	 *            目标文件夹名称
	 * @param replaceable
	 *            是否覆盖目标文件夹中已存在的同名文件
	 */
	public static void cut(String sourceFilePath, String destDirPath,
			boolean replaceable) throws Exception {
		// 内部实现没有使用file.renameTo方法，而是采用先复制再删除源文件的策略
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
