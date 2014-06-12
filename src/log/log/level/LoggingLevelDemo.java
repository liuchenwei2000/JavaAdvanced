/**
 * 
 */
package log.level;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 日志级别演示
 * <p>
 * 只有在当前选择的记录日志级别上的消息以及那些更严格的消息才会被报告。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-6-18
 */
public class LoggingLevelDemo {
	
	/**
	 * 可以有多个日志记录器对象，而且这些日志记录器被组织成一个层次树。
	 * 可以通过编程方式将它和包的名字空间关联起来。
	 * 子日志记录器紧跟着它们的直接父日志记录器，而且缺省地将日志的记录向上传递给父日志记录器。
	 * "根"日志记录器对象总是缺省创建的，而且它是日志记录器对象树的基础。
	 * 通过调用静态方法 Logger.getLogger("")获得指向根日志记录器的引用。
	 */
	private static Logger log = Logger.getLogger("log");
	private static Logger level = Logger.getLogger("log.level");
	private static Logger record = Logger.getLogger("log.record");
	private static Logger doc = Logger.getLogger("doc");

	private static void logMessages() {
		log.info("log : info");
		level.severe("log.level : severe");
		record.info("log.record : info");
		doc.info("doc : info");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		log.setLevel(Level.SEVERE);
		System.out.println("log level: SEVERE");
		logMessages();
		level.setLevel(Level.FINEST);
		record.setLevel(Level.FINEST);
		doc.setLevel(Level.FINEST);
		System.out.println("level/record/doc/ logger set to FINEST");
		logMessages();
		log.setLevel(Level.SEVERE);
		System.out.println("log level: SEVERE");
		logMessages();
	}
}