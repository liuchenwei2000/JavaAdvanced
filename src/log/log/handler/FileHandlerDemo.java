/**
 * 
 */
package log.handler;

import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * ��־�ļ���������ʾ��
 * 
 * @author ����ΰ
 *
 * �������ڣ�2009-6-18
 */
public class FileHandlerDemo {

	private static Logger logger = Logger.getLogger("FileHandlerDemo");

	/**
	 * ��ʹ���ڰ�������͸�һ���ļ����Իῴ������̨�����
	 * ������Ϊÿ����Ϣ��ת���� LogRecord���������ɱ��ص� logger ����ʹ�ã�������������ݸ����Լ��Ĵ�������
	 * ��ʱ��LogRecord �����ݸ������󣬸�����ӵ���Լ��Ĵ�������������̽�������ȥ��ֱ���ﵽ����־��¼��Ϊֹ��
	 * ����־��¼����һ��ȱʡ�� ConsoleHandler�������Ϣ������ʾ���ϳ��֣�Ҳ����־�ļ��г��֣�����ͨ������ setUseParentHandlers(false) ���ر������������
	 */
	public static void main(String[] args) throws Exception {
		String fileName = "files/log.handler/LogToFile.xml";
		String txtFileName = "files/log.handler/LogToFile.txt";
		
		// logger.setUseParentHandlers(false);
		logger.addHandler(new FileHandler(fileName));
		logger.info("A message logged to the file");
		logger.info(new Date().toString());
		
		/* 
		 * ����һ������־׷�ӵ�ָ���ļ�β�����ļ������� ��
		 * �ڶ������������false�����µ���־����ȫ���Ǿɵ����ݣ�Ĭ����false��
		 */
		FileHandler txtHandler = new FileHandler(txtFileName, true);
		
		/*
		 * FileHandler��ȱʡ�����ʽ��XML�������ı����ָ�ʽ�������������������һ����ͬ��Formatter����
		 * ���ļ�ʹ����һ�� SimpleFormatter ������ʹ���Ϊ���ı���ʽ��
		 */
		txtHandler.setFormatter(new SimpleFormatter());
		logger.addHandler(txtHandler);
		logger.info("A message logged to the file");
		logger.info(new Date().toString());
	}
}