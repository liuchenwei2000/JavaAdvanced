package swing.inside.swingworker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingWorker;

/**
 * 文件探测器
 * <p>
 * SwingWorker的子类可能既会生成最终结果也会产生中间结果，
 * 线程在doInBackgroud方法结束时才产生最后结果，但任务线程也可以产生和公布中间数据。
 * 实现SwingWorker子类时，在类声明处要指定最终和中间结果的类型。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2011-1-18
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
	 * 根据指定文件返回其下所有子文件(夹)的信息。
	 */
	private List<File> listAllFiles(File file) {
		if(!file.exists()) return null;
		List<File> files = new ArrayList<File>();
		listAllFiles(file, files);
		return files;
	}

	private void listAllFiles(File file, List<File> files) {
		/*
		 * 如果想允许程序用户取消任务，实现代码要在SwingWorker子类中周期性的检查取消请求。
		 * 调用isCancelled方法来检查是否有取消请求。
		 * 在循环迭代或者其他检查点调用这个方法确保线程能及时获得取消请求。
		 * 线程周期性地检查这种请求并停止工作，比如：
		 * 1.doInBackgroud方法的子任务在循环遍历干活时。
		 * 2.process方法中更新GUI时。
		 * 3.done方法中更新GUI时。
		 */
		if (isCancelled()) return;// 1
		files.add(file);
		if (files.size() % 1000 == 0) {// 每1000条发布一次
			// 可以使用publish方法来发布要处理的中间数据,为在任务执行中而非任务结束时发布数据，
			// 要调用publish方法，并以参数的形式提供要发布的数据，必须在类声明中指定中间数据的类型。
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
	 * 如果SwingWorker子类发布了一些数据，那么也应该实现process方法来处理这些中间结果。
	 * 任务对象的父类会在EDT线程上激活process方法，因此在此方法中程序可以安全的更新UI组件。
	 * <p>
	 * 当从任务线程调用publish方法时，SwingWorker类调度process方法。
	 * 有意思的是process方法是在EDT上面执行的，这意味着可以同Swing组件和其模型直接交互。
	 * 注意publish方法的参数，是一个可变参数，原因是publish方法能够以批模式来调用process方法，
	 * 也就是说，每个publish调用并不总是产生相应的process调用。
	 * 如果可能，publish方法会收集对象并以对象的列表为参数调用process方法。
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
		label.setText(dataModel.getRowCount() + " 个文件");
	}
}