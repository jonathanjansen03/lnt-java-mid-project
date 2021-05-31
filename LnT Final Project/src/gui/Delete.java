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

public class Delete implements ActionListener{

	private JFrame frame;
	private JLabel MenuCode, menuName, menuPrice, menuStock, msg;
	private JTextField codeTxt, nameTxt, priceTxt, stockTxt;
	private JButton insert, update, delete, view;
	
	public Delete() {
		init();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private void init() {
		frame = new JFrame();
		frame.setTitle("Delete");
		frame.setSize(400,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(4,1));
		
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
		if (e.getSource() == delete) {
			String id = codeTxt.getText();
			MenuModel mm = new MenuModel();
			boolean success = mm.delete(id);
			
			if (success)
				msg.setText("Delete Successfull");
		} else if (e.getSource() == update) {
			new Update();
			frame.dispose();
		} else if (e.getSource() == view) {
			new View();
			frame.dispose();
		} else if (e.getSource() == insert) {
			new Insert();
			frame.dispose();
		}
	}

}
