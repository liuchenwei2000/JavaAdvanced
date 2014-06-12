/**
 * 
 */
package awt.layout.box;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import util.Displayer;

/**
 * BoxLayout��ʾ
 * <p>
 * BoxLayout�������������ö������Ĳ��ֹ�������
 * ����ͨ����ν��֧�ܺͽ�ˮ(struts and glue)�Ļ�������������ļ����
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2007-8-21
 */
public class BoxLayoutDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("BoxLayout Demo", new BoxLayoutPanel());
	}
}

class BoxLayoutPanel extends JPanel {

	private static final long serialVersionUID = -1473566036646456811L;
   
	public BoxLayoutPanel(){
    	super();
    	JPanel hPanel = new JPanel();
    	// �����������
    	BoxLayout boxH = new BoxLayout(hPanel,BoxLayout.X_AXIS);
    	hPanel.setLayout(boxH);
    	addButtons2Panel(hPanel);
    	
    	JPanel vPanel = new JPanel();
    	// �����������
		BoxLayout boxV = new BoxLayout(vPanel,BoxLayout.Y_AXIS);
		vPanel.setLayout(boxV);
    	addButtons2Panel(vPanel);
    	
		add(hPanel);
		add(vPanel);
    }
	
	private static void addButtons2Panel(JPanel panel){
		panel.add(new JButton("one"));
		panel.add(new JButton("two"));
		panel.add(new JButton("three"));
		panel.add(new JButton("four"));
		panel.add(new JButton("five"));
	}
}