package b7.bank.B7Bank.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name="Customer")
public class Customer implements  Serializable {
	
	public Customer(String name,String custEmail, long custPhone, String custPan, String custAddress) {
		this.name=name;
		this.custEmail = custEmail;
		this.custPhone = custPhone;
		this.custPan = custPan;
		this.custAddress = custAddress;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	
      String name;
      String custEmail;
	  long custPhone;
	  String custPan;
	  String custAddress;

}
