/**
 * 
 */
package swing.list;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import util.Displayer;

/**
 * ListModel演示类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2007-8-29
 */
public class ListModelDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("List Model Demo", new ListModelPanel());
	}
}

class ListModelPanel extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5546410946145193007L;

	private DefaultListModel model = new DefaultListModel();
	private JList list = new JList(model);
	private JButton deleteButton = new JButton("delete");
	private JButton addButton = new JButton("add");
	private JTextField text = new JTextField("show selection!");
	
	public ListModelPanel(){
		setLayout(new BorderLayout());
		// 将指定值添加到表模型的末尾
		model.addElement("One");
		model.addElement("Two");
		model.addElement("Three");
		model.addElement("Four");
		model.addElement("Five");

		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setVisibleRowCount(3);
		
		JScrollPane scroll = new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(150, 80));
		add(scroll, BorderLayout.CENTER);
		
		deleteButton.addActionListener(this);	
		add(deleteButton, BorderLayout.EAST);	
		
		addButton.addActionListener(this);	
		add(addButton, BorderLayout.WEST);
		
		text.setBounds(0, 0, 150, 20);
		text.setEditable(false);
		add(text, BorderLayout.NORTH);		
	}

	/**
	 * 按钮点击的事件处理
	 */
	public void actionPerformed(ActionEvent e) {
		// 实际上返回的就是 成员变量 model
		DefaultListModel listModel = (DefaultListModel) list.getModel();
		// delete
		if (e.getSource().equals(deleteButton)) {
			// 当前选择项的索引
			int index = list.getSelectedIndex();
			text.setText("delete " + listModel.getElementAt(index));
			// 下面的语句完成相同的操作
			// text.setText("delete" + getList().getSelectedValue());
			// 在列表模型上删除这个选项(列表会根据模型重新绘制)
			listModel.remove(index);
			// 从列表中移除参数对象的第一个(索引最小)的匹配项
			// listModel.removeElement(list.getSelectedValue());
			// 设置默认选择项
			if (index < listModel.getSize()) {
				// 删除的不是最后一个选项则向下选择
				list.setSelectedIndex(index);
			} else {
				// 删除的是最后一个选项则向上选择
				list.setSelectedIndex(index - 1);
			}
			// 全部删除完毕之后按钮置灰
			if (listModel.getSize() == 0) {
				deleteButton.setEnabled(false);
			}
		} 
		// add
		else if (e.getSource().equals(addButton)) {
			// 在列表模型上新增一个选项
			text.setText("add " + "new" + listModel.getSize());
			listModel.add(listModel.getSize(), "new" + listModel.getSize());
			list.setSelectedIndex(listModel.getSize() - 1);
			// 在列表模型的末端添加一个新的对象
			// listModel.addElement("new" + listModel.getSize());
			// 没有选择项新增后删除按钮可用
			if (listModel.getSize() > 0) {
				deleteButton.setEnabled(true);
			}
		}
	}
}