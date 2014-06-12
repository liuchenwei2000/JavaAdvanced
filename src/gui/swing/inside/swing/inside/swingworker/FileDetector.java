package swing.inside.swingworker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingWorker;

/**
 * �ļ�̽����
 * <p>
 * SwingWorker��������ܼȻ��������ս��Ҳ������м�����
 * �߳���doInBackgroud��������ʱ�Ų�����������������߳�Ҳ���Բ����͹����м����ݡ�
 * ʵ��SwingWorker����ʱ������������Ҫָ�����պ��м��������͡�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2011-1-18
 */
public class FileDetector extends SwingWorker<List<File>, File> {

	private JTable table;
	private JLabel label;
	private String filePath;
	
	public FileDetector(JTable table, JLabel label,String filePath) {
		this.table = table;
		this.label = label;
		this.filePath = filePath;
	}

	protected List<File> doInBackground() throws Exception {
		return listAllFiles(new File(filePath));
	}
	
	/**
	 * ����ָ���ļ����������������ļ�(��)����Ϣ��
	 */
	private List<File> listAllFiles(File file) {
		if(!file.exists()) return null;
		List<File> files = new ArrayList<File>();
		listAllFiles(file, files);
		return files;
	}

	private void listAllFiles(File file, List<File> files) {
		/*
		 * �������������û�ȡ������ʵ�ִ���Ҫ��SwingWorker�����������Եļ��ȡ������
		 * ����isCancelled����������Ƿ���ȡ������
		 * ��ѭ����������������������������ȷ���߳��ܼ�ʱ���ȡ������
		 * �߳������Եؼ����������ֹͣ���������磺
		 * 1.doInBackgroud��������������ѭ�������ɻ�ʱ��
		 * 2.process�����и���GUIʱ��
		 * 3.done�����и���GUIʱ��
		 */
		if (isCancelled()) return;// 1
		files.add(file);
		if (files.size() % 1000 == 0) {// ÿ1000������һ��
			// ����ʹ��publish����������Ҫ������м�����,Ϊ������ִ���ж����������ʱ�������ݣ�
			// Ҫ����publish���������Բ�������ʽ�ṩҪ���������ݣ���������������ָ���м����ݵ����͡�
			publish(files.toArray(new File[0]));
		}
		if (file.isDirectory()) {
			File[] children = file.listFiles();
			if (children != null) {
				for (File childFile : children) {
					listAllFiles(childFile, files);
				}
			}
		}}

	/**
	 * ���SwingWorker���෢����һЩ���ݣ���ôҲӦ��ʵ��process������������Щ�м�����
	 * �������ĸ������EDT�߳��ϼ���process����������ڴ˷����г�����԰�ȫ�ĸ���UI�����
	 * <p>
	 * ���������̵߳���publish����ʱ��SwingWorker�����process������
	 * ����˼����process��������EDT����ִ�еģ�����ζ�ſ���ͬSwing�������ģ��ֱ�ӽ�����
	 * ע��publish�����Ĳ�������һ���ɱ������ԭ����publish�����ܹ�����ģʽ������process������
	 * Ҳ����˵��ÿ��publish���ò������ǲ�����Ӧ��process���á�
	 * ������ܣ�publish�������ռ������Զ�����б�Ϊ��������process������
	 * 
	 * @see javax.swing.SwingWorker#process(java.util.List)
	 */
	protected void process(List<File> chunks) {
		if (isCancelled()) return;// 2
		updateUI(chunks);
	}

	protected void done() {
		try {
			if (isCancelled()) return;// 3
			List<File> files = get();
			updateUI(files);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	private void updateUI(List<File> files) {
		FileInfoTableModel dataModel = new FileInfoTableModel(files);
		table.setModel(dataModel);
		label.setText(dataModel.getRowCount() + " ���ļ�");
	}
}