package swing.inside.swingworker.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * File信息TableModel
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2011-1-21
 */
public class FileInfoTableModel extends AbstractTableModel {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8301130997521360463L;
	
	/** 目前只有三列 */
	private static final String[] COLUMN = { "Name", "Path", "Last Write Time" };
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private Date date = new Date();
	
	private List<File> files;

	public FileInfoTableModel(List<File> files) {
		super();
		this.files = files;
	}

	public int getColumnCount() {
		return COLUMN.length;
	}

	public String getColumnName(int column) {
		return COLUMN[column];
	}

	@Override
	public int getRowCount() {
		if(files == null) return 0;
		return files.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(files == null || files.size() == 0) return null;
		File file = files.get(rowIndex);
		switch(columnIndex) {
		case 0:// name
			return file.getName();
		case 1:// path
			return file.getAbsolutePath();
		case 2:// last write time
			return formatLastWriteTime(file.lastModified());
		}
		return null;
	}
	
	private String formatLastWriteTime(long lastModified) {
		date.setTime(lastModified);
		return dateFormat.format(date);
	}
}