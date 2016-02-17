package main.java.view.swing;

import main.java.common.Utils;
import main.java.domain.business.Task;
import main.java.domain.business.WorkOrder;
import main.java.domain.business.WorkOrderId;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.util.Calendar;

public class AssignedTasksPanel extends JPanel{

	private static final long serialVersionUID = 6736187847410032702L;

	private WorkOrderId id;

    private Object[] columnNames = {"Id", "Descripcion", "MP", "Cantidad", "Estado", "Fecha"};


    DefaultTableModel tasks = new DefaultTableModel(null, columnNames);
    JTable table = new JTable(tasks);


    public AssignedTasksPanel(WorkOrderId id){
        this.id = id;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        loadElements();
        implementListenters();
        setVisible(true);
    }

    private void implementListenters() {
        table.getModel().addTableModelListener(this::tableChanged);
    }

    private void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel)e.getSource();
        String columnName = model.getColumnName(column);
        if(columnName.equals("Estado")){
            Status status = (Status) model.getValueAt(row, column);

            int taskId = (Integer)model.getValueAt(row, 0);
            Task task = Task.find(taskId);

            if(taskId<=0) return;

            if(status == Status.EXECUTION){
                task.setInExecution(true);
                task.update();
                WorkOrder workOrder = WorkOrder.find(id);
                workOrder.setStatus(WorkOrder.Status.IN_EXECUTION);
                workOrder.update();
                checkStock(task);
            }

            if(status == Status.FINISHED){
                Calendar date = Calendar.getInstance();
                task.setInExecution(false);
                task.setDateFinish(Utils.toString(date));
                model.setValueAt(Utils.toString(date), row, 5);
                task.update();
                WorkOrder workOrder = WorkOrder.find(id);
                if(workOrder.tasksFinied())
                    workOrder.setStatus(WorkOrder.Status.FINISHED);
                workOrder.update();

            }
            loadElements();
        }
    }

    private void checkStock(Task task) {
        if(task.lowStock()){
            add(new StockRequestPanel(task));
        }

    }



    private void loadElements(){
        tasks.setRowCount(0);
        WorkOrder workOrder = WorkOrder.find(id);
        if(workOrder==null) return;

        for (Task task : workOrder.getTasks()) {
            tasks.addRow(buildRow(task));
        }

        add(table);
    }

    private Object[] buildRow(Task task) {
        TableColumn statusColumn = table.getColumnModel().getColumn(4);
        JComboBox<Status> statusCmb= new JComboBox<>(Status.values());
        statusCmb.setSelectedIndex(task.isInExecution() ? 0 : !Utils.isEmpty(task.getDateFinishString()) ? 1 : -1);
        statusColumn.setCellEditor(new DefaultCellEditor(statusCmb));

        Object[] colums = new Object[6];
        colums[0] = task.getId();
        colums[1] = task.getDescription();
        colums[2] = task.getFeedstock().getAmount()>0 ? task.getFeedstock().getFeedstock().getDescription() : "";
        colums[3] = task.getFeedstock().getAmount()>0 ? task.getFeedstock().getAmount() : "";
        colums[4] = statusColumn;
        colums[5] = "";

        return colums;
    }


    public enum Status{
        EXECUTION("En ejecución"),
        FINISHED("Finalizado");

        private String label;

        private Status(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

}
