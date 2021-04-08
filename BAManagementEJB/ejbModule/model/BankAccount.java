package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the BANKACCOUNT database table.
 * 
 */
@Entity
public class BankAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval=true)
  
    //private Address address;
  
	@ManyToOne
	@JoinColumn(name = "customerID")
  	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "accounttypeid")
	private AccountTypes accountTypes;
  	
	private int sortCode;
	
	private String branchName;
	
	private String IBAN;
	
	private int accountNumber;

	private double accountMoney;

	public BankAccount() {
	}

	public BankAccount(Customer customer, AccountTypes accountTypes, int sortCode, String branchName, String IBAN, int accountNumber, double accountMoney) {
		this.customer = customer;
		this.accountTypes = accountTypes;
		this.sortCode = sortCode;
		this.branchName = branchName;
		this.IBAN = IBAN;
		this.accountNumber = accountNumber;
		this.accountMoney = accountMoney;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public AccountTypes getAccountTypes() {
		return accountTypes;
	}
	
	public void setAccountTypes(AccountTypes accountTypes) {
		this.accountTypes = accountTypes;
	}
	
	public int getSortCode() {
		return sortCode;
	}
	
	public void setSortCode(int sortCode) {
		this.sortCode = sortCode;
	}
	
	public String getBranchName() {
		return this.branchName;
	}
	
	public void setBranchname(String branchName) {
		this.branchName = branchName;
	}

	public String getIBAN() {
		return IBAN;
	}
	
	public void setIBAN(String IBAN) {
		this.IBAN = IBAN;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public double getAccountMoney() {
		return accountMoney;
	}
	
	public void setAccountMoney(double accountMoney) {
		this.accountMoney = accountMoney;
	}

}