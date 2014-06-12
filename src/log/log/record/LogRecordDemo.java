/**
 * 
 */
package log.record;

import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * LogRecord��ʾ��
 * <p>
 * LogRecord��һ����ʹ(Messenger)����������������ǽ���Ϣ��һ���ط����͵���һ���ط���
 * LogRecord�е����з������ǻ�ȡ��(getter)��������(setter)��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-6-18
 */
public class LogRecordDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PrintableLogRecord logRecord = new PrintableLogRecord(Level.FINEST,
				"Simple Log Record");
		System.out.println(logRecord);
	}
}

class PrintableLogRecord extends LogRecord {

	private static final long serialVersionUID = 0L;

	public PrintableLogRecord(Level level, String str) {
		super(level, str);
	}

	public String toString() {
		String result = "Level<" + getLevel() + ">\n" + "LoggerName<"
				+ getLoggerName() + ">\n" + "Message<" + getMessage() + ">\n"
				+ "CurrentMillis<" + getMillis() + ">\n" + "Params";
		Object[] objParams = getParameters();// ��ȡ��־��Ϣ�Ĳ���
		if (objParams == null) {
			result += "<null>\n";
		} else {
			for (int i = 0; i < objParams.length; i++) {
				result += "Param # <" + i + " value " + objParams[i].toString()
						+ ">\n";
			}
		}
		result += "ResourceBundle<" + getResourceBundle()
				+ ">\nResourceBundleName<" + getResourceBundleName()
				+ ">\nSequenceNumber<" + getSequenceNumber()
				+ ">\nSourceClassName<" + getSourceClassName()
				+ ">\nSourceMethodName<" + getSourceMethodName()
				+ ">\nThread Id<" + getThreadID() + ">\nThrown<" + getThrown()
				+ ">";
		return result;
	}
}