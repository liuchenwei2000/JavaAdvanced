/**
 * 
 */
package bean;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

/**
 * ��JavaBean��ʾ
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-6-17
 */
public class Frog {
	
	/** ���е��ֶζ���˽�еģ�ֻ��ͨ���������ܹ����� */
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
	 * ����"add"��"remove"��������ؼ��������������Կ��������Bean��������¼���ActionEvent��KeyEvent��
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
	 *  ԭ����������Ҳ��Bean��һ���֣�������Ϊ����public�ģ���������Ϊ��Ҳ��ѭ���κ���������
	 */
	public void croak() {
		System.out.println("Ribbet!");
	}
}

class Spots {
}