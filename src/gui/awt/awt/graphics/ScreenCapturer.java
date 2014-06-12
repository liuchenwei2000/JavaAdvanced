/**
 * 
 */
package awt.graphics;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * 截屏器
 * 
 * @author 刘晨伟
 *
 * 创建日期：2009-5-22
 */
public class ScreenCapturer {

	/**
	 * 截屏后将图片存到指定文件
	 * 
	 * @param fileName
	 *            截屏图片输出文件名
	 * @throws Exception
	 */
	public static void captureScreen(String fileName) throws Exception {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle screenRectangle = new Rectangle(screenSize);
		Robot robot = new Robot();
		BufferedImage image = robot.createScreenCapture(screenRectangle);
		ImageIO.write(image, "png", new File(fileName));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ScreenCapturer.captureScreen("images/gui.graphics/capturer.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}