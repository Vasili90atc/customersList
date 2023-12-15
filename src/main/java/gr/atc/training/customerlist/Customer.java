package gr.atc.training.customerlist;

public class Customer implements Comparable<Customer> {
	public int id;
	public String email;
	public String first;
	public String last;
	public String company;
	public String created_at;
	public String country;
	
	
	public Customer(int id, String email, String first, String last, String company, String created_at, String country) {
		this.id = id;
		this.email = email;
		this.first = first;
		this.last = last;
		this.company = company;
		this.created_at = created_at;
		this.country = country;
	}
	
	@Override
	public String toString() {
		String s = "id: " + Integer.toString(id) + ", email: " + email + ", first: " + 
	first + ", last: " + last + ", company:" + company + ", created at: " + created_at + ", country: " + country;
		return s;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	public String getField(String field) {
		switch(field) {
		case "id":
			return Integer.toString(id);
		case "email":
			return email;
		case "first":
			return first;
		case "last":
			return last;
		case "company":
			return company;
		case "created_at":
			return created_at;
		case "country":
			return country;
		default:
			return null;
		}
		}
// taxinomisi customers
	
	@Override
	public int compareTo(Customer o) {
		/*
		 * If this < o => return -1
		 * If this == o => return 0
		 * If this > o => return 1
		 */
		// Compare last names
		int result = this.last.compareTo(o.last);
		// If last names are not equal, return the result of the comparison
		if (result != 0) return result;
		
		result = this.first.compareTo(o.first);
		return result;
		
	}
}