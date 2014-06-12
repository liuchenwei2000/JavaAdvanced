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
 * JColorChooser��ʾ��
 * <p>
 * JColorChooser��һ�����������һ���Ի���
 * <p>
 * �����ṩ������ʹ��JColorChooser�����Ի���ķ�ʽ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-7-3
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
	 * ����һ��ģ̬(modal)����ɫѡ����
	 */
	private class ModalListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			Color defaultColor = getBackground();
			// ��ʾ��ģʽ����ɫѡȡ���Ի���
			Color selected = JColorChooser.showDialog(JColorChooserPanel.this,
					"Set background", defaultColor);
			setBackground(selected);
		}
	}

	/**
	 * ����һ����ģ̬(modeless)����ɫѡ����
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
				 * ���������ذ���ָ�� JColorChooser ���� "OK"��"Cancel"��"Reset" ��ť���¶Ի���
				 * ������� "OK" �� "Cancel" ��ť����Ի����Զ�����(��δ�ͷ�)
				 * ������� "Reset" ��ť������ɫѡȡ������ɫ����Ϊ��һ���ڶԻ�����
				 * ���� setVisible ʱ���õ���ɫ�����ҶԻ�����Ȼ��ʾ
				 * 
				 */
				dialog = JColorChooser.createDialog(JColorChooserPanel.this,
						"Background Color", false /* ����Ϊ��ģ̬ */,
						getChooser(), new ActionListener() // OK ��ť������
						{
							public void actionPerformed(ActionEvent event) {
								setBackground(getChooser().getColor());
							}
						}, null /* Cancel ��ť������ */);
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
			// ������ɫѡ�����ĵ�ǰ��ɫ
			getChooser().setColor(getBackground());
			getDialog().setVisible(true);
		}
	}

	/**
	 * ��JColorChooser�Ͻ��в���ʱ��һ��ѡ�����ͻ�����������Ӧ
	 */
	private class ImmediateListener implements ActionListener {

		private JDialog dialog;
		private JColorChooser chooser;

		public ImmediateListener() {
			// do nothing
		}

		private JDialog getDialog() {
			if (dialog == null) {
				dialog = new JDialog((Frame) null, false /* ��ģ̬ */);
				dialog.getContentPane().add(getChooser());
				dialog.pack();
			}
			return dialog;
		}

		private JColorChooser getChooser() {
			if (chooser == null) {
				chooser = new JColorChooser();
				// Ϊѡ������ѡ��ģ����Ӽ�����
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