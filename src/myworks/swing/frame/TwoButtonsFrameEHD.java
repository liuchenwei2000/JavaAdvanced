/**
 * 
 */
package swing.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import util.ui.DialogManager;

/**
 * 有"确定"、"取消"两个按钮的JFrame事件处理器
 * 
 * @author 刘晨伟
 * 
 * 创建时间：2008-9-16
 */
public abstract class TwoButtonsFrameEHD implements ActionListener {

	private TwoButtonsFrame frame;// 关联的JFrame

	/**
	 * 有"确定"、"取消"两个按钮的JFrame事件处理器
	 * 
	 * @param frame
	 *            关联的JFrame
	 */
	public TwoButtonsFrameEHD(TwoButtonsFrame frame) {
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource().equals(getFrame().getOKButton())) {
				doOKAction();
			} else if (e.getSource().equals(getFrame().getCancelButton())) {
				doCancelAction();
			}
		} catch (Exception ex) {
			handleException(ex);
		}
	}

	/**
	 * JFrame自身处理异常的方式
	 */
	private void handleException(Exception e) {
		DialogManager.showErrorDialog(e.getMessage());
	}

	/**
	 * 确定 按钮的事件处理
	 */
	protected abstract void doOKAction() throws Exception;

	/**
	 * 取消 按钮的事件处理
	 */
	protected void doCancelAction() throws Exception {
		getFrame().dispose();
	}

	protected TwoButtonsFrame getFrame() {
		return frame;
	}
}