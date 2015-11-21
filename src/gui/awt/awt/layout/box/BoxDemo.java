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
 * Box类演示
 * <p>
 * Box类(类似JPanel的轻量级容器)使用BoxLayout作为默认布局管理器 。
 * <p>
 * Box类包含一些用于管理箱式布局的静态方法。
 * 在水平的箱子里，组件从左到右排列。
 * 在垂直的箱子里，组件从上到下排列。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-7-1
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
 * 默认情况下箱式布局中各组件之间是没有间距的
 * <p>
 * 如果需要添加间距，可以添加不可见的填充件(filler)。
 * 有三种填充件：支柱(strut)、固定区(rigid area)、胶水(glue)。
 *<p>
 * 支柱直接在组件间增加空间</br>
 * 通过添加支柱，可以使用固定的量将相邻的组件分开。
 * 可以将水平支柱添加到水平箱中，或者将垂直支柱添加到垂直箱中来增加组件间距空间。
 * <p>
 * 固定区填充件有点像一对支柱</br>
 * 它可以把邻接组件分离开，并且会设置另一个方向上的最小尺寸。
 * <p>
 * 胶水则可以尽可能大的间距将组件分开(叫弹簧可能更合适)。
 * 胶水将把组件互相拉开，直至充满整个空间。
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
		// 创建一个使用箱式布局的新容器(纵向)
		Box box = Box.createVerticalBox();
		// Box容器的使用方法和JPanel是一样的
		box.add(getNameBox());
		box.add(getPasswordBox());
		// 添加固定区域(不光Box可以添加，任何容器也都可以，比如JPanel)
		// 效果如同一个高度为30的支柱一样，并把box的最小宽度设为10
		box.add(Box.createRigidArea(new Dimension(10, 30)));
		box.add(getButtonBox());
		return box;
	}

	private Box getNameBox() {
		// 创建一个使用箱式布局的新容器(横向)
		Box box = Box.createHorizontalBox();
		box.setBorder(new LineBorder(Color.RED));
		box.add(getNameLabel());
		// 添加水平支柱(宽10 pix)
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
		// 添加胶水
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
