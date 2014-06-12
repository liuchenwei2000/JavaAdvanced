/**
 * 
 */
package swing.tree;

import java.awt.GridLayout;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import util.Displayer;

/**
 * JTree示例 
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2007-8-20
 */
public class MyTreeDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("My Tree Demo", new MyTreePanel());
	}
}

class MyTreePanel extends JPanel implements TreeSelectionListener {
	
	private static final long serialVersionUID = 4746136924215717397L;

	private JTree tree;
	
	private JTextArea area;
	
	private JTextArea getArea(){
		if(area == null) {
			area = new JTextArea(10,20);
			area.setTransferHandler(new AreaTransferHandler());
		}
		return area;
	}

	private static final DataFlavor LIST_FLAVOR = new DataFlavor(List.class,"Strings");
	
	private static class StringsTransferable implements Transferable{

		private List<String> contents;
		
		public StringsTransferable(List<String> contents){
			this.contents = contents;
		}
		
		public Object getTransferData(DataFlavor flavor)
				throws UnsupportedFlavorException, IOException {
			return contents ;
		}

		public DataFlavor[] getTransferDataFlavors() {
			return new DataFlavor[]{LIST_FLAVOR};
		}

		public boolean isDataFlavorSupported(DataFlavor flavor) {
			return LIST_FLAVOR.equals(flavor);
		}
		
	}
	
	private static class TreeTransferHandler extends TransferHandler {
		
		private static final long serialVersionUID = 1L;

		protected Transferable createTransferable(JComponent c) {
			JTree tree = (JTree)c;
			TreePath[] paths = tree.getSelectionPaths();
			if(paths != null && paths.length != 0) {
				List<String> contents = new ArrayList<String>();
				for (TreePath treePath : paths) {
					Object comp = treePath.getLastPathComponent();
					contents.add(comp.toString());
				}
				return new StringsTransferable(contents);
			}
			return null;
		}

		public int getSourceActions(JComponent c) {
			return COPY;
		}

	}
	
	
	private class AreaTransferHandler extends TransferHandler {
		private static final long serialVersionUID = 1L;

		public boolean canImport(JComponent comp, DataFlavor[] transferFlavors) {
			if(transferFlavors != null && transferFlavors.length != 0){
				return LIST_FLAVOR.equals(transferFlavors[0]);
			}
			return false;
		}
		
