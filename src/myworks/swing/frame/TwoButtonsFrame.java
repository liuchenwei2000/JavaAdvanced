/**
 * 
 */
package swing.frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import util.ui.ComponentFactory;

/**
 * 有"确定"、"取消"两个按钮的JFrame
 * 
 * @author 刘晨伟
 * 
 * 创建时间：2008-9-11
 */
public abstract class TwoButtonsFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3530466648550171765L;

	private JPanel centerPanel;// 对话框直接加载的面板

	private JPanel editorPanel;// 编辑面板
	private JPanel buttonPanel;// 按钮面板

	private JButton okButton;// 确定按钮
	private JButton cancelButton;// 取消按钮

	protected TwoButtonsFrameEHD eventHandler;// 事件处理器

	/**
	 * 有"确定"、"取消"两个按钮的JFrame
	 * 
	 * @param title
	 *            标题
	 */
	public TwoButtonsFrame(String title) {
		super(title);
		initUI();
	}

	private void initUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		this.add(getCenterPanel());
	}

	/**
	 * @return 对话框直接加载的面板
	 */
	protected JPanel getCenterPanel() {
		if (centerPanel == null) {
			centerPanel = new JPanel();
			centerPanel.setLayout(new BorderLayout());
			centerPanel.add(getEditorPanel(), BorderLayout.CENTER);
			centerPanel.add(getButtonPanel(), BorderLayout.SOUTH);
		}
		return centerPanel;
	}

	/**
	 * @return 信息编辑面板
	 */
	protected JPanel getEditorPanel() {
		if (editorPanel == null) {
			editorPanel = new JPanel();
			addComponentsToEditorPanel();
		}
		return editorPanel;
	}

	/**
	 * 向信息编辑面板添加组件
	 */
	protected abstract void addComponentsToEditorPanel();

	/**
	 * @return 按钮面板
	 */
	private JPanel getButtonPanel() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			buttonPanel.add(getOKButton());
			buttonPanel.add(getCancelButton());
		}
		return buttonPanel;
	}

	/**
	 * @return 确定 按钮
	 */
	protected JButton getOKButton() {
		if (okButton == null) {
			okButton = ComponentFactory.createCommonButton("确 定");
		}
		return okButton;
	}

	/**
	 * @return 取消 按钮
	 */
	protected JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = ComponentFactory.createCommonButton("取 消");
		}
		return cancelButton;
	}

	/**
	 * @param eventHandler
	 *            事件处理器对象
	 */
	protected void setEventHandler(TwoButtonsFrameEHD eventHandler) {
		this.eventHandler = eventHandler;
		addActionListener(getEventHandler());
	}

	/**
	 * 为按钮添加监听
	 */
	private void addActionListener(ActionListener l) {
		getOKButton().addActionListener(l);
		getCancelButton().addActionListener(l);
	}

	/**
	 * @return 事件处理器
	 */
	protected TwoButtonsFrameEHD getEventHandler() {
		return eventHandler;
	}
}
