/**
 * 
 */
package log.handler;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**
 * 多重处理器演示类
 * <p>
 * 可以用每个 Logger 对象注册多个处理器。
 * <p>
 * 当日志请求到达 Logger 时，只要 Logger 的日志级别高于或等于处理器的日志请求级别，
 * 它告知所有向它注册的处理器，这些处理器依次拥有其自己的日志级别。
 * 如果 LogRecord 的级别高于或等于某个处理器的级别，那么该处理器就会发布报告。
 * 
 * @author 刘晨伟
 *
 * 创建日期：2009-6-18
 */
public class MultiHandlerDemo {

	private static Logger logger = Logger.getLogger("MultiHandlerDemo");

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String fileName = "files/log.handler/MultiHandlerLog.xml";
		logger.addHandler(new FileHandler(fileName));
		logger.addHandler(new ConsoleHandler());
		logger.setUseParentHandlers(false);
		logger.warning("Output to multiple handlers");
	}
}
