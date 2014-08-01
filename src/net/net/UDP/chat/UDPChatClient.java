/**
 * 
 */
package net.UDP.chat;

import javax.swing.SwingUtilities;

/**
 * 点对点聊天示例
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月1日
 */
public class UDPChatClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/** 如果在同一台机器进行两个客户端之间的聊天 */
		final String localhost = "127.0.0.1";

		final int portSendA = 8881;
		final int portReceiveA = 9991;
		
		final int portSendB = 8882;
		final int portReceiveB = 9992;
		
		// 另起EDT线程进行界面展现，主线程不需要参与
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
		
		/** 如果在不同机器进行两个客户端之间的聊天，则两个客户端必须运行在各自的物理机上 */
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
