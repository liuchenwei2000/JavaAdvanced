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
 * 配置文件演示类
 * 
 * @author 刘晨伟
 * 
 * 创建时间：2008-3-19
 */
public class PropertiesDemo {

	/** 配置文件名 */
	private static final String SETTINGS_FILE_NAME = "files/setting/settings.properties";
	/** 默认配置 */
	private static Properties defaultSetting = getDefaultSettings();

	/**
	 * 生成配置文件
	 */
	private static void createSettingFile(String fileName) {
		try {
			FileOutputStream out = new FileOutputStream(fileName);
			// 将配置信息持久化到本地文件里(第二个参数是文件中的注释)
			defaultSetting.store(out, "color properties");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static JComponent getPanel() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("自定义配置演示");
		// 根据默认配置生成新的属性，当新配置的属性值为空时就是用默认值
		Properties settings = new Properties(defaultSetting);
		try {
			FileInputStream in = new FileInputStream(SETTINGS_FILE_NAME);
			// 加载配置文件信息
			settings.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 读取各个属性对应的值
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
