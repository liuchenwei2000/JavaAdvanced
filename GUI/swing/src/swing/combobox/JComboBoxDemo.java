/**
 * 
 */
package swing.combobox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.Displayer;

/**
 * JComboBox下拉框演示类
 * <p>
 * 下拉框与一组单选按钮的功能类似，也用来强制用户从一组可能的元素中只选择一个。
 * 这种方法更加紧凑，而且在不会使用户感到迷惑的前提下，改变下拉列表中的内容更容易。
 * 如果使下拉框处于可编辑状态，则下拉框将包括用户可在其中键入值的可编辑字段。 
 *
 * @author 刘晨伟
 *
 * 创建日期：2009-5-17
 */
public class JComboBoxDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JComboBox Demo", new JComboBoxPanel());
	}
}

class JComboBoxPanel extends JPanel {

	private static final long serialVersionUID = -3875292452188835566L;

	private static String[] values = { "one", "two", "three", "four", "five",
			"six", "seven", "eight", "nine", "ten" };
	
	private JTextField textField = new JTextField(15);
	private JComboBox comboBox = new JComboBox();
	private JButton button = new JButton("Add items");
	
	private int count = 0;

	public JComboBoxPanel() {
		initComboBox();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (count < values.length) {
					comboBox.addItem(values[count++]);
				}
			}
		});
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex();
				Object value = ((JComboBox) e.getSource()).getSelectedItem();
				textField.setText("index : " + index + " || value : " + value.toString());
			}
		});
		// 可编辑允许用户在文本框中键入内容或者从列表中选择项来初始化文本，执行此操作后
		// 文本就可编辑了，编辑仅影响文本显示，列表项保持原样
		comboBox.setEditable(true);
		textField.setEditable(false);
		add(textField);
		add(comboBox);
		add(button);
	}
	
	private void initComboBox(){
		for (int i = 0; i < 4; i++){
			comboBox.addItem(values[count++]);
		}
	}
}
