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
 * JSpinner演示类
 * <p>
 * JSpinner(微调控制器)是带有两个小按钮的文本域，当点击它时，可以增加或减少文本域的值。
 * 微调控制器中的值可以是数字、日期、来自列表的值以及任何可以用上一个下一个决定值的序列。
 * JSpinner类为前三种情况定义了标准的数据模型，还可以自定义数据模型来描述任意的序列。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-6-8
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
	 * 返回一个编辑整数的微调控制器，初始值为0，增量为1，没有边界
	 */
	private JSpinner getSpinner() {
		return new JSpinner();
	}

	/**
	 * 返回一个使用数字序列数据模型的微调控制器
	 */
	private JSpinner getNumberSpinner() {
		/*
		 * 构造一个具有指定value、minimum/maximum边界和stepSize的SpinnerNumberModel 
		 * value - 该模型的当前值
		 * minimum - 该序列中的第一个数
		 * maximum - 该序列中的最后一个数
		 * stepSize - 该序列中元素之间的差 
		 */
		return new JSpinner(new SpinnerNumberModel(5, 0, 10, 0.5));
	}

	/**
	 * 返回一个使用列表数据模型的微调控制器
	 */
	private JSpinner getListSpinner() {
		/* 
		 * 构造一个由指定数组定义其值序列的SpinnerModel
		 * 该模型的初始值将为 values[0]
		 */
		return new JSpinner(new SpinnerListModel(getFonts()));
	}

	/**
	 * 返回一个使用逆转顺序后的列表数据模型的微调控制器
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
	 * 返回一个使用日期数据模型的微调控制器
	 */
	private JSpinner getDateSpinner() {
		/*
		 * 构造一个SpinnerDateModel
		 * 初始值为当前日期，没有上边界和下边界，增量为Calendar.DAY_OF_MONTH
		 */
		return new JSpinner(new SpinnerDateModel());
	}

	/**
	 * 返回一个使用日期数据模型的微调控制器
	 * 并且微调控制器使用了特定的值编辑器
	 */
	private JSpinner getFormattedDateSpinner() {
		JSpinner spinner = new JSpinner(new SpinnerDateModel());
		String pattern = ((SimpleDateFormat) DateFormat.getDateInstance())
				.toPattern();
		/*
		 * 为微调控制器构造一个日期编辑器，编辑器使用特定格式
		 */
		spinner.setEditor(new JSpinner.DateEditor(spinner, pattern));
		return spinner;
	}

	/**
	 * 返回一个使用日期(时间)数据模型的微调控制器
	 */
	private JSpinner getTimeSpinner() {
		/*
		 * 构造一个SpinnerDateModel
		 * 初始值为第一个参数，没有上边界和下边界，增量为Calendar.HOUR
		 * 
		 * value - 该模型的当前值
		 * start - 该序列中的第一个日期
		 * end - 该序列中的最后一个日期
		 * calendarField - 增量值
		 */
		return new JSpinner(new SpinnerDateModel(new GregorianCalendar(2000,
				Calendar.JANUARY, 1, 12, 0, 0).getTime(), null, null,
				Calendar.HOUR));
	}

	/**
	 * 返回一个使用自定义数据模型的微调控制器
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
 * 微调控制器的自定义数据模型
 * <p>
 * 用于对字符串进行字母顺序转换，继承自AbstractSpinnerModel。
 */
class PermutationSpinnerModel extends AbstractSpinnerModel {

	private String word;

	public PermutationSpinnerModel(String w) {
		word = w;
	}
	
	/**
	 * 返回存储在模型中的值
	 */
	public Object getValue() {
		return word;
	}

	/**
	 * 设置一个新值
	 */
	public void setValue(Object value) {
		if (!(value instanceof String))
			throw new IllegalArgumentException();
		word = (String) value;
		// 必须调用fireStateChanged方法，否则微调控制器不会更新
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
