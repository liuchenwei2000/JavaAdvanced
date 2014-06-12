/**
 * 
 */
package swing.filechooser.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 * 文件打开控件事件处理类
 * 
 * @author 刘晨伟
 *
 * 创建日期：2007-11-13
 */
public class FileChooserPaneEHD implements ActionListener {

	private FileChooserPane chooserPane;// 关联的文件选择控件

	private JFileChooser chooser;// 文件选择器对象

	/**
	 * @param chooserPane
	 *            关联的文件选择控件
	 */
	public FileChooserPaneEHD(FileChooserPane chooserPane) {
		this.chooserPane = chooserPane;
	}

	public void actionPerformed(ActionEvent e) {
		int option = getChooser().showOpenDialog(chooserPane);
		if (option == JFileChooser.APPROVE_OPTION) {
			File file = getChooser().getSelectedFile();
			String filePath = file.getAbsolutePath();
			chooserPane.setFilePath(filePath);
			getChooser().setSelectedFile(null);
		}
	}

	private JFileChooser getChooser() {
		if (chooser == null) {
			chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		}
		return chooser;
	}
	
	/**
	 * 设置文件选择器的选择模式 
	 * <p>
	 * 允许用户只选择文件、只选择目录，或者可选择文件和目录 。
	 * 默认选择模式为文件夹和文件都可选择。
	 * 
	 * @param mode
	 *            选择模式
	 */
	protected void setFileSelectionMode(int mode) {
		getChooser().setFileSelectionMode(mode);
	}
	
	/**
	 * 设置文件选择器的filter
	 * 
	 * @param fileFilter
	 *            文件过滤器
	 */
	protected void setFileFilter(FileFilter fileFilter) {
		getChooser().setFileFilter(fileFilter);
	}
}