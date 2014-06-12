/**
 * 
 */
package swing.radiobutton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import util.Displayer;

/**
 * JRadioButton单选按钮演示类
 * <p>
 * 单选按钮用来强制在多个选项中只能选择一个。
 * 要设置一组关联的单选按钮，需要把它们加入到一个ButtonGroup中。
 * 可以设置其中的一个按钮状态为选中(true)(在构造器的第二个参数中设置)。
 * 如果把多个单选按钮的状态都设置为选中，那么只有最后设置的那个有效。
 *
 * @author 刘晨伟
 *
 * 创建日期：2009-5-17
 */
public class JRadioButtonDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JRadioButton Demo", new JRadioButtonPanel());
	}
}

class JRadioButtonPanel extends JPanel {

	private static final long serialVersionUID = -8514890589052975609L;
	
	private JTextField textField = new JTextField(15);
	private ButtonGroup buttonGroup = new ButtonGroup();
	
	private JRadioButton radioButton1 = new JRadioButton("one", false);
	private JRadioButton radioButton2 = new JRadioButton("two", false);
	private JRadioButton radioButton3 = new JRadioButton("three", false);
	
	{
		radioButton1.addActionListener(getRadioButtonListener());
		radioButton2.addActionListener(getRadioButtonListener());
		radioButton3.addActionListener(getRadioButtonListener());
		buttonGroup.add(radioButton1);
		buttonGroup.add(radioButton2);
		buttonGroup.add(radioButton3);
	}

	public JRadioButtonPanel() {
		textField.setEditable(false);
		add(textField);
		add(radioButton1);
		add(radioButton2);
		add(radioButton3);
	}
	
	private ActionListener getRadioButtonListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JRadioButton radioButton = (JRadioButton) e.getSource();
				textField.setText("Radio button " + radioButton.getText()
						+ " is selected");
				// 返回JRadioButton是否被选中
				if(radioButton.isSelected()){
					// ...
				}
			}
		};
	}
}