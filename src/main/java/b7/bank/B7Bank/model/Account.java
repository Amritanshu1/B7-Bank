package b7.bank.B7Bank.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name="Account")
public class Account implements  Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	    String bankName;
	    String accountType;
	   int accountNumber;
	   float  accountBalance;
	   String custName;  
	   public Account(String bankName, String accountType, int accountNumber, float accountBalance, String custName) {
		this.bankName = bankName;
		this.accountType = accountType;
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		this.custName = custName;
	}
	
	
	
}
