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
 * Java���а���ʾ��
 * <p>
 * Java�������(JFC)��ϵͳ�������ṩ�����޵�֧��(��java.awt.datatransfer������ʽ)��
 * ���԰��ַ���������Ϊ�ı����Ƶ������壬���ߴӼ�����ճ���ı���һ���ַ��������С�
 * �����屻��Ƴɿ��Գ����κ����ݣ������������ϵ�������α�ʾ��ȡ����ʵ�ּ��к�ճ�������ĳ���
 * Java�������APIͨ��"��ζ"�ĸ����ṩ����չ�ԡ�
 * �������뿪������ʱ�����͹�����һ����صķ�ζ�������������Ա�ת���ɵĸ�ʽ��
 * ���磬ͼ�ο����������ַ�����ʾ��Ҳ������ͼ���ʾ��
 * Ҳ���Բ鿴��������������Ƿ�֧����������Ȥ��"��ζ"��
 *
 * @author ����ΰ
 *
 * �������ڣ�2009-5-23
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
	// ��ȡϵͳ���а��һ��ʵ������Clipboard��OS�ṩ�ļ����幤���໥����
	// �ü�����ʹ�����ܹ���JavaӦ�ó����ʹ�ñ��������幤�ߵı���Ӧ�ó���֮�䴫��
	private Clipboard clipboard = getToolkit().getSystemClipboard();

	public ClipboardFrame() {
		super("Clipboard Demo");
		menuBar.add(getEditMenu());
		setJMenuBar(menuBar);
		getContentPane().add(new JScrollPane(textArea));
	}

	private JMenu getEditMenu() {
		if (editMenu == null) {
			editMenu = new JMenu("�༭");
			editMenu.add(getCutItem());
			editMenu.add(getCopyItem());
			editMenu.add(getPasteItem());
		}
		return editMenu;
	}

	private JMenuItem getCutItem() {
		if (cutItem == null) {
			cutItem = new JMenuItem("����");
			cutItem.addActionListener(getCutListener());
		}
		return cutItem;
	}

	private JMenuItem getCopyItem() {
		if (copyItem == null) {
			copyItem = new JMenuItem("����");
			copyItem.addActionListener(getCopyListener());
		}
		return copyItem;
	}

	private JMenuItem getPasteItem() {
		if (pasteItem == null) {
			pasteItem = new JMenuItem("ճ��");
			pasteItem.addActionListener(getPasteListener());
		}
		return pasteItem;
	}

	/** ���¶���ִ��֮��Ҳ����ʹ��OS����ؿ�ݼ��鿴Ч�� */
	
	private ActionListener getCutListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getCopyListener().actionPerformed(e);
				// ��ѡ����ı��滻�ɿմ�
				textArea.replaceRange("", textArea.getSelectionStart(),
						textArea.getSelectionEnd());
			}
		};
	}

	private ActionListener getCopyListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ���ش�TextComponent�а�����ѡ���ı�
				String selection = textArea.getSelectedText();
				if (selection == null) return;
				// ʵ�ִ���String����������Transferable
				// ��Transferable������ȷ֧��DataFlavor.stringFlavor�����е�Чflavor
				StringSelection clipString = new StringSelection(selection);
				// ��������ĵ�ǰ�������õ�ָ����transferable����
				// ����ָ���ļ�������������Ϊ�����ݵ�������ע��
				clipboard.setContents(clipString, clipString);
			}
		};
	}

	private ActionListener getPasteListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ���ر�ʾ�����嵱ǰ���ݵ�transferable����
				Transferable clipData = clipboard.getContents(ClipboardFrame.this);
				try {
					// ����һ�����󣬸ö����ʾ��Ҫ�����������
					// ���ض���������ɲ��� flavor �ı�ʾ�ඨ���
					String clipString = (String) clipData
							.getTransferData(DataFlavor.stringFlavor);
					// ��ѡ����ı��滻Ϊ���а��е�����
					textArea.replaceRange(clipString, textArea
							.getSelectionStart(), textArea.getSelectionEnd());
				} catch (Exception ex) {
					System.err.println("Not String flavor");
				}
			}
		};
	}
}