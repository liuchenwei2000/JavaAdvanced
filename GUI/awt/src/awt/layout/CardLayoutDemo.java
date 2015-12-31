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
 * CardLayout将容器中的每个组件看作一张卡片
 * <p>
 * 一次只能看到一张卡片，而容器充当卡片的堆栈。
 * 当容器第一次显示时，第一个添加到CardLayout对象的组件为可见组件 。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2007-8-21
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
	
	private JPanel cardsPanel;// CardLayout布局的容器
	
	public CardLayoutPanel() {
		JPanel comboBoxPanel = new JPanel();
		JComboBox comboBox = new JComboBox(new String[] { BUTTON_PANEL,
				TEXT_PANEL });
		comboBox.addItemListener(this);
		comboBoxPanel.add(comboBox);
		// cardsPanel中的两个组件
		JPanel buttonCard = new JPanel();
		buttonCard.add(new JButton("Button 1"));
		buttonCard.add(new JButton("Button 2"));
		buttonCard.add(new JButton("Button 3"));

		JPanel textCard = new JPanel();
		textCard.add(new JTextField("TextField", 20));

		// 把组件加入到cardsPanel中
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
		 * 翻转到已添加到此布局的具有指定name的组件
		 */
		cardLayout.show(cardsPanel, (String) e.getItem());
	}
}
