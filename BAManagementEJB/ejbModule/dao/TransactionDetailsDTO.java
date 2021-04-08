package dao;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.sql.*;
import model.BankAccount;
import model.TransactionDetails;

/**
 * Session Bean implementation class TransactionDetailsDTO
 */
@Stateless
@LocalBean
public class TransactionDetailsDTO implements TransactionDetailsDTORemote {

	@PersistenceContext(unitName="BAManagementEJB")
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public TransactionDetailsDTO() {
        // TODO Auto-generated constructor stub
    }
    
    private BankAccount getBankAccountDetails(int bankaccountnumber) {
    	List queryResults = em.createQuery("SELECT b FROM BankAccount b WHERE b.accountNumber = :bankAccountNumber")
				.setParameter("bankAccountNumber", bankaccountnumber)
				.getResultList();
  	  BankAccount ba = (BankAccount)queryResults.get(0);
  	  return ba;
    }
    
    public double[] insertTransactionDetails(int transactionTypes, Double amount, int frombankaccountnumber,
			int tobankaccountnumber) {
    	try {
    		// get the timestamp
        	// 2021-03-24 16:48:05.591
//        	  Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//    		TransactionDetails tDetails = new TransactionDetails(transactionTypes + 1, amount, timestamp, frombankaccountnumber, tobankaccountnumber);
//			em.persist(tDetails);
    		
        	double[] balanceReturn = new double[4];
  
        	BankAccount baTo = getBankAccountDetails(tobankaccountnumber);
        	System.out.println("before updating " + baTo.getAccountMoney());
        	balanceReturn[0] = baTo.getAccountMoney();
        	
        		// deposit money
        		if (transactionTypes == 0) {
//        			 update the bank account balance
        			baTo.setAccountMoney(baTo.getAccountMoney() + amount); // note no checking is performed
        			em.persist(baTo); //insert/update
        	    	System.out.println("after updating Credit " + baTo.getAccountMoney());
        	    	balanceReturn[1] = baTo.getAccountMoney();
        			
        		}
        	
        		// withdraw money
        		else if (transactionTypes == 1) {
        			baTo.setAccountMoney(baTo.getAccountMoney() - amount); // note no checking is performed
        			em.persist(baTo); //insert/update
        	    	System.out.println("after updating Debit " + baTo.getAccountMoney());
        	    	balanceReturn[1] = baTo.getAccountMoney();
        			
        			// insert the transaction details
//        			tDetails.settransactiontypeid(transactionTypes);
//        			tDetails.setamount(amount);
//        			tDetails.settransactionDateTime(timestamp);
//        			tDetails.setfrombankaccountnumber(frombankaccountnumber);
//        			tDetails.settobankaccountnumber(tobankaccountnumber);
//        			em.persist(tDetails);
        		}
        		
        		//transfer money
        		else if (transactionTypes == 2) {
        			// debit from the account
        			BankAccount baFrom = getBankAccountDetails(frombankaccountnumber);
        	    	System.out.println("before updating Debit " + baFrom.getAccountMoney());
        	    	balanceReturn[2] = baFrom.getAccountMoney();
        	    	
        	    	baFrom.setAccountMoney(baFrom.getAccountMoney() - amount); // note no checking is performed
        			em.persist(baFrom); //insert/update
        	    	System.out.println("after updating Debit " + baFrom.getAccountMoney());
        	    	balanceReturn[3] = baFrom.getAccountMoney();
        	    	
        	    	// credit from the account
        	    	baTo.setAccountMoney(baTo.getAccountMoney() + amount); // note no checking is performed
        			em.persist(baTo); //insert/update
        	    	System.out.println("after updating Credit " + baTo.getAccountMoney());
        	    	balanceReturn[1] = baTo.getAccountMoney();
        		}
    		return balanceReturn;
    	} catch (Exception e) {
    		return null;
    	}
    }

}
