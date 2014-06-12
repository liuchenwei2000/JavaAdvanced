/**
 * 
 */
package bean;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

/**
 * 简单JavaBean演示
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-6-17
 */
public class Frog {
	
	/** 所有的字段都是私有的，只能通过方法才能够访问 */
	private int jumps;
	private Color color;
	private Spots spots;
	private boolean jmpr;

	public int getJumps() {
		return jumps;
	}

	public void setJumps(int newJumps) {
		this.jumps = newJumps;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color newColor) {
		this.color = newColor;
	}

	public Spots getSpots() {
		return spots;
	}

	public void setSpots(Spots newSpots) {
		this.spots = newSpots;
	}

	public boolean isJumper() {
		return jmpr;
	}

	public void setJumper(boolean j) {
		this.jmpr = j;
	}

	/**
	 * 根据"add"和"remove"方法对相关监听器的命名可以看出：这个Bean所处理的事件是ActionEvent和KeyEvent。
	 */
	public void addActionListener(ActionListener l) {
		// ...
	}

	public void removeActionListener(ActionListener l) {
		// ...
	}

	public void addKeyListener(KeyListener l) {
		// ...
	}

	public void removeKeyListener(KeyListener l) {
		// ...
	}

	/**
	 *  原生公共方法也是Bean的一部分，这是因为它是public的，而不是因为它也遵循了任何命名规则。
	 */
	public void croak() {
		System.out.println("Ribbet!");
	}
}

class Spots {
}