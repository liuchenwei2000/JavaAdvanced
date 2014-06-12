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
 * ��"ȷ��"��"ȡ��"������ť��JFrame
 * 
 * @author ����ΰ
 * 
 * ����ʱ�䣺2008-9-11
 */
public abstract class TwoButtonsFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3530466648550171765L;

	private JPanel centerPanel;// �Ի���ֱ�Ӽ��ص����

	private JPanel editorPanel;// �༭���
	private JPanel buttonPanel;// ��ť���

	private JButton okButton;// ȷ����ť
	private JButton cancelButton;// ȡ����ť

	protected TwoButtonsFrameEHD eventHandler;// �¼�������

	/**
	 * ��"ȷ��"��"ȡ��"������ť��JFrame
	 * 
	 * @param title
	 *            ����
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
	 * @return �Ի���ֱ�Ӽ��ص����
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
	 * @return ��Ϣ�༭���
	 */
	protected JPanel getEditorPanel() {
		if (editorPanel == null) {
			editorPanel = new JPanel();
			addComponentsToEditorPanel();
		}
		return editorPanel;
	}

	/**
	 * ����Ϣ�༭���������
	 */
	protected abstract void addComponentsToEditorPanel();

	/**
	 * @return ��ť���
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
	 * @return ȷ�� ��ť
	 */
	protected JButton getOKButton() {
		if (okButton == null) {
			okButton = ComponentFactory.createCommonButton("ȷ ��");
		}
		return okButton;
	}

	/**
	 * @return ȡ�� ��ť
	 */
	protected JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = ComponentFactory.createCommonButton("ȡ ��");
		}
		return cancelButton;
	}

	/**
	 * @param eventHandler
	 *            �¼�����������
	 */
	protected void setEventHandler(TwoButtonsFrameEHD eventHandler) {
		this.eventHandler = eventHandler;
		addActionListener(getEventHandler());
	}

	/**
	 * Ϊ��ť��Ӽ���
	 */
	private void addActionListener(ActionListener l) {
		getOKButton().addActionListener(l);
		getCancelButton().addActionListener(l);
	}

	/**
	 * @return �¼�������
	 */
	protected TwoButtonsFrameEHD getEventHandler() {
		return eventHandler;
	}
}