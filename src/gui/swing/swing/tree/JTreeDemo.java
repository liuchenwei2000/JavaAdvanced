/**
 * 
 */
package swing.tree;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import util.Displayer;

/**
 * JTree演示类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-5-21
 */
public class JTreeDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JTree Demo", new JTreePanel());
	}
}

class JTreePanel extends JPanel {

	private static final long serialVersionUID = -6907995495368499098L;
	// 构造树的数据
	private String[][] data = { 
			{ "Colors", "Red", "Blue", "Green" },
			{ "Flavors", "Tart", "Sweet", "Bland" },
			{ "Length", "Short", "Medium", "Long" },
			{ "Volume", "High", "Medium", "Low" },
			{ "Temperature", "High", "Medium", "Low" },
			{ "Intensity", "High", "Medium", "Low" }, };
	
	private static int counter = 0;// 计数器
	
	private JTree tree;
	
	private JButton button;

	public JTreePanel() {
		setLayout(new BorderLayout());
		this.add(new JScrollPane(getTree()), BorderLayout.CENTER);
		this.add(getButton(), BorderLayout.SOUTH);
		setPreferredSize(new Dimension(250, 250));
	}

	private JTree getTree() {
		if (tree == null) {
			DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
			tree = new JTree(root);
		}
		return tree;
	}

	private DefaultTreeModel getTreeModel() {
		return (DefaultTreeModel) getTree().getModel();
	}
	
	private JButton getButton() {
		if (button == null) {
			button = new JButton("Press me");
			button.setBackground(Color.BLUE);
			button.setForeground(Color.WHITE);
			// 在当前选择的树节点上插入子树
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (counter < data.length) {
						// 新产生的子树
						DefaultMutableTreeNode child = new Branch(
								data[counter++]).getRoot();
						// 当前选择的节点
						DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
								.getLastSelectedPathComponent();
						if (selectedNode == null) {
							selectedNode = (DefaultMutableTreeNode) getTreeModel()
									.getRoot();
						}
						/*
						 * 方法insertNodeInto(MutableTreeNode newChild, MutableTreeNode parent, int index)
						 * 这个方法可以在parent节点的子节点中的index位置插入newChild，然后发出事件通知视图
						 * 这是添加子节点的首选方法，因为它将创建适当的事件
						 */
						getTreeModel().insertNodeInto(child, selectedNode, 0);
					}
				}
			});
		}
		return button;
	}
}

/**
 * 树枝类
 */
class Branch {
	
	private DefaultMutableTreeNode root;// 根节点

	/**
	 * 使用字符串数组创建一个树枝，将数组首元素作为根节点，剩下的所有元素都作为根节点的子节点。
	 * 
	 * @param data
	 *            字符串数组
	 */
	public Branch(String[] data) {
		root = new DefaultMutableTreeNode(data[0]);
		for (int i = 1; i < data.length; i++) {
			root.add(new DefaultMutableTreeNode(data[i]));
		}
	}

	/**
	 * 返回根节点
	 */
	public DefaultMutableTreeNode getRoot() {
		return root;
	}
}
