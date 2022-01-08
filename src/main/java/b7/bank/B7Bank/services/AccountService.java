package b7.bank.B7Bank.services;

import java.util.List;

import b7.bank.B7Bank.model.Account;


public interface AccountService {
	
	 Account createAccount(Account account);
	 
	 Account editBalance(String custName,int accountNumber,long balance);
	 
	 Account editAccountType(String custName,String accountType);
	 Account editBankName(String custName,String bankName);
	 
	 void deleteAccount(String custName);
	 
	 List<Account> fetchAllDetail();
	 
	 float checkAccountBalance(int accountNumber);
	 Account validateAccountNumber(int accountNumber);
	 boolean checkAccount(String custName);
	 
	 boolean checkMinBalance(Account account);
	

}
