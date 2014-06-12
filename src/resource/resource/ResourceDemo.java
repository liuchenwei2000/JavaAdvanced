package resource;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * ��Դ������ʾ��
 * <p>
 * applet��Ӧ�ó�����ʹ�õ���ͨ�����õ�һЩ��ص������ļ����磺</br>
 * ͼ��������ļ����ı��ļ��������������ļ���
 * <p>
 * ��Java�У���Щ�������ļ���Ϊ��Դ(resource)��</br>
 * ������Դ���ƣ����ڷ����ļ��������Ҳ���Է���Ķ�λ��Щ��Դ�������Ǳ�Ҫ�Ĳ��裺
 * <li>1����þ�����Դ��Class����(����ΪAboutPanel.class)
 * <li>2������getResource(fileName)����ȡ��ΪURL����Դ��λ��
 * <li>3�������Դ��ͼ�����Ƶ�ļ�������ʹ��getImage��getAudioClip����ֱ�Ӷ�ȡ
 * <li>4������ͨ������getResourceAsStream������ȡ�ļ��е�����
 * </br>
 * �ص�������������ǵ���ζ�λ���λ�ã���������ͬһλ��(���ļ�λ��)������ص���Դ��
 * Ҳ������ͬһ��JAR�ļ��ж�λ��Դ�ļ���
 * <p>
 * �Զ�������Դ�ļ���������Դ����������ɵģ�û�б�׼�ķ�����������Դ�ļ������ݣ�
 * ÿ��������������Լ��Ľ�����Դ�ļ��ķ�����
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-7-3
 */
public class ResourceDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// û��ʹ��Displayer��Ϊ�˴��jar���ܷ�������
		JFrame frame = new JFrame("Resource Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(new AboutPanel(), BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}
}

class AboutPanel extends JPanel {

	private static final long serialVersionUID = 3742125184892009020L;

	private JTextArea textArea;
	private JButton aboutButton;

	public AboutPanel() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(300, 200));
		add(new JScrollPane(getTextArea()), BorderLayout.CENTER);
		add(getAboutButton(), BorderLayout.SOUTH);
	}

	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
		}
		return textArea;
	}

	private JButton getAboutButton() {
		if (aboutButton == null) {
			// ��ζ�Ŵ��ҵ�AboutPanel.class���ļ��ĵط���λabout.gif�ļ�
			URL aboutURL = AboutPanel.class.getResource("about.gif");
			aboutButton = new JButton("About", new ImageIcon(aboutURL));
			aboutButton.addActionListener(new AboutAction());
		}
		return aboutButton;
	}

	private class AboutAction implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			try {
				/*
				 * ����������Դ�ļ������ļ�������ͬһĿ¼�£����԰�����������Ŀ¼���棬
				 * ��ʹ�þ��в�ε���Դ���ƣ� �磺text/about.txt
				 * 
				 * ����һ�������Դ���ƣ����ᱻ����Ϊ����ڼ�����Դ�������ڵİ���
				 * ע�⣬����ʹ��"/"��Ϊ�ָ�������OS�޹صģ���Դ���������Զ��ذ�"/"ת��Ϊ"\"��
				 */
				InputStream in = AboutPanel.class
						.getResourceAsStream("text/about.txt");
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(in));
				String line;
				while ((line = reader.readLine()) != null) {
					getTextArea().append(line + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}