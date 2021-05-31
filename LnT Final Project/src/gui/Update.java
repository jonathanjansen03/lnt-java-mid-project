package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.MenuModel;

public class Update implements ActionListener{
	private JFrame frame;
	private JLabel MenuCode, menuName, menuPrice, menuStock, msg;
	private JTextField codeTxt, nameTxt, priceTxt, stockTxt;
	private JButton insert,update,delete,view;
	
	public Update() {
		init();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private void init() {
		frame = new JFrame();
		frame.setTitle("Update");
		frame.setSize(800,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(10,1));
		
		MenuCode = new JLabel("Menu Code: ");
		menuName = new JLabel("Menu Name: ");
		menuPrice = new JLabel("Menu Price: ");
		menuStock = new JLabel("Menu Stock: ");
		
		codeTxt= new JTextField();
		nameTxt = new JTextField();
		priceTxt = new JTextField();
		stockTxt = new JTextField();
		
		frame.add(MenuCode);
		frame.add(codeTxt);
		
		frame.add(menuName);
		frame.add(nameTxt);
		
		frame.add(menuPrice);
		frame.add(priceTxt);
		
		frame.add(menuStock);
		frame.add(stockTxt);
		
		
		insert = new JButton("Insert");
		update = new JButton("Update");
		delete = new JButton("Delete");
		view = new JButton("View");
		
		JPanel p1 = new JPanel();
		p1.add(insert);
		p1.add(update);
		p1.add(delete);
		p1.add(view);
		frame.add(p1);
		
		JPanel p2 = new JPanel();
		
		msg = new JLabel("");
		msg.setForeground(Color.green);
		p2.add(msg);
		frame.add(p2);
		addListener();
	}
	
	private void addListener() {
		insert.addActionListener(this);
		update.addActionListener(this);
		delete.addActionListener(this);
		view.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == update) {
			String id = "", name = "";
			Integer price = 0, qty = 0;
			id = codeTxt.getText();
			name = nameTxt.getText();
			price = Integer.parseInt(priceTxt.getText());
			qty = Integer.parseInt(stockTxt.getText());
			
			MenuModel mm = new MenuModel();
			boolean success = mm.Update(id, name, price, qty);
			
			if (success)
				msg.setText("Update Successful!");
		} else if (e.getSource() == delete) {
			new Delete();
			frame.dispose();
		} else if (e.getSource() == insert) {
			new Insert();
			frame.dispose();
		} else if (e.getSource() == view) {
			new View();
			frame.dispose();
		}
	}

}
