/**
 * 
 */
package swing;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.Displayer;

/**
 * HTML����Button��ʾ��
 * <p>
 * �κ��ܽ����ı�����������Խ���HTML�ı������ܸ���HTML�Ĺ��������¸�ʽ���ı���
 * ���Կ��Ժ����׵���Swing����ϼ���Ư�����ı���
 *
 * @author ����ΰ
 *
 * �������ڣ�2009-5-20
 */
public class HTMLButtonDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("HTML Button Demo", new HTMLButtonPanel());
	}
}

class HTMLButtonPanel extends JPanel {

	private static final long serialVersionUID = -7125409559539255952L;

	private JButton button;

	public HTMLButtonPanel() {
		setPreferredSize(new Dimension(400,120));
		add(getHTMLButton());
	}

	private JButton getHTMLButton() {
		if (button == null) {
			// ����ʹ�ı���"<html>"��ǿ�ʼ��Ȼ��Ϳ���ʹ����ͨ��HTML����ˣ������ᱻǿ��Ҫ����ӽ������
			// JTabbedPane,JMenuItem,JToolTip,JRadioButton�Լ�JCheckBox�ж�����ʹ��HTML�ı�
			button = new JButton(
					"<html><b><font size=+2><center>Hi!<br><i>Press me now!");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					HTMLButtonPanel.this.add(new JLabel("<html>"
							+ "<i><font size=+4>Hello!"));
					// ��֤���������������������ʹ��validate()������ʹ�����ٴβ����������
					// �Ѿ��������������޸Ĵ��������������ʱ��(����������ӻ��Ƴ����
					// ���߸����벼����ص���Ϣ)Ӧ�õ�����������
					// �������������validate()������ǿ�ƶ�����������²��֣�����������ʾ���±�ǩ��
					validate();
//					updateUI();// Ҳ���Դﵽ��ͬ��Ŀ��
				}
			});
		}
		return button;
	}
}