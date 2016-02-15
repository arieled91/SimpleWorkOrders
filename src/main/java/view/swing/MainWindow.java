package main.java.view.swing;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class MainWindow extends JFrame{

	private static final long serialVersionUID = -8799414965109639998L;
	
	private static final String MAIN_TITLE = "Sistema de Ordenes de Trabajo";
	
	private JMenuItem inputMi = new JMenuItem("Ingreso y consola de OT");
	
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
		inputMi.addActionListener(e -> showWorkOrderPanel());
		
	}

	private void showWorkOrderPanel() {
		show(new WorkOrderPanel());
	}

	private void populateWindow() {
		add(getMenu(),BorderLayout.NORTH);
		mainPanel.add(defaultPanel, BorderLayout.CENTER);
		add(mainPanel);
		
	}
	
	public JMenuBar getMenu(){
		JMenuBar menuBar = new JMenuBar();
		JMenu supervisor = new JMenu("Supervisor");
		supervisor.add(inputMi);
		menuBar.add(supervisor);
		
		return menuBar;
	}
	

	private void show(JPanel panel){
		mainPanel.removeAll();
		mainPanel.add(panel);
		mainPanel.revalidate(); 
		mainPanel.repaint();
	}
}
