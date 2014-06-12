/**
 * 
 */
package awt.layout;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.Displayer;

/**
 * CardLayout�������е�ÿ���������һ�ſ�Ƭ
 * <p>
 * һ��ֻ�ܿ���һ�ſ�Ƭ���������䵱��Ƭ�Ķ�ջ��
 * ��������һ����ʾʱ����һ����ӵ�CardLayout��������Ϊ�ɼ���� ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2007-8-21
 */
public class CardLayoutDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("CardLayout Demo", new CardLayoutPanel());
	}
}

class CardLayoutPanel extends JPanel implements ItemListener {
	
	private static final long serialVersionUID = -1977847145809954797L;
	
	private static final String BUTTON_PANEL = "JPanel with JButtons";
	private static final String TEXT_PANEL = "JPanel with JTextField";
	
	private JPanel cardsPanel;// CardLayout���ֵ�����
	
	public CardLayoutPanel() {
		JPanel comboBoxPanel = new JPanel();
		JComboBox comboBox = new JComboBox(new String[] { BUTTON_PANEL,
				TEXT_PANEL });
		comboBox.addItemListener(this);
		comboBoxPanel.add(comboBox);
		// cardsPanel�е��������
		JPanel buttonCard = new JPanel();
		buttonCard.add(new JButton("Button 1"));
		buttonCard.add(new JButton("Button 2"));
		buttonCard.add(new JButton("Button 3"));

		JPanel textCard = new JPanel();
		textCard.add(new JTextField("TextField", 20));

		// ��������뵽cardsPanel��
		cardsPanel = new JPanel(new CardLayout());
		cardsPanel.add(buttonCard, BUTTON_PANEL);
		cardsPanel.add(textCard, TEXT_PANEL);

		this.setLayout(new BorderLayout());
		this.add(comboBoxPanel, BorderLayout.NORTH);
		this.add(cardsPanel, BorderLayout.CENTER);
	}

	public void itemStateChanged(ItemEvent e) {
		CardLayout cardLayout = (CardLayout) (cardsPanel.getLayout());
		/*
		 * ��ת������ӵ��˲��ֵľ���ָ��name�����
		 */
		cardLayout.show(cardsPanel, (String) e.getItem());
	}
}