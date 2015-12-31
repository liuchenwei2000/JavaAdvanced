/**
 * 
 */
package swing;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import swing.frame.BasicFrame;

/**
 * Java剪切板演示类
 * <p>
 * Java基本类库(JFC)对系统剪贴板提供了有限的支持(以java.awt.datatransfer包的形式)。
 * 可以把字符串对象作为文本复制到剪贴板，或者从剪贴板粘贴文本到一个字符串对象中。
 * 剪贴板被设计成可以持有任何数据，不过剪贴板上的数据如何表示则取决于实现剪切和粘贴动作的程序。
 * Java剪贴板的API通过"风味"的概念提供了扩展性。
 * 当数据离开剪贴板时，它就关联了一个相关的风味集，表明它可以被转换成的格式。
 * 比如，图形可能以数字字符串表示，也可以用图像表示。
 * 也可以查看这个剪贴板数据是否支持你所感兴趣的"风味"。
 *
 * @author 刘晨伟
 *
 * 创建日期：2009-5-23
 */
public class ClipboardDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ClipboardFrame().setVisible(true);
	}
}

class ClipboardFrame extends BasicFrame {

	private static final long serialVersionUID = -3433025565633241680L;

	private JMenuBar menuBar = new JMenuBar();
	private JMenu editMenu;
	private JMenuItem cutItem;
	private JMenuItem copyItem;
	private JMenuItem pasteItem;
	private JTextArea textArea = new JTextArea(20, 20);
	// 获取系统剪切板的一个实例，该Clipboard与OS提供的剪贴板工具相互作用
	// 该剪贴板使数据能够在Java应用程序和使用本机剪贴板工具的本机应用程序之间传输
	private Clipboard clipboard = getToolkit().getSystemClipboard();

	public ClipboardFrame() {
		super("Clipboard Demo");
		menuBar.add(getEditMenu());
		setJMenuBar(menuBar);
		getContentPane().add(new JScrollPane(textArea));
	}

	private JMenu getEditMenu() {
		if (editMenu == null) {
			editMenu = new JMenu("编辑");
			editMenu.add(getCutItem());
			editMenu.add(getCopyItem());
			editMenu.add(getPasteItem());
		}
		return editMenu;
	}

	private JMenuItem getCutItem() {
		if (cutItem == null) {
			cutItem = new JMenuItem("剪切");
			cutItem.addActionListener(getCutListener());
		}
		return cutItem;
	}

	private JMenuItem getCopyItem() {
		if (copyItem == null) {
			copyItem = new JMenuItem("复制");
			copyItem.addActionListener(getCopyListener());
		}
		return copyItem;
	}

	private JMenuItem getPasteItem() {
		if (pasteItem == null) {
			pasteItem = new JMenuItem("粘贴");
			pasteItem.addActionListener(getPasteListener());
		}
		return pasteItem;
	}

	/** 如下动作执行之后也可以使用OS的相关快捷键查看效果 */
	
	private ActionListener getCutListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getCopyListener().actionPerformed(e);
				// 将选择的文本替换成空串
				textArea.replaceRange("", textArea.getSelectionStart(),
						textArea.getSelectionEnd());
			}
		};
	}

	private ActionListener getCopyListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 返回此TextComponent中包含的选定文本
				String selection = textArea.getSelectedText();
				if (selection == null) return;
				// 实现传输String所需能力的Transferable
				// 此Transferable可以正确支持DataFlavor.stringFlavor及所有等效flavor
				StringSelection clipString = new StringSelection(selection);
				// 将剪贴板的当前内容设置到指定的transferable对象
				// 并将指定的剪贴板所有者作为新内容的所有者注册
				clipboard.setContents(clipString, clipString);
			}
		};
	}

	private ActionListener getPasteListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 返回表示剪贴板当前内容的transferable对象
				Transferable clipData = clipboard.getContents(ClipboardFrame.this);
				try {
					// 返回一个对象，该对象表示将要被传输的数据
					// 返回对象的类是由参数 flavor 的表示类定义的
					String clipString = (String) clipData
							.getTransferData(DataFlavor.stringFlavor);
					// 将选择的文本替换为剪切板中的内容
					textArea.replaceRange(clipString, textArea
							.getSelectionStart(), textArea.getSelectionEnd());
				} catch (Exception ex) {
					System.err.println("Not String flavor");
				}
			}
		};
	}
}
