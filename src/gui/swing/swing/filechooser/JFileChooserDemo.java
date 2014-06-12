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
 * JFileChooser��ʾ��
 * <p>
 * ͼ�β���ϵͳһ�㶼֧�ִ򿪺ͱ����ļ�������Java�ṩ��JFileChooser��
 * ����װ����Щ������ʹ�ļ�������ø��ӷ��㡣
 *
 * @author ����ΰ
 *
 * �������ڣ�2009-5-19
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
	 * �򿪶���������
	 */
	class OpenActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			// ����ѡ����Ĭ�ϵĵ�ǰĿ¼
			chooser.setCurrentDirectory(new File("."));
			int result = chooser.showOpenDialog(JFileChooserPanel.this);
			//  ѡ��ȷ��(yes��ok)�󷵻ظ�ֵ
			if (result == JFileChooser.APPROVE_OPTION) {
				// ����ѡ�е��ļ������ɳ���Աͨ��setSelectedFile()����ͨ���û�����
				// ����UI�м����ļ��������ߴ�UI�е��б���ѡ���ļ������д�����
				filenameField.setText(chooser.getSelectedFile().getName());
				// ���ص�ǰĿ¼
				dirpathField.setText(chooser.getCurrentDirectory().toString());
			} 
			// ѡ��ȡ���󷵻ظ�ֵ
			else if (result == JFileChooser.CANCEL_OPTION) {
				filenameField.setText("You pressed cancel");
				dirpathField.setText("");
			}
		}
	}

	/**
	 * ���涯��������
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