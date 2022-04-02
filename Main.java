import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class btn extends JButton{
	public btn(String name,Color color) {
		super(name);
		super.setBackground(color);
	}
}

public class Main {

	private JFrame frame;
	private JPanel panel;
	private JButton btnDelete;
	private JTextArea createNote;
	private JTextArea outputResult;
	private JList<String> tArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Query.getData();
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 820, 526);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(176, 196, 222));
		panel.setBounds(0, 0, 820, 526);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel titlePanel_1 = new JPanel();
		titlePanel_1.setBounds(0, 0, 810, 64);
		panel.add(titlePanel_1);
		titlePanel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NoteBook");
		lblNewLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 19));
		lblNewLabel.setBounds(340, 26, 104, 27);
		titlePanel_1.add(lblNewLabel);
		
		JPanel writePanel = new JPanel();
		writePanel.setBounds(496, 63, 314, 437);
		panel.add(writePanel);
		writePanel.setLayout(null);
		
		JLabel lblCreateNewNote = new JLabel("Create New Note");
		lblCreateNewNote.setBounds(29, 30, 212, 14);
		writePanel.add(lblCreateNewNote);
		
		createNote = new JTextArea();
		createNote.setText("Write Your Note");
		JScrollPane inp = new JScrollPane(createNote,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		inp.setBounds(23, 55, 259, 239);
		writePanel.add(inp);
		writePanel.add(inp);
		
		
		outputResult = new JTextArea();
		outputResult.setText("Output Result Goes Here");
		JScrollPane otp = new JScrollPane(outputResult,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		otp.setBounds(23, 305, 259, 110);
		writePanel.add(otp);
		writePanel.add(otp);
		
		JPanel readPanel = new JPanel();
		readPanel.setBounds(0, 63, 496, 437);
		panel.add(readPanel);
		readPanel.setLayout(null);
		Object[] notes=  Query.notes.toArray();
		tArea=new JList<String>();
		tArea.setFont(new Font("Tahoma", Font.BOLD, 16));
		tArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tArea.setVisibleRowCount(4);
		tArea.setModel(new AbstractListModel<String>() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] values = new String[Query.notes.size()] ;
			private void load() {
				for(int a=0;a<Query.notes.size();a++) {
					values[a]=Query.notes.get(a);
				}
			}
		
			
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				load();
				return values[index];
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(tArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 56, 476, 288);
		readPanel.add(scrollPane);
		
		JLabel lblYourAllNote = new JLabel("Your All Note Visible Here");
		lblYourAllNote.setBounds(27, 23, 212, 14);
		readPanel.add(lblYourAllNote);
		
		btnDelete = new btn("Delete",Color.RED);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selected = tArea.getSelectedIndex();
				String data= Query.notes.get(selected).split(" ")[0];
				try {
					int recordId=Integer.valueOf(data.substring(0,data.length()-1));
					if (Query.delete(recordId)>0) {
						outputResult.setText("Delete Successfully");
					}else {
						outputResult.setText("Delete Failed");
					}
					
				} catch (Exception e2) {
					System.out.println("id wrong");
				}
				
				
			}
		});
		btnDelete.setBounds(27, 370, 89, 23);
		readPanel.add(btnDelete);
		
		btn btnUpdate = new btn("Delete", Color.ORANGE);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUpdate.setText("Update");
		btnUpdate.setBounds(195, 370, 89, 23);
		readPanel.add(btnUpdate);
		
		btn btnCreate = new btn("Delete", Color.GREEN);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newNote = createNote.getText();
				int stat=Query.write(newNote);
				if(stat>0) {
					outputResult.setText("Record Inserted");
				}
			}
		});
		btnCreate.setText("Create");
		btnCreate.setBounds(359, 370, 89, 23);
		readPanel.add(btnCreate);
		
		
		
	}
}
