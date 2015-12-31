package swing.filechooser;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import util.Displayer;

/**
 * JColorChooser演示类
 * <p>
 * JColorChooser是一个组件而不是一个对话框。
 * <p>
 * 本例提供了三种使用JColorChooser创建对话框的方式。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-7-3
 */
public class JColorChooserDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JColorChooser Demo",
				new JColorChooserPanel());
	}
}

class JColorChooserPanel extends JPanel {

	private static final long serialVersionUID = -3872067960320753711L;

	private JButton modalButton;
	private JButton modelessButton;
	private JButton immediateButton;
	
	public JColorChooserPanel() {
		setPreferredSize(new Dimension(300, 200));
		add(getModalButton());
		add(getModelessButton());
		add(getImmediateButton());
	}

	private JButton getModalButton() {
		if (modalButton == null) {
			modalButton = new JButton("Modal");
			modalButton.addActionListener(new ModalListener());
		}
		return modalButton;
	}

	private JButton getModelessButton() {
		if (modelessButton == null) {
			modelessButton = new JButton("Modeless");
			modelessButton.addActionListener(new ModelessListener());
		}
		return modelessButton;
	}

	private JButton getImmediateButton() {
		if (immediateButton == null) {
			immediateButton = new JButton("Immediate");
			immediateButton.addActionListener(new ImmediateListener());
		}
		return immediateButton;
	}
	
	/**
	 * 创建一个模态(modal)的颜色选择器
	 */
	private class ModalListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			Color defaultColor = getBackground();
			// 显示有模式的颜色选取器对话框
			Color selected = JColorChooser.showDialog(JColorChooserPanel.this,
					"Set background", defaultColor);
			setBackground(selected);
		}
	}

	/**
	 * 创建一个非模态(modeless)的颜色选择器
	 */
	private class ModelessListener implements ActionListener {

		private JDialog dialog;
		private JColorChooser chooser;

		public ModelessListener() {
			// do nothing
		}

		private JDialog getDialog() {
			if (dialog == null) {
				/*
				 * 创建并返回包含指定 JColorChooser 窗格及 "OK"、"Cancel"、"Reset" 按钮的新对话框
				 * 如果按下 "OK" 或 "Cancel" 按钮，则对话框自动隐藏(但未释放)
				 * 如果按下 "Reset" 按钮，则将颜色选取器的颜色重置为上一次在对话框上
				 * 调用 setVisible 时设置的颜色，并且对话框仍然显示
				 * 
				 */
				dialog = JColorChooser.createDialog(JColorChooserPanel.this,
						"Background Color", false /* 设置为非模态 */,
						getChooser(), new ActionListener() // OK 按钮监听器
						{
							public void actionPerformed(ActionEvent event) {
								setBackground(getChooser().getColor());
							}
						}, null /* Cancel 按钮监听器 */);
			}
			return dialog;
		}

		private JColorChooser getChooser() {
			if (chooser == null) {
				chooser = new JColorChooser();
			}
			return chooser;
		}

		public void actionPerformed(ActionEvent event) {
			// 设置颜色选择器的当前颜色
			getChooser().setColor(getBackground());
			getDialog().setVisible(true);
		}
	}

	/**
	 * 当JColorChooser上进行操作时，一旦选择变更就会做出立即反应
	 */
	private class ImmediateListener implements ActionListener {

		private JDialog dialog;
		private JColorChooser chooser;

		public ImmediateListener() {
			// do nothing
		}

		private JDialog getDialog() {
			if (dialog == null) {
				dialog = new JDialog((Frame) null, false /* 非模态 */);
				dialog.getContentPane().add(getChooser());
				dialog.pack();
			}
			return dialog;
		}

		private JColorChooser getChooser() {
			if (chooser == null) {
				chooser = new JColorChooser();
				// 为选择器的选择模型添加监听器
				chooser.getSelectionModel().addChangeListener(
						new ChangeListener() {

							public void stateChanged(ChangeEvent event) {
								setBackground(chooser.getColor());
							}
						});
			}
			return chooser;
		}

		public void actionPerformed(ActionEvent event) {
			getChooser().setColor(getBackground());
			getDialog().setVisible(true);
		}
	}
}
