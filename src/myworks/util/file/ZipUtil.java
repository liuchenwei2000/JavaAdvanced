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
 * zip�ļ�ѹ��������
 * <p>
 * ѹ������ǰ����ֽڷ�ʽ�������ַ���ʽ�����
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-7-20
 */
public class ZipUtil {

	/**
	 * ѹ��ָ���ļ���ָ��zip�ļ�
	 * 
	 * @param source
	 *            ��ѹ��Դ�ļ� ��"C:/a.txt"
	 * @param destZipPath
	 *            ѹ����zip�ļ�·�� ��"C:/a.zip"
	 */
	public static void zip(File source, String destZipPath) throws IOException {
		zip(source, null, destZipPath);
	}

	/**
	 * ѹ��ָ��Ŀ¼����������δ�����˵����ļ�(��)��ָ��zip�ļ�
	 * 
	 * @param source
	 *            ��ѹ��ԴĿ¼ ��"C:/a"
	 * @param filter
	 *            ԴĿ¼ʹ�õ��ļ���������
	 * @param destZipPath
	 *            ѹ����zip�ļ�·�� ��"C:/a.zip"
	 */
	public static void zip(File source, FilenameFilter filter,
			String destZipPath) throws IOException {
		if (source == null || !source.exists()) {
			throw new IllegalArgumentException("source file is not found.");
		}
		File zipFile = new File(destZipPath);
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
		// ��Դ�ļ���Ϊ�²��Ŀ¼
		zip(out, source, filter, source.getName());
		out.close();
	}

	/**
	 * ��Դ�ļ�(��)ѹ����ѹ�������ָ�����ļ���
	 * 
	 * @param out
	 *            ѹ������ļ�������ѹ�������
	 * @param source
	 *            ��ѹ��Դ�ļ�
	 * @param filter
	 *            ԴĿ¼ʹ�õ��ļ���������
	 * @param base
	 *            ѹ����Ŀ�Ļ�·��
	 */
	private static void zip(ZipOutputStream out, File source,
			FilenameFilter filter, String base) throws IOException {
		FileInputStream in = null;
		// �����ļ���ʱʹ���˵ݹ�
		if (source.isDirectory()) {
			File[] files = source.listFiles(filter);
			out.putNextEntry(new ZipEntry(base + "/"));
			// ƴ�����ļ���ѹ����Ŀ·��
			for (File f : files) {
				// ѹ�����ļ��������е��ļ����ļ���
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