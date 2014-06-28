/**
 * 
 */
package setting;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.Displayer;

/**
 * �����ļ���ʾ��
 * 
 * @author ����ΰ
 * 
 * ����ʱ�䣺2008-3-19
 */
public class PropertiesDemo {

	/** �����ļ��� */
	private static final String SETTINGS_FILE_NAME = "files/setting/settings.properties";
	/** Ĭ������ */
	private static Properties defaultSetting = getDefaultSettings();

	/**
	 * ���������ļ�
	 */
	private static void createSettingFile(String fileName) {
		try {
			FileOutputStream out = new FileOutputStream(fileName);
			// ��������Ϣ�־û��������ļ���(�ڶ����������ļ��е�ע��)
			defaultSetting.store(out, "color properties");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static JComponent getPanel() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("�Զ���������ʾ");
		// ����Ĭ�����������µ����ԣ��������õ�����ֵΪ��ʱ������Ĭ��ֵ
		Properties settings = new Properties(defaultSetting);
		try {
			FileInputStream in = new FileInputStream(SETTINGS_FILE_NAME);
			// ���������ļ���Ϣ
			settings.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ȡ�������Զ�Ӧ��ֵ
		int red = Integer.parseInt(settings.getProperty("color.red"));
		int green = Integer.parseInt(settings.getProperty("color.green"));
		int blue = Integer.parseInt(settings.getProperty("color.blue"));
		Color color = new Color(red, green, blue);
		label.setForeground(color);
		panel.add(label);
		return panel;
	}

	private static Properties getDefaultSettings() {
		Properties defaultSettings = new Properties();
		defaultSettings.put("color.red", "50");
		defaultSettings.put("color.green", "50");
		defaultSettings.put("color.blue", "50");
		return defaultSettings;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File file = new File(SETTINGS_FILE_NAME);
		if (!file.exists()) {
			createSettingFile(SETTINGS_FILE_NAME);
		}
		Displayer.createAndShowGUI("Properties Demo", getPanel());
	}
}