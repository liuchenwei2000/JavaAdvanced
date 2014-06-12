/**
 * 
 */
package log;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 日志演示类
 * 
 * @author 刘晨伟
 *
 * 创建日期：2009-6-18
 */
public class LoggerDemo {

	private static Logger logger = Logger.getLogger("LoggerDemo");

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String message = "Logging an INFO-Level message.";
		/*
		 * "全局"Logger对象是向偶尔使用Logging包开发的人员提供的一种便捷方法。
		 * 对于经常使用logging包的开发人员应创建和使用其自己的Logger对象(如下)。
		 * 这样才能在适合于每个 Logger 粒度的级别上控制日志记录。
		 */
		Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info(message);
		/*
		 * 记录日志系统能够探测到了产生日志的类名和方法名，
		 * 它不能保证这些名字都是正确的，因此不应该依赖于它所能提供的精确性。
		 */
		logger.info(message);
		/*
		 * 如果想确保打印出正确的类名和方法，那么就可以使用下面的方法： 
		 * logp()方法接受记录日志级别、类名、方法名以及要记入日志的字串作为其参数。
		 */
		logger.logp(Level.INFO, "LoggerDemo", "main", message);
		/* 日志的常见用途是记录那些不可预料的异常 */
		try {
			throw new RuntimeException("Exception occured.");
		} catch (Exception e) {
			logger.log(Level.WARNING, "exception:", e);
		}
	}
}