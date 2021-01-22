import java.util.ArrayList;
import java.util.Scanner;

import model.Employee;

public class Main {
	Scanner scan = new Scanner(System.in);
	Employee emp = new Employee();
	
	ArrayList<Employee> employees = new ArrayList<Employee>();
	
	int[] count = new int[3];
//	count[0] = manager
//	count[1] = supervisor
//	count[2] = admin
	
	public Main() {
		String temp = "";
		int n = 0;
		
		do {
			newScreen();
			n = 0;
			System.out.println("MENTOL APP");
			System.out.println("1. Insert data karyawan");
			System.out.println("2. View data karyawan");
			System.out.println("3. Update data karyawan");
			System.out.println("4. Delete data karyawan");
			System.out.println("5. Exit");
			
			do {
				System.out.print(">> ");
				temp = scan.nextLine();
				try {
					n = Integer.parseInt(temp);
					if(n < 1 || n > 5) {
						System.out.print("Masukkan angka antara 1 sampai 5!");
						scan.nextLine();
					}
				} catch (NumberFormatException e) {
					System.out.print("Masukkan angka antara 1 sampai 5!");
					scan.nextLine();
				}
			} while(n < 1 || n > 5);
			
			switch(n) {
			case 1:
				newScreen();
				emp.insert(employees, count);
				break;
			case 2:
				newScreen();
				emp.view(employees);
				System.out.print("ENTER to return");
				scan.nextLine();
				break;
			case 3:
				newScreen();
				emp.update(employees, count);
				break;
			case 4:
				newScreen();
				emp.delete(employees, count);
				break;
			case 5:
				newScreen();
				System.out.print("Terima kasih telah menggunakan aplikasi ini!");
				scan.nextLine();
				break;
			}
		} while(n != 5);
	}
	
	public void newScreen() {
		for(int i = 0; i < 50; i++) {
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
