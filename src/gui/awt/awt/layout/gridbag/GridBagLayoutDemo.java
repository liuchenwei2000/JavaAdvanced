package awt.layout.gridbag;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import util.Displayer;

/**
 * GridBagLayout��ʾ
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-7-9
 */
public class GridBagLayoutDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("GridBagLayout Demo",
				new GridBagLayoutPanel());
	}
}

/**
 * �����鲼��(GridBagLayout)�����в��ֹ�����֮�ף�������Ϊ�����鲼����û�����Ƶ����񲼾֡�
 * �������鲼���У��к��еĳߴ���Ըı䡣����ͨ�������ڵĵ�Ԫ�ϲ�����Ӧ����������
 * �������Ҫ���������Ԫ�����򣬲�����ָ�������ڵ�Ԫ���ڵĶ��뷽ʽ��
 * <p>
 * �����鲼�ֿ����൱���ӣ�����������������÷�Χ��㡣
 * ʹ�ò��ֹ�������Ŀ����ʹ���齨�������ڸ��������Լ����ֲ���ϵͳ�϶��ܹ�������ʾ��
 * ����Ҫ��ˮƽ�ʹ�ֱ�����ϰ������������ʱ����Ӧ�ÿ���ʹ��GridBagLayout�ˡ�
 * <p>
 * GridBagLayout����һ�����Ĳ��ֹ�����������Ҫ������Ĵ�С��ͬ���ɽ������ֱ��ˮƽ���롣
 * ÿ��GridBagLayout����ά��һ����̬�ľ��ε�Ԫ����ÿ�����ռ��һ�����������ĵ�Ԫ����Ϊ��ʾ����
 * ÿ����GridBagLayout������������GridBagConstraints��ʵ���������
 * Constraints����ָ������������е���ʾ�����Լ����������ʾ�����еķ��÷�ʽ��
 * ����Constraints����֮�⣬GridBagLayout������ÿ���������С����ѡ��С����ȷ������Ĵ�С��
 *  <p>
 * ��������巽��ȡ����������ComponentOrientation����</br>
 * ����ˮƽ�Ĵ����ҵķ����������� (0,0) λ�����������Ͻǣ����� X ���ҵ�����Y ���µ�����
 * ����ˮƽ�Ĵ��ҵ���ķ����������� (0,0) λ�����������Ͻǣ����� X ���������Y ���µ�����
 */
class GridBagLayoutPanel extends JPanel {

	private static final long serialVersionUID = 3824355385856418695L;

	private JLabel styleLabel = new JLabel("Font Style��");
	private JLabel sizeLabel = new JLabel("Size��");

	private JComboBox styleBox;
	private JComboBox sizeBox;

	private JCheckBox boldBox;
	private JCheckBox italicBox;

	private JTextArea textArea;

	private ActionListener fontListener = new FontActionListener();

