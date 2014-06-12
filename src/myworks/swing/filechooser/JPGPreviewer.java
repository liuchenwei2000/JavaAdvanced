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
 * JPG预览视图
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-1-21
 */
public class JPGPreviewer extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5299599456789847551L;

	/**
	 * JPG文件预览图
	 * 
	 * @param chooser
	 *            关联的文件选择器
	 */
	public JPGPreviewer(JFileChooser chooser) {
		super();
		setPreferredSize(new Dimension(100, 100));
		setBorder(new EtchedBorder());
		// 文件选择变更的时候会自动预览当前选择的JPG图片
		chooser.addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent e) {
				if (e.getPropertyName() == JFileChooser.SELECTED_FILE_CHANGED_PROPERTY) {
					File file = (File) e.getNewValue();
					if (file == null) {
						setIcon(null);
						return;
					}
					ImageIcon icon = new ImageIcon(file.getPath());
					// 如果图片太大，则进行压缩预览
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