package swing.other;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.AbstractSpinnerModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;

import util.Displayer;
import util.ui.BorderFactory;

/**
 * JSpinner��ʾ��
 * <p>
 * JSpinner(΢��������)�Ǵ�������С��ť���ı��򣬵������ʱ���������ӻ�����ı����ֵ��
 * ΢���������е�ֵ���������֡����ڡ������б��ֵ�Լ��κο�������һ����һ������ֵ�����С�
 * JSpinner��Ϊǰ������������˱�׼������ģ�ͣ��������Զ�������ģ����������������С�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-6-8
 */
public class JSpinnerDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JSpinner Demo", new JSpinnerPanel());
	}
}

class JSpinnerPanel extends JPanel {

	private static final long serialVersionUID = -3649000453285373110L;

	private JPanel contentPanel;
	private JButton button;

	public JSpinnerPanel() {
		setPreferredSize(new Dimension(600, 250));
		setLayout(new BorderLayout());
		add(getContentPanel(), BorderLayout.CENTER);
		add(getButtonPanel(), BorderLayout.SOUTH);
	}

	private JPanel getContentPanel() {
		if (contentPanel == null) {
			contentPanel = new JPanel();
			GridLayout layout = new GridLayout(10, 1);
			layout.setVgap(5);
			contentPanel.setLayout(layout);
			contentPanel.add(makeSpinnerPanel("Default:", getSpinner()));
			contentPanel.add(makeSpinnerPanel("Bounded:", getNumberSpinner()));
			contentPanel.add(makeSpinnerPanel("List:", getListSpinner()));
			contentPanel.add(makeSpinnerPanel("Reverse List:", getReverseListSpinner()));
			contentPanel.add(makeSpinnerPanel("Date:", getDateSpinner()));
			contentPanel.add(makeSpinnerPanel("Better Date:", getFormattedDateSpinner()));
			contentPanel.add(makeSpinnerPanel("Time:", getTimeSpinner()));
			contentPanel.add(makeSpinnerPanel("Word permutations:",getWordPermutationsSpinner()));
		}
		return contentPanel;
	}

	/**
	 * ����һ���༭������΢������������ʼֵΪ0������Ϊ1��û�б߽�
	 */
	private JSpinner getSpinner() {
		return new JSpinner();
	}

	/**
	 * ����һ��ʹ��������������ģ�͵�΢��������
	 */
	private JSpinner getNumberSpinner() {
		/*
		 * ����һ������ָ��value��minimum/maximum�߽��stepSize��SpinnerNumberModel 
		 * value - ��ģ�͵ĵ�ǰֵ
		 * minimum - �������еĵ�һ����
		 * maximum - �������е����һ����
		 * stepSize - ��������Ԫ��֮��Ĳ� 
		 */
		return new JSpinner(new SpinnerNumberModel(5, 0, 10, 0.5));
	}

	/**
	 * ����һ��ʹ���б�����ģ�͵�΢��������
	 */
	private JSpinner getListSpinner() {
		/* 
		 * ����һ����ָ�����鶨����ֵ���е�SpinnerModel
		 * ��ģ�͵ĳ�ʼֵ��Ϊ values[0]
		 */
		return new JSpinner(new SpinnerListModel(getFonts()));
	}

	/**
	 * ����һ��ʹ����ת˳�����б�����ģ�͵�΢��������
	 */
	private JSpinner getReverseListSpinner() {
		return new JSpinner(new SpinnerListModel(getFonts()) {
			private static final long serialVersionUID = 1L;

			public Object getNextValue() {
				return super.getPreviousValue();
			}

			public Object getPreviousValue() {
				return super.getNextValue();
			}
		});
	}

