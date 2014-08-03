package swing.inside.swingworker.action;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import util.Displayer;

/**
 * �ļ�̽����UI
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2011-1-21
 */
public class FileDetectorUI extends JPanel {

	private static final long serialVersionUID = 2397984886935004344L;

	private JTextField textField;

	private JScrollPane scrollPane;
	private JTable table;

	private JLabel label;

	public FileDetectorUI() {
		super();
		initUI();
	}

	private void initUI() {
		setPreferredSize(new Dimension(800, 600));
		setLayout(new BorderLayout());
		add(getTextField(), BorderLayout.NORTH);
		add(getScrollPane(), BorderLayout.CENTER);
		add(getLabel(), BorderLayout.SOUTH);
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel();
			label.setHorizontalAlignment(JLabel.LEADING);
		}
		return label;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane(getTable());
		}
		return scrollPane;
	}

	private JTable getTable() {
		if (table == null) {
			table = new JTable(new FileInfoTableModel(null));
			table.getTableHeader().setReorderingAllowed(false);
		}
		return table;
	}

	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.getDocument().addDocumentListener(
					new AsynDocumentListener());
		}
		return textField;
	}

	private FileDetector fileDetector;
	
	class AsynDocumentListener implements DocumentListener {

		public void changedUpdate(DocumentEvent e) {
			// do nothing
		}

		/**
		 * �ĵ�ִ���˲��������֪ͨ
		 */
		public void insertUpdate(DocumentEvent e) {
			update();
		}

		/**
		 * �Ƴ���һ�����ĵ���֪ͨ
		 */
		public void removeUpdate(DocumentEvent e) {
			update();
		}

		private void update() {
			if(fileDetector != null){
				if(!fileDetector.isCancelled()){
					// �ɵ���cancel����ȡ��SwingWorker�̣߳���������������������cancelȡ��֮��
					fileDetector.cancel(true);
				}
			}
			// ������cancel����ʱ����������µ�����ʵ����ÿһ���µ�������Ҫ�Լ�������ʵ��
			fileDetector = new FileDetector(getTable(), getLabel(), getTextField().getText());
			fileDetector.execute();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				Displayer.createAndShowGUI("Everything", new FileDetectorUI());
			}
		});
	}
}