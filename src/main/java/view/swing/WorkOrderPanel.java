package main.java.view.swing;


import java.util.Calendar;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import main.java.common.Utils;
import main.java.domain.business.Product;
import main.java.domain.business.Task;
import main.java.domain.business.WorkOrder;
import main.java.domain.business.WorkOrderId;
import main.java.domain.business.Worker;

public class WorkOrderPanel extends JPanel{

	private static final long serialVersionUID = -375536068486373324L;
	private static final int BIG = 20;
	private static final int NORMAL = 10;
	private static final int SMALL = 4;
	
	private JTextField idFilter = new JTextField("", SMALL);
	private JTextField yearFilter = new JTextField("", SMALL);
	private JButton searchBtn = new JButton("Buscar");
	
	private JTextField dateCreate = new JTextField(NORMAL);
	private JTextField dateFinish = new JTextField(SMALL);
	private JTextField monthFinish = new JTextField(SMALL);
	private JTextField yearFinish = new JTextField(SMALL);
	
	private JComboBox<Object> productCombo = new JComboBox<>(Product.list().toArray());
	private JTextField productAmount = new JTextField(SMALL); 
	
	private JTextArea description = new JTextArea(2, BIG);
	
	private JCheckBox isUrgent = new JCheckBox("Urgente");
	
	private JTextField status = new JTextField(NORMAL);
	
	private JComboBox<Object> workerCombo = new JComboBox<>(Worker.list().toArray());
	
	private JButton acceptBtn = new JButton("Acceptar");
	private JButton clearBtn = new JButton("Borrar");
	
	public WorkOrderPanel(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		loadElements();
		implementListenters();
		setVisible(true);
	}

	private void implementListenters() {
		searchBtn.addActionListener(e -> search());
		acceptBtn.addActionListener(e -> persist());
		clearBtn.addActionListener(e -> clear());
		
	}

	private void search() {
		int id = Integer.parseInt(idFilter.getText());
		int year = Integer.parseInt(yearFilter.getText());
		clear();
		idFilter.setText(id+"");
		yearFilter.setText(year+"");
		
		WorkOrder workOrder = WorkOrder.find(id, year);
		if(workOrder == null) {
			Utils.showMessageDialog("No se encontro la OT ingresada");
			return;
		}
		populate(workOrder);
	}
	
	
	
	private void populate(WorkOrder wo){
		dateCreate.setText(wo.getDateCreateString());
		dateFinish.setText(wo.getDateFinish().get(Calendar.DATE)+"");
		monthFinish.setText(wo.getDateFinish().get(Calendar.MONTH+1)+"");
		yearFinish.setText(wo.getDateFinish().get(Calendar.YEAR)+"");
		productCombo.setSelectedItem(wo.getProduct());
		productAmount.setText(wo.getAmount()+"");
		description.setText(wo.getDescription());
		isUrgent.setSelected(wo.isUrgent());
		status.setText(wo.getStatus().getLabel());
		List<Task> tasks = wo.getTasks();
		if(!Utils.isEmpty(tasks) && tasks.get(0).getWorker()!=null) 
			workerCombo.setSelectedItem(tasks.get(0).getWorker());
	}

	private void clear() {
		idFilter.setText("");
		yearFilter.setText("");
	}

	private void persist() {
		if(validData()){
			WorkOrder workOrder = build();
			workOrder.persist();
			//TODO persistir tasks
		}
	}
	
	private boolean validData(){
		return false; // TODO terminar
	}
	
	private WorkOrder build(){
		WorkOrder wo = new WorkOrder();
		wo.setId(new WorkOrderId(Utils.parseInt(idFilter.getText()), Utils.parseInt(yearFilter.getText())));
		wo.setDateFinish(Utils.fromInt(Utils.parseInt(dateFinish.getText()), Utils.parseInt(monthFinish.getText()), Utils.parseInt(yearFinish.getText())));
		wo.setProduct((Product)productCombo.getSelectedItem());
		wo.setAmount(Utils.parseInt(productAmount.getText()));
		wo.setDescription(description.getText());
		wo.setUrgent(isUrgent.isSelected());
		return wo;
	}

	private void loadElements() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel searchPanel = new JPanel();
		searchPanel.add(new JLabel("Id "));
		searchPanel.add(idFilter);
		searchPanel.add(new JLabel("Año "));
		searchPanel.add(yearFilter);
		searchPanel.add(searchBtn);
		panel.add(searchPanel);
		
		JPanel dateCreatePan = new JPanel();
		dateCreate.setEditable(false);
		dateCreatePan.add(new JLabel("Fecha Creación"));
		dateCreatePan.add(dateCreate);
		panel.add(dateCreatePan);
		
		JPanel dateFinishPan = new JPanel();
		dateFinishPan.add(new JLabel("Fecha Finalización"));
		dateFinishPan.add(dateFinish);
		dateFinishPan.add(monthFinish);
		dateFinishPan.add(yearFinish);
		panel.add(dateFinishPan);
		
		JPanel productPan = new JPanel();
		productPan.add(new JLabel("Producto"));
		productPan.add(productCombo);
		productPan.add(new JLabel("Cantidad"));
		productPan.add(productAmount);
		panel.add(productPan);
		
		JPanel descriptionPan = new JPanel();
		descriptionPan.add(new JLabel("Description"));
		descriptionPan.add(description);
		panel.add(descriptionPan);
		
		panel.add(isUrgent);
		
		JPanel statusPan = new JPanel();
		statusPan.add(new JLabel("Estado"));
		status.setEditable(false);
		statusPan.add(status);
		panel.add(statusPan);
		
		JPanel workerPan = new JPanel();
		workerPan.add(new JLabel("Operario"));
		workerPan.add(workerCombo);
		panel.add(workerPan);
		
		JPanel buttonsPan = new JPanel();
		buttonsPan.add(acceptBtn);
		buttonsPan.add(clearBtn);
		panel.add(buttonsPan);
		
		add(panel);
		
	}
	

}
