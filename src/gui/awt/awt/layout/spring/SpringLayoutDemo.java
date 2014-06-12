package awt.layout.spring;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Spring;
import javax.swing.SpringLayout;

import util.Displayer;

/**
 * ���ɲ�������ʾ
 * <p>
 * ������ڸ��ӵĲ��ֻ���Ӧ��ʹ�������鲼�֡�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-7-13
 */
public class SpringLayoutDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("SpringLayout Demo", new SpringLayoutPanel());
	}
}

class SpringLayoutPanel extends JPanel {

	private static final long serialVersionUID = -1284238951065109154L;

	private JLabel styleLabel = new JLabel("Font Style��");
	private JLabel sizeLabel = new JLabel("Size��");

	private JComboBox styleBox;
	private JComboBox sizeBox;

	public SpringLayoutPanel() {
		setPreferredSize(new Dimension(300, 200));

		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		this.add(styleLabel);
		this.add(sizeLabel);
		this.add(getStyleBox());
		this.add(getSizeBox());
		// ֧�������������ǩ��������
		Spring strut = Spring.constant(10);
		// getConstraints����������һ��SpringLayout.Constraints���͵Ķ���
		// ���Խ�������������һ�����Σ�ֻ����x��y����Ⱥ͸߶ȵ�ֵ�ǵ��ɣ�����������
		// ͨ��getWidth()�õ��ᴩ����Ŀ��
		Spring styleWidth = layout.getConstraints(styleLabel).getWidth();
		Spring sizeWidth = layout.getConstraints(sizeLabel).getWidth();
		// max�õ��������ɵ����ֵ�����������������нϳ����Ǹ�����
		Spring maxWidth = Spring.max(styleWidth, sizeWidth);
		// sum�õ��������ɵĺ�
		Spring labelsEast = Spring.sum(strut, maxWidth);
		// ʹ��ͬһ��������ʹ��������ǩ�����Ҷ��룬����������ǩҲ�Ƕ����
		layout.putConstraint(SpringLayout.EAST, styleLabel, labelsEast,
				SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, sizeLabel, labelsEast,
				SpringLayout.WEST, this);
		// ʹ��ͬһ������ʹ�ñ�ǩ���������NORTH���ܱ��ֶ���
		layout.putConstraint(SpringLayout.NORTH, styleLabel, strut,
				SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, getStyleBox(), strut,
				SpringLayout.NORTH, this);
		// getConstraint����һ�����ָ���߽絽���丸���Ķ��߻���ߵĵ���
		Spring styleLabelSouth = layout.getConstraint(SpringLayout.SOUTH,
				styleLabel);
		Spring styleBoxSouth = layout.getConstraint(SpringLayout.SOUTH,
				getStyleBox());
		Spring secondRowNorth = Spring.sum(strut, Spring.max(styleLabelSouth,
				styleBoxSouth));

		layout.putConstraint(SpringLayout.NORTH, sizeLabel, secondRowNorth,
				SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, getSizeBox(), secondRowNorth,
				SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, getStyleBox(), strut,
				SpringLayout.EAST, styleLabel);
		layout.putConstraint(SpringLayout.WEST, getSizeBox(), strut,
				SpringLayout.EAST, sizeLabel);
	}

	private JComboBox getStyleBox() {
		if (styleBox == null) {
			styleBox = new JComboBox(new String[] { "Serif", "SansSerif",
					"Monospaced", "Dialog", "DialogInput" });
			styleBox.setSelectedIndex(0);
		}
		return styleBox;
	}

	private JComboBox getSizeBox() {
		if (sizeBox == null) {
			sizeBox = new JComboBox(new String[] { "14", "12", "10", "8" });
			sizeBox.setSelectedIndex(0);
		}
		return sizeBox;
	}
}