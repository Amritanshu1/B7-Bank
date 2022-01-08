package b7.bank.B7Bank.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import b7.bank.B7Bank.model.Account;
import b7.bank.B7Bank.model.AccountDao;
import b7.bank.B7Bank.model.Customer;
import b7.bank.B7Bank.model.CustomerDao;
import b7.bank.B7Bank.services.AccountService;
import b7.bank.B7Bank.services.CustomerService;
import b7.bank.B7Bank.services.FundTransferService;
import b7.bank.B7Bank.utility.CustomError;

@RestController
public class FundTransferController {

	@Autowired
	AccountDao accountRepo;

	@Autowired
	CustomerDao custRepo;
	
	@Autowired
	FundTransferService fundService;
	
	@Autowired
	AccountService accountService;

	@Autowired
	CustomerService custService;

	/**
	 * 
	 * welcome message for FundTransfer Controller
	 * @return
	 */
	@GetMapping("/fundTransfer")
	public String welcome() {
		return "welcome to Fund Transfer app";
	}

		
	/**
	 * Fund transer from toAccount to From account .
	 * @param To_AccountNumber
	 * @param From_AccountNumber
	 * @param Money
	 * @return
	 */
	@GetMapping("/fundTransfer/transfer/{To_AccountNumber}/{From_AccountNumber}/{Money}")

	public ResponseEntity<?> alUpdateAccountBalance(@PathVariable int To_AccountNumber, @PathVariable int From_AccountNumber,
			@PathVariable float Money) {
		ResponseEntity<?> response = new ResponseEntity<>(new CustomError("Transsaction Failed"),
				HttpStatus.CONFLICT);
          //getting  both account object based on AccountNumber
		Account toAccount=accountService.validateAccountNumber(To_AccountNumber);
		Account fromAccount=accountService.validateAccountNumber(From_AccountNumber);
		
		
		int flag=0;
		//checking ToAccount object is not null
		if(toAccount!=null)
		{
			//checking account minimum balance should be equal to or more than 500
			 if(accountService.checkMinBalance(toAccount))
			 {
				flag++; 
			 }
		  	
		}
		//checking FromAccount object is not null
		if(fromAccount!=null)
		{
			//checking FromAccount minimum balance should be 500
			if(accountService.checkMinBalance(fromAccount))
			 {
				 flag++;
			 }
		}
		
		if(flag==2)
		{
			//verifying  transfer amount is available or not
	           if(fundService.verifyTransferAmount(From_AccountNumber,Money))
	           {
	        	   fundService.fundTransfer(toAccount, fromAccount, Money);
	        	   
	        	   response= new ResponseEntity<>("fund tranfered successfully...!",HttpStatus.OK);
	           }
		}
		
		return response;
	}
	
	/**
	 * fetching all details customer as well as account based on account Number
	 * @param accountNumber
	 * @return
	 */
	@GetMapping("/fundTransfer/findAlldetailsBYAccountNumber/{accountNumber}")
	public ResponseEntity<?> alFindAll(@PathVariable int accountNumber) {
		ResponseEntity<?> response = new ResponseEntity<>(new CustomError("Data Not found in Db"), HttpStatus.CONFLICT);
		
        List<Object> check=new ArrayList<Object>();
		 Account account=accountRepo.findAcountNumber(accountNumber);
		check.add(account);
		 Customer cust1=custRepo.findCustomer(account.getCustName());
		 check.add(cust1);
		if (check.size()==2) {
			response = new ResponseEntity<>(check, HttpStatus.FOUND);
		}
 
		return response;
	}
	
}
