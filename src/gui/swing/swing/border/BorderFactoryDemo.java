package swing.border;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

import util.Displayer;

/**
 * BorderFactory��ʾ��
 * <p>
 * BorderFactory�ṩ�˴������ñ߿�ľ�̬������
 * <p>
 * ������£�</br>
 * ��б�桢͹б�桢ʴ�̡�ֱ�ߡ����⻬���ձ߿���ϱ߿�
 * <p>
 * ΪJComponent���ñ߿�ʱһ�������ַ�����</br>
 * <li>1��ʹ��BorderFactory�������߿����
 * <li>2��ֱ�� new �߿����
 * </br>�ܵ���˵��ʹ��BorderFactory�ܹ���ø������ı߿�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-6-25
 */
public class BorderFactoryDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("BorderFactory Demo",
				new BorderFactoryPanel());
	}
}

class BorderFactoryPanel extends JPanel {

	private static final long serialVersionUID = -4932355246858608900L;

	private static final int WIDTH = 600;
	private static final int HEIGHT = 200;

	private JPanel demoPanel;
	private JPanel buttonPanel;
	private ButtonGroup group;

	public BorderFactoryPanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		// ��б�棺����һ�����а���б���Ե�ı߿򣬽������ǰ����ɫ�Ľ�����ɫ������ͻ����ʾ���ϰ���ɫ��������Ӱ
		addRadioButton("Lowered bevel", BorderFactory.createLoweredBevelBorder());
		// ͹б�棺����һ������͹��б���Ե�ı߿򣬽������ǰ����ɫ�Ľ�����ɫ������ͻ����ʾ���ϰ���ɫ��������Ӱ
		addRadioButton("Raised bevel", BorderFactory.createRaisedBevelBorder());
		// ʴ�̣�����һ������"����"���Ч���ı߿򣬽�����ĵ�ǰ����ɫ����ͻ����ʾ����Ӱ��ʾ
		addRadioButton("Etched", BorderFactory.createEtchedBorder());
		// ֱ�ߣ�����һ������ָ����ɫ���߱߿�
		addRadioButton("Line", BorderFactory.createLineBorder(Color.BLUE));
		// ���⻬��ʹ�ô�ɫ����һ�����Ƴıߵı߿�(ĳ������Ĳ���Ϊ0ʱ�������Ǹ�����ı߿���ʾ)
		addRadioButton("Matte", BorderFactory.createMatteBorder(10, 1, 10, 0, Color.BLUE));
		// �ձ߿򣺴���һ����ռ�ÿռ�Ŀձ߿�
		addRadioButton("Empty", BorderFactory.createEmptyBorder());
		// ��ϱ߿򣺴���һ���ϳɱ߿�ָ���������ⲿ���ڲ��ı߿����
		addRadioButton("Compound", BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.BLUE), BorderFactory
						.createEtchedBorder()));
		
		Border etched = BorderFactory.createEtchedBorder();
		// �����б߿����һ�����⣬��ָ���˱����ı�
		Border titled = BorderFactory.createTitledBorder(etched, "Border types");
		getButtonPanel().setBorder(titled);

		setLayout(new GridLayout(2, 1));
		add(getButtonPanel());
		// ��ʹ��һ��panel��������Ϊ��ʹ�ñ߿��Ч����ֱ��
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout());
		panel.add(getDemoPanel());
		add(panel);
	}

	private void addRadioButton(String buttonName, final Border b) {
		JRadioButton button = new JRadioButton(buttonName);
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				getDemoPanel().setBorder(b);
				validate();
			}
		});
		getButtonGroup().add(button);
		getButtonPanel().add(button);
	}

	private JPanel getDemoPanel() {
		if (demoPanel == null) {
			demoPanel = new JPanel();
		}
		return demoPanel;
	}

	private JPanel getButtonPanel() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
		}
		return buttonPanel;
	}

	private ButtonGroup getButtonGroup() {
		if (group == null) {
			group = new ButtonGroup();
		}
		return group;
	}
}