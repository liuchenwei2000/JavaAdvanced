/**
 * 
 */
package awt.layout.box;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import util.Displayer;

/**
 * BoxLayout演示
 * <p>
 * BoxLayout允许纵向或横向布置多个组件的布局管理器，
 * 并且通过所谓的支架和胶水(struts and glue)的机制来控制组件的间隔。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2007-8-21
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
    	// 横向排列组件
    	BoxLayout boxH = new BoxLayout(hPanel,BoxLayout.X_AXIS);
    	hPanel.setLayout(boxH);
    	addButtons2Panel(hPanel);
    	
    	JPanel vPanel = new JPanel();
    	// 纵向排列组件
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
