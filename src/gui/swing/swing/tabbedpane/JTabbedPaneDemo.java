/**
 * 
 */
package swing.tabbedpane;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import util.Displayer;

/**
 * JTabbedPane演示类
 * <p>
 * 允许用户通过单击具有给定标题和(或)图标的选项卡，在一组组件之间进行切换。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-5-18
 */
public class JTabbedPaneDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JTabbedPane Demo", new JTabbedPanePanel());
	}
}

class JTabbedPanePanel extends JPanel {

	private static final long serialVersionUID = 7603299123532523822L;

	private static final String[] NAMES = { "One", "Two", "Three", "Four",
			"Five", "Six", "Seven" };

	private JTabbedPane tabs = new JTabbedPane();
	private JTextField txt = new JTextField(20);

	public JTabbedPanePanel() {
		setLayout(new BorderLayout());
		for (int i = 0; i < NAMES.length; i++) {
			// 添加一个由title表示，且没有图标的组件
			tabs.addTab(NAMES[i], makeTabPanel(" in TabbedPane " + i));
		}
		// 将一个ChangeListener添加到此选项卡窗格中
		tabs.addChangeListener(new ChangeListener() {
			// 选项卡选择改变时的事件处理
			public void stateChanged(ChangeEvent e) {
				txt.setText("Tab selected: " + tabs.getSelectedIndex());
			}
		});
		txt.setEditable(false);
		add(txt, BorderLayout.SOUTH);
		add(tabs, BorderLayout.CENTER);
	}

	/**
	 * 根据制定名称创建一个选项卡页签
	 */
	private static JPanel makeTabPanel(String name) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JLabel label = new JLabel("JLabel " + name, JLabel.CENTER);
		label.setBorder(new LineBorder(Color.BLACK));
		panel.add(label);
		return panel;
	}
}