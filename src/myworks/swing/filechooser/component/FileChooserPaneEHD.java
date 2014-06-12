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
 * �ļ��򿪿ؼ��¼�������
 * 
 * @author ����ΰ
 *
 * �������ڣ�2007-11-13
 */
public class FileChooserPaneEHD implements ActionListener {

	private FileChooserPane chooserPane;// �������ļ�ѡ��ؼ�

	private JFileChooser chooser;// �ļ�ѡ��������

	/**
	 * @param chooserPane
	 *            �������ļ�ѡ��ؼ�
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
	 * �����ļ�ѡ������ѡ��ģʽ 
	 * <p>
	 * �����û�ֻѡ���ļ���ֻѡ��Ŀ¼�����߿�ѡ���ļ���Ŀ¼ ��
	 * Ĭ��ѡ��ģʽΪ�ļ��к��ļ�����ѡ��
	 * 
	 * @param mode
	 *            ѡ��ģʽ
	 */
	protected void setFileSelectionMode(int mode) {
		getChooser().setFileSelectionMode(mode);
	}
	
	/**
	 * �����ļ�ѡ������filter
	 * 
	 * @param fileFilter
	 *            �ļ�������
	 */
	protected void setFileFilter(FileFilter fileFilter) {
		getChooser().setFileFilter(fileFilter);
	}
}