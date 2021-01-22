package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Employee {
	Scanner scan = new Scanner(System.in);
	
	private String id;
	private String name;
	private String gender;
	private String position;
	private int salary;
	
	public Employee() {
		
	}

	public Employee(String id, String name, String gender, String position, int salary) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.position = position;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public int getSalary() {
		return salary;
	}
	
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public Employee getData() {
		String name = "", gender = "", position = "", id = generateId();
		int salary = 0;
		
		do {
			System.out.print("Input nama karyawan [>= 3]: ");
			name = scan.nextLine();
			
			if(name.length() < 3) {
				System.out.print("Nama karyawan harus terdiri dari setidaknya 3 huruf alphabet!");
				scan.nextLine();
			}
		} while(name.length() < 3);
		
		do {
			System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
			gender = scan.nextLine();
			
			if(gender.equals("Laki-laki") || gender.equals("Perempuan")) {
				break;				
			} else {
				System.out.print("Jenis kelamin hanya bisa Laki-laki atau Perempuan (Case Sensitive)!");
				scan.nextLine();
			}
		} while(true);
		
		do {
			System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
			position = scan.nextLine();
			
			if(position.equals("Manager") || position.equals("Supervisor") || position.equals("Admin")) {
				break;
			} else {
				System.out.print("Jabatan hanya bisa Manager, Supervisor, atau Admin (Case Sensitive)!");
				scan.nextLine();
			}
		} while(true);
		
		if(position.equals("Manager"))
			salary = 8000000;
		else if(position.equals("Supervisor"))
			salary = 6000000;
		else
			salary = 4000000;
		
		Employee emp = new Employee(id, name, gender, position, salary);
		
		return emp;
	}
	
	public String generateId() {
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		while(sb.length() < 7) {
			if(sb.length() < 2)
				sb.append(letters.charAt(rand.nextInt(26)));
			else if(sb.length() > 2)
				sb.append(rand.nextInt(10));	
			else
				sb.append("-");
		}
		
		String id = sb.toString();
		return id;
	}
	
	public void generateBonus(ArrayList<Employee> employees, String position, int[] count) {
		double temp = 0;
		boolean first = true;
		int ctr = 0;
		
		if(position.equals("Manager")) {
			for (Employee i : employees) {
				if(i.getPosition().equals("Manager")) {
					temp = i.getSalary() * 1.1;
					i.setSalary((int)temp);
					if(first) {
						System.out.print("Bonus sebesar 10% telah diberikan kepada karyawan dengan id " + i.getId());
						first = false;
					} else {
						System.out.print(", " + i.getId());						
					}
					ctr++;
				}
				if(ctr == count[0]-1)
					break;
			}
		} else if(position.equals("Supervisor")) {
			for (Employee i : employees) {
				if(i.getPosition().equals("Supervisor")) {
					temp = i.getSalary() * 1.075;
					i.setSalary((int)temp);
					if(first) {
						System.out.print("Bonus sebesar 7.5% telah diberikan kepada karyawan dengan id " + i.getId());
						first = false;
					} else {
						System.out.print(", " + i.getId());						
					}
					ctr++;
				}
				if(ctr == count[1]-1)
					break;
			}
		} else {
			for (Employee i : employees) {
				if(i.getPosition().equals("Admin")) {
					temp = i.getSalary() * 1.05;
					i.setSalary((int)temp);
					if(first) {
						System.out.print("Bonus sebesar 5% telah diberikan kepada karyawan dengan id " + i.getId());
						first = false;
					} else {
						System.out.print(", " + i.getId());						
					}
					ctr++;
				}
				if(ctr == count[2]-1)
					break;
			}
		}
		System.out.println();
	}
	
	public void sortList(ArrayList<Employee> temp) {
		Collections.sort(temp, new Comparator<Employee>() {
			@Override
			public int compare(Employee e1, Employee e2) {
				return e1.getName().compareTo(e2.getName());
			}
		});
	}
	
	public int searchEmployee(ArrayList<Employee> employees, String action) {
		ArrayList<Employee> temp = new ArrayList<>(employees);
		sortList(temp);
		view(temp);
		
		String str = "";
		int n = 0;
		
		do {
			System.out.print("Karyawan yang ingin " + action + " [1 - " + temp.size() + "]: ");
			str = scan.nextLine();
			try {
				n = Integer.parseInt(str);
				if(n < 1 || n > temp.size()) {
					System.out.print("Masukkan angka antara 1 sampai " + temp.size() + "!");
					scan.nextLine();
				}
			} catch (NumberFormatException e) {
				System.out.print("Masukkan angka antara 1 sampai " + temp.size() + "!");
				scan.nextLine();
			}
		} while(n < 1 || n > temp.size());
		
		int idx = -1;
		for (Employee i : employees) {
			idx++;
			if(i.getId().equals(temp.get(n-1).getId()))
				break;			
		}
		
		return idx;
	}
	
	public void insert(ArrayList<Employee> employees, int[] count) {
		Employee emp = getData();
		
		System.out.println("Berhasil menambahkan karyawan dengan id " + emp.getId());
		
		if(emp.getPosition().equals("Manager")) {
			count[0]++;
			if(count[0] != 1 && (count[0]-1) % 3 == 0)
				generateBonus(employees, emp.getPosition(), count);
		} else if(emp.getPosition().equals("Supervisor")) {
			count[1]++;
			if(count[1] != 1 && (count[1]-1) % 3 == 0)
				generateBonus(employees, emp.getPosition(), count);
		} else {
			count[2]++;
			if(count[2] != 1 && (count[2]-1) % 3 == 0)
				generateBonus(employees, emp.getPosition(), count);
		}
		
		employees.add(new Employee(emp.getId(), emp.getName(), emp.getGender(), emp.getPosition(), emp.getSalary()));
		
		System.out.print("ENTER to return");
		scan.nextLine();
	}
	
	public void view(ArrayList<Employee> employees) {
		int idx = 1;
		ArrayList<Employee> temp = new ArrayList<>(employees);
		sortList(temp);
		
		System.out.println("|----|-----------------|------------------------------|---------------|--------------|-------------|");
		System.out.println("|No  |Kode Karyawan    |Nama Karyawan                 |Jenis Kelamin  |Jabatan       |Gaji Karyawan|");
		System.out.println("|----|-----------------|------------------------------|---------------|--------------|-------------|");
		for (Employee i : temp) {
			System.out.printf("|%4d|%17s|%30s|%15s|%14s|%13s|\n", idx, i.getId(), i.getName(), i.getGender(), i.getPosition(), i.getSalary());
			idx++;
		}
		System.out.println("|----|-----------------|------------------------------|---------------|--------------|-------------|");
		System.out.println();
	}
	
	public void update(ArrayList<Employee> employees, int[] count) {
		if(employees.size() == 0) {
			System.out.println("Tidak ada karyawan!");
		} else {
			int idx = searchEmployee(employees, "update"), salary = 0;
			String id = "", temp = "";
			boolean flag = true;
			
			do {
				flag = true;
				System.out.print("Input kode karyawan yang baru [MM-XXXX] (M = huruf, X = angka): ");
				id = scan.nextLine();
				
				if(id.length() != 7) {
					flag = false;
					continue;
				}
				
				for(int i = 0; i < 7; i++) {
					if(i < 2) {
						if(id.charAt(i) < 'A' || id.charAt(i) > 'Z'){
							flag = false;
							break;
						}
					} else if(i > 2) {
						if(id.charAt(i) < '0' || id.charAt(i) > '9') {
							flag = false;
							break;
						}
					} else {
						if(id.charAt(i) != '-') {
							flag = false;
							break;
						}
					}
				}
				
				if(!flag){
					System.out.print("Format kode karyawan salah!");
					scan.nextLine();
				}
			} while(!flag);
			
			Employee emp = getData();
			
			do {
				flag = true;
				System.out.print("Input gaji karyawan yang baru: ");
				temp = scan.nextLine();
				try {
					salary = Integer.parseInt(temp);
				} catch (NumberFormatException e) {
					System.out.print("Masukkan angka!");
					flag = false;
					scan.nextLine();
				}
			} while(!flag);
			
			emp.setId(id);
			emp.setSalary(salary);
			
			if(employees.get(idx).getPosition().equals("Manager"))
				count[0]--;
			else if(employees.get(idx).getPosition().equals("Supervisor"))
				count[1]--;
			else
				count[2]--;
			
			employees.get(idx).setId(id);
			employees.get(idx).setName(emp.getName());
			employees.get(idx).setGender(emp.getGender());
			employees.get(idx).setPosition(emp.getPosition());
			employees.get(idx).setSalary(salary);
			
			if(emp.getPosition().equals("Manager")) {
				count[0]++;
				if(count[0] != 1 && (count[0]-1) % 3 == 0)
					generateBonus(employees, emp.getPosition(), count);
			} else if(emp.getPosition().equals("Supervisor")) {
				count[1]++;
				if(count[1] != 1 && (count[1]-1) % 3 == 0)
					generateBonus(employees, emp.getPosition(), count);
			} else {
				count[2]++;
				if(count[2] != 1 && (count[2]-1) % 3 == 0)
					generateBonus(employees, emp.getPosition(), count);
			}
			
			System.out.println("Karyawan telah di update!");
		}
		
		System.out.print("ENTER to continue");
		scan.nextLine();
	}
	
	public void delete(ArrayList<Employee> employees, int[] count) {
		if(employees.size() == 0) {
			System.out.println("Tidak ada karyawan!");
		} else {
			int idx = searchEmployee(employees, "delete");
			
			System.out.println("Karyawan dengan id " + employees.get(idx).getId() + " telah dihapus");
			String position = employees.get(idx).getPosition();
			
			if(position.equals("Manager"))
				count[0]--;
			else if(position.equals("Supervisor"))
				count[1]--;
			else
				count[2]--;
			
			employees.remove(idx);			
		}
		System.out.print("ENTER to continue");
		scan.nextLine();
	}
}
