/**
 * 
 */
package net.UDP.chat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import util.Displayer;

/**
 * 聊天软件示例
 * <p>
 * 即时聊天软件对安全的要求不是太高，一般都会采用UDP的通信模式。
 * <p>
 * 聊天界面至少有两个部分：接收用户输入和打印聊天记录。它们既然需要同时存在于一个进程中，那肯定是多线程的。
 * 另外，UDP的通信是点对点的，没有谁是服务器端，也没有谁是客户端，
 * 因此UDP的多线程模型就不用有主线程来监听请求了，只需要在线程中分别使用DatagramPacket通信即可。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月1日
 */
public class ChatFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4678152379795505698L;
	
	private JTextArea areaOutput;// 聊天记录
	private JTextArea areaInput;// 用户输入
	
	private JButton buttonSend;
	
	private String ipTarget;// 发送目标的IP
	
	private int portSend;// 本机发送消息端口号
	private int portReceive;// 本机 接收消息端口号
	private int portTarget;// 目标接收消息端口号
	
	public ChatFrame(int portSend, int portReceive, String ipTarget, int portTarget) {
		super("与 对方 聊天中");
		this.portSend = portSend;
		this.portReceive = portReceive;
		this.ipTarget = ipTarget;
		this.portTarget = portTarget;
		
		initUI();
		// 启动一个 消息接收监听 线程
		new Thread(new ReceiveTask(), "receive-thread").start();
	}

	private void initUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(400, 500));
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(createSplitPane());
	}

	private JSplitPane createSplitPane() {
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setDividerLocation(350);
		splitPane.setTopComponent(createOutputPanel());
		splitPane.setBottomComponent(createInputPanel());
		return splitPane;
	}

	private JPanel createOutputPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(new JScrollPane(getAreaOutput()), BorderLayout.CENTER);
		return panel;
	}
	
	private JPanel createInputPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(new JScrollPane(getAreaInput()), BorderLayout.CENTER);
		panel.add(getButtonSend(), BorderLayout.EAST);
		return panel;
	}

	private JTextArea getAreaOutput() {
		if(areaOutput == null) {
			areaOutput = new JTextArea();
		}
		return areaOutput;
	}

	private JTextArea getAreaInput() {
		if(areaInput == null) {
			areaInput = new JTextArea("请输入...");
		} 
		return areaInput;
	}
	
	private JButton getButtonSend() {
		if(buttonSend == null) {
			buttonSend = new JButton("发  送");
			buttonSend.addActionListener(new SendAction());
		}
		return buttonSend;
	}
	
	/**
	 * 发送消息
	 */
	private class SendAction implements ActionListener {
		
		private ExecutorService executor = Executors
				.newSingleThreadExecutor(new ThreadFactory() {

					@Override
					public Thread newThread(Runnable r) {
						return new Thread(r, "send-thread");
					}
				});
		
		private DatagramSocket sender;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String message = getAreaInput().getText();
			if(message != null) {
				getAreaInput().setText("");// 清空输入窗口
				getAreaOutput().append("\n" + formatMessageSend(message));// 在本机聊天记录窗口显示
				sendMessage(message);// 发送消息
			}
		}

		private void sendMessage(String message) {
			// 另起一个线程执行发送消息的任务
			executor.execute(new SendTask(getSender(), message));
		}

		private DatagramSocket getSender() {
			if(sender == null) {
				try {
					sender = new DatagramSocket(portSend);
				} catch (SocketException e) {
					e.printStackTrace();
				}
			}
			return sender;
		}
	}

	/**
	 * 发送消息任务
	 */
	private class SendTask implements Runnable {

		private DatagramSocket sender;
		private String message;

		public SendTask(DatagramSocket sender, String message) {
			this.sender = sender;
			this.message = message;
		}

		public void run() {
			try {
				DatagramPacket packet = new DatagramPacket(message.getBytes(),
						0, message.length(), InetAddress.getByAddress(getIPAddress()),
						portTarget);
				sender.send(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 接收消息任务
	 */
	private class ReceiveTask implements Runnable {

		private DatagramSocket receiver;
		
		public void run() {
			// 恒真，时刻监听是否有消息到来
			while (true) {
				try {
					byte[] buffer = new byte[1024];
					DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
					getReceiver().receive(packet);// 一直阻塞直到有消息到来
					final String message = new String(packet.getData(), packet.getOffset(), packet.getLength());
					// 使用EDT线程操作界面控件，在聊天记录窗口展现消息
					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							getAreaOutput().append("\n" + formatMessageReceived(message));
						}
					});
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		private DatagramSocket getReceiver() {
			if(receiver == null) {
				try {
					receiver = new DatagramSocket(portReceive);
				} catch (SocketException e) {
					e.printStackTrace();
				}
			}
			return receiver;
		}
	}
	
	private byte[] getIPAddress() {
		String[] ips = ipTarget.split("\\.");
		return new byte[] { (byte) (Integer.parseInt(ips[0])),
				(byte) (Integer.parseInt(ips[1])),
				(byte) (Integer.parseInt(ips[2])),
				(byte) (Integer.parseInt(ips[3])) };
	}
	
	private static String formatMessageSend(String message){
		return "你说:\n" + message;
	}
	
	private static String formatMessageReceived(String message){
		return "对方说:\n" + message;
	}
	
	public void setVisible(boolean b) {
		pack();
		Displayer.setCenter(this);
		super.setVisible(b);
	}
}
