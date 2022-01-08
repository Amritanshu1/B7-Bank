package b7.bank.B7Bank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import b7.bank.B7Bank.model.Account;
import b7.bank.B7Bank.model.AccountDao;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountDao accountRepo;

	@Override
	public Account createAccount(Account account) {

		return accountRepo.save(account);
	}

	@Override
	public Account editBalance(String custName, int accountNumber, long balance) {

		accountRepo.updateBalance(custName, accountNumber, balance);

		return accountRepo.findAccount(custName);
	}

	@Override
	public Account editAccountType(String custName, String accountType) {

		accountRepo.updateAccountType(custName, accountType);

		return accountRepo.findAccount(custName);
	}

	@Override
	public Account editBankName(String custName, String bankName) {

		accountRepo.updateBankName(custName, bankName);

		return accountRepo.findAccount(custName);
	}

	@Override
	public void deleteAccount(String custName) {

		accountRepo.deleteAccountByName(custName);
	}

	@Override
	public List<Account> fetchAllDetail() {

		return accountRepo.findAll();
	}

	@Override
	public float checkAccountBalance(int accountNumber) {
		float balance = 0;

		Account foundAccount = accountRepo.findAcountNumber(accountNumber);
		balance = foundAccount.getAccountBalance();
		return balance;
	}

	@Override
	public Account validateAccountNumber(int accountNumber) {
		Account foundAccount = null;
		foundAccount = accountRepo.findAcountNumber(accountNumber);
		return foundAccount;
	}

	@Override
	public boolean checkAccount(String custName) {
		boolean found = false;
		String name = accountRepo.findCust(custName);
		if (name != null) {
			found = true;
		}
		return found;
	}

	@Override
	public boolean checkMinBalance(Account account) {
		boolean check = false;
		if (account.getAccountBalance() >= 500) {
			check = true;
		}

		return check;
	}

}
