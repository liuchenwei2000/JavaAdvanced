/**
 * 
 */
package util.ui;

import java.awt.Color;

import javax.swing.border.LineBorder;

/**
 * Border工厂
 *
 * @author 刘晨伟
 *
 * 创建时间：2008-10-9
 */
public class BorderFactory {

	/** 鲜艳的颜色 */
	private static final Color FRESH = new Color(0X7f9db9);

	private BorderFactory() {
		// no instance
	}

	/**
	 * 使用了鲜艳颜色的LineBorder
	 */
	public static LineBorder createLineBorder() {
		return new LineBorder(FRESH);
	}
}