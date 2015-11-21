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
 * 资源机制演示类
 * <p>
 * applet和应用程序中使用的类通常会用到一些相关的数据文件，如：</br>
 * 图像和声音文件、文本文件、二进制数据文件。
 * <p>
 * 在Java中，这些关联的文件称为资源(resource)。</br>
 * 利用资源机制，对于非类文件类加载器也可以方便的定位这些资源，下面是必要的步骤：
 * <li>1，获得具有资源的Class对象(本例为AboutPanel.class)。
 * <li>2，调用getResource(fileName)来获取作为URL的资源的位置。
 * <li>3，如果资源是图像或音频文件，可以使用getImage或getAudioClip方法直接读取。
 * <li>4，否则，通过调用getResourceAsStream方法读取文件中的数据。
 * </br>
 * 重点在于类加载器记得如何定位类的位置，它可以在同一位置(类文件位置)查找相关的资源，也就是在同一个JAR文件中定位资源文件。
 * <p>
 * 自动加载资源文件是利用资源加载特性完成的，没有标准的方法来解释资源文件的内容，每个程序必须有其自己的解释资源文件的方法。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-7-3
 */
public class ResourceDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 没有使用Displayer是为了打包jar后能方便运行
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
			// 意味着从找到AboutPanel.class类文件的地方定位about.gif文件
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
				 * 如果不想把资源文件和类文件放置在同一目录下，可以把它放置在子目录下面，
				 * 并使用具有层次的资源名称， 如：text/about.txt
				 * 
				 * 这是一个相对资源名称，它会被解释为相对于加载资源的类所在的包。
				 * 注意，必须使用"/"作为分隔符，是OS无关的，资源管理器会自动地把"/"转换为"\"。
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
