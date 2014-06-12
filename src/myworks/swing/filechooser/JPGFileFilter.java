/**
 * 
 */
package swing.filechooser;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * JPG�ļ�������
 * 
 * @author ����ΰ
 *
 * �������ڣ�2009-1-21
 */
public class JPGFileFilter extends FileFilter {

	/**
	 * �ж��Ƿ�Ϊ��ѡ���ļ�
	 */
	public boolean accept(File f) {
		return f.isDirectory() || f.getName().toLowerCase().endsWith(".jpg");
	}

	/**
	 * �ļ�����
	 */
	public String getDescription() {
		return "*.jpg";
	}
}