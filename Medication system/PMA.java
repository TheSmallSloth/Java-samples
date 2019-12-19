import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;

import javax.swing.JTextPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import java.util.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.JTextField;

import java.io.*;

public class PMA {
	private ArrayList<Medication> medication;
	
	private JFrame jframe;
	private JTable table;
	private JScrollPane tableScrollPane;
	private JLabel prescribedMedHeader;
	private JButton prescribedMedBack;
	private JLabel mainMenuText;
	private JButton seePrescriptionsButton;
	private JButton addMedManuallyButton;
	private JTextField drugNameTextField;
	private JLabel drugNameTextLabel;
	private JTextField drugCompanyTextField;
	private JLabel drugCompanyTextLabel;
	private JTextField drugStrengthTextField;
	private JLabel drugStrengthTextLabel;
	private JTextField drugTPPTextField;
	private JLabel drugTPPTextLabel;
	private JButton manualAddButton;
	private JButton addByBarcodeButton;
	private JLabel barcodeLabel;
	private JTextField addByBarcodeText;
	private JButton barcodeAddButton;
	private JLabel redLight;
	private JLabel greenLight;
	private JLabel yellowLight;
	private JLabel isDrugSafeText;
	private JButton acceptDrug;
	private JButton declineDrug;
	private Medication potentialAdd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PMA window = new PMA();
					window.jframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PMA() {
		this.medication = new ArrayList<Medication>();
		
		try
		{
			BufferedReader fileReader = new BufferedReader(new FileReader("DataSet.csv"));
			String line;
			String file = "";
			while((line=fileReader.readLine()) != null)
			{
				file+=line+'\n';
			}
			fileReader.close();

			int cursor = 0;
			String name="";
			String company="";
			String barcode="";
			String strength="";
			String tpp="";

			while(cursor < file.length())
			{
				while(file.charAt(cursor) != ',')
				{
					name += file.charAt(cursor);
					cursor++;
				}
				cursor++;
				while(file.charAt(cursor) != ',')
				{
					company += file.charAt(cursor);
					cursor++;
				}
				cursor++;
				while(file.charAt(cursor) != ',')
				{
					strength += file.charAt(cursor);
					cursor++;
				}
				cursor++;
				while(file.charAt(cursor) != ',')
				{
					barcode += file.charAt(cursor);
					cursor++;
				}
				cursor++;
				while(cursor<file.length() && file.charAt(cursor) != '\n')
				{
					tpp += file.charAt(cursor);
					cursor++;
				}
				cursor++;
				
				this.medication.add(new Medication(name, company, barcode, Float.parseFloat(strength), Integer.parseInt(tpp)));
				name = "";
				company = "";
				barcode = "";
				strength = "";
				tpp = "";
			}
		}
		catch (IOException e)
		{
			return;
		}
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		jframe = new JFrame();
		jframe.getContentPane().setForeground(Color.WHITE);
		jframe.getContentPane().setBackground(Color.WHITE);
		jframe.setTitle("PMA");
		jframe.setBounds(100, 100, 800, 800);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.getContentPane().setLayout(null);
		
		tableScrollPane = new JScrollPane();
		tableScrollPane.setBounds(10, 83, 764, 667);
		jframe.getContentPane().add(tableScrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Company", "Drug Name", "Strength", "Tablets per Pack"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Float.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(224);
		table.getColumnModel().getColumn(1).setPreferredWidth(234);
		table.getColumnModel().getColumn(3).setPreferredWidth(89);
		tableScrollPane.setViewportView(table);
		
		prescribedMedHeader = new JLabel();
		prescribedMedHeader.setForeground(Color.BLACK);
		prescribedMedHeader.setFont(new Font("Perpetua Titling MT", Font.PLAIN, 46));
		prescribedMedHeader.setText("Prescribed Medication");
		prescribedMedHeader.setBounds(100, 11, 585, 61);
		jframe.getContentPane().add(prescribedMedHeader);
		
		prescribedMedBack = new JButton("Back");
		prescribedMedBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				tableScrollPane.setVisible(false);
				prescribedMedHeader.setVisible(false);
				prescribedMedBack.setVisible(false);
				mainMenuText.setVisible(true);
				seePrescriptionsButton.setVisible(true);
				addMedManuallyButton.setVisible(true);
				drugNameTextField.setVisible(false);
				drugNameTextLabel.setVisible(false);
				drugCompanyTextField.setVisible(false);
				drugCompanyTextLabel.setVisible(false);
				drugStrengthTextField.setVisible(false);
				drugStrengthTextLabel.setVisible(false);
				drugTPPTextField.setVisible(false);
				drugTPPTextLabel.setVisible(false);
				manualAddButton.setVisible(false);
				addByBarcodeButton.setVisible(true);
				barcodeLabel.setVisible(false);
				addByBarcodeText.setVisible(false);
				barcodeAddButton.setVisible(false);
				redLight.setVisible(false);
				greenLight.setVisible(false);
				yellowLight.setVisible(false);
				isDrugSafeText.setVisible(false);
				acceptDrug.setVisible(false);
				declineDrug.setVisible(false);
				addByBarcodeButton.setBounds(250, 355, 270, 30);
				
				drugNameTextField.setText("");
				drugCompanyTextField.setText("");
				drugStrengthTextField.setText("");
				drugTPPTextField.setText("");
				addByBarcodeText.setText("");
			}
		});
		prescribedMedBack.setBounds(1, 25, 89, 23);
		jframe.getContentPane().add(prescribedMedBack);
		
		tableScrollPane.setVisible(false);
		prescribedMedHeader.setVisible(false);
		prescribedMedBack.setVisible(false);
		
		mainMenuText = new JLabel();
		mainMenuText.setForeground(Color.BLACK);
		mainMenuText.setFont(new Font("Perpetua Titling MT", Font.PLAIN, 46));
		mainMenuText.setText("Main Menu");
		mainMenuText.setBounds(250, 200, 585, 61);
		jframe.getContentPane().add(mainMenuText);
		
		seePrescriptionsButton = new JButton("See current prescribed medication");
		seePrescriptionsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				tableScrollPane.setVisible(true);
				prescribedMedHeader.setVisible(true);
				prescribedMedBack.setVisible(true);
				mainMenuText.setVisible(false);
				seePrescriptionsButton.setVisible(false);
				addMedManuallyButton.setVisible(false);
				drugNameTextField.setVisible(false);
				drugNameTextLabel.setVisible(false);
				drugCompanyTextField.setVisible(false);
				drugCompanyTextLabel.setVisible(false);
				drugStrengthTextField.setVisible(false);
				drugStrengthTextLabel.setVisible(false);
				drugTPPTextField.setVisible(false);
				drugTPPTextLabel.setVisible(false);
				manualAddButton.setVisible(false);
				addByBarcodeButton.setVisible(false);
				barcodeLabel.setVisible(false);
				addByBarcodeText.setVisible(false);
				barcodeAddButton.setVisible(false);
				redLight.setVisible(false);
				greenLight.setVisible(false);
				yellowLight.setVisible(false);
				isDrugSafeText.setVisible(false);
				acceptDrug.setVisible(false);
				declineDrug.setVisible(false);
				addByBarcodeButton.setBounds(250, 355, 270, 30);
				
				drugNameTextField.setText("");
				drugCompanyTextField.setText("");
				drugStrengthTextField.setText("");
				drugTPPTextField.setText("");
				addByBarcodeText.setText("");
			}
		});
		seePrescriptionsButton.setBounds(250, 275, 270, 30);
		jframe.getContentPane().add(seePrescriptionsButton);
		
		addMedManuallyButton = new JButton("Add new medication manually");
		addMedManuallyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				addByBarcodeButton.setBounds(250, 435, 270, 30);
				drugNameTextField.setVisible(true);
				drugNameTextLabel.setVisible(true);
				drugCompanyTextField.setVisible(true);
				drugCompanyTextLabel.setVisible(true);
				drugStrengthTextField.setVisible(true);
				drugStrengthTextLabel.setVisible(true);
				drugTPPTextField.setVisible(true);
				drugTPPTextLabel.setVisible(true);
				manualAddButton.setVisible(true);
				barcodeLabel.setVisible(false);
				addByBarcodeText.setVisible(false);
				barcodeAddButton.setVisible(false);
				
				drugNameTextField.setText("");
				drugCompanyTextField.setText("");
				drugStrengthTextField.setText("");
				drugTPPTextField.setText("");
				addByBarcodeText.setText("");
			}
		});
		addMedManuallyButton.setBounds(250, 315, 270, 30);
		jframe.getContentPane().add(addMedManuallyButton);
		
		drugNameTextField = new JTextField();
		drugNameTextField.setBounds(10, 365, 285, 30);
		jframe.getContentPane().add(drugNameTextField);
		drugNameTextField.setVisible(false);
		
		drugNameTextLabel = new JLabel();
		drugNameTextLabel.setForeground(Color.BLACK);
		drugNameTextLabel.setFont(new Font("Arial Narrow", Font.PLAIN, 15));
		drugNameTextLabel.setText("Drug Name");
		drugNameTextLabel.setBounds(10, 345, 100, 16);
		jframe.getContentPane().add(drugNameTextLabel);
		drugNameTextLabel.setVisible(false);
		
		drugCompanyTextField = new JTextField();
		drugCompanyTextField.setBounds(295, 365, 285, 30);
		jframe.getContentPane().add(drugCompanyTextField);
		drugCompanyTextField.setVisible(false);
		
		drugCompanyTextLabel = new JLabel();
		drugCompanyTextLabel.setForeground(Color.BLACK);
		drugCompanyTextLabel.setFont(new Font("Arial Narrow", Font.PLAIN, 15));
		drugCompanyTextLabel.setText("Drug Company");
		drugCompanyTextLabel.setBounds(290, 345, 100, 16);
		jframe.getContentPane().add(drugCompanyTextLabel);
		drugCompanyTextLabel.setVisible(false);
		
		drugStrengthTextField = new JTextField();
		drugStrengthTextField.setBounds(580, 365, 100, 30);
		jframe.getContentPane().add(drugStrengthTextField);
		drugStrengthTextField.setVisible(false);
		
		drugStrengthTextLabel = new JLabel();
		drugStrengthTextLabel.setForeground(Color.BLACK);
		drugStrengthTextLabel.setFont(new Font("Arial Narrow", Font.PLAIN, 15));
		drugStrengthTextLabel.setText("Drug Strength");
		drugStrengthTextLabel.setBounds(580, 345, 100, 16);
		jframe.getContentPane().add(drugStrengthTextLabel);
		drugStrengthTextLabel.setVisible(false);
		
		drugTPPTextField = new JTextField();
		drugTPPTextField.setBounds(680, 365, 100, 30);
		jframe.getContentPane().add(drugTPPTextField);
		drugTPPTextField.setVisible(false);
		
		drugTPPTextLabel = new JLabel();
		drugTPPTextLabel.setForeground(Color.BLACK);
		drugTPPTextLabel.setFont(new Font("Arial Narrow", Font.PLAIN, 15));
		drugTPPTextLabel.setText("Tablets Per Pack");
		drugTPPTextLabel.setBounds(680, 345, 100, 16);
		jframe.getContentPane().add(drugTPPTextLabel);
		drugTPPTextLabel.setVisible(false);
		
		manualAddButton = new JButton("Add medication");
		manualAddButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				if(drugNameTextField.getText().equals("") || drugCompanyTextField.getText().equals("") || drugStrengthTextField.getText().equals("") || drugTPPTextField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				float drugStr;
				int tpp;
				
				try
				{
					drugStr = Float.parseFloat(drugStrengthTextField.getText());
					tpp = Integer.parseInt(drugTPPTextField.getText());
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Invalid numerical fields entered", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(tpp <= 0)
				{
					JOptionPane.showMessageDialog(null, "Tablets Per Pack must be a positive integer", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(drugStr <= 0)
				{
					JOptionPane.showMessageDialog(null, "Drug Strength must be positive", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				potentialAdd = new Medication(drugNameTextField.getText(), drugCompanyTextField.getText(), "", drugStr, tpp);
				
				isDrugSafeText.setText("<html>" + potentialAdd.getName() + " has been checked for compatibility with your current prescriptions:<br>" + potentialAdd.getName() + " may cause minor side effects if used alongside your current prescriptions, but no major incompatibilities were found</html>");
				
				tableScrollPane.setVisible(false);
				prescribedMedHeader.setVisible(false);
				prescribedMedBack.setVisible(false);
				mainMenuText.setVisible(false);
				seePrescriptionsButton.setVisible(false);
				addMedManuallyButton.setVisible(false);
				drugNameTextField.setVisible(false);
				drugNameTextLabel.setVisible(false);
				drugCompanyTextField.setVisible(false);
				drugCompanyTextLabel.setVisible(false);
				drugStrengthTextField.setVisible(false);
				drugStrengthTextLabel.setVisible(false);
				drugTPPTextField.setVisible(false);
				drugTPPTextLabel.setVisible(false);
				manualAddButton.setVisible(false);
				addByBarcodeButton.setVisible(false);
				barcodeLabel.setVisible(false);
				addByBarcodeText.setVisible(false);
				barcodeAddButton.setVisible(false);
				redLight.setVisible(true);
				greenLight.setVisible(true);
				yellowLight.setVisible(true);
				isDrugSafeText.setVisible(true);
				acceptDrug.setVisible(true);
				declineDrug.setVisible(true);
				addByBarcodeButton.setBounds(250, 355, 270, 30);
				
				drugNameTextField.setText("");
				drugCompanyTextField.setText("");
				drugStrengthTextField.setText("");
				drugTPPTextField.setText("");
				addByBarcodeText.setText("");
			}
		});
		manualAddButton.setBounds(325, 395, 150, 30);
		jframe.getContentPane().add(manualAddButton);
		manualAddButton.setVisible(false);
		
		addByBarcodeButton = new JButton("Add new medication by barcode");
		addByBarcodeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				addByBarcodeButton.setBounds(250, 355, 270, 30);
				barcodeLabel.setVisible(true);
				addByBarcodeText.setVisible(true);
				drugNameTextField.setVisible(false);
				drugNameTextLabel.setVisible(false);
				drugCompanyTextField.setVisible(false);
				drugCompanyTextLabel.setVisible(false);
				drugStrengthTextField.setVisible(false);
				drugStrengthTextLabel.setVisible(false);
				drugTPPTextField.setVisible(false);
				drugTPPTextLabel.setVisible(false);
				manualAddButton.setVisible(false);
				barcodeAddButton.setVisible(true);
				
				drugNameTextField.setText("");
				drugCompanyTextField.setText("");
				drugStrengthTextField.setText("");
				drugTPPTextField.setText("");
				addByBarcodeText.setText("");
			}
		});
		addByBarcodeButton.setBounds(250, 355, 270, 30);
		jframe.getContentPane().add(addByBarcodeButton);
		
		barcodeLabel = new JLabel();
		barcodeLabel.setForeground(Color.BLACK);
		barcodeLabel.setFont(new Font("Arial Narrow", Font.PLAIN, 15));
		barcodeLabel.setText("Barcode");
		barcodeLabel.setBounds(250, 385, 100, 16);
		jframe.getContentPane().add(barcodeLabel);
		barcodeLabel.setVisible(false);
		
		addByBarcodeText = new JTextField();
		addByBarcodeText.setBounds(250, 405, 270, 30);
		jframe.getContentPane().add(addByBarcodeText);
		addByBarcodeText.setVisible(false);
		
		barcodeAddButton = new JButton("Add Medication");
		barcodeAddButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				if(addByBarcodeText.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "No barcode entered", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				tableScrollPane.setVisible(false);
				prescribedMedHeader.setVisible(false);
				prescribedMedBack.setVisible(false);
				mainMenuText.setVisible(false);
				seePrescriptionsButton.setVisible(false);
				addMedManuallyButton.setVisible(false);
				drugNameTextField.setVisible(false);
				drugNameTextLabel.setVisible(false);
				drugCompanyTextField.setVisible(false);
				drugCompanyTextLabel.setVisible(false);
				drugStrengthTextField.setVisible(false);
				drugStrengthTextLabel.setVisible(false);
				drugTPPTextField.setVisible(false);
				drugTPPTextLabel.setVisible(false);
				manualAddButton.setVisible(false);
				addByBarcodeButton.setVisible(false);
				barcodeLabel.setVisible(false);
				addByBarcodeText.setVisible(false);
				barcodeAddButton.setVisible(false);
				redLight.setVisible(true);
				greenLight.setVisible(true);
				yellowLight.setVisible(true);
				isDrugSafeText.setVisible(true);
				acceptDrug.setVisible(true);
				declineDrug.setVisible(true);
				addByBarcodeButton.setBounds(250, 355, 270, 30);
				
				isDrugSafeText.setText("<html>" + addByBarcodeText.getText() + " has been checked for compatibility with your current prescriptions:<br>" + addByBarcodeText.getText() + " may cause minor side effects if used alongside your current prescriptions, but no major incompatibilities were found</html>");
				
				drugNameTextField.setText("");
				drugCompanyTextField.setText("");
				drugStrengthTextField.setText("");
				drugTPPTextField.setText("");
				addByBarcodeText.setText("");
				
				potentialAdd = null;
			}
		});
		barcodeAddButton.setBounds(325, 435, 150, 30);
		jframe.getContentPane().add(barcodeAddButton);
		barcodeAddButton.setVisible(false);
		
		redLight = new JLabel();
		redLight.setBounds(10, 10, 100, 100);
		redLight.setBackground(new Color(255,200,200));
		redLight.setOpaque(true);
		redLight.setVisible(false);
		jframe.getContentPane().add(redLight);
		
		yellowLight = new JLabel();
		yellowLight.setBounds(10, 320, 100, 100);
		yellowLight.setBackground(Color.YELLOW);
		yellowLight.setOpaque(true);
		yellowLight.setVisible(false);
		jframe.getContentPane().add(yellowLight);
		
		greenLight = new JLabel();
		greenLight.setBounds(10, 650, 100, 100);
		greenLight.setBackground(new Color(200,255,200));
		greenLight.setOpaque(true);
		greenLight.setVisible(false);
		jframe.getContentPane().add(greenLight);
		
		isDrugSafeText = new JLabel();
		isDrugSafeText.setBounds(130, 100, 600,200);
		isDrugSafeText.setVisible(false);
		jframe.getContentPane().add(isDrugSafeText);
		
		acceptDrug = new JButton("Add to prescriptions");
		acceptDrug.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				if(potentialAdd != null)
				{
					medication.add(potentialAdd);
					updateTable();
					
					try
					{
						FileWriter fileWriter = new FileWriter("DataSet.csv", true);
						String addString = potentialAdd.getName() + ',' + potentialAdd.getCompany() + ',' + potentialAdd.getStrength() + ',' + potentialAdd.getBarcode() + ',' + potentialAdd.getTabletsPerPack() + '\n';
						fileWriter.write(addString);
						fileWriter.close();
					}
					catch (Exception ex)
					{
					}
					
					potentialAdd = null;
				}
				
				tableScrollPane.setVisible(true);
				prescribedMedHeader.setVisible(true);
				prescribedMedBack.setVisible(true);
				mainMenuText.setVisible(false);
				seePrescriptionsButton.setVisible(false);
				addMedManuallyButton.setVisible(false);
				drugNameTextField.setVisible(false);
				drugNameTextLabel.setVisible(false);
				drugCompanyTextField.setVisible(false);
				drugCompanyTextLabel.setVisible(false);
				drugStrengthTextField.setVisible(false);
				drugStrengthTextLabel.setVisible(false);
				drugTPPTextField.setVisible(false);
				drugTPPTextLabel.setVisible(false);
				manualAddButton.setVisible(false);
				addByBarcodeButton.setVisible(false);
				barcodeLabel.setVisible(false);
				addByBarcodeText.setVisible(false);
				barcodeAddButton.setVisible(false);
				redLight.setVisible(false);
				greenLight.setVisible(false);
				yellowLight.setVisible(false);
				isDrugSafeText.setVisible(false);
				acceptDrug.setVisible(false);
				declineDrug.setVisible(false);
				addByBarcodeButton.setBounds(250, 355, 270, 30);
				
				drugNameTextField.setText("");
				drugCompanyTextField.setText("");
				drugStrengthTextField.setText("");
				drugTPPTextField.setText("");
				addByBarcodeText.setText("");
			}
		});
		acceptDrug.setBounds(130, 320, 150, 30);
		acceptDrug.setVisible(false);
		jframe.getContentPane().add(acceptDrug);
		
		declineDrug = new JButton("Discard");
		declineDrug.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				tableScrollPane.setVisible(false);
				prescribedMedHeader.setVisible(false);
				prescribedMedBack.setVisible(false);
				mainMenuText.setVisible(true);
				seePrescriptionsButton.setVisible(true);
				addMedManuallyButton.setVisible(true);
				drugNameTextField.setVisible(false);
				drugNameTextLabel.setVisible(false);
				drugCompanyTextField.setVisible(false);
				drugCompanyTextLabel.setVisible(false);
				drugStrengthTextField.setVisible(false);
				drugStrengthTextLabel.setVisible(false);
				drugTPPTextField.setVisible(false);
				drugTPPTextLabel.setVisible(false);
				manualAddButton.setVisible(false);
				addByBarcodeButton.setVisible(true);
				barcodeLabel.setVisible(false);
				addByBarcodeText.setVisible(false);
				barcodeAddButton.setVisible(false);
				redLight.setVisible(false);
				greenLight.setVisible(false);
				yellowLight.setVisible(false);
				isDrugSafeText.setVisible(false);
				acceptDrug.setVisible(false);
				declineDrug.setVisible(false);
				addByBarcodeButton.setBounds(250, 355, 270, 30);
				
				drugNameTextField.setText("");
				drugCompanyTextField.setText("");
				drugStrengthTextField.setText("");
				drugTPPTextField.setText("");
				addByBarcodeText.setText("");
			}
		});
		declineDrug.setBounds(630, 320, 100, 30);
		declineDrug.setVisible(false);
		jframe.getContentPane().add(declineDrug);
		
		updateTable();
	}
	
	private void updateTable()
	{
		int rowCount = ((DefaultTableModel)this.table.getModel()).getRowCount();
		
		for(int i = rowCount-1; i>=0; --i)
		{
			((DefaultTableModel)this.table.getModel()).removeRow(i);
		}
		
		for(Medication m:this.medication)
		{
			((DefaultTableModel)this.table.getModel()).addRow(new Object[]{m.getCompany(), m.getName(), m.getStrength(), m.getTabletsPerPack()});
		}
	}
}
