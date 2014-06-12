/**
 * 
 */
package swing.text;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import util.Displayer;

/**
 * �ı�����ʾ
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-6-25
 */
public class JTextAreaDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JTextArea Demo", new JTextAreaPanel());
	}
}

class JTextAreaPanel extends JPanel {

	private static final long serialVersionUID = 5957521281596399546L;

	private JTextArea textArea;
	private JButton insertButton;
	private JButton wrapButton;

	public JTextAreaPanel() {
		setLayout(new BorderLayout());

		JScrollPane scroll = new JScrollPane(getTextArea());
		add(scroll, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(getInsertButton());
		buttonPanel.add(getWrapButton());
		add(buttonPanel, BorderLayout.SOUTH);
	}

	private JTextArea getTextArea() {
		if (textArea == null) {
			// ����һ��ָ���������������ı���
			textArea = new JTextArea(8, 20);
		}
		return textArea;
	}

	private JButton getInsertButton() {
		if (insertButton == null) {
			insertButton = new JButton("insert");
			insertButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					// ���������ı����ӵ��ı����������ı��ĵײ�
					getTextArea().append(
							"The quick brown fox jumps over the lazy dog.");
				}
			});
		}
		return insertButton;
	}

	private JButton getWrapButton() {
		if (wrapButton == null) {
			wrapButton = new JButton("wrap");
			wrapButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					boolean wrap = !getTextArea().getLineWrap();
					// �򿪻��߹ر��Զ����й���
					// ����ֻ����ͼ�����˱仯�����������ı��������"\n"
					getTextArea().setLineWrap(wrap);
					// ���û��з�ʽ(����ı���Ҫ����)��������Ĭ��Ϊfalse
					// �������Ϊtrue�����еĳ��ȴ���������Ŀ��ʱ�����ڵ��ʱ߽�(�հ�)������
					// �������Ϊfalse�������ַ��߽紦����(���ῼ�ǵ���������)
					getTextArea().setWrapStyleWord(true);
					wrapButton.setText(wrap ? "no wrap" : "wrap");
				}
			});
		}
		return wrapButton;
	}
}