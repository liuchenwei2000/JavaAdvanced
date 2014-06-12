/**
 * 
 */
package log.record;

import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * LogRecord演示类
 * <p>
 * LogRecord是一个信使(Messenger)对象，它的任务仅仅是将信息从一个地方传送到另一个地方。
 * LogRecord中的所有方法都是获取器(getter)和设置器(setter)。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-6-18
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
		Object[] objParams = getParameters();// 获取日志消息的参数
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