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
 * 文件探测器UI
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2011-1-21
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
		 * 文档执行了插入操作的通知
		 */
		public void insertUpdate(DocumentEvent e) {
			update();
		}

		/**
		 * 移除了一部分文档的通知
		 */
		public void removeUpdate(DocumentEvent e) {
			update();
		}

		private void update() {
			if(fileDetector != null){
				if(!fileDetector.isCancelled()){
					// 可调用cancel方法取消SwingWorker线程，如果任务正在运行则调用cancel取消之。
					fileDetector.cancel(true);
				}
			}
			// 当调用cancel方法时，代码产生新的任务实例，每一个新的搜索需要自己的任务实例
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