/**
 * 
 */
package awt.event.more;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

/**
 * UI���¼�������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2007-8-24
 */
public class UIEventHandler {

	// ʵ������Ϊ������UI
	private UI ui;

	/**
	 * ���캯����UI�����ʵ������ͨ��
	 */
	public UIEventHandler(UI ui) {
		this.ui = ui;
	}

	public void doButtonAction(ActionEvent e) {
		JOptionPane.showMessageDialog(ui, "���Գɹ�");
	}
}