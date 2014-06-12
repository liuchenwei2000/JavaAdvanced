/**
 * 
 */
package swing.button;

import java.awt.GridLayout;
import java.lang.reflect.Constructor;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

import util.Displayer;

/**
 * 按钮组演示
 * <p>
 * 要想让单选按钮表现出某种"排他"行为，必须把它们加入到一个"按钮组"中。
 * ButtonGroup用于为一组按钮创建一个多斥(multiple-exclusion)作用域，
 * 使用相同的ButtonGroup对象创建一组按钮意味着"开启"其中一个按钮时，将关闭组中的其他所有按钮 。
 * 可将ButtonGroup用于任何从AbstractButton继承的对象组。
 * 通常，按钮组包含JRadioButton、JRadioButtonMenuItem或JToggleButton的实例。
 * 但将JButton或JMenuItem的实例放入按钮组中并没有什么意义，因为JButton和JMenuItem不实现选择状态 。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-5-16
 */
public class ButtonGroupDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("ButtonGroup Demo", new ButtonGroupPanel());
	}
}

class ButtonGroupPanel extends JPanel {

	private static final long serialVersionUID = 1243483188884805647L;

	/** 按钮名称 */
	private static final String[] names = { "one", "two", "three", "four",
			"five", "six" };

	public ButtonGroupPanel() {
		setLayout(new GridLayout(4, 1));
		add(makeButtonPanel(JButton.class, names));
		add(makeButtonPanel(JToggleButton.class, names));
		add(makeButtonPanel(JCheckBox.class, names));
		add(makeButtonPanel(JRadioButton.class, names));
	}

	private static JPanel makeButtonPanel(Class<?> klass, String[] names) {
		ButtonGroup bg = new ButtonGroup();
		JPanel panel = new JPanel();
		String title = klass.getName();
		title = title.substring(title.lastIndexOf('.') + 1);
		panel.setBorder(new TitledBorder(title));
		// 添加按钮
		for (int i = 0; i < names.length; i++) {
			AbstractButton button = new JButton("failed");
			try {
				// 使用了反射机制
				Constructor<?> c = klass
						.getConstructor(new Class[] { String.class });
				button = (AbstractButton) c
						.newInstance(new Object[] { names[i] });
			} catch (Exception e) {
				e.printStackTrace();
			}
			bg.add(button);
			panel.add(button);
		}
		return panel;
	}
}