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
 * JFormattedTextField��ʽ���ı�����ʾ��
 * <p>
 * Ĭ����Ϊ�ǣ�</br>
 * ����Ƿ����ݣ������뿪֮����Զ�����ʽ���ı������ʾֵ��Ϊԭֵ��
 * (����Ƿ�����ǰ����Чֵ)�������ı����getValue()����ԭֵ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-6-5
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
	 * ���ظ��ݵ�ǰĬ�����Ի�����������ʽ��Ϊ��ʽ������������ʽ���ı���
	 * <p>
	 * ����Ч����</br>
	 * ����Ƿ����ݣ������뿪֮����Զ�����ʽ���ı������ʾֵ��Ϊԭֵ��
	 * (����Ƿ�����ǰ����Чֵ)�������ı����getValue()����ԭֵ��
	 */
	private JFormattedTextField getIntTextField() {
		JFormattedTextField intField = new JFormattedTextField(NumberFormat
				.getIntegerInstance());
		intField.setValue(new Integer(100));
		return intField;
	}

	/**
	 * ���ظ��ݵ�ǰĬ�����Ի�����������ʽ��Ϊ��ʽ������������ʽ���ı���
	 * ���ı����ڽ��㶪ʧ֮�����Ϊ��"�ύ"(JFormattedTextField.COMMIT)��
	 * <p>
	 * ����Ч����</br>
	 * ����Ƿ����ݣ������뿪֮��Ὣ�ı������ʾֵ�������÷Ƿ�ֵ��
	 * ���ǲ����ı����getValue()����ԭֵ(����Ƿ�����ǰ����Чֵ)��
	 */
	private JFormattedTextField getIntTextFieldWithCommitBehavior() {
		JFormattedTextField intField = new JFormattedTextField(NumberFormat
				.getIntegerInstance());
		intField.setValue(new Integer(100));
		/*
		 * ����ʽ�����ı���ʧȥ����ʱ����ʽ�����鿴�û�������ı��ַ�����
		 * �����ʽ����֪����ΰ��ı��ַ���ת��Ϊ�����ı�����Ч���������Ч��
		 * ʧȥ�����Ĭ����Ϊ����Ϊ"�ύ��ָ�"��
		 * ����ı��ַ�������Ч�ģ��������ύ(commit)����ʽ��������ת��Ϊ���󣬸ö����Ϊ��ǰ�ı����ֵ(getValue())��
		 * Ȼ���ֵ��ת��Ϊ�ַ�������Ϊ�ı����пɼ����ı��ַ�����
		 * ����ı��ַ�����Ч����ǰֵ(getValue())�Ͳ���ı䣬�ı���ָ�(REVERT)��ʾԭֵ���ַ�����
		 * 
		 * ���ｹ�㶪ʧʹ�õ���"�ύ"��
		 * ����ı��ַ�����Ч����ǰֵ(getValue())�Ͳ���ı䣬�ı����򽫱�����Чֵ�� 
		 */
		intField.setFocusLostBehavior(JFormattedTextField.COMMIT);
		return intField;
	}

	/**
	 * ���ش����Զ����ĵ���������������ʽ���ı���
	 * <p>
	 * ����Ч����</br>
	 * ��ȫ��ֹ����Ƿ����ݡ�
	 */
	private JFormattedTextField getIntTextFieldWithFormatter() {
		/*
		 * ��ʽ���ı���Ļ��������ڴ��������Ѿ������ˡ�
		 * �������ϣ����ȫ��ֹ�û�����Ƿ�ֵ����Ҫ�õ��ĵ���������(document filter)��
		 * ��װ�ĵ�������û��һ��ֱ�ӵķ�������Ҫ���Ǹ�ʽ�����getDocumentFilter������
		 * ������һ���ĵ���������Ķ����JFormattedTextField��
		 * 
		 * ����������һ����;�ǰ��ַ����е������ַ���ɴ�д���ڹ�������insertString��replace�����У�
		 * ��Ҫ��������ַ���ת���ɴ�д��Ȼ����ó���ķ�����
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
	 * ���ش���У������������ʽ���ı���
	 * <p>
	 * ����Ч����</br>
	 * ����Ƿ����ݺ󽹵��ǿ�������ı����ϣ���ʹ�û����Ƿ������޸ĳɺϷ��ġ�
	 */
	private JFormattedTextField getIntTextFieldWithInputVerifier() {
		JFormattedTextField intField = new JFormattedTextField(NumberFormat
				.getIntegerInstance());
		intField.setValue(new Integer(100));
		/*
		 * ��������һ��Ǳ�ڵ�ʵ�û��ƣ����������澯���û��Ա�����Ч�����롣
	     * ���Ը��κ�JComponent����У����(verifier)����������ʧ���㣬��ѯ��У������
	     * ���У������������е���������Ч�ģ�����ͻ������ػ񽹵㡣
	     * ��ˣ��û����ṩ��������֮ǰ��������������Ч�����ݡ�
	     * 
	     * У��������������һʧ�ģ���������ť����ť������Ч������»�ý���֮ǰ֪ͨ���Ķ���������
	     * ���������������֤ʧ�ܵ�����õ���Ч�Ľ����
		 */
		intField.setInputVerifier(new FormattedTextFieldVerifier());
		return intField;
	}

	/**
	 * ���ػ��Ҹ�ʽ���ı���
	 * <p>
	 * ����Ч����
	 * ͬgetIntTextField()
	 */
	private JFormattedTextField getCurrencyTextField() {
		JFormattedTextField currencyField = new JFormattedTextField(
				NumberFormat.getCurrencyInstance());
		currencyField.setValue(new Double(10));
		return currencyField;
	}

	/**
	 * �������ڸ�ʽ���ı���
	 * <p>
	 * ����Ч����
	 * ͬgetIntTextField()
	 */
	private JFormattedTextField getDateTextField() {
		JFormattedTextField dateField = new JFormattedTextField(DateFormat
				.getDateInstance());
		dateField.setValue(new Date());
		return dateField;
	}

	/**
	 * ���ؼ����ڸ�ʽ���ı���
	 * <p>
	 * ����Ч����
	 * ͬgetIntTextField()
	 */
	private JFormattedTextField getShortDateTextField() {
		DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
		format.setLenient(false);
		JFormattedTextField dateField = new JFormattedTextField(format);
		dateField.setValue(new Date());
		return dateField;
	}

	/**
	 * ����URL��ʽ���ı���
	 * <p>
	 * ����Ч����
	 * ͬgetIntTextField()
	 */
	private JFormattedTextField getURLTextField() {
		/*
		 * DefaultFormatter���Ը�ʽ���κ���Ķ���ֻҪ������һ���ַ������Ͳ����Ĺ�������ƥ���toString������
		 * 
		 * URL����һ��URL(String)�����������ַ�������URL�����Կ���ʹ��DefaultFormatter��ʽ��URL����
		 * ��ʽ������ı���ֵ����toString��������ʼ���ı�����ı���
		 * ���ı���ʧȥ����ʱ����ʽ��ʹ�ô���String�����Ĺ�����������ͬ����¶�����Ϊ��ǰֵ��
		 * ����������׳����쳣���༭������Ч�ġ�
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
	 * ���ص绰�����ʽ���ı���
	 * <p>
	 * ����Ч����
	 * �����ֲ������룬���볤�ȹ̶����䣬ɾ���ַ����Զ���0
	 */
	private JFormattedTextField getPhoneNumberTextField() {
		/*
		 * MaskFormatter�԰���һЩ������һЩ�����ַ��Ĺ̶���С����ʽ�����á�
		 * �����ʱ�͵绰���롣
		 * 
		 * �ַ�           ����
		 *  #   �κ���Ч���֣�ʹ�� Character.isDigit�� 
		 *  '   ת���ַ������ڱܿ��κξ��������ʽ���ַ��� 
		 *  U   �κ��ַ� (Character.isLetter)��������Сд��ĸӳ��Ϊ��д�� 
		 *  L   �κ��ַ� (Character.isLetter)�������д�д��ĸӳ��ΪСд�� 
		 *  A   �κ��ַ������֣�Character.isLetter �� Character.isDigit�� 
		 *  ?   �κ��ַ� (Character.isLetter)�� 
		 *  *   �����ַ��� 
		 *  H   �κ�ʮ�������ַ���0-9��a-f �� A-F���� 
		 *  
		 *  ����ͨ������MaskFormatter������з������������ı�����ַ���
		 *  setValidCharacters()�����ؿ����������Ч�ַ���
		 *  setInvalidCharacters()�������һ�����ƿ���������ַ���
		 *  ֻ��������ָ����validCharacters������invalidCharacters��
		 */
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter("####-#######");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// ����û��ڱ༭������ɾ���ַ�����ô���ǽ���ռλ��(place holder character)���
		formatter.setPlaceholderCharacter('0');
		JFormattedTextField field = new JFormattedTextField(formatter);
		field.setValue("0539-2179184");
		return field;
	}

	/**
	 * ����IP��ַ��ʽ���ı���
	 */
	private JFormattedTextField getIPAddressTextField() {
		/* ������еı�׼��ʽ���������ã�����Ҫ�����Լ��ĸ�ʽ�� */
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
	 * �����ı�������(ֻ�����ֺ͸���(-)���ᱻ����)
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
	 * JFormattedTextField��У����
	 * <p>
	 * ������������չ������InputVerifier�����Ҷ���verify������
	 */
	private class FormattedTextFieldVerifier extends InputVerifier {
		
		public boolean verify(JComponent component) {
			// isEditValid�������ø�ʽ���������ʽ�����԰��ı��ַ�ת��Ϊ�����򷵻�true��
			return ((JFormattedTextField) component).isEditValid();
		}
	}

	/**
	 * IP��ַ��ʽ��
	 * <p>
	 * �Զ����ʽ����Ҫ�̳���DefaultFormatter�����Ҹ���valueToString��stringToValue������
	 */
	private class IPAddressFormatter extends DefaultFormatter {
		private static final long serialVersionUID = 1L;

		/**
		 * ��ֵת��Ϊ��ʾ���ı����е��ַ���
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
		 * ���������ı���ת��Ϊ����
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