/**
 * 
 */
package applet.sign;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * �����ļ�ϵͳ��Applet��ʾ��
 * <p>
 * ����Javaɳ�а�ȫģ�͵����ƣ���ҳ�е�Applet�ǲ�����������ļ�ϵͳ�ġ�
 * ������Ϊ��ʾ�࣬�鿴����Ч��Ҫ����FileAccessAppletDemo.html��
 * ����ҳ�����applet���ܴ򿪺͹رտͻ���ϵͳ�ϵ��ļ���
 * <p>
 * ������������ΪApplet�ڿ�������������ʱ�ǲ�����ɳ�а�ȫģ�����Ƶġ�
 * 
 * @author ����ΰ
 *
 * �������ڣ�2009-5-24
 */
public class FileAccessAppletDemo extends JApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4413424920226063584L;

	private JTextField filenameField = new JTextField(15); 
	private JTextField dirpathField = new JTextField(15);
	
	private JButton openButton = new JButton("Open");
	private JButton saveButton = new JButton("Save");

	public void init() {
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(getButtonPanel(), BorderLayout.NORTH);
		getContentPane().add(getFieldPanel(), BorderLayout.SOUTH);
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

	class OpenActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			int result = chooser.showOpenDialog(FileAccessAppletDemo.this);
			if (result == JFileChooser.APPROVE_OPTION) {
				filenameField.setText(chooser.getSelectedFile().getName());
				dirpathField.setText(chooser.getCurrentDirectory().toString());
			} 
			else if (result == JFileChooser.CANCEL_OPTION) {
				filenameField.setText("You pressed cancel");
				dirpathField.setText("");
			}
		}
	}

	class SaveActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			int result = chooser.showSaveDialog(FileAccessAppletDemo.this);
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