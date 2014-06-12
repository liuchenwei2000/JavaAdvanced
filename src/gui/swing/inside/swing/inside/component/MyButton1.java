/**
 * 
 */
package swing.inside.component;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

/**
 * �Զ��尴ť1
 * <p>
 * �����ﲻ����Model��UI Delegate�ķ��룬���԰�ť״̬�������Ϊ�����Ƶ��˱�����(Component)ʵ�֡�
 * ʵ��������Υ��MVCԭ��ġ�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2011-2-24
 */
public class MyButton1 extends JComponent implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3471383034498422967L;

	private boolean isPressed;// �Ƿ񱻰���
	
	// �¼��������б�����Լ������¼�������
	private List<ActionListener> actionListeners = new ArrayList<ActionListener>();
	
	public MyButton1(){
		super();
		addMouseListener(this);
	}
	
	/** ���Ӻ�ɾ���¼������� */
	
	public void addActionListener(ActionListener l){
		actionListeners.add(l);
	}
	
	public void rmoveActionListener(ActionListener l){
		actionListeners.remove(l);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		setPressed(true);
	}

	private void fireActionPerformed(ActionEvent e) {
		for (ActionListener listener : actionListeners) {
			listener.actionPerformed(e);
		}
	}

	/**
	 * ��дpaint������ʵ�ָ��ݰ�ť״̬���ػ�
	 *
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		if(isPressed){
			// ������ť������ʱ������
		}else {
			// ������ťû������ʱ������
		}
	}
	
	/** ���������Ƿ���������Եķ���  */
	
	public boolean isPressed() {
		return isPressed;
	}

	public void setPressed(boolean isPressed) {
		this.isPressed = isPressed;// ���Է����˱仯
		repaint();// ֪ͨ��ť�ػ�
		fireActionPerformed(new ActionEvent(this, 1, null));// ������ť�����¼�
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}