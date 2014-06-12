package swing;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import util.Displayer;

/**
 * 感观动态设置演示类
 * <p>
 * <li>1，先调用UIManager.setLookAndFeel()方法设置感观。
 * <li>2，再调用SwingUtilities.updateComponentTreeUI()方法刷新所有组件。
 * 
 * @author 刘晨伟
 *
 * 创建日期：2009-5-27
 */
public class LookAndFeelDynamicSettingDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("动态感观设置演示",
				new LookAndFeelDynamicSettingPanel());
	}
}

class LookAndFeelDynamicSettingPanel extends JPanel {

	private static final long serialVersionUID = 4788452073977812179L;

	public LookAndFeelDynamicSettingPanel() {
		addButtons();
		setPreferredSize(new Dimension(300, 200));
	}

	private void addButtons() {
		// 返回安装的所有感观类
		UIManager.LookAndFeelInfo[] infos = UIManager
				.getInstalledLookAndFeels();
		for (UIManager.LookAndFeelInfo info : infos) {
			this.add(makeButton(info.getName(), info.getClassName()));
		}
	}

	/**
	 * 根据感观类创建按钮
	 * <p>
	 * 按钮被点击之后会自动使用感观类设置当前的外观
	 * 
	 * @param name
	 *            按钮名称
	 * @param plafName
	 *            感观全类名
	 */
	private JButton makeButton(String name, final String plafName) {
		JButton button = new JButton(name);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					// 设置感观
					UIManager.setLookAndFeel(plafName);
					// 刷新全部组件集
					SwingUtilities
							.updateComponentTreeUI(LookAndFeelDynamicSettingPanel.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		return button;
	}
}