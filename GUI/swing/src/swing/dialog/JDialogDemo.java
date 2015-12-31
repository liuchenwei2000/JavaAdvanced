/**
 * 
 */
package swing.dialog;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.Displayer;

/**
 * JDialog演示类
 * <p>
 * 创建对话框窗口的主要类，可以使用此类创建自定义的对话框。
 * <p>
 * AWT区分模式对话框和无模式对话框</br>
 * 一个模式对话框在用户结束对它的操作之前，不允许用户与应用程序其余的窗口进行交互，
 * 模式对话框用于在程序继续运行之前获得用户提供的信息。
 * 无模式对话框允许用户同时在对话框和应用程序其余的窗口中输入信息。
 * 
 * @author 刘晨伟
 *
 * 创建日期：2009-5-19
 */
public class JDialogDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JDialog Demo", new JDialogPanel());
	}
}

class JDialogPanel extends JPanel{
	
	private static final long serialVersionUID = -9164451690576771881L;
	
	private JButton button = new JButton("Show Dialog");
	private MyDialog dialog = new MyDialog(null);

	public JDialogPanel() {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(true);
			}
		});
		add(button);
	}
}

class MyDialog extends JDialog {

	private static final long serialVersionUID = 2378317178536582286L;
	
	public MyDialog(JFrame parent) {
		super(parent, "My dialog");
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(new JLabel("This is my dialog"));
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Closes the dialog
			}
		});
		cp.add(ok);
		// 指定此dialog是否应该是模态的，当模态的Dialog为可见时
		// 用户输入到应用程序中其他窗口的内容将被阻断(即只可和Dialog进行交互)
		// 将此Dialog作为其所有者创建的窗口除外
		setModal(true);
		setSize(150, 125);
	}

	public void setVisible(boolean b) {
		Displayer.setCenter(this);
		super.setVisible(b);
	}
}
