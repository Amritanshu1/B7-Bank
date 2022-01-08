package b7.bank.B7Bank.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import b7.bank.B7Bank.model.Customer;
import b7.bank.B7Bank.model.CustomerDao;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDao customerRepo;
	@Override
	public Customer createCust(Customer cust) {
		Customer custResponse=null; 
		
		String  name=customerRepo.findCust(cust.getName());	
		//System.out.println("cust.name:"+name);
		if(name==null)
		{
			customerRepo.save(cust);
			custResponse=cust;
		}
	
		return custResponse;
	}

	@Override
	public Customer editEmail(String custName, String email) {
		
		customerRepo.updateEmail(custName, email);
		
		return customerRepo.findCustomer(custName);
	}

	@Override
	public Customer editAddress(String custName, String address) {
		
		
		customerRepo.updateAddress(custName, address);
		
		return customerRepo.findCustomer(custName);
	}

	@Override
	public Customer editPhone(String custName, long phone) {
		
		customerRepo.updatePhone(custName, phone);
		    
		return  customerRepo.findCustomer(custName);
	}

	@Override
	public List<Customer> fetchDetail() {
		
		
		return customerRepo.findAll();
	}

	@Override
	public boolean checkAvailable(String custName) {
	
		boolean found=false;
		String name=customerRepo.findCust(custName);
		if(name!=null)
		{
			found=true;
		}
		return found;
	}

	@Override
	public void deleteAccount(String custName) {
		
		customerRepo.deleteAccountByName(custName);
		
	}
	

}
