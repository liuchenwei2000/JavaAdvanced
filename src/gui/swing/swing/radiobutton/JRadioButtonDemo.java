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
 * JRadioButton��ѡ��ť��ʾ��
 * <p>
 * ��ѡ��ť����ǿ���ڶ��ѡ����ֻ��ѡ��һ����
 * Ҫ����һ������ĵ�ѡ��ť����Ҫ�����Ǽ��뵽һ��ButtonGroup�С�
 * �����������е�һ����ť״̬Ϊѡ��(true)(�ڹ������ĵڶ�������������)��
 * ����Ѷ����ѡ��ť��״̬������Ϊѡ�У���ôֻ��������õ��Ǹ���Ч��
 *
 * @author ����ΰ
 *
 * �������ڣ�2009-5-17
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
				// ����JRadioButton�Ƿ�ѡ��
				if(radioButton.isSelected()){
					// ...
				}
			}
		};
	}
}