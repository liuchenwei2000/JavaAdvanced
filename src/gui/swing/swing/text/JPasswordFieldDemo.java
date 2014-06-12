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
 * ��������ʾ��
 * <p>
 * ����������༭һ�������ı�������ͼָʾ�������ݣ�������ʾԭʼ�ַ� ��
 * ÿ��������ַ����û����ַ�(echo character)��ʾ��
 * JDK1.5ͨ�����Ǻ�"*"ȡ����JDK1.6ͨ�����Ǻ�"."ȡ����
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-6-25
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
			passwordLabel = ComponentFactory.createCommonLabel(" ��  �� ");
		}
		return passwordLabel;
	}

	private JLabel getEchoLabel() {
		if (echoLabel == null) {
			echoLabel = ComponentFactory.createCommonLabel("��������");
		}
		return echoLabel;
	}

	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = ComponentFactory.createCommonPasswordField();
			// Ϊ���������û����ַ���0��ʾ����ΪĬ�ϵĻ����ַ�
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
				    // �����������е��ı�
					// ���벢�������ַ���String�ͷ��صģ���Ϊ�ַ���һֱ������JVM�ϣ�ֱ����������
					char[] password = getPasswordField().getPassword();
					getTextField().setText(new String(password));
					// Ϊ�˰�ȫ�������ʹ��֮��Ӧ�ø�д���ص���������
					Arrays.fill(password, ' ');
				}
			});
		}
		return button;
	}
}