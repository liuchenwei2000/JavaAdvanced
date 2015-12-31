/**
 * 
 */
package swing.list;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import util.Displayer;

/**
 * JList演示类
 * <p>
 * 该组件允许用户从列表中选择一个或多个对象。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2007-8-29
 */
public class JListDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JList Demo", new JListPanel());
	}
}

class JListPanel extends JPanel implements ListSelectionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4203723178063162218L;

	private static final String[] VALUES = { "Andy", "Sany", "Tom", "Peter",
			"Mosie", "Mosie2", "Mosie3" };
	// 参数可以是对象数组、向量和ListModel
	private JList list = new JList(VALUES);
	private JTextField text = new JTextField("列表选择显示框");

	public JListPanel() {
		setLayout(new BorderLayout());
		/*
		 * 设置允许单项选择(SINGLE_SELECTION)还是多项选择(SINGLE_INTERVAL_SELECTION)默认是多项选择。
		 * 按住"Ctrl"键，连续在多个项目上单击，那么原先被选中的项目仍旧保持选中状态，所以可以选中任意多的项目。
		 * 如果选中了某个项目，任何按住"Shift"键并单击另一个项目，那么这两个项目之间的所有项目都将被选中。
		 * 要从选中的项目组中去掉一个，可以按住"Ctrl"键在此项目上单击。
		 */
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		/* 
		 * 设置布局列表单元的方式
		 * VERTICAL 指示默认布局：一列单元
		 * HORIZONTAL_WRAP 单元按先横向后纵向布局
		 * VERTICAL_WRAP 单元按先纵向后横向布局
		 */
		list.setLayoutOrientation(JList.VERTICAL);
		// 设置不使用滚动条可以在列表中显示的首选行数
		// 这一点由最近的JViewport祖先(如果有)确定
		// 本例由JScrollPane的viewport所决定，所以这个语句没有实际作用
		list.setVisibleRowCount(1);
		// 实现ListSelectionListener接口以响应选择项变化的事件
		list.addListSelectionListener(this);
		
		JScrollPane scroll = new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(250, 80));
		add(scroll, BorderLayout.NORTH);

		text.setEditable(false);
		add(text, BorderLayout.SOUTH);
	}

	/**
	 * List选择项变化的事件处理
	 */
	public void valueChanged(ListSelectionEvent e) {
		/*
		 * 若是单选则用getSelectedValue()返回对象；
		 * 若是多选则用getSelectedValues()返回对象数组。
		 */
		text.setText(list.getSelectedValue().toString());
	}
}
