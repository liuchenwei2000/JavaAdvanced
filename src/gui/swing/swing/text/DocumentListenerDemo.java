package swing.text;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import util.Displayer;

/**
 * 文档监听器演示类
 * <p>
 * JTextComponent的子类都可以注册文档监听器以监听文本内容的变更。
 * Document是模型，而JTextComponent是视图，直接监听模型的变更。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-5-27
 */
public class DocumentListenerDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("DocumentListener Demo",
				new DocumentListenerPanel());
	}
}

class DocumentListenerPanel extends JPanel {

	private static final long serialVersionUID = 2397984886935004344L;

	private JTextField tf;
	private JLabel label;

	public DocumentListenerPanel() {
		setLayout(new GridLayout(2, 1));
		add(getTextField());
		add(getLabel());
	}

	private JTextField getTextField() {
		if (tf == null) {
			tf = new JTextField(20);
			// 为文本域的Document模型添加监听器
			tf.getDocument().addDocumentListener(new DocumentListener() {
				// 一般无需实现该方法(只有当类似文本格式发生变化时才处罚)
				public void changedUpdate(DocumentEvent e) {
					// do nothing
				}
				// 文档执行了插入操作的通知
				public void insertUpdate(DocumentEvent e) {
					updateLabel();
				}
				// 移除了一部分文档的通知
				public void removeUpdate(DocumentEvent e) {
					updateLabel();
				}
			});
		}
		return tf;
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel();
		}
		return label;
	}

	/**
	 * 更新JLabel的显示内容，使其和文本域保持同步
	 */
	private void updateLabel() {
		getLabel().setText(getTextField().getText());
	}
}