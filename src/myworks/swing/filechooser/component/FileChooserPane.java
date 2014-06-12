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
 * �ļ�ѡ��ؼ���
 * <p>
 * �ؼ�������һ��TextField��һ��Button��
 * �����Button��ʱ���ܹ�����һ��FileChooser�Ի���
 * ѡ�����ļ����ȷ�����ܹ����ļ����·����д��TextField���档
 * 
 * @author ����ΰ
 *
 * �������ڣ�2007-11-13
 */
public class FileChooserPane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -612311745368894675L;
	
	/** ����� ��� */
	private static final int WIDTH = 180;
	/** �ؼ� �߶� */
	private static final int HEIGHT = 21;

	/* ��ťĬ��ͼ�� */
	private ImageIcon icon = new ImageIcon(getClass().getResource("browse.GIF"));
	
	private JTextField textField;// �ļ�·�� ��
	private JButton button;// �� ��ť

	protected FileChooserPaneEHD ehd;// �¼��������

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
	 * �ļ�·�� ��
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
	 * ��ť
	 */
	private JButton getButton() {
		if (button == null) {
			button = new JButton(icon);
			button.setBounds(WIDTH - HEIGHT, 0, HEIGHT, HEIGHT);
		}
		return button;
	}

	/**
	 * �¼��������
	 */
	protected FileChooserPaneEHD getFileChooserPaneEHD() {
		if (ehd == null) {
			ehd = new FileChooserPaneEHD(this);
		}
		return ehd;
	}

	/**
	 * �ı��������(��ѡ���ļ���ȫ·��)
	 */
	public String getFilePath() {
		return getTextField().getText();
	}

	/**
	 * �ò��������ı�������(��ѡ���ļ���ȫ·��)
	 * 
	 * @param path
	 *            �ı�
	 */
	public void setFilePath(String path) {
		File file = new File(path);
		if (!file.exists())
			throw new RuntimeException("ָ���ļ�������.");
		getTextField().setText(path);
	}

	/**
	 * ��ť���ı��������
	 * 
	 * @see javax.swing.JComponent#setEnabled(boolean)
	 */
	public void setEnabled(boolean enabled) {
		getTextField().setEnabled(enabled);
		getTextField().setEditable(enabled);
		getButton().setEnabled(enabled);
	}
	
	/**
	 * �����ļ�ѡ������ѡ��ģʽ 
	 * <p>
	 * �����û�ֻѡ���ļ���ֻѡ��Ŀ¼�����߿�ѡ���ļ���Ŀ¼ ��
	 * Ĭ��ѡ��ģʽΪ�ļ��к��ļ�����ѡ��
	 * 
	 * @param mode
	 *            ѡ��ģʽ
	 * @see JFileChooser.FILES_ONLY 
	 *      JFileChooser.DIRECTORIES_ONLY
	 *      JFileChooser.FILES_AND_DIRECTORIES
	 */
	public void setFileSelectionMode(int mode) {
		getFileChooserPaneEHD().setFileSelectionMode(mode);
	}
	
	/**
	 * �����ļ�ѡ������filter
	 * 
	 * @param fileFilter
	 *            �ļ�������
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