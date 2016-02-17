package main.java.view.swing;


import main.java.common.Utils;
import main.java.domain.business.*;

import javax.swing.*;
import java.util.Calendar;

public class WorkOrderPanel extends JPanel{

	private static final long serialVersionUID = -375536068486373324L;
	private static final int BIG = 20;
	private static final int NORMAL = 10;
	private static final int SMALL = 4;
	
	private JTextField idFilter = new JTextField("", SMALL);
	private JTextField yearFilter = new JTextField("", SMALL);
	private JButton searchBtn = new JButton("Buscar");
	
	private JTextField dateCreate = new JTextField(BIG);

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
		int id = Utils.parseInt(idFilter.getText());
		int year = Utils.parseInt(yearFilter.getText());
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
		monthFinish.setText(wo.getDateFinish().get(Calendar.MONTH)+"");
		yearFinish.setText(wo.getDateFinish().get(Calendar.YEAR)+"");
		productCombo.setSelectedItem(wo.getProduct());
		productAmount.setText(wo.getAmount()+"");
		description.setText(wo.getDescription());
		isUrgent.setSelected(wo.isUrgent());
		status.setText(wo.getStatus().getLabel());
		workerCombo.setSelectedItem(wo.getWorker());
	}

	private void clear() {
		idFilter.setText("");
		yearFilter.setText("");
        dateCreate.setText("");
        dateFinish.setText("");
        monthFinish.setText("");
        yearFinish.setText("");
        productAmount.setText("");
        description.setText("");
        status.setText("");
        productCombo.setSelectedIndex(0);
        workerCombo.setSelectedIndex(0);
        isUrgent.setEnabled(false);
	}

	private void persist() {
		if(validData()){
			try{
				WorkOrder workOrder = build();
				if(workOrder.exists()){
					workOrder.update();
					Utils.showMessageDialog("OT Actualizada correctamente");
				}else{
					workOrder.insert();
					Utils.showMessageDialog("OT Creada correctamente");
				}
			}catch(Exception e){
				Utils.showMessageDialog("Error persistiendo la OT");
				e.printStackTrace();
			}
		}else{
            Utils.showMessageDialog("Los datos ingresados no son validos");
        }
	}
	
	private boolean validData(){

        if(!Utils.isInt(idFilter.getText()) && Utils.parseInt(idFilter.getText())<=0) return false;
        if(!Utils.isInt(yearFilter.getText()) && Utils.parseInt(yearFilter.getText())<=1970) return false;
        if(!Utils.isValidDate(dateFinish.getText(), monthFinish.getText(), yearFinish.getText())) return false;
        if(!Utils.isDouble(productAmount.getText())) return false;



		return true;

	}
	
	private WorkOrder build(){
		WorkOrder wo = new WorkOrder();
		wo.setId(new WorkOrderId(Utils.parseInt(idFilter.getText()), Utils.parseInt(yearFilter.getText())));
		wo.setDateFinish(Utils.fromInt(Utils.parseInt(dateFinish.getText()), Utils.parseInt(monthFinish.getText()), Utils.parseInt(yearFinish.getText())));
		wo.setProduct((Product)productCombo.getSelectedItem());
		wo.setAmount(Utils.parseDouble(productAmount.getText()));
		wo.setDescription(description.getText());
		wo.setUrgent(isUrgent.isSelected());
		wo.setTasks(Task.getTasks(wo.getProduct().getId()));
		wo.setWorker((Worker) workerCombo.getSelectedItem());
		if(wo.getWorker()!=null)
			wo.setStatus(WorkOrder.Status.ASSIGNED);
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
