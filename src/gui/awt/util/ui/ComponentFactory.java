/**
 * 
 */
package util.ui;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * 公用组件工具类
 *
 * @author 刘晨伟
 *
 * 创建时间：2008-9-11
 */
public class ComponentFactory {
	
	/** 输入框 宽度 */
	public static final int WIDTH = 180;
	/** 一般控件 高度 */
	public static final int HEIGHT = 21;
	
	/**
	 * 返回标签和组件的公用面板(水平布局)
	 * 
	 * @param label
	 *            标签
	 * @param com
	 *            组件
	 */
	public static JPanel createLabelComponentPanel(JLabel label, JComponent com) {
		JPanel panel = new JPanel();
		panel.add(label);
		panel.add(com);
		return panel;
	}

	/**
	 * 返回输入框和按钮面板(水平布局)
	 * 
	 * @param textField
	 *            输入框
	 * @param button
	 *            按钮
	 */
	public static JPanel createTextFieldButtonPanel(JTextField textField, JButton button) {
		JPanel panel = new JPanel();
		panel.add(textField);
		panel.add(button);
		return panel;
	}
	
	/**
	 * 返回根据名称构建的公用标签 
	 * (内容向右靠齐、14号普通宋体)
	 * 
	 * @param name
	 *            标签名
	 */
	public static JLabel createCommonLabel(String name) {
		JLabel label = new JLabel(name);
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		return label;
	}

	/**
	 * 返回公用输入框
	 */
	public static JTextField createCommonTextField() {
		JTextField textField = new JTextField();
		textField.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		textField.setBorder(BorderFactory.createLineBorder());
		return textField;
	}
	
	/**
	 * 返回公用密码输入框
	 */
	public static JPasswordField createCommonPasswordField() {
		JPasswordField pwField = new JPasswordField();
		pwField.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		pwField.setBorder(BorderFactory.createLineBorder());
		return pwField;
	}
	
	/**
	 * 返回公用按钮
	 */
	public static JButton createCommonButton(String name) {
		JButton button = new JButton(name);
		button.setFont(new Font("宋体", Font.PLAIN, 12));
		return button;
	}
}