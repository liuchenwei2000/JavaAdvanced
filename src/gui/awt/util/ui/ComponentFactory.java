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
 * �������������
 *
 * @author ����ΰ
 *
 * ����ʱ�䣺2008-9-11
 */
public class ComponentFactory {
	
	/** ����� ��� */
	public static final int WIDTH = 180;
	/** һ��ؼ� �߶� */
	public static final int HEIGHT = 21;
	
	/**
	 * ���ر�ǩ������Ĺ������(ˮƽ����)
	 * 
	 * @param label
	 *            ��ǩ
	 * @param com
	 *            ���
	 */
	public static JPanel createLabelComponentPanel(JLabel label, JComponent com) {
		JPanel panel = new JPanel();
		panel.add(label);
		panel.add(com);
		return panel;
	}

	/**
	 * ���������Ͱ�ť���(ˮƽ����)
	 * 
	 * @param textField
	 *            �����
	 * @param button
	 *            ��ť
	 */
	public static JPanel createTextFieldButtonPanel(JTextField textField, JButton button) {
		JPanel panel = new JPanel();
		panel.add(textField);
		panel.add(button);
		return panel;
	}
	
	/**
	 * ���ظ������ƹ����Ĺ��ñ�ǩ 
	 * (�������ҿ��롢14����ͨ����)
	 * 
	 * @param name
	 *            ��ǩ��
	 */
	public static JLabel createCommonLabel(String name) {
		JLabel label = new JLabel(name);
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("����", Font.PLAIN, 14));
		return label;
	}

	/**
	 * ���ع��������
	 */
	public static JTextField createCommonTextField() {
		JTextField textField = new JTextField();
		textField.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		textField.setBorder(BorderFactory.createLineBorder());
		return textField;
	}
	
	/**
	 * ���ع������������
	 */
	public static JPasswordField createCommonPasswordField() {
		JPasswordField pwField = new JPasswordField();
		pwField.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		pwField.setBorder(BorderFactory.createLineBorder());
		return pwField;
	}
	
	/**
	 * ���ع��ð�ť
	 */
	public static JButton createCommonButton(String name) {
		JButton button = new JButton(name);
		button.setFont(new Font("����", Font.PLAIN, 12));
		return button;
	}
}