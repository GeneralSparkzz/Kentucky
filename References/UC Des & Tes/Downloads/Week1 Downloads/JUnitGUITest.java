package Classes;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class JUnitGUITest extends JFrame implements ActionListener, ItemListener{
	public JTextArea hours = new JTextArea("Enter Hours");
	public JButton calcSalary = new JButton("Calc Salary");
	public JRadioButton radWorker = new JRadioButton("Worker");
	public JRadioButton radManager = new JRadioButton("Manager");
	public JRadioButton radBoss = new JRadioButton("Boss");
	public ButtonGroup radGroup = new ButtonGroup();
	public JLabel lblSalary = new JLabel("0.00");
	public double hourly = 0;
	/**
	 * 
	 */
	public JUnitGUITest() {
		super();
		// TODO Auto-generated constructor stub
		this.radGroup.add(radWorker);
		this.radGroup.add(radManager);
		this.radGroup.add(radBoss);
		this.setLayout(new FlowLayout());
		this.add(hours);
		this.add(radWorker);
		this.add(radManager);
		this.add(radBoss);
		this.add(lblSalary);
		this.add(calcSalary);
		calcSalary.addActionListener(this);
		radWorker.addItemListener(this);
		radManager.addItemListener(this);
		radBoss.addItemListener(this);
		
		
				
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		double hoursWorked;
		double salary;
		try {
			hoursWorked = Double.parseDouble(this.hours.getText());
			salary = hourly * hoursWorked;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			salary = -1;
		}
		this.lblSalary.setText(salary + "");
		
		
		
	}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		JRadioButton source = (JRadioButton) arg0.getSource();
		
		if(source.equals(radWorker)){
			hourly = 12.75;
		}
		else if (source.equals(radBoss)){
			hourly = 35.50;
		}
		else if (source.equals(radManager)){
			hourly = 14.00;
		}
		else{
			hourly = -1;
		}
			
	}
	
	
}
