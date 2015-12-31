/**
 * 
 */
package log.handler;

import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * 日志文件处理器演示类
 * 
 * @author 刘晨伟
 *
 * 创建日期：2009-6-18
 */
public class FileHandlerDemo {

	private static Logger logger = Logger.getLogger("FileHandlerDemo");

	/**
	 * 即使正在把输出发送给一个文件，仍会看到控制台输出。
	 * 这是因为每条消息被转换成 LogRecord，它首先由本地的 logger 对象使用，这个对象将它传递给它自己的处理器。
	 * 此时，LogRecord 被传递给父对象，父对象拥有自己的处理器。这个过程将持续下去，直到达到根日志记录器为止。
	 * 根日志记录器有一个缺省的 ConsoleHandler，因此消息既在显示器上出现，也在日志文件中出现（可以通过调用 setUseParentHandlers(false) 来关闭这个动作）。
	 */
	public static void main(String[] args) throws Exception {
		String fileName = "files/log.handler/LogToFile.xml";
		String txtFileName = "files/log.handler/LogToFile.txt";
		
		// logger.setUseParentHandlers(false);
		logger.addHandler(new FileHandler(fileName));
		logger.info("A message logged to the file");
		logger.info(new Date().toString());
		
		/* 
		 * 创建一个将日志追加到指定文件尾部的文件处理器 。
		 * 第二个参数如果是false，则新的日志会完全覆盖旧的内容，默认是false。
		 */
		FileHandler txtHandler = new FileHandler(txtFileName, true);
		
		/*
		 * FileHandler的缺省输出格式是XML，如果想改变这种格式，必须给处理器附加上一个不同的Formatter对象。
		 * 该文件使用了一个 SimpleFormatter 对象，以使输出为纯文本格式。
		 */
		txtHandler.setFormatter(new SimpleFormatter());
		logger.addHandler(txtHandler);
		logger.info("A message logged to the file");
		logger.info(new Date().toString());
	}
}
