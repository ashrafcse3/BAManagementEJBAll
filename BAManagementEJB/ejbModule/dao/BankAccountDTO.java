package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.BankAccount;

/**
 * Session Bean implementation class BankAccountDTO
 */
@Stateless
@LocalBean
public class BankAccountDTO implements BankAccountDTORemote {

	@PersistenceContext(unitName="BAManagementEJB")
	EntityManager em;
	
    public BankAccountDTO() {
        // TODO Auto-generated constructor stub
    }
    
    public List<Map<String, String>> allAccountDetails(int userID) {
//    	List queryResults = em.createQuery("SELECT b FROM BankAccount b WHERE b.customerid = :customerID")
//    			.setParameter("customerID", userID)
//    			.getResultList();
    	List queryResults = em.createQuery("SELECT b FROM BankAccount b WHERE b.customer.Id = :customerID")
    			.setParameter("customerID", userID)
    			.getResultList();
    	
    	Map<String, String> hmap = new HashMap<>(); // HashMap/dictionary to hold BankAccount details
    	List<Map<String, String>> lst = new ArrayList<>(); // A list of all BankAccount related dictionary/Hashmap
    	BankAccount ba = new BankAccount(); // to hold the reference of each bank account
    	for (int i = 0; i < queryResults.size(); i++) {
            ba = (BankAccount)queryResults.get(i); // bank account object
//            hmap.put("id", Integer.toString(i + 1)); // auto incremented value for fetched bank account.
            hmap.put("customerName", ba.getCustomer().getName()); // Name of the bank account
            hmap.put("accountNumber", Integer.toString(ba.getAccountNumber())); // number of the bank account
            hmap.put("accountMoney", Double.toString(ba.getAccountMoney())); // balance of the bank account
            hmap.put("accountType", ba.getAccountTypes().getName()); // account type of the bank account
            hmap.put("branchName", ba.getBranchName()); // branch name of the bank account
            hmap.put("IBAN", ba.getIBAN()); // IBAN of the bank account
            System.out.println("hmap " + i + ": " + hmap);
            lst.add(hmap); // adding the HashMap inside the list
             System.out.println("lst " + i + ":" + lst); 
        }
    	
        System.out.println(lst);
        return lst;
    }

}
