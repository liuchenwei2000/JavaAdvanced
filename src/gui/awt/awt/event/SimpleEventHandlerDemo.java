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
 * 简单的事件处理
 * <p>
 * 正确处理GUI事件：</br>
 * <li>1，实现ActionListener接口</br>
 * public class MyClass implements ActionListener
 * <li>2，为将要产生事件的GUI组件注册一个事件监听器</br>
 * someComponent.addActionListener(instanceOfMyClass);
 * <li>3，实现一个事件处理方法</br>
 * public void actionPerformed(ActionEvent e) { 
 * ...//code that reacts to the action... 
 * } <p></br><p>
 * 事件监听器负责监听事件源产生的特定类型的事件，然后调用事件处理方法响应它所监听到的事件
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2007-8-21
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
