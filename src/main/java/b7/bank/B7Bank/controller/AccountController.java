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

import b7.bank.B7Bank.model.Account;
import b7.bank.B7Bank.model.Customer;
import b7.bank.B7Bank.services.AccountService;
import b7.bank.B7Bank.services.CustomerService;
import b7.bank.B7Bank.utility.CustomError;

@RestController
public class AccountController {

	@Autowired
	AccountService accountService;

	@Autowired
	CustomerService custService;

	/**
	 * 
	 * Welcome message for Account COntroller
	 * @return
	 */
	@GetMapping("/account")
	public String welcome() {
		return "welcome to account app";
	}

	/**
	 * create Account record in Db.
	 * @param account
	 * @return
	 */
	@PostMapping("/account/create")
	public ResponseEntity<?> alCreate(@RequestBody Account account) {
		ResponseEntity<?> response = new ResponseEntity<>(new CustomError("Unknown Customer.please create a customer"),
				HttpStatus.CONFLICT);

		int flag = 0;
		//checking customer based on customerName,  is it available in db or not. if not user need to create customer first. 
		if (custService.checkAvailable(account.getCustName())) {
			flag++;
			response = new ResponseEntity<>(new CustomError("Account exist"), HttpStatus.CONFLICT);
		}

		//checking any account is available based on customer name.
		if (!accountService.checkAccount(account.getCustName())) {
			flag++;
		}

		if (flag == 2) {

			if (accountService.checkMinBalance(account)) {
				Account accountResponse = accountService.createAccount(account);
				response = new ResponseEntity<>(accountResponse, HttpStatus.CREATED);

			}

			else {

				response = new ResponseEntity<>(new CustomError("Account balance is less than Rs500"),
						HttpStatus.CONFLICT);
			}
		}

		return response;
	}

	/**
	 * fetching all account details from db.
	 * @return
	 */
	@GetMapping("/account/findAll")
	public ResponseEntity<?> alFindAll() {
		ResponseEntity<?> response = new ResponseEntity<>(new CustomError("Data Not found in Db"), HttpStatus.CONFLICT);

		List<Account> accountResponse = accountService.fetchAllDetail();
		if (accountResponse != null) {
			response = new ResponseEntity<>(accountResponse, HttpStatus.FOUND);
		}

		return response;
	}

	/**
	 * updating bankName in db based on user input.
	 * 
	 * @param name
	 * @param bankname
	 * @return
	 */
	@GetMapping("/account/updateBankName/{name}/{bankname}")

	public ResponseEntity<?> alUpdateBankName(@PathVariable String name, @PathVariable String bankname) {
		ResponseEntity<?> response = new ResponseEntity<>(new CustomError("account not found unable to update"),
				HttpStatus.CONFLICT);

		if (accountService.checkAccount(name)) {

			Account accountResponse = accountService.editBankName(name, bankname);
			response = new ResponseEntity<>(accountResponse, HttpStatus.FOUND);
		}
		return response;
	}

	/**
	 * updating accountType in db.
	 * @param name
	 * @param accountType
	 * @return
	 */
	@GetMapping("/account/updateAccountType/{name}/{accountType}")

	public ResponseEntity<?> alUpdateAccountType(@PathVariable String name, @PathVariable String accountType) {
		ResponseEntity<?> response = new ResponseEntity<>(new CustomError("account not found unable to perform update"),
				HttpStatus.CONFLICT);

		if (accountService.checkAccount(name)) {

			Account accountResponse = accountService.editAccountType(name, accountType);
			response = new ResponseEntity<>(accountResponse, HttpStatus.FOUND);
		}
		return response;
	}

	/**
	 * 
	 * updating account balance
	 * @param name
	 * @param accountNumber
	 * @param accountBalance
	 * @return
	 */
	@GetMapping("/account/updateBankBalance/{name}/{accountNumber}/{accountBalance}")

	public ResponseEntity<?> alUpdateAccountBalance(@PathVariable String name, @PathVariable int accountNumber,
			@PathVariable long accountBalance) {
		ResponseEntity<?> response = new ResponseEntity<>(new CustomError("account not found unable to perform update"),
				HttpStatus.CONFLICT);

		if (accountService.checkAccount(name)) {

			Account accountResponse = accountService.editBalance(name, accountNumber, accountBalance);
			response = new ResponseEntity<>(accountResponse, HttpStatus.FOUND);
		}
		return response;
	}

	/**
	 * deleting customer account
	 * @param custName
	 * @return
	 */
	@GetMapping("/account/deleteByName/{custName}")
	public ResponseEntity<?> alDelete(@PathVariable String custName)
	{
		ResponseEntity<?> response = new ResponseEntity<>(new CustomError("account not found unable to perform delete operation"),
				HttpStatus.CONFLICT);
		
		System.out.println("result: "+accountService.checkAccount(custName));
		if (accountService.checkAccount(custName)) {
           
			 accountService.deleteAccount(custName);
			response = new ResponseEntity<>(HttpStatus.OK);
		}
		
		return response;
	}
}
