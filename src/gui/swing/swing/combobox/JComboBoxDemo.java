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
 * JComboBox��������ʾ��
 * <p>
 * ��������һ�鵥ѡ��ť�Ĺ������ƣ�Ҳ����ǿ���û���һ����ܵ�Ԫ����ֻѡ��һ����
 * ���ַ������ӽ��գ������ڲ���ʹ�û��е��Ի��ǰ���£��ı������б��е����ݸ����ס�
 * ���ʹ�������ڿɱ༭״̬���������򽫰����û��������м���ֵ�Ŀɱ༭�ֶΡ� 
 *
 * @author ����ΰ
 *
 * �������ڣ�2009-5-17
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
		// �ɱ༭�����û����ı����м������ݻ��ߴ��б���ѡ��������ʼ���ı���ִ�д˲�����
		// �ı��Ϳɱ༭�ˣ��༭��Ӱ���ı���ʾ���б����ԭ��
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