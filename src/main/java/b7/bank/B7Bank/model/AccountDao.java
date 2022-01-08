package b7.bank.B7Bank.model;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface AccountDao extends JpaRepository<Account,Integer> {
	
	@Query("Select a.custName from Account a WHERE a.custName=:name")
	String findCust(@Param("name")String name);

	@Query("Select a from Account a WHERE a.custName=:name")
	Account findAccount(@Param("name")String name);
	
	@Modifying
	@Query("Update Account a SET a.bankName=:bankName WHERE a.custName=:name")
	int updateBankName(@Param("name")String custName,@Param("bankName")String bankName);
	
	@Modifying
	@Query("Update Account a SET a.accountType=:accountType WHERE a.custName=:name")
	int updateAccountType(@Param("name")String custName,@Param("accountType")String accountType);
	
	@Modifying
	@Query("Update Account a SET a.accountBalance=:accountBalance WHERE a.custName=:name and a.accountNumber=:accountNumber")
	int updateBalance(@Param("name")String custName,@Param("accountNumber") int accountNumber,@Param("accountBalance")float accountBalance);
   
	@Modifying
	@Query("Delete Account a WHERE a.custName=:name")
	int deleteAccountByName(@Param("name")String custName);

	
	@Query("Select a from Account a WHERE a.accountNumber=:accountNumber")
	Account findAcountNumber(@Param("accountNumber")int accountNumber);
	
}

