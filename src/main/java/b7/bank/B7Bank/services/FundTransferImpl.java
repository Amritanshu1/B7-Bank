package b7.bank.B7Bank.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import b7.bank.B7Bank.model.Account;
import b7.bank.B7Bank.model.AccountDao;
import b7.bank.B7Bank.model.Customer;
import b7.bank.B7Bank.model.CustomerDao;

@Service
public class FundTransferImpl implements FundTransferService {

	@Autowired
	AccountDao accountRepo;

	@Autowired
	CustomerDao custRepo;

	@Autowired
	AccountService accountService;

	@Override
	public void fundTransfer(Account toAccount, Account fromAccount, float balance) {

		float toBalance = accountService.checkAccountBalance(toAccount.getAccountNumber());
		float updateToBalance = toBalance + balance;
		accountRepo.updateBalance(toAccount.getCustName(), toAccount.getAccountNumber(), updateToBalance);

		float fromBalance = accountService.checkAccountBalance(fromAccount.getAccountNumber());
		float updateFromBalance = fromBalance - balance;
		accountRepo.updateBalance(fromAccount.getCustName(), fromAccount.getAccountNumber(), updateFromBalance);
	}

	@Override
	public boolean verifyTransferAmount(int fromAccountNumber, float balance) {
		boolean validFund = false;

		float fromBalance = accountService.checkAccountBalance(fromAccountNumber);

		if (balance <= fromBalance) {
			validFund = true;
		}

		return validFund;
	}

}
