/**
 * 
 */
package swing.dialog;

import javax.swing.*;

import util.Displayer;

import java.awt.event.*;
import java.awt.*;

/**
 * JOptionPane��ʾ��
 * <p>
 * JOptionPane�����ڷ���ص���Ҫ���û��ṩֵ�����䷢��֪ͨ�ı�׼�Ի���
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-5-18
 */
public class JOptionPaneDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JOptionPane Demo", new JOptionPanePanel());
	}
}

class JOptionPanePanel extends JPanel {

	private static final long serialVersionUID = 4522521919750930283L;

	private JButton[] buttons = { new JButton("Alert"), new JButton("Yes/No"),
			new JButton("Color"), new JButton("Input"), new JButton("3 Values") };

	private JTextField text = new JTextField(15);

	private ActionListener listener;

	public JOptionPanePanel() {
		setLayout(new FlowLayout());
		for (JButton button : buttons) {
			button.addActionListener(getListener());
			add(button);
		}
		add(text);
	}

	private ActionListener getListener() {
		if (listener == null) {
			listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String id = ((JButton) e.getSource()).getText();
					if (id.equals("Alert")) {
						// ��ʾһ������Ի��򣬸öԻ�����ʾ�� message Ϊ 'alert'
						JOptionPane.showMessageDialog(null,
								"There's a bug on you!", "Hey!",
								JOptionPane.ERROR_MESSAGE);
					} else if (id.equals("Yes/No")) {
						// ��ʾһ����Ϣ��壬��optionsΪ "yes/no"������ָʾ�û���ѡѡ���intֵ
						JOptionPane.showConfirmDialog(null, "or no",
								"choose yes", JOptionPane.YES_NO_OPTION);
					} else if (id.equals("Color")) {
						Object[] options = { "Red", "Green" };
						// ��ʾһ������Ի�����optionsΪ Red��Green
						int selection = JOptionPane.showOptionDialog(null,
								"Choose a Color!", "Warning",
								JOptionPane.DEFAULT_OPTION,
								JOptionPane.WARNING_MESSAGE, null, options,
								options[0]);
						if (selection != JOptionPane.CLOSED_OPTION)
							text.setText("Color Selected: "
											+ options[selection]);
					} else if (id.equals("Input")) {
						// ��ʾһ��Ҫ���û�����String�ĶԻ���
						String value = JOptionPane
								.showInputDialog("What did you say?");
						text.setText(value);
					} else if (id.equals("3 Values")) {
						Object[] selections = { "Red", "Green", "Blue" };
						// ��ʾһ��Ҫ���û�ѡ��String�ĶԻ���
						Object value = JOptionPane.showInputDialog(null,
								"Choose one", "Input",
								JOptionPane.INFORMATION_MESSAGE, null,
								selections, selections[0]);
						if (value != null)
							text.setText(value.toString());
					}
				}
			};
		}
		return listener;
	}
}