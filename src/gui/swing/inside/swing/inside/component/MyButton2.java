/**
 * 
 */
package swing.inside.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.border.Border;

/**
 * �Զ��尴ť2
 * <p>
 * �Ľ�����Զ��尴ť����Ҫ�����ڻ�������ϡ�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2011-2-24
 */
public class MyButton2 extends JComponent implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3471383034498422967L;

	private boolean isPressed;// �Ƿ񱻰���
	
	// �¼��������б�����Լ������¼�������
	private List<ActionListener> actionListeners = new ArrayList<ActionListener>();
	
	public MyButton2(){
		super();
		setBorder(new RedLineBorder());
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
	 * ��дpaintComponent������ɶ����������ػ�
	 *
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 * 
	 * ������JComponent.paintComponent(java.awt.Graphics)��ʵ�֣�
	 
	 protected void paintComponent(Graphics g) {
        if (ui != null) {
            Graphics scratchGraphics = (g == null) ? null : g.create();
            try {
                ui.update(scratchGraphics, this);
            }
            finally {
                scratchGraphics.dispose();
            }
        }
    }
     * ����������ȼ������Ƿ�װ��UI Delegate�������װ�˾ͽ���Ⱦ���̴����UI Delegate�������Ƕ��Ƥ���ĵط���
     * 
     * ������JComponent��Ӧ��UI Delegate��update������Ĭ��ʵ�֣�
     
     public void update(Graphics g, JComponent c) {
	   if (c.isOpaque()) {
	    g.setColor(c.getBackground());
	    g.fillRect(0, 0, c.getWidth(),c.getHeight());
	   }
	   paint(g, c);
     }
     
     * �����ж�JComponent�Ƿ񱳾�͸���ģ������͸����ʹ�ñ���ɫ��������������
     * Ȼ�����paint(g, c)��������������LookAndFeel�µ���Ⱦ��
     * ���Կ�������͸�����ƺ��л�Ƥ��������ʵ�֡�
	 */
	public void paintComponent(Graphics g) {
		// ʵ�ֱ���͸������
		if (isOpaque()) {
			g.setColor(getBackground());
			g.fillRect(0, 0, getWidth(), getHeight());
		}
		if (isPressed) {
			// ������ť������ʱ������
		} else {
			// ������ťû������ʱ������
		}
	}
	
	/**
	 * paintBorder��˼�ǻ�������ı߿�
	 * Swing����������б߿�ĸ������˵����Ϊ�κ������Ӹ��ֱ߿򣬰����Զ���߿�
	 * 
	 * @see javax.swing.JComponent#paintBorder(java.awt.Graphics)
	 * 
	 * ������JComponent.paintBorder(java.awt.Graphics)��ʵ�֣�
	 
	 protected void paintBorder(Graphics g) {
        Border border = getBorder();
        if (border != null) {
            border.paintBorder(this, g, 0, 0, getWidth(), getHeight());
        }
     }
	 
	 * �ǳ�ֱ�ӣ��������б߿��򽫻��߿�������������border��
	 * ���Ҫ�Զ���߿���Ҫʵ��javax.swing.border.Border�ӿڡ�
	 */
	protected void paintBorder(Graphics g) {
		super.paintBorder(g);
	}
	
	/**
	 * �Զ���һ�����߱߿�
	 */
	static class RedLineBorder implements Border {

		/**
		 * ��������߿�
		 * 
		 * @see javax.swing.border.Border#paintBorder(java.awt.Component, java.awt.Graphics, int, int, int, int)
		 */
		public void paintBorder(Component c, Graphics g, int x, int y,
				int width, int height) {
			g.setColor(Color.RED);
			g.drawRect(x, y, width, height);// �������߱߿�
		}

		/**
		 * ����߿�߽�
		 * 
		 * @see javax.swing.border.Border#getBorderInsets(java.awt.Component)
		 */
		public Insets getBorderInsets(Component c) {
			return new Insets(1, 1, 1, 1);
		}

		/**
		 * �߿�ı����ǲ���͸����
		 * 
		 * <li>����͸����Ҫ���𻭳��߿�Ķ�������
		 * <li>��͸����ʹ������ı�����
		 * 
		 * @see javax.swing.border.Border#isBorderOpaque()
		 */
		public boolean isBorderOpaque() {
			return false;// ����͸��
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