	private static String[] getFonts() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getAvailableFontFamilyNames();
	}

	/**
	 * ����һ��ʹ����������ģ�͵�΢��������
	 */
	private JSpinner getDateSpinner() {
		/*
		 * ����һ��SpinnerDateModel
		 * ��ʼֵΪ��ǰ���ڣ�û���ϱ߽���±߽磬����ΪCalendar.DAY_OF_MONTH
		 */
		return new JSpinner(new SpinnerDateModel());
	}

	/**
	 * ����һ��ʹ����������ģ�͵�΢��������
	 * ����΢��������ʹ�����ض���ֵ�༭��
	 */
	private JSpinner getFormattedDateSpinner() {
		JSpinner spinner = new JSpinner(new SpinnerDateModel());
		String pattern = ((SimpleDateFormat) DateFormat.getDateInstance())
				.toPattern();
		/*
		 * Ϊ΢������������һ�����ڱ༭�����༭��ʹ���ض���ʽ
		 */
		spinner.setEditor(new JSpinner.DateEditor(spinner, pattern));
		return spinner;
	}

	/**
	 * ����һ��ʹ������(ʱ��)����ģ�͵�΢��������
	 */
	private JSpinner getTimeSpinner() {
		/*
		 * ����һ��SpinnerDateModel
		 * ��ʼֵΪ��һ��������û���ϱ߽���±߽磬����ΪCalendar.HOUR
		 * 
		 * value - ��ģ�͵ĵ�ǰֵ
		 * start - �������еĵ�һ������
		 * end - �������е����һ������
		 * calendarField - ����ֵ
		 */
		return new JSpinner(new SpinnerDateModel(new GregorianCalendar(2000,
				Calendar.JANUARY, 1, 12, 0, 0).getTime(), null, null,
				Calendar.HOUR));
	}

	/**
	 * ����һ��ʹ���Զ�������ģ�͵�΢��������
	 */
	private JSpinner getWordPermutationsSpinner() {
		return new JSpinner(new PermutationSpinnerModel("meat"));
	}

	private JPanel makeSpinnerPanel(String labelText, final JSpinner spinner) {
		JPanel panel = new JPanel();
		GridLayout layout = new GridLayout(1, 3);
		layout.setHgap(5);
		panel.setLayout(layout);
		JLabel label = new JLabel(labelText);
		final JLabel valueLabel = new JLabel();
		panel.add(label);
		panel.add(spinner);
		panel.add(valueLabel);

		spinner.setBorder(BorderFactory.createLineBorder());

		getButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Object value = spinner.getValue();
				valueLabel.setText(value.toString());
			}
		});
		return panel;
	}

	private JPanel getButtonPanel() {
		JPanel panel = new JPanel();
		panel.add(getButton());
		return panel;
	}

	private JButton getButton() {
		if (button == null) {
			button = new JButton("OK");
		}
		return button;
	}
}

/**
 * ΢�����������Զ�������ģ��
 * <p>
 * ���ڶ��ַ���������ĸ˳��ת�����̳���AbstractSpinnerModel��
 */
class PermutationSpinnerModel extends AbstractSpinnerModel {

	private String word;

	public PermutationSpinnerModel(String w) {
		word = w;
	}
	
	/**
	 * ���ش洢��ģ���е�ֵ
	 */
	public Object getValue() {
		return word;
	}

	/**
	 * ����һ����ֵ
	 */
	public void setValue(Object value) {
		if (!(value instanceof String))
			throw new IllegalArgumentException();
		word = (String) value;
		// �������fireStateChanged����������΢���������������
		fireStateChanged();
	}

	public Object getNextValue() {
		StringBuffer buffer = new StringBuffer(word);
		for (int i = buffer.length() - 1; i > 0; i--) {
			if (buffer.charAt(i - 1) < buffer.charAt(i)) {
				int j = buffer.length() - 1;
				while (buffer.charAt(i - 1) > buffer.charAt(j))
					j--;
				swap(buffer, i - 1, j);
				reverse(buffer, i, buffer.length() - 1);
				return buffer.toString();
			}
		}
		reverse(buffer, 0, buffer.length() - 1);
		return buffer.toString();
	}

	public Object getPreviousValue() {
		StringBuffer buffer = new StringBuffer(word);
		for (int i = buffer.length() - 1; i > 0; i--) {
			if (buffer.charAt(i - 1) > buffer.charAt(i)) {
				int j = buffer.length() - 1;
				while (buffer.charAt(i - 1) < buffer.charAt(j))
					j--;
				swap(buffer, i - 1, j);
				reverse(buffer, i, buffer.length() - 1);
				return buffer.toString();
			}
		}
		reverse(buffer, 0, buffer.length() - 1);
		return buffer.toString();
	}

	private static void swap(StringBuffer buffer, int i, int j) {
		char temp = buffer.charAt(i);
		buffer.setCharAt(i, buffer.charAt(j));
		buffer.setCharAt(j, temp);
	}

	private static void reverse(StringBuffer buffer, int i, int j) {
		while (i < j) {
			swap(buffer, i, j);
			i++;
			j--;
		}
	}
}