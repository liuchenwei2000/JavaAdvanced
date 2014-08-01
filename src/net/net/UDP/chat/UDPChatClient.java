/**
 * 
 */
package net.UDP.chat;

import javax.swing.SwingUtilities;

/**
 * ��Ե�����ʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��1��
 */
public class UDPChatClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/** �����ͬһ̨�������������ͻ���֮������� */
		final String localhost = "127.0.0.1";

		final int portSendA = 8881;
		final int portReceiveA = 9991;
		
		final int portSendB = 8882;
		final int portReceiveB = 9992;
		
		// ����EDT�߳̽��н���չ�֣����̲߳���Ҫ����
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new ChatFrame(portSendA, portReceiveA, localhost, portReceiveB)
						.setVisible(true);
			}
		});

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new ChatFrame(portSendB, portReceiveB, localhost, portReceiveA)
						.setVisible(true);
			}
		});
		
		/** ����ڲ�ͬ�������������ͻ���֮������죬�������ͻ��˱��������ڸ��Ե�������� */
//		final String ipA = "10.25.2.212";
//		final String ipB = "10.25.3.158";

//		final int portSend = 8888;
//		final int portReceive = 9999;
		
		
//		SwingUtilities.invokeLater(new Runnable() {
//
//			@Override
//			public void run() {
//				new ChatFrame(portSend, portReceive, ipA, portReceive).setVisible(true);
//			}
//		});
		
//		SwingUtilities.invokeLater(new Runnable() {
//
//			@Override
//			public void run() {
//				new ChatFrame(portSend, portReceive, ipB, portReceive).setVisible(true);
//			}
//		});
	}
}
