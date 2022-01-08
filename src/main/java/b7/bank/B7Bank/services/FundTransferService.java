package b7.bank.B7Bank.services;

import java.util.List;

import b7.bank.B7Bank.model.Account;

public interface FundTransferService {

	void fundTransfer(Account toAccount,Account fromAccount,float balance);
    boolean verifyTransferAmount(int fromAccountNumber,float balance);
}
