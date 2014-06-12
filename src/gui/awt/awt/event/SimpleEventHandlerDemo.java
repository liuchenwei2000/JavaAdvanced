/**
 * 
 */
package awt.event;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.Displayer;

/**
 * �򵥵��¼�����
 * <p>
 * ��ȷ����GUI�¼���</br>
 * <li>1��ʵ��ActionListener�ӿ�</br>
 * public class MyClass implements ActionListener
 * <li>2��Ϊ��Ҫ�����¼���GUI���ע��һ���¼�������</br>
 * someComponent.addActionListener(instanceOfMyClass);
 * <li>3��ʵ��һ���¼�������</br>
 * public void actionPerformed(ActionEvent e) { 
 * ...//code that reacts to the action... 
 * } <p></br><p>
 * �¼���������������¼�Դ�������ض����͵��¼���Ȼ������¼���������Ӧ�������������¼�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2007-8-21
 */
public class SimpleEventHandlerDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("Simple Event Handler",
				new SimpleEventHandlerPanel());
	}
}

class SimpleEventHandlerPanel extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2439414288202032686L;
	
	private JLabel label;
	private int counter = 0;

	public SimpleEventHandlerPanel() {
		JButton button = new JButton("click");
		button.addActionListener(this);
		setLayout(new BorderLayout());
		add(button, BorderLayout.NORTH);
		label = new JLabel("0 clicked");
		add(label, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		counter++;
		label.setText(counter + " clicked");
	}
}