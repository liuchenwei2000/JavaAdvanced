/**
 * 
 */
package log.level;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ��־������ʾ
 * <p>
 * ֻ���ڵ�ǰѡ��ļ�¼��־�����ϵ���Ϣ�Լ���Щ���ϸ����Ϣ�Żᱻ���档
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-6-18
 */
public class LoggingLevelDemo {
	
	/**
	 * �����ж����־��¼�����󣬶�����Щ��־��¼������֯��һ���������
	 * ����ͨ����̷�ʽ�����Ͱ������ֿռ����������
	 * ����־��¼�����������ǵ�ֱ�Ӹ���־��¼��������ȱʡ�ؽ���־�ļ�¼���ϴ��ݸ�����־��¼����
	 * "��"��־��¼����������ȱʡ�����ģ�����������־��¼���������Ļ�����
	 * ͨ�����þ�̬���� Logger.getLogger("")���ָ�����־��¼�������á�
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