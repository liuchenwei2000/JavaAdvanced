/**
 * 
 */
package swing.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import util.ui.DialogManager;

/**
 * ��"ȷ��"��"ȡ��"������ť��JFrame�¼�������
 * 
 * @author ����ΰ
 * 
 * ����ʱ�䣺2008-9-16
 */
public abstract class TwoButtonsFrameEHD implements ActionListener {

	private TwoButtonsFrame frame;// ������JFrame

	/**
	 * ��"ȷ��"��"ȡ��"������ť��JFrame�¼�������
	 * 
	 * @param frame
	 *            ������JFrame
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
	 * JFrame�������쳣�ķ�ʽ
	 */
	private void handleException(Exception e) {
		DialogManager.showErrorDialog(e.getMessage());
	}

	/**
	 * ȷ�� ��ť���¼�����
	 */
	protected abstract void doOKAction() throws Exception;

	/**
	 * ȡ�� ��ť���¼�����
	 */
	protected void doCancelAction() throws Exception {
		getFrame().dispose();
	}

	protected TwoButtonsFrame getFrame() {
		return frame;
	}
}