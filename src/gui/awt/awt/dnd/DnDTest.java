/**
 * 
 */
package awt.dnd;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;

import util.Displayer;

/**
 * 
 * 
 * @author 刘晨伟
 *
 * 创建日期：2009-6-8
 */
public class DnDTest extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("DnDTest Demo", new DnDTest());
	}

	private static final DataFlavor STUDENT_FLAVOR = new DataFlavor(Student.class,"student");
	
	private String[] names = {"Tom","Jim","Sam","Lily","Lucy"};
	private char[] sexes = {'M','M','M','F','F'};
	private int[] ages = {18,19,20,21,21};
	
	private JPanel leftPanel;
	private JPanel rightPanel;
	
	private JList list;
	private JTextArea area;
	
	public DnDTest(){
		setLayout(new GridLayout(1,2));
		add(getLeftPanel());
		add(getRightPanel());
	}

	private JPanel getLeftPanel() {
		if(leftPanel == null){
			leftPanel = new JPanel();
			leftPanel.setLayout(new BorderLayout());
			leftPanel.add(new JScrollPane(getList()));
			
		}
		return leftPanel;
	}

	private JList getList(){
		if(list == null){
			list = new JList(getListModel());
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			list.setDragEnabled(true);
			list.setTransferHandler(new ListTransferHandler());
		}
		return list;
	}
	
	private ListModel getListModel() {
		DefaultListModel model = new DefaultListModel();
		for (int i = 0; i < names.length; i++) {
			model.addElement(new Student(names[i],sexes[i],ages[i]));
		}
		model.addElement(new Teacher("Mr.Smith"));
		return model;
	}

	private class Teacher{
		
		private String name;
		
		public Teacher(String name) {
			this.name = name;
		}

		public String toString() {
			return name;
		}
	
	}
	
	private class Student {
		
		private String name;
		private char sex;
		private int age;
		
		public Student(String name,char sex,int age){
			this.name = name;
			this.sex = sex;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public char getSex() {
			return sex;
		}

		public int getAge() {
			return age;
		}

		public String toString(){
			return name;
		}
	}
	
	private JPanel getRightPanel() {
		if(rightPanel == null){
			rightPanel = new JPanel();
			rightPanel.setLayout(new BorderLayout());
			rightPanel.add(new JScrollPane(getArea()));
		}
		return rightPanel;
	}
	
	private JTextArea getArea(){
		if(area == null) {
			area = new JTextArea(10,20);
			area.setTransferHandler(new AreaTransferHandler());
		}
		return area;
	}

	private class StudentTransferable implements Transferable{

		private Student student;
		
		public StudentTransferable(Student student){
			this.student = student;
		}
		
		public Object getTransferData(DataFlavor flavor)
				throws UnsupportedFlavorException, IOException {
			return student ;
		}

		public DataFlavor[] getTransferDataFlavors() {
			return new DataFlavor[]{STUDENT_FLAVOR};
		}

		public boolean isDataFlavorSupported(DataFlavor flavor) {
			return STUDENT_FLAVOR.equals(flavor);
		}
		
	}
	
    private class ListTransferHandler extends TransferHandler {
		private static final long serialVersionUID = 1L;

		protected Transferable createTransferable(JComponent c) {
			JList list = (JList)c;
			if(list.getSelectedValue() instanceof Student){
				Student student = (Student)list.getSelectedValue();
				return new StudentTransferable(student);
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
				return STUDENT_FLAVOR.equals(transferFlavors[0]);
			}
			return false;
		}
		
		public boolean importData(JComponent comp, Transferable t) {
			if(canImport(comp,t.getTransferDataFlavors())){
				try {
					Student student = (Student)(t.getTransferData(STUDENT_FLAVOR));
					getArea().setText("Student：\n");
					getArea().append(student.getName());
					getArea().append("\n");
					getArea().append(student.getSex()+"\n");
					getArea().append(student.getAge()+"\n");
				} catch (UnsupportedFlavorException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return false;
		}
	}
}
