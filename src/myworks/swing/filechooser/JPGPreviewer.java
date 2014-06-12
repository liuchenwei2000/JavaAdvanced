/**
 * 
 */
package swing.filechooser;

import java.awt.Dimension;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

/**
 * JPGԤ����ͼ
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-1-21
 */
public class JPGPreviewer extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5299599456789847551L;

	/**
	 * JPG�ļ�Ԥ��ͼ
	 * 
	 * @param chooser
	 *            �������ļ�ѡ����
	 */
	public JPGPreviewer(JFileChooser chooser) {
		super();
		setPreferredSize(new Dimension(100, 100));
		setBorder(new EtchedBorder());
		// �ļ�ѡ������ʱ����Զ�Ԥ����ǰѡ���JPGͼƬ
		chooser.addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent e) {
				if (e.getPropertyName() == JFileChooser.SELECTED_FILE_CHANGED_PROPERTY) {
					File file = (File) e.getNewValue();
					if (file == null) {
						setIcon(null);
						return;
					}
					ImageIcon icon = new ImageIcon(file.getPath());
					// ���ͼƬ̫�������ѹ��Ԥ��
					if (icon.getIconWidth() > getWidth()) {
						icon = new ImageIcon(icon.getImage().getScaledInstance(
								getWidth(), -1, Image.SCALE_DEFAULT));
					}
					setIcon(icon);
				}
			}
		});
	}
}