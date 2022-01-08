package b7.bank.B7Bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import b7.bank.B7Bank.model.Customer;
import b7.bank.B7Bank.services.CustomerService;
import b7.bank.B7Bank.utility.CustomError;


@RestController
public class CustomerController {

	@Autowired
	CustomerService custService;
	/**
	 * 
	 * Welcome message for Customer controller
	 * @return
	 */
	@GetMapping("/customer")
	public String welcome()
	{
		
		return "welcome to customer Curd Operation";
	}
	
	/**
	 * 
	 * creating new Customer in Db
	 * @param customer
	 * @return
	 */
	@PostMapping("/customer/create")
	 public ResponseEntity<?> alCreate(@RequestBody Customer customer){
		
		 ResponseEntity<?> response= new ResponseEntity<>
		 (new CustomError("Customer Exist in the db"),HttpStatus.CONFLICT);
		 
		 Customer custResponse=custService.createCust(customer);
		 if(custResponse!=null)
		  {
			response=new ResponseEntity<>(custResponse,HttpStatus.CREATED);
		  }
		 
		return response;
		
	}
	
	/**
	 * 
	 * fetching all customer details
	 * @return
	 */
	@GetMapping("/customer/findAll")
	public ResponseEntity<?> alFindAll()
	{
         ResponseEntity<?> response= new ResponseEntity<>
		 (new CustomError("Data Not found in Db"),HttpStatus.CONFLICT);
		    
		  List<Customer> custResponse=custService.fetchDetail();
		  if(custResponse!=null)
		  {
			  response=new ResponseEntity<>(custResponse,HttpStatus.FOUND);
		  }
		 
		 return response;
	}
	
	/**
	 * updating customer phone number.
	 * @param name
	 * @param phone
	 * @return
	 */
	@GetMapping("/customer/updatePhone/{name}/{phone}")
	public ResponseEntity<?> alUpdatePhone(@PathVariable String name,@PathVariable long phone)
	{
		 ResponseEntity<?> response= new ResponseEntity<>
		 (new CustomError("name Not found in Db"),HttpStatus.CONFLICT);
		  
		 if(custService.checkAvailable(name))
		 {
			 Customer custResponse=custService.editPhone(name, phone);
			 response=new ResponseEntity<>(custResponse,HttpStatus.FOUND);
			 
		 }
		return response;
	}
	
	/**
	 * updating customer email
	 * @param name
	 * @param email
	 * @return
	 */
	@GetMapping("/customer/updateEmail/{name}/{email}")
	public ResponseEntity<?> alUpdateEmail(@PathVariable String name,@PathVariable String email)
	{
		 ResponseEntity<?> response= new ResponseEntity<>
		 (new CustomError("name Not found in Db"),HttpStatus.CONFLICT);
		  
		 if(custService.checkAvailable(name))
		 {
			 Customer custResponse=custService.editEmail(name, email);
			 response=new ResponseEntity<>(custResponse,HttpStatus.FOUND);
			 
		 }
		return response;
	}
	
	/**
	 * updating customer address
	 * @param name
	 * @param address
	 * @return
	 */
	@GetMapping("/customer/updateAddress/{name}/{address}")
	public ResponseEntity<?> alUpdatePhone(@PathVariable String name,@PathVariable String address)
	{
		 ResponseEntity<?> response= new ResponseEntity<>
		 (new CustomError("name Not found in Db"),HttpStatus.CONFLICT);
		  
		 if(custService.checkAvailable(name))
		 {
			 Customer custResponse=custService.editAddress(name, address);
			 response=new ResponseEntity<>(custResponse,HttpStatus.FOUND);
			 
		 }
		return response;
	}
	
	/**
	 * deleting customer from db
	 * @param custName
	 * @return
	 */
	@GetMapping("/customer/deleteByName/{custName}")
	public ResponseEntity<?> alDelete(@PathVariable String custName)
	{
		ResponseEntity<?> response = new ResponseEntity<>(new CustomError("account not found unable to perform delete operation"),
				HttpStatus.CONFLICT);
		
		if (custService.checkAvailable(custName)) {
           
			custService.deleteAccount(custName);
			response = new ResponseEntity<>(HttpStatus.OK);
		}
		
		return response;
	}
}
