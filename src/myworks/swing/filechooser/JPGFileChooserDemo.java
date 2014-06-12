/**
 * 
 */
package swing.filechooser;

import javax.swing.JFileChooser;

/**
 * JPGFileChoose��ʾ��
 * 
 * @author ����ΰ
 *
 * �������ڣ�2009-1-21
 */
public class JPGFileChooserDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFileChooser chooser = new JFileChooser();
		// �����ļ���������JFileChooserʹ���ļ����������û�����ͼ�й����ļ�
		chooser.setFileFilter(new JPGFileFilter());
		// ����"ȫ���ļ�"��ѡ����
		chooser.setAcceptAllFileFilterUsed(false);
		// �ļ���ݷ�ʽ��ͼ
		chooser.setFileView(new JPGFileView());
		// ����accessory�����accessoryͨ��������ʾ��ѡ���ļ���Ԥ����ͼ
		chooser.setAccessory(new JPGPreviewer(chooser));
		chooser.showOpenDialog(null);
		System.exit(0);
	}
}