		@SuppressWarnings("unchecked")
		public boolean importData(JComponent comp, Transferable t) {
			if(canImport(comp,t.getTransferDataFlavors())){
				try {
					List<String> strings = (List<String>)(t.getTransferData(LIST_FLAVOR));
					getArea().setText(strings.toString());
				} catch (UnsupportedFlavorException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return false;
		}
	}
	
	public MyTreePanel() {
		super(new GridLayout(2, 0));
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("root");
		DefaultMutableTreeNode child1 = new DefaultMutableTreeNode("child1");
		createNodes(child1);
		DefaultMutableTreeNode child2 = new DefaultMutableTreeNode("child2");
		createNodes(child2);
		top.add(child1);
		top.add(child2);
		// 以根节点创建树
		tree = new JTree(top);
		tree.setDragEnabled(true);
		tree.setTransferHandler(new TreeTransferHandler());
		// 设置树的选择模式(单选)
//		tree.getSelectionModel().setSelectionMode(
//				TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(this);

		// 设置提示语在树节点上可以显示
		ToolTipManager.sharedInstance().registerComponent(tree);		 
		 		
		/*
		 * 节点外观设置
		 */
		ImageIcon leafIcon = new ImageIcon("images/gui.swing.tree/treeleaf.gif");// 叶节点
		// 若是打包jar发布后运行需要这样获取系统资源
		// ImageIcon leafIcon = new
		// ImageIcon(getToolkit().getImage(ClassLoader.getSystemResource("images/treeleaf.gif")));
		ImageIcon branchIcon = new ImageIcon("images/gui.swing.tree/treenode.gif");// 非叶节点
		ImageIcon collapsedIcon = new ImageIcon("images/gui.swing.tree/treecollapse.gif");// 节点收缩图标
		ImageIcon expandedIcon = new ImageIcon("images/gui.swing.tree/treeexpand.gif");// 节点展开图标
		
		if ((leafIcon != null) && (branchIcon != null)) {
			/*
			 * // 节点渲染器(设置自定义的节点外观) 
			 * DefaultTreeCellRenderer renderer = new
			 * DefaultTreeCellRenderer(); 
			 * renderer.setLeafIcon(leafIcon); // 设置叶节点的外观
			 * renderer.setOpenIcon(branchIcon); // 设置非叶节点打开时的外观
			 * renderer.setClosedIcon(branchIcon);// 设置非叶节点关闭时的外观
			 * tree.setCellRenderer(renderer);
			 */
			
			 // 使用自定义的渲染器 
			 tree.setCellRenderer(new MyRenderer(leafIcon, branchIcon));			
		} else {
			System.err.println("Icon missing; using default.");
		}
		
		 /**
		  * 设置节点的收缩扩展图标 
		  */
		BasicTreeUI treeUI = (BasicTreeUI) tree.getUI();
		treeUI.setCollapsedIcon(collapsedIcon);// 收缩
		treeUI.setExpandedIcon(expandedIcon);// 展开
		
		/**
		 * 节点被鼠标点击事件处理
		 */
		MouseListener ml = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int selRow = tree.getRowForLocation(e.getX(), e.getY());
				TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
				if (selRow != -1) {
					/*
					 * // 单击 if(e.getClickCount() == 1) {
					 * System.out.println(selPath.getLastPathComponent().toString() +
					 * "single click"); }
					 */
					// 双击
					if (e.getClickCount() == 2) {
						System.out.println(selPath.getPathComponent(selPath
								.getPathCount() - 2)
								+ "."
								+ selPath.getLastPathComponent().toString()
								+ " is double clicked ");
						DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
								.getLastSelectedPathComponent();
						/*
						 * node.add(new DefaultMutableTreeNode("new"));//直接添加不能及时通知给视图
						 * DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
						 * model.insertNodeInto(new DefaultMutableTreeNode("new2"), node, 0);
						 */
						Enumeration<?> nodes = node.breadthFirstEnumeration();
						while (nodes.hasMoreElements()) {
							DefaultMutableTreeNode aNode = (DefaultMutableTreeNode) nodes
									.nextElement();
							System.out.println(aNode.toString());
						}
					}
				}
			}
		};
		tree.addMouseListener(ml);
		tree.setRootVisible(true);
		/*
		 * // 是否显示继承线(默认显示) 
		 * tree.putClientProperty("JTree.lineStyle", "None");
		 */
		
		JScrollPane scroll = new JScrollPane(tree);
		
		/*
		 * JScrollPane scroll = new JScrollPane();
		 * scroll.getViewport().add(tree);
		 */
		
		add(scroll);
		add(new JScrollPane(getArea()));
	}

	private void createNodes(DefaultMutableTreeNode top) {
		top.add(new DefaultMutableTreeNode(new Person("A")));
		top.add(new DefaultMutableTreeNode(new Person("B")));
		top.add(new DefaultMutableTreeNode(new Person("C")));
		top.add(new DefaultMutableTreeNode(new Person("E")));
		top.add(new DefaultMutableTreeNode(new Person("D")));
		top.add(new DefaultMutableTreeNode(new Person("F")));
	}

	/**
	 * 树节点选择变换的时候触发
	 */
	public void valueChanged(TreeSelectionEvent e) {
//		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
//				.getLastSelectedPathComponent();
//		if (node == null) return;
//		System.out.println(node.getLevel());
//		if (node.isLeaf()) {
//			/*
//			 * JOptionPane.showMessageDialog(this, node.getParent().toString() +
//			 * "." + node.getUserObject());
//			 */
//		}
	}
}