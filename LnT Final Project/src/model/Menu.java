package model;

public class Menu {
	private String code;
	private String name;
	private int price;
	private int stock;
	
	public Menu() {
		// TODO Auto-generated constructor stub
	}

	public Menu(String code, String name, int price, int stock) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
		this.stock = stock;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public boolean checkCode(String code) {
		if (code.charAt(0) != 'B' || code.charAt(1) != 'C' || code.charAt(2) != '-')
			return true;
		
		for (int i = 3; i < 6; i++) {
			if (code.charAt(i) < '0' || code.charAt(i) > '9')
				return true;
		}
		
		return false;
	}
	
}
