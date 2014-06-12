/**
 * 
 */
package swing.dialog;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.Displayer;

/**
 * JDialog��ʾ��
 * <p>
 * �����Ի��򴰿ڵ���Ҫ�࣬����ʹ�ô��ഴ���Զ���ĶԻ���
 * <p>
 * AWT����ģʽ�Ի������ģʽ�Ի���</br>
 * һ��ģʽ�Ի������û����������Ĳ���֮ǰ���������û���Ӧ�ó�������Ĵ��ڽ��н�����
 * ģʽ�Ի��������ڳ����������֮ǰ����û��ṩ����Ϣ��
 * ��ģʽ�Ի��������û�ͬʱ�ڶԻ����Ӧ�ó�������Ĵ�����������Ϣ��
 * 
 * @author ����ΰ
 *
 * �������ڣ�2009-5-19
 */
public class JDialogDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JDialog Demo", new JDialogPanel());
	}
}

class JDialogPanel extends JPanel{
	
	private static final long serialVersionUID = -9164451690576771881L;
	
	private JButton button = new JButton("Show Dialog");
	private MyDialog dialog = new MyDialog(null);

	public JDialogPanel() {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(true);
			}
		});
		add(button);
	}
}

class MyDialog extends JDialog {

	private static final long serialVersionUID = 2378317178536582286L;
	
	public MyDialog(JFrame parent) {
		super(parent, "My dialog");
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(new JLabel("This is my dialog"));
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Closes the dialog
			}
		});
		cp.add(ok);
		// ָ����dialog�Ƿ�Ӧ����ģ̬�ģ���ģ̬��DialogΪ�ɼ�ʱ
		// �û����뵽Ӧ�ó������������ڵ����ݽ������(��ֻ�ɺ�Dialog���н���)
		// ����Dialog��Ϊ�������ߴ����Ĵ��ڳ���
		setModal(true);
		setSize(150, 125);
	}

	public void setVisible(boolean b) {
		Displayer.setCenter(this);
		super.setVisible(b);
	}
}