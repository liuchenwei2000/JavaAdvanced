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
 * JTree��ʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-5-21
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
	// ������������
	private String[][] data = { 
			{ "Colors", "Red", "Blue", "Green" },
			{ "Flavors", "Tart", "Sweet", "Bland" },
			{ "Length", "Short", "Medium", "Long" },
			{ "Volume", "High", "Medium", "Low" },
			{ "Temperature", "High", "Medium", "Low" },
			{ "Intensity", "High", "Medium", "Low" }, };
	
	private static int counter = 0;// ������
	
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
			// �ڵ�ǰѡ������ڵ��ϲ�������
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (counter < data.length) {
						// �²���������
						DefaultMutableTreeNode child = new Branch(
								data[counter++]).getRoot();
						// ��ǰѡ��Ľڵ�
						DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
								.getLastSelectedPathComponent();
						if (selectedNode == null) {
							selectedNode = (DefaultMutableTreeNode) getTreeModel()
									.getRoot();
						}
						/*
						 * ����insertNodeInto(MutableTreeNode newChild, MutableTreeNode parent, int index)
						 * �������������parent�ڵ���ӽڵ��е�indexλ�ò���newChild��Ȼ�󷢳��¼�֪ͨ��ͼ
						 * ��������ӽڵ����ѡ��������Ϊ���������ʵ����¼�
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
 * ��֦��
 */
class Branch {
	
	private DefaultMutableTreeNode root;// ���ڵ�

	/**
	 * ʹ���ַ������鴴��һ����֦����������Ԫ����Ϊ���ڵ㣬ʣ�µ�����Ԫ�ض���Ϊ���ڵ���ӽڵ㡣
	 * 
	 * @param data
	 *            �ַ�������
	 */
	public Branch(String[] data) {
		root = new DefaultMutableTreeNode(data[0]);
		for (int i = 1; i < data.length; i++) {
			root.add(new DefaultMutableTreeNode(data[i]));
		}
	}

	/**
	 * ���ظ��ڵ�
	 */
	public DefaultMutableTreeNode getRoot() {
		return root;
	}
}