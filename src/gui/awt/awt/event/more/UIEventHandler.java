/**
 * 
 */
package awt.event.more;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

/**
 * UI的事件处理类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2007-8-24
 */
public class UIEventHandler {

	// 实例变量为关联的UI
	private UI ui;

	/**
	 * 构造函数以UI类对象实例进行通信
	 */
	public UIEventHandler(UI ui) {
		this.ui = ui;
	}

	public void doButtonAction(ActionEvent e) {
		JOptionPane.showMessageDialog(ui, "测试成功");
	}
}
