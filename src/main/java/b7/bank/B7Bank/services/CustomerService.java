package b7.bank.B7Bank.services;


import java.util.List;

import b7.bank.B7Bank.model.Customer;

public interface CustomerService {

	 Customer createCust(Customer cust);
	 Customer editEmail(String custName,String email);
	 Customer editAddress(String custName,String address);
	 Customer editPhone(String custName,long phone);
	 List<Customer> fetchDetail();
	 boolean checkAvailable(String name);
	 void deleteAccount(String custName);
}
