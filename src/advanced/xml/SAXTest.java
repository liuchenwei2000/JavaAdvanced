/**
 * 
 */
package xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import util.Printer;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 读取XML文件演示
 * 
 * @author 刘晨伟
 * 
 * 创建时间：2008-1-9
 */
public class SAXTest {

	private static Map<String, String> docName_docUI_Map;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileInputStream fin = null;
		DefaultHandler handler = null;
		try {
			fin = new FileInputStream("files/xml/register.xml");
			handler = new DefaultHandler() {

				public void startElement(String uri, String localName,
						String name, Attributes attributes) throws SAXException {
					if (localName.equalsIgnoreCase("doc") && attributes != null) {
						getMap().put(attributes.getValue("name"),
								attributes.getValue("ui"));
					}
				}
			};
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(true);
		try {
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(fin, handler);
			fin.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Printer.printAll(getMap());
	}

	private static Map<String, String> getMap() {
		if (docName_docUI_Map == null) {
			docName_docUI_Map = new HashMap<String, String>();
		}
		return docName_docUI_Map;
	}
}