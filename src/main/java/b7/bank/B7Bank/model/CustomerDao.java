package b7.bank.B7Bank.model;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CustomerDao extends JpaRepository<Customer,Integer> {

	@Query("Select c from Customer c WHERE c.name=:name")
	Customer findCustomer(@Param("name")String name);
	
	@Query("Select c.name from Customer c WHERE c.name=:name")
	String findCust(@Param("name")String name);
	
	@Modifying
	@Query("Update Customer c SET c.custPhone=:phone WHERE c.name=:custName")
	int updatePhone(@Param("custName")String custName,@Param("phone")long phone);
	
	
	@Modifying
	@Query("Update Customer c SET c.custEmail=:email WHERE c.name=:custName")
	int updateEmail(@Param("custName")String custName,@Param("email")String email);
	
	@Modifying
	@Query("Update Customer c SET c.custAddress=:address WHERE c.name=:custName")
	int updateAddress(@Param("custName")String custName,@Param("address")String address);
	
	@Modifying
	@Query("Delete Customer c WHERE c.name=:custName")
	int deleteAccountByName(@Param("custName")String custName);
	
}
