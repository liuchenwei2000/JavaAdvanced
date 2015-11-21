/**
 * 
 */
package swing.frame;

import javax.swing.JFrame;

import util.Displayer;

/**
 * 基本Frame
 * <p>
 * JFrame的常用方法：
 * <p>
 * <li>dispose方法用于关闭窗口，并回收创建窗口所使用的全部系统资源。
 * <li>setIconImage方法用于将窗口最小化时的图标设置为指定的image对象。
 * <li>setResizable方法利用一个boolean值确定框架的大小是否允许用户改变。
 * <li>setLocation方法可以重定位组件的位置。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-2-25
 */
public class BasicFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4678152379795505698L;

	public BasicFrame() {
		super();
		initUI();
	}

	public BasicFrame(String title) {
		super(title);
		initUI();
	}

	private void initUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * 默认为居中显示
	 */
	public void setVisible(boolean b) {
		pack();
		Displayer.setCenter(this);
		super.setVisible(b);
	}
}
