/**
 * 
 */
package log.filter;

import java.util.logging.ConsoleHandler;
import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * 日志过滤器演示类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-6-19
 */
public class FilterDemo {

	private static Logger logger = Logger.getLogger("FilterDemo");
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Log use no filter.");
		Handler handler = new ConsoleHandler();
		logger.setUseParentHandlers(false);
		logger.addHandler(handler);
		sendLogMessages();
		
		System.out.println("Log use Logger/Handler filter.");
		logger.setFilter(new LoggerFilter());
		handler.setFilter(new HandlerFilter());
		sendLogMessages();
	}
	
	private static void sendLogMessages() {
		// 第三个参数是传递给 LogRecord 的参数(parameter)
		logger.log(Level.WARNING, "I am a man!", new Man());
		logger.log(Level.WARNING, "I am a woman!", new Woman());
		logger.log(Level.WARNING, "I am a kid!", new Kid());
	}
}
/** 
 * Logger使用的过滤器 
 * <p>
 * 决定日志是否值得报送，这里只有参数是Man对象和Woman对象时才会报送。
 */
class LoggerFilter implements Filter {

	public boolean isLoggable(LogRecord record) {
		Object[] params = record.getParameters();
		if (params == null)
			return true;
		if (record.getParameters()[0] instanceof Man)
			return true;
		if (record.getParameters()[0] instanceof Woman)
			return true;
		return false;
	}
} 
/**
 * Handler使用的过滤器 
 * <p>
 * 决定日志是否值得发布，这里只有参数是Man对象和Kid对象时才会报送。
 */
class HandlerFilter implements Filter {
	
	public boolean isLoggable(LogRecord record) {
		Object[] params = record.getParameters();
		if (params == null)
			return true; 
		if (record.getParameters()[0] instanceof Man)
			return true;
		return false;
	}
} 

class Man {
}

class Woman {
}

class Kid {
}