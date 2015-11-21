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
 * 自定义树节点渲染器类
 * <p>
 * (叶节点为一种图标，非叶节点是另一种图标叶节点的名称若是含有'A'则有提示语)。
 * 继承自DefaultTreeCellRenderer类，需要覆盖getTreeCellRendererComponent方法。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2007-8-23
 */
public class MyRenderer extends DefaultTreeCellRenderer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4362529347974019607L;

	private Icon leafIcon;// 叶节点
	private Icon branchIcon;// 非叶节点

	public MyRenderer(Icon leaf, Icon branch) {
		leafIcon = leaf;
		branchIcon = branch;
	}

	/**
	 * 重写此方法以实现自定义的渲染器规则
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
			// 设置符合条件的叶节点的Icon和提示语
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
	 * 判断是否含有字母A 
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
