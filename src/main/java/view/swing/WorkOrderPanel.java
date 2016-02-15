package main.java.view.swing;


import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import main.java.domain.business.Product;
import main.java.domain.business.WorkOrder;
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
		populatePanel();
		implementListenters();
		setVisible(true);
	}

	private void implementListenters() {
		searchBtn.addActionListener(e -> search());
		acceptBtn.addActionListener(e -> createOrEdit());
		clearBtn.addActionListener(e -> clear());
		
	}

	private void search() {
		int id = Integer.parseInt(idFilter.getText());
		int year = Integer.parseInt(yearFilter.getText());
		if(WorkOrder.find(id, year) == null){
			clear();
			idFilter.setText(id+"");
			yearFilter.setText(year+"");
		}
	}

	private void clear() {
		idFilter.setText("");
		yearFilter.setText("");
	}

	private void createOrEdit() {
		
			
	}

	private void populatePanel() {
		add(dataPanel());
		
	}
	
	private JPanel dataPanel(){
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
		return panel;
	}
	

}
