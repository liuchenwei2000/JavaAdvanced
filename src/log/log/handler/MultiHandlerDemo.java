/**
 * 
 */
package log.handler;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**
 * ���ش�������ʾ��
 * <p>
 * ������ÿ�� Logger ����ע������������
 * <p>
 * ����־���󵽴� Logger ʱ��ֻҪ Logger ����־������ڻ���ڴ���������־���󼶱�
 * ����֪��������ע��Ĵ���������Щ����������ӵ�����Լ�����־����
 * ��� LogRecord �ļ�����ڻ����ĳ���������ļ�����ô�ô������ͻᷢ�����档
 * 
 * @author ����ΰ
 *
 * �������ڣ�2009-6-18
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