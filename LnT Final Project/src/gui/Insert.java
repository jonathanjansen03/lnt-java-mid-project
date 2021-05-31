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

import model.Menu;
import model.MenuModel;

public class Insert implements ActionListener {
	private JFrame frame;
	private JLabel code, name, price, stock, msg;
	private JTextField codeTxt, nameTxt, priceTxt, stockTxt;
	private JButton insert, update, delete, view;
	private Menu menu;
	
	public Insert() {
		init();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private void init() {
		menu = new Menu();
		frame = new JFrame();
		frame.setTitle("Insert");
		frame.setSize(800,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(10,1));
		
		code = new JLabel("Menu Code: ");
		name = new JLabel("Menu Name: ");
		price = new JLabel("Menu Price: ");
		stock = new JLabel("Menu Stock: ");
		
		codeTxt= new JTextField();
		nameTxt = new JTextField();
		priceTxt = new JTextField();
		stockTxt = new JTextField();
		
		frame.add(code);
		frame.add(codeTxt);
		
		frame.add(name);
		frame.add(nameTxt);
		
		frame.add(price);
		frame.add(priceTxt);
		
		frame.add(stock);
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
	
	public static void main(String[] args) {
		new Insert();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == insert) {
			String code = "", name = "";
			int price = 0, stock = 0;
			
			try {
				code = codeTxt.getText();
				name = nameTxt.getText();
				price = Integer.parseInt(priceTxt.getText());
				stock = Integer.parseInt(stockTxt.getText());
			} catch(Exception ex) {
				msg.setText("Error!");
				return;
			}

			boolean invalid = menu.checkCode(code);
			
			if (invalid) {
				msg.setText("Error!");
				return;
			}
			
			MenuModel mm = new MenuModel();
			boolean success = mm.Insert(code, name, price, stock);
			
			if (success)
				msg.setText("Insert Successful!");
		} else if (e.getSource() == delete) {
			new Delete();
			frame.dispose();
		} else if (e.getSource() == update) {
			new Update();
			frame.dispose();
		} else if (e.getSource() == view) {
			new View();
			frame.dispose();
		}
	}

}
