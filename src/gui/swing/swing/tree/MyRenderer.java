/**
 * 
 */
package swing.tree;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * �Զ������ڵ���Ⱦ����
 * <p>
 * (Ҷ�ڵ�Ϊһ��ͼ�꣬��Ҷ�ڵ�����һ��ͼ��Ҷ�ڵ���������Ǻ���'A'������ʾ��)��
 * �̳���DefaultTreeCellRenderer�࣬��Ҫ����getTreeCellRendererComponent������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2007-8-23
 */
public class MyRenderer extends DefaultTreeCellRenderer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4362529347974019607L;

	private Icon leafIcon;// Ҷ�ڵ�
	private Icon branchIcon;// ��Ҷ�ڵ�

	public MyRenderer(Icon leaf, Icon branch) {
		leafIcon = leaf;
		branchIcon = branch;
	}

	/**
	 * ��д�˷�����ʵ���Զ������Ⱦ������
	 * 
	 * @see javax.swing.tree.DefaultTreeCellRenderer#getTreeCellRendererComponent(javax.swing.JTree,
	 *      java.lang.Object, boolean, boolean, boolean, int, boolean)
	 */
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
				row, hasFocus);
		if (leaf) {
			// ���÷���������Ҷ�ڵ��Icon����ʾ��
			setIcon(leafIcon);
			if (hasWordA(value))
				setToolTipText("The name has word 'A'");
			else
				setToolTipText(null);
		} else {
			setIcon(branchIcon);
		}
		return this;
	}

	/**
	 * �ж��Ƿ�����ĸA 
	 */
	protected boolean hasWordA(Object value) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		Person nodeInfo = (Person) (node.getUserObject());
		String name = nodeInfo.getInfo();
		if (name.indexOf("A") >= 0) {
			return true;
		}
		return false;
	}
}