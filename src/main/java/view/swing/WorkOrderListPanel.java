package main.java.view.swing;

import main.java.common.Utils;
import main.java.domain.business.WorkOrder;
import main.java.domain.business.WorkOrderId;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class WorkOrderListPanel extends JPanel{


	private static final long serialVersionUID = -6169851430765467102L;
	
	private static final int NORMAL = 10;
    private static final int SMALL = 4;

    private boolean isAdmin;


    DefaultListModel<WorkOrder> workOrders = new DefaultListModel<>();
    JList list = new JList<>(workOrders);

    JTextField workerId = new JTextField(NORMAL);

    JButton selectBtn = new JButton(">>");
    JButton searchBtn = new JButton("Buscar");

    private JTextField dateFinish = new JTextField(SMALL);
    private JTextField monthFinish = new JTextField(SMALL);
    private JTextField yearFinish = new JTextField(SMALL);

    JPanel assignedTasks = new JPanel();


    JPanel dateFinishPan = new JPanel();

    public WorkOrderListPanel(boolean isAdmin){
        this.isAdmin=isAdmin;
        setLayout(new FlowLayout());
        loadElements();
        implementListeners();
        setVisible(true);
    }

    private void implementListeners() {
        selectBtn.addActionListener(e -> loadTasks());
        searchBtn.addActionListener(e -> populateList());
    }

    private void loadTasks() {
        if(list.getSelectedIndex()<0) return;
        WorkOrderId selectedId = workOrders.get(list.getSelectedIndex()).getId();
        remove(assignedTasks);
        assignedTasks = new AssignedTasksPanel(selectedId);
        add(assignedTasks);
        revalidate();
        repaint();
    }

    private void loadElements() {
        JPanel workOrdersPan = new JPanel();

        workOrdersPan.setLayout(new BoxLayout(workOrdersPan, BoxLayout.Y_AXIS));

        JPanel workerPan = new JPanel();
        workerPan.add(new JLabel("Nº Legajo"));
        workerPan.add(workerId);
        if(!isAdmin) workOrdersPan.add(workerPan);

        workOrdersPan.setLayout(new BoxLayout(workOrdersPan, BoxLayout.Y_AXIS));

        dateFinishPan.add(new JLabel("Fecha Finalización"));
        dateFinishPan.add(dateFinish);
        dateFinishPan.add(monthFinish);
        dateFinishPan.add(yearFinish);
        dateFinishPan.setVisible(false);
        workOrdersPan.add(dateFinishPan);

        populateList();
        workOrdersPan.add(list);
        JPanel buttonsPan = new JPanel();
        buttonsPan.add(searchBtn);
        buttonsPan.add(selectBtn);
        workOrdersPan.add(buttonsPan);
        add(workOrdersPan);
        add(assignedTasks);
        loadTasks();
    }

    private void populateList(){
        workOrders.removeAllElements();
        for (WorkOrder workOrder : WorkOrder.list()) {
            if(isAdmin || workOrder.getWorker().getId() == Utils.parseInt(workerId.getText())){
                if(dateEmpty() || isValidDate() && Utils.areEquals(workOrder.getDateFinish(), getDateFilter())) {
                    workOrders.addElement(workOrder);
                    dateFinishPan.setVisible(true);
                }
            }
        }
        list.setSelectedIndex(0);

    }

    private boolean dateEmpty(){
        return dateFinish.getText().isEmpty() || monthFinish.getText().isEmpty() || yearFinish.getText().isEmpty();
    }

    private boolean isValidDate(){
        return Utils.isValidDate(Utils.parseInt(dateFinish.getText()), Utils.parseInt(monthFinish.getText()),Utils.parseInt(yearFinish.getText()));
    }

    private Calendar getDateFilter(){
        return Utils.fromInt(Utils.parseInt(dateFinish.getText()), Utils.parseInt(monthFinish.getText()),Utils.parseInt(yearFinish.getText()));
    }


}
