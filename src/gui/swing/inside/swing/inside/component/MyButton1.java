/**
 * 
 */
package swing.inside.component;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

/**
 * 自定义按钮1
 * <p>
 * 在这里不考虑Model和UI Delegate的分离，所以按钮状态和外观行为都被推倒了本类中(Component)实现。
 * 实际上这是违反MVC原则的。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2011-2-24
 */
public class MyButton1 extends JComponent implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3471383034498422967L;

	private boolean isPressed;// 是否被按下
	
	// 事件监听器列表，组件自己管理事件监听器
	private List<ActionListener> actionListeners = new ArrayList<ActionListener>();
	
	public MyButton1(){
		super();
		addMouseListener(this);
	}
	
	/** 增加和删除事件监听器 */
	
	public void addActionListener(ActionListener l){
		actionListeners.add(l);
	}
	
	public void rmoveActionListener(ActionListener l){
		actionListeners.remove(l);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		setPressed(true);
	}

	private void fireActionPerformed(ActionEvent e) {
		for (ActionListener listener : actionListeners) {
			listener.actionPerformed(e);
		}
	}

	/**
	 * 重写paint方法，实现根据按钮状态的重画
	 *
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		if(isPressed){
			// 画出按钮被按下时的样子
		}else {
			// 画出按钮没被按下时的样子
		}
	}
	
	/** 下面两个是访问组件属性的方法  */
	
	public boolean isPressed() {
		return isPressed;
	}

	public void setPressed(boolean isPressed) {
		this.isPressed = isPressed;// 属性发生了变化
		repaint();// 通知按钮重画
		fireActionPerformed(new ActionEvent(this, 1, null));// 触发按钮动作事件
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}