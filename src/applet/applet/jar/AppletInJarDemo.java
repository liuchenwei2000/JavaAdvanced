/**
 * 
 */
package applet.jar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * Applet�����jar��ʾ��
 * 
 * @see applet.jar.applet�����jar.txt
 * 
 * @author ����ΰ
 *
 * �������ڣ�2009-5-23
 */
public class AppletInJarDemo extends JApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6947418109117870067L;

	public void init() {
		JButton button = new JButton("Press me");
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "OK,I am here!");
			}
		});
		getContentPane().add(button);
	}
}