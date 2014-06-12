/**
 * 
 */
package swing.text;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import util.Displayer;
import util.ui.BorderFactory;

/**
 * JTextPane演示类
 * <p>
 * JTextPane的目的是提供即时编辑文本的功能，还有诸如自动换行的内置功能。
 *
 * @author 刘晨伟
 *
 * 创建日期：2009-5-17
 */
public class JTextPaneDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JTextPane Demo", new JTextPanePanel());
	}
}

class JTextPanePanel extends JPanel {

	private static final long serialVersionUID = -3768557605463168935L;
	
	private JButton button = new JButton("打 印");
	private JTextPane textPane = new JTextPane();

	public JTextPanePanel() {
		setLayout(new BorderLayout());
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 获取编辑器文本的方法getText()
				System.out.println(textPane.getText());
			}
		});
		textPane.setPreferredSize(new Dimension(150, 150));
		JScrollPane scroll = new JScrollPane(textPane);
		scroll.setBorder(BorderFactory.createLineBorder());
		add(button, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
	}
}