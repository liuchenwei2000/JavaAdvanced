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
 * 格式器演示类
 * <p>
 * Formatter(格式器)是一种向Handler的处理步骤中插入格式化操作的方式。
 * 如果向Handler对象注册了Formatter对象，那么在通过Handler发布LogRecord之前，
 * 它首先会被送到Formatter，在被格式化之后LogRecord被返回给Handler，接着把它发布出去。
 * 
 * @author 刘晨伟
 *
 * 创建日期：2009-6-19
 */
public class FormatterDemo {
	
	private static Logger logger = Logger.getLogger("FormatterDemo");

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Handler handler = new ConsoleHandler();
		// 将Formatter注册到Handler
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
 * 编写一个自定义的Formatter，要扩展Formatter类并且覆盖format(LogRecord)
 */
class MyFormatter extends Formatter {
	
	public String format(LogRecord record) {
		return record.getLevel() + ":" + record.getSourceClassName()
				+ " -:- " + record.getSourceMethodName() + " -:- "
				+ record.getMessage() + "\n";
	}
}