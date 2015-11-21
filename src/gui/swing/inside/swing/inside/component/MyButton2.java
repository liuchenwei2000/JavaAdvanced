/**
 * 
 */
package swing.inside.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.border.Border;

/**
 * 自定义按钮2
 * <p>
 * 改进版的自定义按钮，主要体现在绘制组件上。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2011-2-24
 */
public class MyButton2 extends JComponent implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3471383034498422967L;

	private boolean isPressed;// 是否被按下
	
	// 事件监听器列表，组件自己管理事件监听器
	private List<ActionListener> actionListeners = new ArrayList<ActionListener>();
	
	public MyButton2(){
		super();
		setBorder(new RedLineBorder());
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
	 * 重写paintComponent方法完成对组件自身的重画
	 *
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 * 
	 * 下面是JComponent.paintComponent(java.awt.Graphics)的实现：
	 
	 protected void paintComponent(Graphics g) {
        if (ui != null) {
            Graphics scratchGraphics = (g == null) ? null : g.create();
            try {
                ui.update(scratchGraphics, this);
            }
            finally {
                scratchGraphics.dispose();
            }
        }
    }
     * 这个方法首先检测组件是否安装了UI Delegate，如果安装了就将渲染过程代理给UI Delegate，这儿是嵌入皮肤的地方。
     * 
     * 下面是JComponent对应的UI Delegate的update方法的默认实现：
     
     public void update(Graphics g, JComponent c) {
	   if (c.isOpaque()) {
	    g.setColor(c.getBackground());
	    g.fillRect(0, 0, c.getWidth(),c.getHeight());
	   }
	   paint(g, c);
     }
     
     * 首先判断JComponent是否背景透明的，如果不透明则使用背景色填充整个组件区域；
     * 然后调用paint(g, c)来完成组件在这种LookAndFeel下的渲染。
     * 可以看到背景透明机制和切换皮肤在这里实现。
	 */
	public void paintComponent(Graphics g) {
		// 实现背景透明机制
		if (isOpaque()) {
			g.setColor(getBackground());
			g.fillRect(0, 0, getWidth(), getHeight());
		}
		if (isPressed) {
			// 画出按钮被按下时的样子
		} else {
			// 画出按钮没被按下时的样子
		}
	}
	
	/**
	 * paintBorder意思是画出组件的边框。
	 * Swing所有组件都有边框的概念，就是说可以为任何组件添加各种边框，包括自定义边框。
	 * 
	 * @see javax.swing.JComponent#paintBorder(java.awt.Graphics)
	 * 
	 * 下面是JComponent.paintBorder(java.awt.Graphics)的实现：
	 
	 protected void paintBorder(Graphics g) {
        Border border = getBorder();
        if (border != null) {
            border.paintBorder(this, g, 0, 0, getWidth(), getHeight());
        }
     }
	 
	 * 非常直接，如果组件有边框，则将画边框的任务代理给这个border。
	 * 如果要自定义边框，需要实现javax.swing.border.Border接口。
	 */
	protected void paintBorder(Graphics g) {
		super.paintBorder(g);
	}
	
	/**
	 * 自定义一个红线边框
	 */
	static class RedLineBorder implements Border {

		/**
		 * 画出组件边框
		 * 
		 * @see javax.swing.border.Border#paintBorder(java.awt.Component, java.awt.Graphics, int, int, int, int)
		 */
		public void paintBorder(Component c, Graphics g, int x, int y,
				int width, int height) {
			g.setColor(Color.RED);
			g.drawRect(x, y, width, height);// 画出红线边框
		}

		/**
		 * 定义边框边界
		 * 
		 * @see javax.swing.border.Border#getBorderInsets(java.awt.Component)
		 */
		public Insets getBorderInsets(Component c) {
			return new Insets(1, 1, 1, 1);
		}

		/**
		 * 边框的背景是不是透明的
		 * 
		 * <li>不是透明的要负责画出边框的而背景。
		 * <li>是透明的使用组件的背景。
		 * 
		 * @see javax.swing.border.Border#isBorderOpaque()
		 */
		public boolean isBorderOpaque() {
			return false;// 背景透明
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
