package b7.bank.B7Bank;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import b7.bank.B7Bank.model.Account;
import b7.bank.B7Bank.model.AccountDao;
import b7.bank.B7Bank.model.Customer;
import b7.bank.B7Bank.model.CustomerDao;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableSwagger2
@SpringBootApplication
public class B7BankApplication {
	
	@Autowired
	CustomerDao customerRepo;
	
	@Autowired
	AccountDao accountRepo;

	public static void main(String[] args) {
		SpringApplication.run(B7BankApplication.class, args);
	}
	
	
	@Bean
	CommandLineRunner orm() {
		return (args)->{
			
			Customer cust1=new Customer("Aman","aman@gmail.com",99026258,"bgakl4650r","pune india");
			Customer cust2=new Customer("Rohit","rohit@gmail.com",63026258,"fghl4650r","Delhi india");
			customerRepo.save(cust1);
			customerRepo.save(cust2);
			
			Account account1=new Account("SBI","saving",67890,500,"Aman");
			Account account2=new Account("HDFC","saving",12345,5000,"Rohit");
			
			accountRepo.save(account1);
			accountRepo.save(account2);
			
			/*
			cust1.setAddress(account1);
			account1.setCustomer(cust1);
			customerRepo.save(cust1);
			
			//List<Account> dbAddress=accountRepo.findAll();
			//System.out.println(dbAddress);
			
			//System.out.println("dfgdgdgd");
		
			Customer cust2=new Customer("aman");
			Account account2=new Account("patna");
			cust2.setAddress(account2);
			account2.setCustomer(cust2);
			customerRepo.save(cust2);
			*/
		};
		
	}
	
	@Bean
	public Docket demoSwagger() {
		
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("b7.bank.B7Bank.controller")).build();
	}

	
}
