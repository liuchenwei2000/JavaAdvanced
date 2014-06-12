/**
 * 
 */
package swing.text;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.Displayer;

/**
 * JTextField文本域演示类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-6-24
 */
public class JTextFieldDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JTextField Demo", new JTextFieldPanel());
	}
}

class JTextFieldPanel extends JPanel {

	private static final long serialVersionUID = -1005142544635098024L;

	private JTextField textField;
	private JButton button;

	public JTextFieldPanel() {
		add(getTextField());
		add(getButton());
	}

	private JTextField getTextField() {
		if (textField == null) {
			// 默认长度为20列
			textField = new JTextField("20", 20);
		}
		return textField;
	}

	private JButton getButton() {
		if (button == null) {
			button = new JButton("确定");
			button.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					int columns = Integer.parseInt(getTextField().getText());
					// setColumns可以在运行时设置文本域的长度
					getTextField().setColumns(columns);
					// 需要调用包含这个文本域容器的revalidate方法
					// revalidate会重新计算容器内所有组件的大小，并且对它们重新进行布局
					revalidate();
				}
			});
		}
		return button;
	}
}