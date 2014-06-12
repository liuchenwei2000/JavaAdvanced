/**
 * 
 */
package swing.filechooser.component;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import util.Displayer;

/**
 * 文件选择控件类
 * <p>
 * 控件面板包括一个TextField和一个Button。
 * 当点击Button的时候能够弹出一个FileChooser对话框。
 * 选择完文件点击确定后能够将文件完成路径名写到TextField里面。
 * 
 * @author 刘晨伟
 *
 * 创建日期：2007-11-13
 */
public class FileChooserPane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -612311745368894675L;
	
	/** 输入框 宽度 */
	private static final int WIDTH = 180;
	/** 控件 高度 */
	private static final int HEIGHT = 21;

	/* 按钮默认图标 */
	private ImageIcon icon = new ImageIcon(getClass().getResource("browse.GIF"));
	
	private JTextField textField;// 文件路径 栏
	private JButton button;// 打开 按钮

	protected FileChooserPaneEHD ehd;// 事件处理对象

	public FileChooserPane() {
		init();
	}

	private void init() {
		setLayout(null);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		add(getTextField());
		add(getButton());
		initConnections();
	}

	private void initConnections() {
		getButton().addActionListener(getFileChooserPaneEHD());
	}

	/**
	 * 文件路径 栏
	 */
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBorder(BorderFactory.createLineBorder(new Color(0X7f9db9)));
			textField.setBounds(0, 0, WIDTH - HEIGHT + 1, HEIGHT);
		}
		return textField;
	}

	/**
	 * 按钮
	 */
	private JButton getButton() {
		if (button == null) {
			button = new JButton(icon);
			button.setBounds(WIDTH - HEIGHT, 0, HEIGHT, HEIGHT);
		}
		return button;
	}

	/**
	 * 事件处理对象
	 */
	protected FileChooserPaneEHD getFileChooserPaneEHD() {
		if (ehd == null) {
			ehd = new FileChooserPaneEHD(this);
		}
		return ehd;
	}

	/**
	 * 文本域的内容(即选择文件的全路径)
	 */
	public String getFilePath() {
		return getTextField().getText();
	}

	/**
	 * 用参数设置文本域内容(即选择文件的全路径)
	 * 
	 * @param path
	 *            文本
	 */
	public void setFilePath(String path) {
		File file = new File(path);
		if (!file.exists())
			throw new RuntimeException("指定文件不存在.");
		getTextField().setText(path);
	}

	/**
	 * 按钮和文本框可用性
	 * 
	 * @see javax.swing.JComponent#setEnabled(boolean)
	 */
	public void setEnabled(boolean enabled) {
		getTextField().setEnabled(enabled);
		getTextField().setEditable(enabled);
		getButton().setEnabled(enabled);
	}
	
	/**
	 * 设置文件选择器的选择模式 
	 * <p>
	 * 允许用户只选择文件、只选择目录，或者可选择文件和目录 。
	 * 默认选择模式为文件夹和文件都可选择。
	 * 
	 * @param mode
	 *            选择模式
	 * @see JFileChooser.FILES_ONLY 
	 *      JFileChooser.DIRECTORIES_ONLY
	 *      JFileChooser.FILES_AND_DIRECTORIES
	 */
	public void setFileSelectionMode(int mode) {
		getFileChooserPaneEHD().setFileSelectionMode(mode);
	}
	
	/**
	 * 设置文件选择器的filter
	 * 
	 * @param fileFilter
	 *            文件过滤器
	 */
	public void setFileFilter(FileFilter fileFilter) {
		getFileChooserPaneEHD().setFileFilter(fileFilter);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("FileChooserPane", new FileChooserPane());
	}
}