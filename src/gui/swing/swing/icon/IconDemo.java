/**
 * 
 */
package swing.icon;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import util.Displayer;

/**
 * Icon��ʾ��
 * <p>
 * ������JLable�����κδ�AbstractButton(����JButton��JCheckBox��JRadioButton
 * �Լ�����JMenuItem)�̳е������ʹ��Icon��
 * ���Swing����Ĺ�����������Icon���͵Ĳ�����Ҳ����ʹ��setIcon()��������߸ı�ͼ�ꡣ
 *
 * @author ����ΰ
 *
 * �������ڣ�2009-5-16
 */
public class IconDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("Icon Demo", new IconPanel());
	}
}

class IconPanel extends JPanel {

	private static final long serialVersionUID = 3450356191613769247L;

	private static Icon[] faces;
	private JButton button1 = new JButton("Disable");
	private JButton button2 = new JButton("Disable");
	private boolean mad = false;

	public IconPanel() {
		faces = new Icon[] {
				// ����Icon�ķ�ʽ
				new ImageIcon(getClass().getResource("Face0.gif")),
				new ImageIcon(getClass().getResource("Face1.gif")),
				new ImageIcon(getClass().getResource("Face2.gif")),
				new ImageIcon(getClass().getResource("Face3.gif")),
				new ImageIcon(getClass().getResource("Face4.gif")), };
		// ��ͼ��İ�ť
		button1 = new JButton("JButton", faces[3]);
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mad) {
					button1.setIcon(faces[3]);
					mad = false;
				} else {
					button1.setIcon(faces[0]);
					mad = true;
				}
				// ����ͼ����ı��Ĵ�ֱ���뷽ʽ
				button1.setVerticalAlignment(JButton.TOP);
				// ����ͼ����ı���ˮƽ���뷽ʽ
				button1.setHorizontalAlignment(JButton.LEFT);
			}
		});
		
		button1.setRolloverEnabled(true);
		// ���ð�ť�ķ�תͼ��
		button1.setRolloverIcon(faces[1]);
		// ���ð�ť�İ���ͼ��
		button1.setPressedIcon(faces[2]);
		// ���ð�ť�Ľ���ͼ��
		button1.setDisabledIcon(faces[4]);
		// ���ù�����ʾ��Ϣ
		button1.setToolTipText("Yow!");
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (button1.isEnabled()) {
					button1.setEnabled(false);
					button2.setText("Enable");
				} else {
					button1.setEnabled(true);
					button2.setText("Disable");
				}
			}
		});
		
		setLayout(new FlowLayout());
		add(button1);
		add(button2);
	}
}