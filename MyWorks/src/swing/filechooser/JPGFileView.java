/**
 * 
 */
package swing.filechooser;

import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileView;

/**
 * JPG文件默认视图(在JFileChooser中显示的小图标)
 * 
 * @author 刘晨伟
 *
 * 创建日期：2009-1-21
 */
public class JPGFileView extends FileView {

	private JPGFileFilter filter = new JPGFileFilter();

	public Icon getIcon(File f) {
		if ((!f.isDirectory()) && filter.accept(f)) {
			return new ImageIcon("images/gui.swing.filechooser/view.gif");
		} else {
			return super.getIcon(f);
		}
	}
}