	/**
	 * ����һ�������鲼�ֵķ�����<p>
	 * <li>1����ֽ�ϻ���������ֲ�ͼ��
	 * <li>2���ҳ�һ������С�������������һ����Ԫ���ڣ��������ռ�ö����Ԫ��
	 * <li>3��ʹ��0��1��2...��ʶ������к��У��Ϳ��Զ�ȡ��gridx��gridy��gridwidth��gridweight��ֵ��
	 * <li>4������ÿ���������Ҫ�����Ƿ���Ҫˮƽ��ֱ��������ڵĵ�Ԫ���������Ҫ��������У�Ҳ����ȷ��fill��anchor������
	 * <li>5�������е���������Ϊ100�������Ҫĳһ�л�һ�б���Ĭ�ϵĳߴ磬�ͽ����л�����е����������weightx��weighty����Ϊ0��
	 * <li>6���������У����ϵص������롣
	 */
	public GridBagLayoutPanel() {
		setPreferredSize(new Dimension(300, 200));
		/*
		 * Ҫ��ʹ�������鲼�ֹ��������в��֣����뾭�����й��̣�
		 * 1������GridBagLayout���͵Ķ��󣬲���Ҫָ����������������������ֹ���������ݺ�����������Ϣ�²����
		 * 2����GridBagLayout�������ó�����Ĳ��ֹ�����
		 * 3��Ϊÿ���������һ��GridBagConstraints���͵Ķ�������GridBagConstraints������ֶ�ֵ�Ա��ƶ�������������еĲ��ַ���
		 * 4�����ͨ������ĵ�����������Լ����add(component,constraints);
		 */
		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);
		/*
		 * �������GridBagConstraints�����״̬�Ƿǳ���Ҫ�ģ�
		 * 1��gridx��gridy��gridwidth��gridheight����
		 * ��ЩԼ������������������е�λ��
		 * 
		 * gridx��gridyָ���˱����������Ͻǵ��к��е�λ��
		 * ָ�����������ʾ�����ǰ���ǵĵ�Ԫ���ڴ���ʾ�����У�λ������ԭ��ĵ�Ԫ��ַ��gridx = 0��gridy = 0
		 * ����ˮƽ�Ĵ����ҵĲ��֣������ǰ�����������Ͻǡ�����ˮƽ�Ĵ��ҵ���Ĳ��֣������ǰ�����������Ͻ�
		 * ʹ��GridBagConstraints.RELATIVE(Ĭ��ֵ)ָ�������������Ӵ����ǰ�ո���ӵ���������ĺ���(��gridx��X���gridy��Y��)
		 * 
		 * gridwidth��gridheightֵ���������ռ�õ�����������
		 * ָ���������ʾ��������(���gridwidth)����(���gridheight)�еĵ�Ԫ����Ĭ��ֵΪ1
		 * ʹ��GridBagConstraints.REMAINDERָ���������ʾ����Ϊ��gridx������(���gridwidth)�е����һ����Ԫ
		 * ���ߴ�gridy������(���gridheight)�е����һ����Ԫ
		 * ʹ��GridBagConstraints.RELATIVEָ���������ʾ����Ϊ��gridx����������(���gridwidth)�ĵ����ڶ�����Ԫ
		 * ���ߴ�gridy����������(���gridheight)�ĵ����ڶ�����Ԫ
		 * 
		 * 2�������ֶ�weightx��weighty
		 * �������鲼������ҪΪÿ���������������ֶΣ���������ֶ�Ϊ0�������������ԶΪ��ʼ�ߴ�(���������������Сʱ����Ĵ�Сʼ�ձ��ֲ���)
		 * �������һ�л�һ�еĴ�С���ֲ��䣬����Ҫ�Ѹ��С����е������������������Ϊ0
		 * ��������ʵ�ʸ����е���Դ�С������������������ѡ��Сʱ��������ʾ�����ÿ���������չ����ֵ
		 * ��������е���������Ϊ100�����г���鿴һ�²������
		 * ���ŶԻ���鿴һ���к�������ε����ģ��������ĳ�л�ĳ�в�Ӧ�����󣬾Ͱ����е������������Ϊ0
		 * 
		 * 3��fill��anchor����
		 * ���������ʾ�����������������Сʱ������Ҫ����fillԼ��������ȷ���Ƿ�(�Լ����)�������
		 * ���ܵ�ֵΪGridBagConstraints.NONE(Ĭ��ֵ)��HORIZONTAL(�ӿ����ֱ����������ˮƽ��������������ʾ���򣬵���������߶�)��
		 * VERTICAL(�Ӹ����ֱ���������ڴ�ֱ��������������ʾ���򣬵�����������)�� BOTH(ʹ�����ȫ��������ʾ����) 
		 * 
		 * ������û������������򣬿���ͨ������anchor�ֶ���ָ����λ�ã�
		 * GridBagConstraints.NORTH��SOUTH��WEST��EAST��NORTHWEST��NORTHEAST��SOUTHWEST��SOUTHEAST��CENTER(Ĭ��)
		 * 
		 * 4������
		 * ͨ������GridBagLayout��insets�ֶ����������Χ���Ӹ��ӵĿհ�����
		 * ͨ������insets�����left��top��right��bottomֵ�����������Χ�Ŀռ������ⱻ�����ⲿ����
		 * ��ipadx��ipadyֵ�����ڲ�������������ֵ������ӵ��������С��Ⱥ���С�߶��ϣ��������Ա�֤���������������С�ߴ�֮��
		 */
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 100;
		gbc.weighty = 100;
		add(styleLabel, gbc, 0, 0, 1, 1);
		add(sizeLabel, gbc, 0, 1, 1, 1);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0;
		gbc.weighty = 100;
		add(getStyleBox(), gbc, 1, 0, 1, 1);
		add(getSizeBox(), gbc, 1, 1, 1, 1);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = 100;
		add(getBoldBox(), gbc, 1, 2, 1, 1);
		add(getItalicBox(), gbc, 1, 3, 1, 1);
//		gbc.fill = GridBagConstraints.BOTH;
//		gbc.weightx = 100;
//		gbc.weighty = 100;
//		add(getTextArea(), gbc, 2, 0, 1, 4);
		// ʹ���˸�����GBC����Constraints��Ч������ע�Ͳ���
		add(getTextArea(), new GBC(2, 0, 1, 4).setFill(GridBagConstraints.BOTH)
				.setWeight(100, 100));
	}

	private JComboBox getStyleBox() {
		if (styleBox == null) {
			styleBox = new JComboBox(new String[] { "Serif", "SansSerif",
					"Monospaced", "Dialog", "DialogInput" });
			styleBox.addActionListener(fontListener);
			styleBox.setSelectedIndex(0);
		}
		return styleBox;
	}

	private JComboBox getSizeBox() {
		if (sizeBox == null) {
			sizeBox = new JComboBox(new String[] { "14", "12", "10", "8" });
			sizeBox.addActionListener(fontListener);
			sizeBox.setSelectedIndex(0);
		}
		return sizeBox;
	}

	private JCheckBox getBoldBox() {
		if (boldBox == null) {
			boldBox = new JCheckBox("Bold");
			boldBox.addActionListener(fontListener);
		}
		return boldBox;
	}

	private JCheckBox getItalicBox() {
		if (italicBox == null) {
			italicBox = new JCheckBox("Italic");
			italicBox.addActionListener(fontListener);
		}
		return italicBox;
	}

	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea(20, 15);
			textArea.setText("The quick brown fox jumps over the lazy dog.");
			textArea.setEditable(false);
			textArea.setLineWrap(true);
			textArea.setBorder(BorderFactory.createEtchedBorder());
		}
		return textArea;
	}

	private void add(Component c, GridBagConstraints gbc, int x, int y, int w,
			int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		add(c, gbc);
	}

	private void updateFont() {
		String name = getStyleBox().getSelectedItem().toString();
		int bold = getBoldBox().isSelected() ? Font.BOLD : Font.PLAIN;
		int italic = getItalicBox().isSelected() ? Font.ITALIC : Font.PLAIN;
		int style = bold + italic;
		int size = Integer.parseInt(getSizeBox().getSelectedItem().toString());
		Font font = new Font(name, style, size);
		getTextArea().setFont(font);
		repaint();
	}

	private class FontActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			updateFont();
		}

	}
}

