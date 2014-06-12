/**
 * 
 */
package swing.text.format;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.DocumentFilter;
import javax.swing.text.InternationalFormatter;
import javax.swing.text.MaskFormatter;

import util.Displayer;
import util.ui.BorderFactory;

/**
 * JFormattedTextField格式化文本域演示类
 * <p>
 * 默认行为是：</br>
 * 输入非法数据，焦点离开之后会自动将格式化文本域的显示值设为原值，
 * (输入非法数据前的有效值)，并且文本域的getValue()保持原值。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-6-5
 */
public class JFormattedTextFieldDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("JFormattedTextField Demo",
				new JFormattedTextFieldPanel());
	}
}

class JFormattedTextFieldPanel extends JPanel {

	private static final long serialVersionUID = -2111111975392309691L;

	private JPanel contentPanel;
	private JButton button;

	public JFormattedTextFieldPanel() {
		setPreferredSize(new Dimension(600,250));
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
			contentPanel.add(makeFormattedTextFieldPanel("Number:",
					getIntTextField()));
			contentPanel.add(makeFormattedTextFieldPanel(
					"Number (Commit behavior):",
					getIntTextFieldWithCommitBehavior()));
			contentPanel.add(makeFormattedTextFieldPanel("Filtered Number",
					getIntTextFieldWithFormatter()));
			contentPanel.add(makeFormattedTextFieldPanel("Verified Number:",
					getIntTextFieldWithInputVerifier()));
			contentPanel.add(makeFormattedTextFieldPanel("Currency:",
					getCurrencyTextField()));
			contentPanel.add(makeFormattedTextFieldPanel("Date (default):",
					getDateTextField()));
			contentPanel.add(makeFormattedTextFieldPanel(
					"Date (short, not lenient):", getShortDateTextField()));
			contentPanel.add(makeFormattedTextFieldPanel("URL:",
					getURLTextField()));
			contentPanel.add(makeFormattedTextFieldPanel("Phone Number:",
					getPhoneNumberTextField()));
			contentPanel.add(makeFormattedTextFieldPanel("IP Address:",
					getIPAddressTextField()));
		}
		return contentPanel;
	}

	/**
	 * 返回根据当前默认语言环境的整数格式作为格式化器的整数格式化文本域
	 * <p>
	 * 界面效果：</br>
	 * 输入非法数据，焦点离开之后会自动将格式化文本域的显示值设为原值，
	 * (输入非法数据前的有效值)，并且文本域的getValue()保持原值。
	 */
	private JFormattedTextField getIntTextField() {
		JFormattedTextField intField = new JFormattedTextField(NumberFormat
				.getIntegerInstance());
		intField.setValue(new Integer(100));
		return intField;
	}

	/**
	 * 返回根据当前默认语言环境的整数格式作为格式化器的整数格式化文本域。
	 * 该文本域在焦点丢失之后的行为是"提交"(JFormattedTextField.COMMIT)。
	 * <p>
	 * 界面效果：</br>
	 * 输入非法数据，焦点离开之后会将文本域的显示值将保留该非法值，
	 * 但是并且文本域的getValue()保持原值(输入非法数据前的有效值)。
	 */
	private JFormattedTextField getIntTextFieldWithCommitBehavior() {
		JFormattedTextField intField = new JFormattedTextField(NumberFormat
				.getIntegerInstance());
		intField.setValue(new Integer(100));
		/*
		 * 当格式化的文本域失去焦点时，格式化器查看用户输入的文本字符串。
		 * 如果格式化器知道如何把文本字符串转换为对象，文本就有效，否则就无效。
		 * 失去焦点的默认行为被称为"提交或恢复"：
		 * 如果文本字符串是有效的，它将被提交(commit)，格式化器将它转换为对象，该对象成为当前文本域的值(getValue())。
		 * 然后该值被转换为字符串，成为文本域中可见的文本字符串。
		 * 如果文本字符串无效，当前值(getValue())就不会改变，文本域恢复(REVERT)表示原值的字符串。
		 * 
		 * 这里焦点丢失使用的是"提交"：
		 * 如果文本字符串无效，当前值(getValue())就不会改变，文本域则将保留无效值。 
		 */
		intField.setFocusLostBehavior(JFormattedTextField.COMMIT);
		return intField;
	}

	/**
	 * 返回带有自定义文档过滤器的整数格式化文本域
	 * <p>
	 * 界面效果：</br>
	 * 完全禁止输入非法数据。
	 */
	private JFormattedTextField getIntTextFieldWithFormatter() {
		/*
		 * 格式化文本域的基本功能在大多数情况已经够用了。
		 * 如果可能希望完全阻止用户输入非法值，就要用到文档过滤器了(document filter)。
		 * 安装文档过滤器没有一个直接的方法，需要覆盖格式器类的getDocumentFilter方法，
		 * 并传递一个文档过滤器类的对象给JFormattedTextField。
		 * 
		 * 过滤器的另一个用途是把字符串中的所有字符变成大写，在过滤器的insertString和replace方法中，
		 * 把要被插入的字符串转换成大写，然后调用超类的方法。
		 */
		JFormattedTextField intField = new JFormattedTextField(
				new InternationalFormatter(NumberFormat.getIntegerInstance()) {
					private static final long serialVersionUID = 1L;

					private DocumentFilter filter = new IntFilter();

					protected DocumentFilter getDocumentFilter() {
						return filter;
					}
				});
		intField.setValue(new Integer(100));
		return intField;
	}

	/**
	 * 返回带有校验器的整数格式化文本域
	 * <p>
	 * 界面效果：</br>
	 * 输入非法数据后焦点会强制设在文本域上，迫使用户将非法数据修改成合法的。
	 */
	private JFormattedTextField getIntTextFieldWithInputVerifier() {
		JFormattedTextField intField = new JFormattedTextField(NumberFormat
				.getIntegerInstance());
		intField.setValue(new Integer(100));
		/*
		 * 这是另外一种潜在的实用机制，可用来警告警告用户以避免无效的输入。
	     * 可以给任何JComponent附加校验器(verifier)。如果组件丢失焦点，就询问校验器。
	     * 如果校验器报告组件中的内容是无效的，组件就会马上重获焦点。
	     * 因此，用户在提供其他输入之前，必须先修正无效的内容。
	     * 
	     * 校验器并不是万无一失的，如果点击按钮，按钮会在无效组件重新获得焦点之前通知它的动作监听器
	     * 动作监听器会从验证失败的组件得到无效的结果。
		 */
		intField.setInputVerifier(new FormattedTextFieldVerifier());
		return intField;
	}

	/**
	 * 返回货币格式化文本域
	 * <p>
	 * 界面效果：
	 * 同getIntTextField()
	 */
	private JFormattedTextField getCurrencyTextField() {
		JFormattedTextField currencyField = new JFormattedTextField(
				NumberFormat.getCurrencyInstance());
		currencyField.setValue(new Double(10));
		return currencyField;
	}

	/**
	 * 返回日期格式化文本域
	 * <p>
	 * 界面效果：
	 * 同getIntTextField()
	 */
	private JFormattedTextField getDateTextField() {
		JFormattedTextField dateField = new JFormattedTextField(DateFormat
				.getDateInstance());
		dateField.setValue(new Date());
		return dateField;
	}

	/**
	 * 返回简单日期格式化文本域
	 * <p>
	 * 界面效果：
	 * 同getIntTextField()
	 */
	private JFormattedTextField getShortDateTextField() {
		DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
		format.setLenient(false);
		JFormattedTextField dateField = new JFormattedTextField(format);
		dateField.setValue(new Date());
		return dateField;
	}

	/**
	 * 返回URL格式化文本域
	 * <p>
	 * 界面效果：
	 * 同getIntTextField()
	 */
	private JFormattedTextField getURLTextField() {
		/*
		 * DefaultFormatter可以格式化任何类的对象，只要该类有一个字符串类型参数的构造器和匹配的toString方法。
		 * 
		 * URL类有一个URL(String)构造器利用字符串构造URL，所以可以使用DefaultFormatter格式化URL对象。
		 * 格式器针对文本域值调用toString方法来初始化文本域的文本。
		 * 当文本域失去焦点时，格式器使用带有String参数的构造器构造相同类的新对象作为当前值。
		 * 如果构造器抛出了异常，编辑就是无效的。
		 */
		DefaultFormatter formatter = new DefaultFormatter();
		formatter.setOverwriteMode(false);
		JFormattedTextField urlField = new JFormattedTextField(formatter);
		try {
			urlField.setValue(new URL("http://java.sun.com"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return urlField;
	}

	/**
	 * 返回电话号码格式化文本域
	 * <p>
	 * 界面效果：
	 * 非数字不可输入，输入长度固定不变，删除字符会自动补0
	 */
	private JFormattedTextField getPhoneNumberTextField() {
		/*
		 * MaskFormatter对包含一些常量和一些变量字符的固定大小的样式很有用。
		 * 例如邮编和电话号码。
		 * 
		 * 字符           描述
		 *  #   任何有效数字，使用 Character.isDigit。 
		 *  '   转义字符，用于避开任何具有特殊格式的字符。 
		 *  U   任何字符 (Character.isLetter)。将所有小写字母映射为大写。 
		 *  L   任何字符 (Character.isLetter)。将所有大写字母映射为小写。 
		 *  A   任何字符或数字（Character.isLetter 或 Character.isDigit） 
		 *  ?   任何字符 (Character.isLetter)。 
		 *  *   所有字符。 
		 *  H   任何十六进制字符（0-9、a-f 或 A-F）。 
		 *  
		 *  可以通过调用MaskFormatter类的下列方法限制输入文本域的字符：
		 *  setValidCharacters()：返回可以输入的有效字符。
		 *  setInvalidCharacters()：允许进一步限制可以输入的字符。
		 *  只允许输入指定的validCharacters而不是invalidCharacters。
		 */
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter("####-#######");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 如果用户在编辑过程中删除字符，那么它们将被占位符(place holder character)替代
		formatter.setPlaceholderCharacter('0');
		JFormattedTextField field = new JFormattedTextField(formatter);
		field.setValue("0539-2179184");
		return field;
	}

	/**
	 * 返回IP地址格式化文本域
	 */
	private JFormattedTextField getIPAddressTextField() {
		/* 如果所有的标准格式器都不适用，就需要定义自己的格式器 */
		JFormattedTextField ipField = new JFormattedTextField(
				new IPAddressFormatter());
		ipField.setValue(new byte[] { (byte) 130, 65, 86, 66 });
		return ipField;
	}

	public JPanel makeFormattedTextFieldPanel(String labelText,
			final JFormattedTextField field) {
		JPanel panel = new JPanel();
		GridLayout layout = new GridLayout(1, 3);
		layout.setHgap(5);
		panel.setLayout(layout);
		JLabel label = new JLabel(labelText);
		final JLabel valueLabel = new JLabel();
		panel.add(label);
		panel.add(field);
		panel.add(valueLabel);
		
		field.setBorder(BorderFactory.createLineBorder());
		
		getButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Object value = field.getValue();
				if (value.getClass().isArray()) {
					StringBuffer buffer = new StringBuffer();
					buffer.append('{');
					for (int i = 0; i < Array.getLength(value); i++) {
						if (i > 0)
							buffer.append('.');
						buffer.append(Array.get(value, i).toString());
					}
					buffer.append('}');
					valueLabel.setText(buffer.toString());
				} else
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

	/**
	 * 整数文本过滤器(只有数字和负号(-)不会被过滤)
	 */
	private class IntFilter extends DocumentFilter {
		
		public void insertString(FilterBypass fb, int offset, String string,
				AttributeSet attr) throws BadLocationException {
			StringBuffer buffer = new StringBuffer(string);
			for (int i = buffer.length() - 1; i >= 0; i--) {
				char ch = buffer.charAt(i);
				if (!Character.isDigit(ch) && ch != '-')
					buffer.deleteCharAt(i);
			}
			super.insertString(fb, offset, buffer.toString(), attr);
		}

		public void replace(FilterBypass fb, int offset, int length,
				String string, AttributeSet attr) throws BadLocationException {
			if (string != null) {
				StringBuffer buffer = new StringBuffer(string);
				for (int i = buffer.length() - 1; i >= 0; i--) {
					char ch = buffer.charAt(i);
					if (!Character.isDigit(ch) && ch != '-')
						buffer.deleteCharAt(i);
				}
				string = buffer.toString();
			}
			super.replace(fb, offset, length, string, attr);
		}
	}

	/**
	 * JFormattedTextField的校验器
	 * <p>
	 * 检验器必须扩展抽象类InputVerifier，并且定义verify方法。
	 */
	private class FormattedTextFieldVerifier extends InputVerifier {
		
		public boolean verify(JComponent component) {
			// isEditValid方法调用格式器，如果格式器可以把文本字符转化为对象则返回true。
			return ((JFormattedTextField) component).isEditValid();
		}
	}

	/**
	 * IP地址格式器
	 * <p>
	 * 自定义格式器需要继承自DefaultFormatter，并且覆盖valueToString和stringToValue方法。
	 */
	private class IPAddressFormatter extends DefaultFormatter {
		private static final long serialVersionUID = 1L;

		/**
		 * 把值转换为显示在文本域中的字符串
		 */
		public String valueToString(Object value) throws ParseException {
			if (!(value instanceof byte[]))
				throw new ParseException("Not a byte[]", 0);
			byte[] a = (byte[]) value;
			if (a.length != 4)
				throw new ParseException("Length != 4", 0);
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < 4; i++) {
				int b = a[i];
				if (b < 0)
					b += 256;
				buffer.append(String.valueOf(b));
				if (i < 3)
					buffer.append('.');
			}
			return buffer.toString();
		}

		/**
		 * 解析输入文本并转换为对象
		 */
		public Object stringToValue(String text) throws ParseException {
			byte[] a = new byte[4];
			if (text != null) {
				String[] values = text.split(".");
				if (values == null || values.length != 4) {
					throw new ParseException("Length != 4", 0);
				}
				for (int i = 0; i < 4; i++) {
					int b = 0;
					try {
						b = Integer.parseInt(values[i]);
					} catch (NumberFormatException e) {
						throw new ParseException("Not an integer", 0);
					}
					if (b < 0 || b >= 256)
						throw new ParseException("Byte out of range", 0);
					a[i] = (byte) b;
				}
			}
			return a;
		}
	}
}