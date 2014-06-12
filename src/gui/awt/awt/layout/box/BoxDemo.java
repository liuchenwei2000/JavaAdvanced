package awt.layout.box;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import util.Displayer;
import util.ui.ComponentFactory;

/**
 * Box����ʾ
 * <p>
 * Box��(����JPanel������������)ʹ��BoxLayout��ΪĬ�ϲ��ֹ����� ��
 * <p>
 * Box�����һЩ���ڹ�����ʽ���ֵľ�̬������
 * ��ˮƽ�������������������С�
 * �ڴ�ֱ�������������ϵ������С�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-7-1
 */
public class BoxDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("Box Demo", new BoxPanel());
	}
}

/**
 * Ĭ���������ʽ�����и����֮����û�м���
 * <p>
 * �����Ҫ��Ӽ�࣬������Ӳ��ɼ�������(filler)��
 * ������������֧��(strut)���̶���(rigid area)����ˮ(glue)��
 *<p>
 * ֧��ֱ������������ӿռ�</br>
 * ͨ�����֧��������ʹ�ù̶����������ڵ�����ֿ���
 * ���Խ�ˮƽ֧����ӵ�ˮƽ���У����߽���ֱ֧����ӵ���ֱ����������������ռ䡣
 * <p>
 * �̶��������е���һ��֧��</br>
 * �����԰��ڽ�������뿪�����һ�������һ�������ϵ���С�ߴ硣
 * <p>
 * ��ˮ����Ծ����ܴ�ļ�ཫ����ֿ�(�е��ɿ��ܸ�����)��
 * ��ˮ�����������������ֱ�����������ռ䡣
 */
class BoxPanel extends JPanel {

	private static final long serialVersionUID = 684859255060592867L;

	private JLabel nameLabel;
	private JLabel passwordLabel;
	private JTextField nameTextField;
	private JTextField passwordField;
	private JButton okButton;
	private JButton cancelButton;

	public BoxPanel() {
		super();
		initUI();
	}

	private void initUI() {
		this.setPreferredSize(new Dimension(200, 200));
		this.setLayout(new BorderLayout());
		this.add(getBox(), BorderLayout.CENTER);
	}

	private Box getBox() {
		// ����һ��ʹ����ʽ���ֵ�������(����)
		Box box = Box.createVerticalBox();
		// Box������ʹ�÷�����JPanel��һ����
		box.add(getNameBox());
		box.add(getPasswordBox());
		// ��ӹ̶�����(����Box������ӣ��κ�����Ҳ�����ԣ�����JPanel)
		// Ч����ͬһ���߶�Ϊ30��֧��һ��������box����С�����Ϊ10
		box.add(Box.createRigidArea(new Dimension(10, 30)));
		box.add(getButtonBox());
		return box;
	}

	private Box getNameBox() {
		// ����һ��ʹ����ʽ���ֵ�������(����)
		Box box = Box.createHorizontalBox();
		box.setBorder(new LineBorder(Color.RED));
		box.add(getNameLabel());
		// ���ˮƽ֧��(��10 pix)
		box.add(Box.createHorizontalStrut(10));
		box.add(getNameTextField());
		return box;
	}

	private Box getPasswordBox() {
		Box box = Box.createHorizontalBox();
		box.setBorder(new LineBorder(Color.BLUE));
		box.add(getPasswordLabel());
		box.add(Box.createHorizontalStrut(10));
		box.add(getPasswordField());
		return box;
	}

	private Box getButtonBox() {
		Box box = Box.createHorizontalBox();
		box.setBorder(new LineBorder(Color.GREEN));
		box.add(getOkButton());
		// ��ӽ�ˮ
		box.add(Box.createGlue());
		box.add(getCancelButton());
		return box;
	}

	private JLabel getNameLabel() {
		if (nameLabel == null) {
			nameLabel = ComponentFactory.createCommonLabel("Name");
			nameLabel.setBorder(new LineBorder(Color.BLACK));
		}
		return nameLabel;
	}

	private JLabel getPasswordLabel() {
		if (passwordLabel == null) {
			passwordLabel = ComponentFactory.createCommonLabel("Password");
			passwordLabel.setBorder(new LineBorder(Color.BLACK));
		}
		return passwordLabel;
	}

	private JTextField getNameTextField() {
		if (nameTextField == null) {
			nameTextField = ComponentFactory.createCommonTextField();
			nameTextField.setMaximumSize(nameTextField.getPreferredSize());
		}
		return nameTextField;
	}

	private JTextField getPasswordField() {
		if (passwordField == null) {
			passwordField = ComponentFactory.createCommonTextField();
			passwordField.setMaximumSize(passwordField.getPreferredSize());
		}
		return passwordField;
	}

	private JButton getOkButton() {
		if (okButton == null) {
			okButton = ComponentFactory.createCommonButton("OK");
		}
		return okButton;
	}

	private JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = ComponentFactory.createCommonButton("Cancel");
		}
		return cancelButton;
	}
}