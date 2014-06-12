/**
 * 
 */
package swing.filechooser;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * JPG文件过滤器
 * 
 * @author 刘晨伟
 *
 * 创建日期：2009-1-21
 */
public class JPGFileFilter extends FileFilter {

	/**
	 * 判断是否为可选的文件
	 */
	public boolean accept(File f) {
		return f.isDirectory() || f.getName().toLowerCase().endsWith(".jpg");
	}

	/**
	 * 文件描述
	 */
	public String getDescription() {
		return "*.jpg";
	}
}