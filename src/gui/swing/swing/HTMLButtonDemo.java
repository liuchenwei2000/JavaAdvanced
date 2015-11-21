/**
 * 
 */
package swing;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.Displayer;

/**
 * HTML绘制Button演示类
 * <p>
 * 任何能接受文本的组件都可以接受HTML文本，且能根据HTML的规则来重新格式化文本。
 * 所以可以很容易地在Swing组件上加入漂亮的文本。
 *
 * @author 刘晨伟
 *
 * 创建日期：2009-5-20
 */
public class HTMLButtonDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("HTML Button Demo", new HTMLButtonPanel());
	}
}

class HTMLButtonPanel extends JPanel {

	private static final long serialVersionUID = -7125409559539255952L;

	private JButton button;

	public HTMLButtonPanel() {
		setPreferredSize(new Dimension(400,120));
		add(getHTMLButton());
	}

	private JButton getHTMLButton() {
		if (button == null) {
			// 必须使文本以"<html>"标记开始，然后就可以使用普通的HTML标记了，但不会被强制要求添加结束标记
			// JTabbedPane,JMenuItem,JToolTip,JRadioButton以及JCheckBox中都可以使用HTML文本
			button = new JButton(
					"<html><b><font size=+2><center>Hi!<br><i>Press me now!");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					HTMLButtonPanel.this.add(new JLabel("<html>"
							+ "<i><font size=+4>Hello!"));
					// 验证此容器及其所有子组件，使用validate()方法会使容器再次布置其子组件
					// 已经布置容器后，在修改此容器的子组件的时候(在容器中添加或移除组件
					// 或者更改与布局相关的信息)应该调用上述方法
					// 必须调用容器的validate()方法来强制对组件进行重新布局，这样就能显示该新标签了
					validate();
//					updateUI();// 也可以达到相同的目的
				}
			});
		}
		return button;
	}
}
