package swing.text;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import util.Displayer;

/**
 * �ĵ���������ʾ��
 * <p>
 * JTextComponent�����඼����ע���ĵ��������Լ����ı����ݵı����
 * Document��ģ�ͣ���JTextComponent����ͼ��ֱ�Ӽ���ģ�͵ı����
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-5-27
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
			// Ϊ�ı����Documentģ����Ӽ�����
			tf.getDocument().addDocumentListener(new DocumentListener() {
				// һ������ʵ�ָ÷���(ֻ�е������ı���ʽ�����仯ʱ�Ŵ���)
				public void changedUpdate(DocumentEvent e) {
					// do nothing
				}
				// �ĵ�ִ���˲��������֪ͨ
				public void insertUpdate(DocumentEvent e) {
					updateLabel();
				}
				// �Ƴ���һ�����ĵ���֪ͨ
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
	 * ����JLabel����ʾ���ݣ�ʹ����ı��򱣳�ͬ��
	 */
	private void updateLabel() {
		getLabel().setText(getTextField().getText());
	}
}