/**
 * 
 */
package log.handler.formatter;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * ��ʽ����ʾ��
 * <p>
 * Formatter(��ʽ��)��һ����Handler�Ĵ������в����ʽ�������ķ�ʽ��
 * �����Handler����ע����Formatter������ô��ͨ��Handler����LogRecord֮ǰ��
 * �����Ȼᱻ�͵�Formatter���ڱ���ʽ��֮��LogRecord�����ظ�Handler�����Ű���������ȥ��
 * 
 * @author ����ΰ
 *
 * �������ڣ�2009-6-19
 */
public class FormatterDemo {
	
	private static Logger logger = Logger.getLogger("FormatterDemo");

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Handler handler = new ConsoleHandler();
		// ��Formatterע�ᵽHandler
		handler.setFormatter(new MyFormatter());
		logger.setUseParentHandlers(false);
		logger.addHandler(handler);
		logMessages();
	}
	
	private static void logMessages() {
		logger.info("Line One");
		logger.info("Line Two");
	}
}

/**
 * ��дһ���Զ����Formatter��Ҫ��չFormatter�ಢ�Ҹ���format(LogRecord)
 */
class MyFormatter extends Formatter {
	
	public String format(LogRecord record) {
		return record.getLevel() + ":" + record.getSourceClassName()
				+ " -:- " + record.getSourceMethodName() + " -:- "
				+ record.getMessage() + "\n";
	}
}