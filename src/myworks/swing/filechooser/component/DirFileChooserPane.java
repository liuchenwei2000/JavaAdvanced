/**
 * 
 */
package swing.filechooser.component;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 * 目录文件打开控件类
 * 
 * @author 刘晨伟
 *
 * 创建日期：2007-11-14
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
			 * 只有当文件是文件夹的时候才接受
			 */
			public boolean accept(File f) {
				return f.isDirectory();
			}

			/**
			 * 返回文件描述信息
			 */
			public String getDescription() {
				return "文件夹";
			}
		});
	}
}