/**
 * ������Լ��������
 * <p>
 * �����鲼���ζ�Ĺ�������Ϊ����Լ����д���룬���Կ���ʹ�ð�������Э������Լ����<p>
 * ������������������ԣ�</br>
 * <li>1�����ּ�̣�GBC���GridBagConstraints
 * <li>2����չ��GridBagConstraints����˿���ʹ��Լ������д����GBC.EAST
 * <li>3����������Լ��ʱ������ʹ��GBC�����磺add(component,new GBC(1,2));
 * <li>4������������������������ͨ���Ĳ�����gridx��girdy����gridx��girdy��gridwidth��gridheight
 * <li>5���ֶ��з����setter��x/yֵ�ԣ�add(component,new GBC(1,2).setWeight(100,100));
 * <li>6��setter����������this�����Կ����������ǣ�</br>
 * add(component,new GBC(1,2).setAnchor(GBC.EAST).setWight(100,100));
 * <li>7��setInsets����������һ��Inset����Ҫ���ȡһ�����ص�insets�����Ե��ã�</br>
 * add(component,new GBC(1,2).setInsets(1));
 */
class GBC extends GridBagConstraints {

	private static final long serialVersionUID = 8541774806056282157L;

	public GBC(int gridx, int gridy) {
		this.gridx = gridx;
		this.gridy = gridy;
	}

	public GBC(int gridx, int gridy, int gridwidth, int gridheight) {
		this.gridx = gridx;
		this.gridy = gridy;
		this.gridwidth = gridwidth;
		this.gridheight = gridheight;
	}

	public GBC setAnchor(int anchor) {
		this.anchor = anchor;
		return this;
	}

	public GBC setFill(int fill) {
		this.fill = fill;
		return this;
	}

	public GBC setWeight(double weightx, double weighty) {
		this.weightx = weightx;
		this.weighty = weighty;
		return this;
	}

	public GBC setInsets(int distance) {
		this.insets = new Insets(distance, distance, distance, distance);
		return this;
	}

	public GBC setIpad(int ipadx, int ipady) {
		this.ipadx = ipadx;
		this.ipady = ipady;
		return this;
	}
}