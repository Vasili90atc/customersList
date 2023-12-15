package gr.atc.training.customerlist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.opencsv.CSVWriter;

public class CustomerDataHandler {
	private ArrayList<Customer> customers;
//αλλαγη
	public CustomerDataHandler() {
		FileInputStream f;
		try {
			f = new FileInputStream(new File("customers.json"));

		} catch (FileNotFoundException e) {
			f = null;
		}

		BufferedReader reader = null;
		reader = new BufferedReader(new InputStreamReader(f));
		String line = null;
		try {
			line = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Gson jsonReader = new Gson();
		customers = jsonReader.fromJson(line, new TypeToken<List<Customer>>() {
		}.getType());
	}

	public ArrayList<String[]> getCustomersPerCountry() {
		HashMap<String, Integer> country_customers = new HashMap<String, Integer>();
		for (int i = 0; i < customers.size(); i++) {
			String country = customers.get(i).getCountry();

			if (country_customers.get(country) == null) {
				country_customers.put(country, 1);
			} else {
				country_customers.put(country, country_customers.get(country) + 1);
			}
		}

		// Sort the countries by their customer number
		ArrayList<Integer> values = new ArrayList<Integer>(new HashSet<Integer>(country_customers.values()));
		values.sort(null);

		ArrayList<String[]> sorted_country_customers = new ArrayList<String[]>();
		for (int i = values.size() - 1; i > -1; i--) {
			int current_customers = values.get(i);
			for (String country : country_customers.keySet()) {
				String row[] = { country, Integer.toString(current_customers) };
				if (country_customers.get(country) == current_customers) {
					sorted_country_customers.add(row);
				}
			}
		}
		return sorted_country_customers;
	}

	public ArrayList<String []> getCustomersPerFirstName() {

		HashMap<String,Integer> firstName_customers = new HashMap<String,Integer>();
		for (int i=0; i<customers.size(); i++) {
			String firstName = customers.get(i).getFirst();
			
			if (firstName_customers.get(firstName) == null ) {
				firstName_customers.put(firstName, 1);
			}
			else {
				firstName_customers.put(firstName,firstName_customers.get(firstName) + 1);
			}
		}
		
		// Sort the countries by their customer numbers
		ArrayList<Integer> values = new ArrayList<Integer>(new HashSet<Integer>(firstName_customers.values()));
		values.sort(null);
		
		ArrayList<String[]> sorted_firstName_customers = new ArrayList<String []>();
		for (int i=values.size()-1; i>-1; i--) {
			int current_customers = values.get(i);
			for (String firstName:firstName_customers.keySet()) {
				String row[] = {firstName, Integer.toString(current_customers)};
				if (firstName_customers.get(firstName) == current_customers) {
					sorted_firstName_customers.add(row);
				}
			}
		}
		return sorted_firstName_customers;
	}


	
	
	public void printStringArrList(ArrayList<String[]> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i)[0] + "," + list.get(i)[1]);
		}
	}

	public void saveToCSV(ArrayList<String[]> list, String path, String header[]) {
		CSVWriter writer = null;
		try {
			writer = new CSVWriter(new FileWriter(new File(path)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (header != null)
			writer.writeNext(header);

		for (int i = 0; i < list.size(); i++) {
			writer.writeNext(list.get(i));
		}

		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getDistinctItemsCount(String columnName) {
		HashSet<String> column_unique_values = new HashSet<String>();
		for (int i = 0; i < customers.size(); i++) {
			Customer c = customers.get(i);
			column_unique_values.add(c.getField(columnName));
		}
		return column_unique_values.size();
	}

	public ArrayList<Customer> getCustomers(){

		return customers;
	}
	public void printCustomers() {

		System.out.println("Customer List:");
		for (Customer c:customers) {
			System.out.println(c);
		}
	}

	public void sortCustomersByName() {

		customers.sort(null);
	}
	
 	public void writeCustomersToCSV(String path) {
		CSVWriter writer = null;
		try {
			writer = new CSVWriter(new FileWriter(new File(path)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < customers.size(); i++) {
			String line[] =  {customers.get(i).getLast(), customers.get(i).getFirst()};
			writer.writeNext(line);
		}

		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
