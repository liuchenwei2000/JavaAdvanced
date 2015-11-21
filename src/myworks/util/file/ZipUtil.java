/**
 * 
 */
package util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * zip文件压缩工具类
 * <p>
 * 压缩类库是按照字节方式而不是字符方式处理的
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-7-20
 */
public class ZipUtil {

	/**
	 * 压缩指定文件到指定zip文件
	 * 
	 * @param source
	 *            待压缩源文件 如"C:/a.txt"
	 * @param destZipPath
	 *            压缩后zip文件路径 如"C:/a.zip"
	 */
	public static void zip(File source, String destZipPath) throws IOException {
		zip(source, null, destZipPath);
	}

	/**
	 * 压缩指定目录及其下所有未被过滤掉的文件(夹)到指定zip文件
	 * 
	 * @param source
	 *            待压缩源目录 如"C:/a"
	 * @param filter
	 *            源目录使用的文件名过滤器
	 * @param destZipPath
	 *            压缩后zip文件路径 如"C:/a.zip"
	 */
	public static void zip(File source, FilenameFilter filter,
			String destZipPath) throws IOException {
		if (source == null || !source.exists()) {
			throw new IllegalArgumentException("source file is not found.");
		}
		File zipFile = new File(destZipPath);
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
		// 以源文件名为下层基目录
		zip(out, source, filter, source.getName());
		out.close();
	}

	/**
	 * 将源文件(夹)压缩到压缩输出流指定的文件中
	 * 
	 * @param out
	 *            压缩后的文件创建的压缩输出流
	 * @param source
	 *            待压缩源文件
	 * @param filter
	 *            源目录使用的文件名过滤器
	 * @param base
	 *            压缩条目的基路径
	 */
	private static void zip(ZipOutputStream out, File source,
			FilenameFilter filter, String base) throws IOException {
		FileInputStream in = null;
		// 处理文件夹时使用了递归
		if (source.isDirectory()) {
			File[] files = source.listFiles(filter);
			out.putNextEntry(new ZipEntry(base + "/"));
			// 拼出该文件的压缩条目路径
			for (File f : files) {
				// 压缩该文件夹下所有的文件和文件夹
				zip(out, f, filter, base + "/" + f.getName());
			}
		} else {
			out.putNextEntry(new ZipEntry(base));
			in = new FileInputStream(source);
			int b;
			while ((b = in.read()) != -1) {
				out.write(b);
			}
		}
		if (in != null) {
			in.close();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File source = new File(System.getProperty("user.dir"));
		String zipFilePath = source.getParent() + File.separator
				+ source.getName() + ".zip";
		try {
			zip(source, new FilenameFilter() {

				public boolean accept(File dir, String name) {
					File file = new File(dir, name);
					return file.isDirectory()
							|| name.toLowerCase().endsWith(".java");
				}
			}, zipFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
