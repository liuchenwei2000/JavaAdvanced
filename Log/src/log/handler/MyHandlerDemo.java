/**
 * 
 */
package log.handler;

import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * 自定义日志处理器演示类
 * 
 * @author 刘晨伟
 *
 * 创建日期：2009-6-18
 */
public class MyHandlerDemo {

	private static Logger logger =Logger.getLogger("MyHandlerDemo");
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		logger.setUseParentHandlers(false);
		logger.addHandler(new MyHandler());
		logger.warning("Logging Warning");
		logger.info("Logging Info");
	}
}

/**
 * 自定义日志处理器
 * <p>
 * 通过继承 Handler 类，可以很容易地编写定制的处理器。
 * 不但要实现 publish()方法（它执行实际上的报送），还要实现 flush()和 close()，它们确保用于报送的流被完全清空。
 */
class MyHandler extends Handler {

	/**
	 * 发布日志信息
	 */
	public void publish(LogRecord logRecord) {
		StringBuilder buffer = new StringBuilder();
		buffer.append(logRecord.getLevel() + ":");
		buffer.append(logRecord.getSourceClassName() + ":");
		buffer.append(logRecord.getSourceMethodName() + ":");
		buffer.append("<" + logRecord.getMessage() + ">");
		System.out.println(buffer.toString());
	}

	public void flush() {
	}

	public void close() {
	}
}
