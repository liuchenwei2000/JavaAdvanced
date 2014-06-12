/**
 * 
 */
package swing.text;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import util.Displayer;

/**
 * 文本区演示
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-6-25
 */
public class JTextAreaDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JTextArea Demo", new JTextAreaPanel());
	}
}

class JTextAreaPanel extends JPanel {

	private static final long serialVersionUID = 5957521281596399546L;

	private JTextArea textArea;
	private JButton insertButton;
	private JButton wrapButton;

	public JTextAreaPanel() {
		setLayout(new BorderLayout());

		JScrollPane scroll = new JScrollPane(getTextArea());
		add(scroll, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(getInsertButton());
		buttonPanel.add(getWrapButton());
		add(buttonPanel, BorderLayout.SOUTH);
	}

	private JTextArea getTextArea() {
		if (textArea == null) {
			// 创建一个指定行数和列数的文本区
			textArea = new JTextArea(8, 20);
		}
		return textArea;
	}

	private JButton getInsertButton() {
		if (insertButton == null) {
			insertButton = new JButton("insert");
			insertButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					// 将给定的文本附加到文本区中已有文本的底部
					getTextArea().append(
							"The quick brown fox jumps over the lazy dog.");
				}
			});
		}
		return insertButton;
	}

	private JButton getWrapButton() {
		if (wrapButton == null) {
			wrapButton = new JButton("wrap");
			wrapButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					boolean wrap = !getTextArea().getLineWrap();
					// 打开或者关闭自动换行功能
					// 换行只是视图发生了变化，而不是在文本中添加了"\n"
					getTextArea().setLineWrap(wrap);
					// 设置换行方式(如果文本区要换行)，此属性默认为false
					// 如果设置为true，则当行的长度大于所分配的宽度时，将在单词边界(空白)处换行
					// 如果设置为false，则将在字符边界处换行(不会考虑单词完整性)
					getTextArea().setWrapStyleWord(true);
					wrapButton.setText(wrap ? "no wrap" : "wrap");
				}
			});
		}
		return wrapButton;
	}
}