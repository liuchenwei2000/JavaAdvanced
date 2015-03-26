/**
 * 
 */
package log.handler;

import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * �Զ�����־��������ʾ��
 * 
 * @author ����ΰ
 *
 * �������ڣ�2009-6-18
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
 * �Զ�����־������
 * <p>
 * ͨ���̳� Handler �࣬���Ժ����׵ر�д���ƵĴ�������
 * ����Ҫʵ�� publish()��������ִ��ʵ���ϵı��ͣ�����Ҫʵ�� flush()�� close()������ȷ�����ڱ��͵�������ȫ��ա�
 */
class MyHandler extends Handler {

	/**
	 * ������־��Ϣ
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