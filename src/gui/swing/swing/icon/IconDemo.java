/**
 * 
 */
package swing.icon;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import util.Displayer;

/**
 * Icon演示类
 * <p>
 * 可以在JLable或者任何从AbstractButton(包括JButton，JCheckBox，JRadioButton
 * 以及几种JMenuItem)继承的组件中使用Icon。
 * 许多Swing组件的构造器都接受Icon类型的参数，也可以使用setIcon()来加入或者改变图标。
 *
 * @author 刘晨伟
 *
 * 创建日期：2009-5-16
 */
public class IconDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("Icon Demo", new IconPanel());
	}
}

class IconPanel extends JPanel {

	private static final long serialVersionUID = 3450356191613769247L;

	private static Icon[] faces;
	private JButton button1 = new JButton("Disable");
	private JButton button2 = new JButton("Disable");
	private boolean mad = false;

	public IconPanel() {
		faces = new Icon[] {
				// 创建Icon的方式
				new ImageIcon(getClass().getResource("Face0.gif")),
				new ImageIcon(getClass().getResource("Face1.gif")),
				new ImageIcon(getClass().getResource("Face2.gif")),
				new ImageIcon(getClass().getResource("Face3.gif")),
				new ImageIcon(getClass().getResource("Face4.gif")), };
		// 带图标的按钮
		button1 = new JButton("JButton", faces[3]);
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mad) {
					button1.setIcon(faces[3]);
					mad = false;
				} else {
					button1.setIcon(faces[0]);
					mad = true;
				}
				// 设置图标和文本的垂直对齐方式
				button1.setVerticalAlignment(JButton.TOP);
				// 设置图标和文本的水平对齐方式
				button1.setHorizontalAlignment(JButton.LEFT);
			}
		});
		
		button1.setRolloverEnabled(true);
		// 设置按钮的翻转图标
		button1.setRolloverIcon(faces[1]);
		// 设置按钮的按下图标
		button1.setPressedIcon(faces[2]);
		// 设置按钮的禁用图标
		button1.setDisabledIcon(faces[4]);
		// 设置工具提示信息
		button1.setToolTipText("Yow!");
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (button1.isEnabled()) {
					button1.setEnabled(false);
					button2.setText("Enable");
				} else {
					button1.setEnabled(true);
					button2.setText("Disable");
				}
			}
		});
		
		setLayout(new FlowLayout());
		add(button1);
		add(button2);
	}
}
