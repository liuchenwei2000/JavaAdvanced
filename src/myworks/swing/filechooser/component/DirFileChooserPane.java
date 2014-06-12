/**
 * 
 */
package swing.filechooser.component;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 * Ŀ¼�ļ��򿪿ؼ���
 * 
 * @author ����ΰ
 *
 * �������ڣ�2007-11-14
 */
public class DirFileChooserPane extends FileChooserPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4899544813393367247L;

	public DirFileChooserPane() {
		super();
		setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		setFileFilter(new FileFilter() {

			/**
			 * ֻ�е��ļ����ļ��е�ʱ��Ž���
			 */
			public boolean accept(File f) {
				return f.isDirectory();
			}

			/**
			 * �����ļ�������Ϣ
			 */
			public String getDescription() {
				return "�ļ���";
			}
		});
	}
}