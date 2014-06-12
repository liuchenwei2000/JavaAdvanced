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
 * JTextField�ı�����ʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-6-24
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
			// Ĭ�ϳ���Ϊ20��
			textField = new JTextField("20", 20);
		}
		return textField;
	}

	private JButton getButton() {
		if (button == null) {
			button = new JButton("ȷ��");
			button.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					int columns = Integer.parseInt(getTextField().getText());
					// setColumns����������ʱ�����ı���ĳ���
					getTextField().setColumns(columns);
					// ��Ҫ���ð�������ı���������revalidate����
					// revalidate�����¼�����������������Ĵ�С�����Ҷ��������½��в���
					revalidate();
				}
			});
		}
		return button;
	}
}