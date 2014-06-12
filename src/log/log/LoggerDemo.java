/**
 * 
 */
package log;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ��־��ʾ��
 * 
 * @author ����ΰ
 *
 * �������ڣ�2009-6-18
 */
public class LoggerDemo {

	private static Logger logger = Logger.getLogger("LoggerDemo");

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String message = "Logging an INFO-Level message.";
		/*
		 * "ȫ��"Logger��������ż��ʹ��Logging����������Ա�ṩ��һ�ֱ�ݷ�����
		 * ���ھ���ʹ��logging���Ŀ�����ԱӦ������ʹ�����Լ���Logger����(����)��
		 * �����������ʺ���ÿ�� Logger ���ȵļ����Ͽ�����־��¼��
		 */
		Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info(message);
		/*
		 * ��¼��־ϵͳ�ܹ�̽�⵽�˲�����־�������ͷ�������
		 * �����ܱ�֤��Щ���ֶ�����ȷ�ģ���˲�Ӧ���������������ṩ�ľ�ȷ�ԡ�
		 */
		logger.info(message);
		/*
		 * �����ȷ����ӡ����ȷ�������ͷ�������ô�Ϳ���ʹ������ķ����� 
		 * logp()�������ܼ�¼��־�����������������Լ�Ҫ������־���ִ���Ϊ�������
		 */
		logger.logp(Level.INFO, "LoggerDemo", "main", message);
		/* ��־�ĳ�����;�Ǽ�¼��Щ����Ԥ�ϵ��쳣 */
		try {
			throw new RuntimeException("Exception occured.");
		} catch (Exception e) {
			logger.log(Level.WARNING, "exception:", e);
		}
	}
}