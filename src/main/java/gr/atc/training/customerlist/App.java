package gr.atc.training.customerlist;

import java.util.ArrayList;

public class App {
	
	public static void main(String[] args) {
		CustomerDataHandler cdr = new CustomerDataHandler();
		
		ArrayList<String []> customers_per_country = cdr.getCustomersPerCountry();
		cdr.printStringArrList(customers_per_country);
		String header_country[] = {"Country","Number of Customers"};
		String savePath_country = "./customers_per_country.csv";
		cdr.saveToCSV(customers_per_country,savePath_country,header_country);
		
		ArrayList<String []> customers_per_firstName = cdr.getCustomersPerFirstName();
		cdr.printStringArrList(customers_per_firstName);
		String header_firstName[] = {"First Name","Number of Customers"};
		String savePath_firstName = "./customers_per_firstName.csv";
		cdr.saveToCSV(customers_per_firstName, savePath_firstName, header_firstName);
		
		System.out.println("Distinct country values: " + cdr.getDistinctItemsCount("country")); 
		System.out.println("Distinct first values: " + cdr.getDistinctItemsCount("first")); 
		System.out.println("Distinct last values: " + cdr.getDistinctItemsCount("last")); 
		
		
		cdr.sortCustomersByName();
		cdr.writeCustomersToCSV("./customersByName.csv");
	}
}