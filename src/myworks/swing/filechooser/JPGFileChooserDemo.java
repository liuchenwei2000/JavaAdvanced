/**
 * 
 */
package swing.filechooser;

import javax.swing.JFileChooser;

/**
 * JPGFileChoose演示类
 * 
 * @author 刘晨伟
 *
 * 创建日期：2009-1-21
 */
public class JPGFileChooserDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFileChooser chooser = new JFileChooser();
		// 设置文件过滤器，JFileChooser使用文件过滤器从用户的视图中过滤文件
		chooser.setFileFilter(new JPGFileFilter());
		// 禁用"全部文件"的选择项
		chooser.setAcceptAllFileFilterUsed(false);
		// 文件快捷方式视图
		chooser.setFileView(new JPGFileView());
		// 设置accessory组件，accessory通常用于显示已选中文件的预览视图
		chooser.setAccessory(new JPGPreviewer(chooser));
		chooser.showOpenDialog(null);
		System.exit(0);
	}
}