/**
 * 
 */
package swing.other;

import java.awt.GridLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.Displayer;
import util.ui.BorderFactory;

/**
 * JLabel��ǩ��ʾ��
 * <p>
 * <li>������ʾ�ı���ͼ���ͬʱ��ʾ���ߡ�
 * <li>����ͨ�����ô�ֱ��ˮƽ���뷽ʽ��ָ����ǩ��ʾ���б�ǩ�����ںδ����롣
 * <li>Ĭ������£���ǩ������ʾ���ڴ�ֱ���ж��롣
 * <li>Ĭ������£�ֻ��ʾ�ı��ı�ǩ�ǿ�ʼ�߶��룬��ֻ��ʾͼ��ı�ǩ��ˮƽ���ж��� ��
 * 
 * @author ����ΰ
 *
 * �������ڣ�2009-5-27
 */
public class JLabelDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JLabel Demo", new JLabelPanel());
	}
}

class JLabelPanel extends JPanel {

	private static final long serialVersionUID = 2172877046353384823L;

	public JLabelPanel(){
		setLayout(new GridLayout(6, 1));
		add(makeLabel("LEFT", JLabel.LEFT));
		add(makeLabel("RIGHT", JLabel.RIGHT));
		add(makeLabel("CENTER", JLabel.CENTER));
		add(makeLabel("LEADING", JLabel.LEADING));
		add(makeLabel("TRAILING", JLabel.TRAILING));
		add(makeLabel("Loading", new ImageIcon("images/gui.swing.other/loading.gif")));
	}
	
	/**
	 * ���ݲ���������ǩ
	 * 
	 * @param name
	 *            ��ǩ����
	 * @param horizontalAlignment
	 *            ��ǩ����ˮƽ���뷽ʽ
	 * @see javax.swing.SwingConstants.LEFT
	 *      javax.swing.SwingConstants.CENTER
	 *      javax.swing.SwingConstants.RIGHT
	 *      javax.swing.SwingConstants.LEADING
	 *      javax.swing.SwingConstants.TRAILING
	 */
	private static JLabel makeLabel(String name, int horizontalAlignment) {
		JLabel label = new JLabel(name, horizontalAlignment);
		label.setBorder(BorderFactory.createLineBorder());
		return label;
	}
	
	/**
	 * ���ݲ���������ǩ
	 * 
	 * @param name
	 *            ��ǩ����
	 * @param icon
	 *            ��ǩͼ��
	 */
	private static JLabel makeLabel(String name, Icon icon) {
		JLabel label = new JLabel(name, icon, JLabel.CENTER);
		label.setBorder(BorderFactory.createLineBorder());
		return label;
	}
}