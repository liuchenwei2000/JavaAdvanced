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
 * ��־��������ʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-6-19
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
		// �����������Ǵ��ݸ� LogRecord �Ĳ���(parameter)
		logger.log(Level.WARNING, "I am a man!", new Man());
		logger.log(Level.WARNING, "I am a woman!", new Woman());
		logger.log(Level.WARNING, "I am a kid!", new Kid());
	}
}
/** 
 * Loggerʹ�õĹ����� 
 * <p>
 * ������־�Ƿ�ֵ�ñ��ͣ�����ֻ�в�����Man�����Woman����ʱ�Żᱨ�͡�
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
 * Handlerʹ�õĹ����� 
 * <p>
 * ������־�Ƿ�ֵ�÷���������ֻ�в�����Man�����Kid����ʱ�Żᱨ�͡�
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