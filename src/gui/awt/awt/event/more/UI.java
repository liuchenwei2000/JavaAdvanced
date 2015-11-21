/**
 * 
 */
package awt.event.more;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import util.Displayer;

/**
 * UI类 
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2007-8-24
 */
public class UI extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5071640928802087396L;
	
	private JButton button;
	private UIEventHandler eventHandler;

	public UI() {
		add(getButton());
	}

	private JButton getButton() {
		if (button == null) {
			button = new JButton("测试");
			button.addActionListener(this);
		}
		return button;
	}

	private UIEventHandler getEventHandler() {
		if (eventHandler == null) {
			eventHandler = new UIEventHandler(this);
		}
		return eventHandler;
	}

	public void actionPerformed(ActionEvent e) {
		getEventHandler().doButtonAction(e);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("测试", new UI());
	}
}
