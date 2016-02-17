package main.java.view.swing;


import main.java.common.Utils;
import main.java.domain.business.Feedstock;
import main.java.domain.business.Task;

import javax.swing.*;
import java.awt.*;

public class StockRequestPanel extends JPanel{

	private static final long serialVersionUID = 4783337207195066751L;

	private Task task;

    JTextField amount = new JTextField(5);
    JButton requestBtn = new JButton("Enviar");

    public StockRequestPanel(Task task){
        this.task = task;
        setLayout(new FlowLayout());
        loadElements();
        implementListeners();
        setVisible(true);
    }

    private void implementListeners() {
        requestBtn.addActionListener(e -> requestStock());
    }

    private void requestStock() {
        Feedstock feedstock = task.getFeedstock().getFeedstock();
        if(Utils.isDouble(amount.getText()) || Utils.parseDouble(amount.getText())>0)
            Feedstock.requestStock(feedstock.getId(), Utils.parseDouble(amount.getText()));
    }

    private void loadElements() {
        Feedstock feedstock = task.getFeedstock().getFeedstock();
        add(new JLabel(feedstock.toString()));
        amount.setText(task.stockMissing()+"");
        add(amount);
        add(requestBtn);
    }

}
