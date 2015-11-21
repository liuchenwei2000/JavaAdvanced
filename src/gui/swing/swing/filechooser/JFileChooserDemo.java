/**
 * 
 */
package swing.filechooser;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

import util.Displayer;

/**
 * JFileChooser演示类
 * <p>
 * 图形操作系统一般都支持打开和保存文件，所以Java提供了JFileChooser。
 * 它封装了这些操作，使文件操作变得更加方便。
 *
 * @author 刘晨伟
 *
 * 创建日期：2009-5-19
 */
public class JFileChooserDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JFileChooser Demo", new JFileChooserPanel());
	}
}

class JFileChooserPanel extends JPanel {

	private static final long serialVersionUID = 2341949551169574520L;
	
	private JTextField filenameField = new JTextField(15); 
	private JTextField dirpathField = new JTextField(15);
	
	private JButton openButton = new JButton("Open");
	private JButton saveButton = new JButton("Save");

	public JFileChooserPanel() {
		initUI();
	}

	private void initUI() {
		setLayout(new BorderLayout());
		add(getButtonPanel(), BorderLayout.NORTH);
		add(getFieldPanel(), BorderLayout.SOUTH);
	}

	private JPanel getButtonPanel() {
		JPanel buttonPanel = new JPanel();
		openButton.addActionListener(new OpenActionListener());
		saveButton.addActionListener(new SaveActionListener());
		buttonPanel.add(openButton);
		buttonPanel.add(saveButton);
		return buttonPanel;
	}

	private JPanel getFieldPanel() {
		JPanel fieldPanel = new JPanel();
		dirpathField.setEditable(false);
		filenameField.setEditable(false);
		fieldPanel.setLayout(new GridLayout(2, 1));
		fieldPanel.add(filenameField);
		fieldPanel.add(dirpathField);
		return fieldPanel;
	}

	/**
	 * 打开动作监听器
	 */
	class OpenActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			// 设置选择器默认的当前目录
			chooser.setCurrentDirectory(new File("."));
			int result = chooser.showOpenDialog(JFileChooserPanel.this);
			//  选择确认(yes、ok)后返回该值
			if (result == JFileChooser.APPROVE_OPTION) {
				// 返回选中的文件，可由程序员通过setSelectedFile()或者通过用户操作
				// 如在UI中键入文件名，或者从UI中的列表内选择文件来进行此设置
				filenameField.setText(chooser.getSelectedFile().getName());
				// 返回当前目录
				dirpathField.setText(chooser.getCurrentDirectory().toString());
			} 
			// 选择取消后返回该值
			else if (result == JFileChooser.CANCEL_OPTION) {
				filenameField.setText("You pressed cancel");
				dirpathField.setText("");
			}
		}
	}

	/**
	 * 保存动作监听器
	 */
	class SaveActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			int result = chooser.showSaveDialog(JFileChooserPanel.this);
			if (result == JFileChooser.APPROVE_OPTION) {
				filenameField.setText(chooser.getSelectedFile().getName());
				dirpathField.setText(chooser.getCurrentDirectory().toString());
			}
			if (result == JFileChooser.CANCEL_OPTION) {
				filenameField.setText("You pressed cancel");
				dirpathField.setText("");
			}
		}
	}
}
