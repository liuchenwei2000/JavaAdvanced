/**
 * 
 */
package swing;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import util.Displayer;

/**
 * 选择外观(Look and Feel)演示类
 * <p>
 * "可插拔外观"(plaf：Pluggable Look and Feel)使程序能够模仿不同的操作系统的外观，
 * 甚至可以得到各种奇妙的功能，比如在程序运行期间动态改变程序的外观。</br>
 * 不过，通常只会在以下二者中选择一个：</br>
 * 要么选择"跨平台"的外观(即Swing的"金属"外观)，要么选择程序当前所在系统的外观。</br>
 * 这样Java程序看起来就好像是为该系统专门设计的(这的确是最好的选择，而且可以避免误导用户)。
 * 实现这两种行为的代码都很简单，不过要确保在创建任何可视组件之前先调用这些代码。
 * 这是因为组件是根据当前的外观而创建的，而且创建之后就不会改变。
 * 如果要使用跨平台的"金属"外观(它是Swing程序的特征)，什么都不用做，因为它是缺省外观。</br>
 * 不过要想使用当前操作系统的外观，加入下列代码即可:</br>
 * <p>
 * try {
 * UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
 * } catch(Exception e) 
 * { e.printStackTrace();}
 * <p>
 * 在catch子句中什么也不用做，因为在缺省情况下，如果设置代码失败了，UIManager将设置成跨平台的外观。
 * <p>
 * 一般把这些代码添加在main()开始的地方，至少也要在添加任何组件之前。
 * 
 * @author 刘晨伟
 *
 * 创建日期：2009-5-21
 */
public class LookAndFeelDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Object[] selections = { "system", "motif", "cross" };
		// 显示一个要求用户选择String的对话框
		Object selection = JOptionPane.showInputDialog(null, "Choose one",
				"Look and Feel", JOptionPane.INFORMATION_MESSAGE, null,
				selections, selections[0]);
		String value = selection == null ? "system" : selection.toString();
		try {
			if (value.equals("cross")) {
				UIManager.setLookAndFeel(UIManager
						.getCrossPlatformLookAndFeelClassName());
			} else if (value.equals("system")) {
				UIManager.setLookAndFeel(UIManager
						.getSystemLookAndFeelClassName());
			} else if (value.equals("motif")) {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 确保在任何组件创建之前设置LookAndFeel
		Displayer.createAndShowGUI("LookAndFeel Demo", new LookAndFeelPanel());
	}
}

class LookAndFeelPanel extends JPanel {

	private static final long serialVersionUID = -5915555538675728354L;

	private String[] choices = { "one", "two", "three", "four" };

	private Component[] comps = { new JButton("JButton"),
			new JTextField("JTextField"), new JLabel("JLabel"),
			new JCheckBox("JCheckBox"), new JRadioButton("JRadioButton"),
			new JComboBox(choices), new JList(choices) };

	public LookAndFeelPanel() {
		for (int i = 0; i < comps.length; i++) {
			this.add(comps[i]);
		}
	}
}