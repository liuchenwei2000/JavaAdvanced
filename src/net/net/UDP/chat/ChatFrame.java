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
 * �������ʾ��
 * <p>
 * ��ʱ��������԰�ȫ��Ҫ����̫�ߣ�һ�㶼�����UDP��ͨ��ģʽ��
 * <p>
 * ��������������������֣������û�����ʹ�ӡ�����¼�����Ǽ�Ȼ��Ҫͬʱ������һ�������У��ǿ϶��Ƕ��̵߳ġ�
 * ���⣬UDP��ͨ���ǵ�Ե�ģ�û��˭�Ƿ������ˣ�Ҳû��˭�ǿͻ��ˣ�
 * ���UDP�Ķ��߳�ģ�;Ͳ��������߳������������ˣ�ֻ��Ҫ���߳��зֱ�ʹ��DatagramPacketͨ�ż��ɡ�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��1��
 */
public class ChatFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4678152379795505698L;
	
	private JTextArea areaOutput;// �����¼
	private JTextArea areaInput;// �û�����
	
	private JButton buttonSend;
	
	private String ipTarget;// ����Ŀ���IP
	
	private int portSend;// ����������Ϣ�˿ں�
	private int portReceive;// ���� ������Ϣ�˿ں�
	private int portTarget;// Ŀ�������Ϣ�˿ں�
	
	public ChatFrame(int portSend, int portReceive, String ipTarget, int portTarget) {
		super("�� �Է� ������");
		this.portSend = portSend;
		this.portReceive = portReceive;
		this.ipTarget = ipTarget;
		this.portTarget = portTarget;
		
		initUI();
		// ����һ�� ��Ϣ���ռ��� �߳�
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
			areaInput = new JTextArea("������...");
		} 
		return areaInput;
	}
	
	private JButton getButtonSend() {
		if(buttonSend == null) {
			buttonSend = new JButton("��  ��");
			buttonSend.addActionListener(new SendAction());
		}
		return buttonSend;
	}
	
	/**
	 * ������Ϣ
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
				getAreaInput().setText("");// ������봰��
				getAreaOutput().append("\n" + formatMessageSend(message));// �ڱ��������¼������ʾ
				sendMessage(message);// ������Ϣ
			}
		}

		private void sendMessage(String message) {
			// ����һ���߳�ִ�з�����Ϣ������
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
	 * ������Ϣ����
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
	 * ������Ϣ����
	 */
	private class ReceiveTask implements Runnable {

		private DatagramSocket receiver;
		
		public void run() {
			// ���棬ʱ�̼����Ƿ�����Ϣ����
			while (true) {
				try {
					byte[] buffer = new byte[1024];
					DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
					getReceiver().receive(packet);// һֱ����ֱ������Ϣ����
					final String message = new String(packet.getData(), packet.getOffset(), packet.getLength());
					// ʹ��EDT�̲߳�������ؼ����������¼����չ����Ϣ
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
		return "��˵:\n" + message;
	}
	
	private static String formatMessageReceived(String message){
		return "�Է�˵:\n" + message;
	}
	
	public void setVisible(boolean b) {
		pack();
		Displayer.setCenter(this);
		super.setVisible(b);
	}
}
