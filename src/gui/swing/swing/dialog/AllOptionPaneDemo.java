package swing.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import util.Displayer;

/**
 * JOptionPane的所有类型演示类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-7-2
 */
public class AllOptionPaneDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("AllOptionPane Demo",
				new AllOptionPanePanel());
	}
}

class AllOptionPanePanel extends JPanel {

	private static final long serialVersionUID = 4482199684432009280L;

	private RadioButtonPanel typePanel;
	private RadioButtonPanel messagePanel;
	private RadioButtonPanel messageTypePanel;
	private RadioButtonPanel optionTypePanel;
	private RadioButtonPanel optionsPanel;
	private RadioButtonPanel inputPanel;

	private String messageString = "Message";
	private Icon messageIcon = new ImageIcon(
			"images/gui.swing.toolbar/blue-ball.gif");
	private Object messageObject = new Date();
	private Component messageComponent = new SamplePanel();

	public AllOptionPanePanel() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(600, 400));
		initPanels();

		JPanel radiosPanel = new JPanel();
		radiosPanel.setLayout(new GridLayout(2, 3));
		radiosPanel.add(typePanel);
		radiosPanel.add(messageTypePanel);
		radiosPanel.add(messagePanel);
		radiosPanel.add(optionTypePanel);
		radiosPanel.add(optionsPanel);
		radiosPanel.add(inputPanel);

		JPanel buttonPanel = new JPanel();
		JButton showButton = new JButton("Show");
		showButton.addActionListener(new ShowAction());
		buttonPanel.add(showButton);

		this.add(radiosPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}

	private void initPanels() {
		typePanel = new RadioButtonPanel("Type", new String[] { "Message",
				"Confirm", "Option", "Input" });

		messageTypePanel = new RadioButtonPanel("Message Type", new String[] {
				"ERROR_MESSAGE", "INFORMATION_MESSAGE", "WARNING_MESSAGE",
				"QUESTION_MESSAGE", "PLAIN_MESSAGE" });

		messagePanel = new RadioButtonPanel("Message", new String[] { "String",
				"Icon", "Component", "Other", "Object[]" });

		optionTypePanel = new RadioButtonPanel("Confirm", new String[] {
				"DEFAULT_OPTION", "YES_NO_OPTION", "YES_NO_CANCEL_OPTION",
				"OK_CANCEL_OPTION" });

		optionsPanel = new RadioButtonPanel("Option", new String[] {
				"String[]", "Icon[]", "Object[]" });

		inputPanel = new RadioButtonPanel("Input", new String[] { "Text field",
				"Combo box" });
	}

	private Object getMessage() {
		String s = messagePanel.getSelection();
		if (s.equals("String")) {
			return messageString;
		} else if (s.equals("Icon")) {
			return messageIcon;
		} else if (s.equals("Component")) {
			return messageComponent;
		} else if (s.equals("Object[]")) {
			return new Object[] { messageString, messageIcon, messageComponent,
					messageObject };
		} else if (s.equals("Other")) {
			return messageObject;
		}
		return null;
	}

	private Object[] getOptions() {
		String s = optionsPanel.getSelection();
		if (s.equals("String[]")) {
			return new String[] { "Yellow", "Blue", "Red" };
		} else if (s.equals("Icon[]")) {
			return new Icon[] {
					new ImageIcon("images/gui.swing.toolbar/yellow-ball.gif"),
					new ImageIcon("images/gui.swing.toolbar/blue-ball.gif"),
					new ImageIcon("images/gui.swing.toolbar/red-ball.gif") };
		} else if (s.equals("Object[]")) {
			return new Object[] { messageString, messageIcon, messageComponent,
					messageObject };
		}
		return null;
	}

	private int getType(RadioButtonPanel panel) {
		String s = panel.getSelection();
		try {
			return JOptionPane.class.getField(s).getInt(null);
		} catch (Exception e) {
			return -1;
		}
	}

	private class ShowAction implements ActionListener {

		public void actionPerformed(ActionEvent evt) {
			if (typePanel.getSelection().equals("Confirm")) {
				JOptionPane.showConfirmDialog(AllOptionPanePanel.this,
						getMessage(), "Title", getType(optionTypePanel),
						getType(messageTypePanel));
			} else if (typePanel.getSelection().equals("Input")) {
				if (inputPanel.getSelection().equals("Text field")) {
					JOptionPane.showInputDialog(AllOptionPanePanel.this,
							getMessage(), "Title", getType(messageTypePanel));
				} else {
					JOptionPane.showInputDialog(AllOptionPanePanel.this,
							getMessage(), "Title", getType(messageTypePanel),
							null, new String[] { "Yellow", "Blue", "Red" },
							"Blue");
				}
			} else if (typePanel.getSelection().equals("Message")) {
				JOptionPane.showMessageDialog(AllOptionPanePanel.this,
						getMessage(), "Title", getType(messageTypePanel));
			} else if (typePanel.getSelection().equals("Option")) {
				JOptionPane.showOptionDialog(AllOptionPanePanel.this,
						getMessage(), "Title", getType(optionTypePanel),
						getType(messageTypePanel), null, getOptions(),
						getOptions()[0]);
			}
		}
	}
}

class RadioButtonPanel extends JPanel {

	private static final long serialVersionUID = -3799963184217730580L;

	private ButtonGroup group = new ButtonGroup();

	public RadioButtonPanel(String title, String[] options) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createTitledBorder(title));

		for (int i = 0; i < options.length; i++) {
			JRadioButton b = new JRadioButton(options[i]);
			b.setActionCommand(options[i]);
			add(b);
			group.add(b);
			b.setSelected(i == 0);
		}
	}

	public String getSelection() {
		return group.getSelection().getActionCommand();
	}
}

class SamplePanel extends JPanel {

	private static final long serialVersionUID = 6960008145368770665L;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D rect = new Rectangle2D.Double(0, 0, getWidth() - 1,
				getHeight() - 1);
		g2.setPaint(Color.YELLOW);
		g2.fill(rect);
		g2.setPaint(Color.BLUE);
		g2.draw(rect);
	}

	public Dimension getMinimumSize() {
		return new Dimension(10, 10);
	}
}