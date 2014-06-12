/**
 * 
 */
package util.ui;

import java.awt.Color;

import javax.swing.border.LineBorder;

/**
 * Border����
 *
 * @author ����ΰ
 *
 * ����ʱ�䣺2008-10-9
 */
public class BorderFactory {

	/** ���޵���ɫ */
	private static final Color FRESH = new Color(0X7f9db9);

	private BorderFactory() {
		// no instance
	}

	/**
	 * ʹ����������ɫ��LineBorder
	 */
	public static LineBorder createLineBorder() {
		return new LineBorder(FRESH);
	}
}