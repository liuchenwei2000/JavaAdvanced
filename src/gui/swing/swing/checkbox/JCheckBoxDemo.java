/**
 * 
 */
package swing.checkbox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import util.Displayer;

/**
 * JCheckBox复选框演示类
 * <p>
 * 复选框是一个可以被选定和取消选定的项，它将其状态显示给用户。
 * 它提供一种用以进行开/关选择的方式，它包含了一个小方框和一个标签(即复选框的文本)。
 * 这个方框中通常是有一个"x"标记(或者其它能表明被选中的标记)或者为空，这取决于检查框是否被选中。
 * 通常会使用接受标签作为参数的构造器来创建JCheckBox，可以获取和设置状态，也可以获取和设置其标签，
 * 甚至可以在JCheckBox对象已经建立之后改变标签。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-5-17
 */
public class JCheckBoxDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JCheckBox Demo", new JCheckBoxPanel());
	}
}

class JCheckBoxPanel extends JPanel {

	private static final long serialVersionUID = -5349217288538114962L;

	private JTextArea textArea = new JTextArea(6, 20);
	// 使用指定文本创建复选框
	private JCheckBox checkBox1 = new JCheckBox("CheckBox 1");
	private JCheckBox checkBox2 = new JCheckBox("CheckBox 2");
	private JCheckBox checkBox3 = new JCheckBox("CheckBox 3");

	{
		textArea.setEditable(false);
		checkBox1.addActionListener(getCheckBoxListener());
		checkBox2.addActionListener(getCheckBoxListener());
		checkBox3.addActionListener(getCheckBoxListener());
	}

	public JCheckBoxPanel() {
		add(new JScrollPane(textArea));
		add(checkBox1);
		add(checkBox2);
		add(checkBox3);
	}

	private void printCheckBox(JCheckBox checkBox) {
		// 复选框是否被选中
		if (checkBox.isSelected()) {
			// 将给定文本追加到文档结尾(会同步在界面显示出来)
			textArea.append(checkBox.getText() + " is selected.\n");
		} else {
			textArea.append(checkBox.getText() + " clears selection.\n");
		}
	}

	private ActionListener getCheckBoxListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printCheckBox((JCheckBox) e.getSource());
			}
		};
	}
}