/**
 * 
 */
package swing.dialog;

import javax.swing.*;

import util.Displayer;

import java.awt.event.*;
import java.awt.*;

/**
 * JOptionPane演示类
 * <p>
 * JOptionPane有助于方便地弹出要求用户提供值或向其发出通知的标准对话框。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-5-18
 */
public class JOptionPaneDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JOptionPane Demo", new JOptionPanePanel());
	}
}

class JOptionPanePanel extends JPanel {

	private static final long serialVersionUID = 4522521919750930283L;

	private JButton[] buttons = { new JButton("Alert"), new JButton("Yes/No"),
			new JButton("Color"), new JButton("Input"), new JButton("3 Values") };

	private JTextField text = new JTextField(15);

	private ActionListener listener;

	public JOptionPanePanel() {
		setLayout(new FlowLayout());
		for (JButton button : buttons) {
			button.addActionListener(getListener());
			add(button);
		}
		add(text);
	}

	private ActionListener getListener() {
		if (listener == null) {
			listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String id = ((JButton) e.getSource()).getText();
					if (id.equals("Alert")) {
						// 显示一个错误对话框，该对话框显示的 message 为 'alert'
						JOptionPane.showMessageDialog(null,
								"There's a bug on you!", "Hey!",
								JOptionPane.ERROR_MESSAGE);
					} else if (id.equals("Yes/No")) {
						// 显示一个信息面板，其options为 "yes/no"，返回指示用户所选选项的int值
						JOptionPane.showConfirmDialog(null, "or no",
								"choose yes", JOptionPane.YES_NO_OPTION);
					} else if (id.equals("Color")) {
						Object[] options = { "Red", "Green" };
						// 显示一个警告对话框，其options为 Red、Green
						int selection = JOptionPane.showOptionDialog(null,
								"Choose a Color!", "Warning",
								JOptionPane.DEFAULT_OPTION,
								JOptionPane.WARNING_MESSAGE, null, options,
								options[0]);
						if (selection != JOptionPane.CLOSED_OPTION)
							text.setText("Color Selected: "
											+ options[selection]);
					} else if (id.equals("Input")) {
						// 显示一个要求用户键入String的对话框
						String value = JOptionPane
								.showInputDialog("What did you say?");
						text.setText(value);
					} else if (id.equals("3 Values")) {
						Object[] selections = { "Red", "Green", "Blue" };
						// 显示一个要求用户选择String的对话框
						Object value = JOptionPane.showInputDialog(null,
								"Choose one", "Input",
								JOptionPane.INFORMATION_MESSAGE, null,
								selections, selections[0]);
						if (value != null)
							text.setText(value.toString());
					}
				}
			};
		}
		return listener;
	}
}