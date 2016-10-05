package main.java.view.swing;

import javax.swing.*;
import java.awt.*;


public class MainWindow extends JFrame{

	private static final long serialVersionUID = -8799414965109639998L;
	
	private static final String MAIN_TITLE = "Sistema de Ordenes de Trabajo";
	
	private JMenuItem workOrder = new JMenuItem("Ingreso y consulta de OT");
    private JMenuItem workOrderListAdmin = new JMenuItem("Ordenes de Trabajo");
    private JMenuItem workOrderListWorker = new JMenuItem("Tareas Asignadas");

	private JPanel mainPanel = new JPanel();
	private JPanel defaultPanel = new JPanel();
	

	public static void main(String[] args) {
		new MainWindow();
	}
	
	public MainWindow(){
		super(MAIN_TITLE);
		setSize(800,500);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLocationRelativeTo(null);
		setVisible(true);
		
		populateWindow();
		implementListeners();
	}

	private void implementListeners() {
		workOrder.addActionListener(e -> show(new WorkOrderPanel()));
        workOrderListAdmin.addActionListener(e -> show(new WorkOrderListPanel(true)));
        workOrderListWorker.addActionListener(e -> show(new WorkOrderListPanel(false)));
        //assignedTasks.addActionListener(e -> show(new AssignedTasksPanel()));

	}

	private void populateWindow() {
		add(getMenu(),BorderLayout.NORTH);
		mainPanel.add(defaultPanel, BorderLayout.CENTER);
		add(mainPanel);
		
	}
	
	public JMenuBar getMenu(){
		JMenuBar menuBar = new JMenuBar();
		JMenu supervisor = new JMenu("Supervisor");
        JMenu operario = new JMenu("Operario");
		supervisor.add(workOrder);
        supervisor.add(workOrderListAdmin);

        operario.add(workOrderListWorker);
		menuBar.add(supervisor);
        menuBar.add(operario);
		
		return menuBar;
	}
	

	private void show(JPanel panel){
		mainPanel.removeAll();
		mainPanel.add(panel);
		mainPanel.revalidate(); 
		mainPanel.repaint();
	}
}
