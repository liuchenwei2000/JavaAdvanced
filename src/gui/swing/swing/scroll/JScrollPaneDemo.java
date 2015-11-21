/**
 * 
 */
package swing.scroll;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import util.Displayer;
import util.ui.BorderFactory;

/**
 * JScrollPane演示类
 * <p>
 * JScrollPane去执行滚动控制，不过还可以去控制有哪些滚动条是可用的：</br>
 * 水平的、垂直的、两者都用还是两者都不用。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-5-17
 */
public class JScrollPaneDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JScrollPane Demo", new JScrollPanePanel());
	}
}

class JScrollPanePanel extends JPanel {

	private static final long serialVersionUID = 3477024713392480248L;

	private JLabel label1 = new JLabel("横:不可用，纵:不可用");
	private JLabel label2 = new JLabel("横:可用，纵:不可用");
	private JLabel label3 = new JLabel("横:不可用，纵:可用");
	private JLabel label4 = new JLabel("横:可用，纵:可用");
	private JLabel label5 = new JLabel("横:需要时可用，纵:需要时可用");
	
	private JTextArea textArea1 = new JTextArea("textArea1", 5, 5);
	private JTextArea textArea2 = new JTextArea("textArea2", 5, 5);
	private JTextArea textArea3 = new JTextArea("textArea3", 5, 5);
	private JTextArea textArea4 = new JTextArea("textArea4", 5, 5);
	private JTextArea textArea5 = new JTextArea("textArea5", 5, 5);
	
	// 创建一个具有指定滚动条策略的空(无窗口的视图)JScrollPane
	// JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED 需要时显示滚动条
    // JScrollPane.HORIZONTAL_SCROLLBAR_NEVER 从不显示滚动条
    // JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS 一直显示滚动条
	private JScrollPane scrollPane1 = new JScrollPane(textArea1,
			JScrollPane.VERTICAL_SCROLLBAR_NEVER,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JScrollPane scrollPane2 = new JScrollPane(textArea2,
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JScrollPane scrollPane3 = new JScrollPane(textArea3,
			JScrollPane.VERTICAL_SCROLLBAR_NEVER,
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	private JScrollPane scrollPane4 = new JScrollPane(textArea4,
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	private JScrollPane scrollPane5 = new JScrollPane(textArea5,
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	public JScrollPanePanel() {
		setLayout(new FlowLayout());
		add(makeTextAreaPanel(label1,scrollPane1));
		add(makeTextAreaPanel(label2,scrollPane2));
		add(makeTextAreaPanel(label3,scrollPane3));
		add(makeTextAreaPanel(label4,scrollPane4));
		add(makeTextAreaPanel(label5,scrollPane5));
	}

	private static JPanel makeTextAreaPanel(JLabel label,JScrollPane scroll){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		scroll.setBorder(BorderFactory.createLineBorder());
		panel.add(label,BorderLayout.NORTH);
		panel.add(scroll,BorderLayout.CENTER);
		return panel;
	}
}
