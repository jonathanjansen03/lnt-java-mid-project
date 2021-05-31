package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Menu;
import model.MenuModel;

public class View implements ActionListener{
	private JFrame frame;
	private JButton insert,update,delete;
	private JScrollPane scrollPane;
	private DefaultTableModel dtm;
	private JTable table;
	
	public View() {
		init();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private void init() {
		frame = new JFrame();
		frame.setLayout(new GridLayout(2,1));
		frame.setSize(600,400);

		initializeTable();
		
		insert = new JButton("Insert");
		update = new JButton("Update");
		delete = new JButton("Delete");
		JPanel bot = new JPanel();
		bot.add(insert);
		bot.add(update);
		bot.add(delete);
		frame.add(bot);
		addListener();
	}
	
	private void addListener() {
		insert.addActionListener(this);
		update.addActionListener(this);
		delete.addActionListener(this);
	}
	
	private void fetchData() {
		Vector<String> header = new Vector<String>();
		header.add("Menu Code");
		header.add("Menu Name");
		header.add("Menu Price");
		header.add("Menu Stock");
		dtm = new DefaultTableModel(header, 0);
		
		MenuModel mm = new MenuModel();
		Vector<Menu> menus = mm.select();
		
		for (Menu i : menus) {
			Vector<Object> obj = new Vector<Object>();
			obj.add(i.getCode());
			obj.add(i.getName());
			obj.add(i.getPrice());
			obj.add(i.getStock());
			dtm.addRow(obj);
		}
		
	}
	
	private void initializeTable() {
		fetchData();
		table = new JTable(dtm);
		table.getTableHeader().setOpaque(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.setOpaque(true);
		scrollPane = new JScrollPane(table);
		frame.add(scrollPane);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == insert) {
			new Insert();
			frame.dispose();
		} else if (e.getSource() == update) {
			new Update();
			frame.dispose();
		} else if (e.getSource() == delete) {
			new Delete();
			frame.dispose();
		}
	}

}
