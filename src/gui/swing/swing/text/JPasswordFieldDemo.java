/**
 * 
 */
package swing.text;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import util.Displayer;
import util.ui.ComponentFactory;

/**
 * 密码域演示类
 * <p>
 * 密码域允许编辑一个单行文本，其视图指示键入内容，但不显示原始字符 。
 * 每个输入的字符都用回显字符(echo character)表示。
 * JDK1.5通常用星号"*"取代，JDK1.6通常用星号"."取代。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-6-25
 */
public class JPasswordFieldDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JPasswordField Demo",
				new JPasswordFieldPanel());
	}
}

class JPasswordFieldPanel extends JPanel {

	private static final long serialVersionUID = -8361566187953740540L;

	private JLabel passwordLabel;
	private JLabel echoLabel;

	private JPasswordField passwordField;
	private JTextField textField;

	private JButton button;

	public JPasswordFieldPanel() {
		setLayout(new BorderLayout());
		add(getButton(), BorderLayout.CENTER);
		add(ComponentFactory.createLabelComponentPanel(getPasswordLabel(),
				getPasswordField()), BorderLayout.NORTH);
		add(ComponentFactory.createLabelComponentPanel(getEchoLabel(),
				getTextField()), BorderLayout.SOUTH);
	}

	private JLabel getPasswordLabel() {
		if (passwordLabel == null) {
			passwordLabel = ComponentFactory.createCommonLabel(" 密  码 ");
		}
		return passwordLabel;
	}

	private JLabel getEchoLabel() {
		if (echoLabel == null) {
			echoLabel = ComponentFactory.createCommonLabel("回显密码");
		}
		return echoLabel;
	}

	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = ComponentFactory.createCommonPasswordField();
			// 为密码域设置回显字符，0表示重置为默认的回显字符
			passwordField.setEchoChar('#');
		}
		return passwordField;
	}

	private JTextField getTextField() {
		if (textField == null) {
			textField = ComponentFactory.createCommonTextField();
			textField.setEditable(false);
		}
		return textField;
	}

	private JButton getButton() {
		if (button == null) {
			button = ComponentFactory.createCommonButton("Echo");
			button.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    // 返回密码域中的文本
					// 密码并不是以字符串String型返回的，因为字符串一直保留在JVM上，直到垃圾回收
					char[] password = getPasswordField().getPassword();
					getTextField().setText(new String(password));
					// 为了安全起见，在使用之后应该覆写返回的数组内容
					Arrays.fill(password, ' ');
				}
			});
		}
		return button;
	}
}
