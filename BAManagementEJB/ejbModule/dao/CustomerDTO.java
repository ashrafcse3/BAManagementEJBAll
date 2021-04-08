package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Customer;

/**
 * Session Bean implementation class CustomerDTO
 */
@Stateless
@LocalBean
public class CustomerDTO implements CustomerDTORemote {

	@PersistenceContext(unitName="BAManagementEJB")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public CustomerDTO() {
        // TODO Auto-generated constructor stub
    }
    
    public String encryptPassword(String password) {
        MessageDigest messageDigest;
        
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] messageDigestMD5 = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte bytes : messageDigestMD5) {
                stringBuffer.append(String.format("%02x", bytes & 0xff));
            }
     
            return stringBuffer.toString();
            
        } catch (NoSuchAlgorithmException exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
            return "notEncrypted";
        }
    }
    
    public int checkLogin(String userName, String password) {
    	// change the password into md5
    	String encryptedPassword = encryptPassword(password);
    	
    	//grab records if email and password match
    	try {
    		Customer customer = em.createQuery("SELECT c FROM Customer c WHERE c.name = :customerName and c.password = :customerPassword", Customer.class)
    				.setParameter("customerName", userName)
    				.setParameter("customerPassword", encryptedPassword)
    				.getSingleResult();
    		return customer.getId();
    	} catch (Exception e) {
    		return 0;
		}
    }
    
    public Map<String, String> currentCustomerDetails(int ID) {
    	Customer customer = em.createQuery("SELECT c FROM Customer c WHERE c.id = :customerID", Customer.class)
				.setParameter("customerID", ID)
				.getSingleResult();
    	Map<String, String> hmap = new HashMap<>(); // HashMap/dictionary to hold BankAccount details // A list of all BankAccount related dictionary/Hashmap
        hmap.put("name", customer.getName()); // name of the customer
        hmap.put("passport", customer.getPassport());
        hmap.put("phoneNumber", customer.getPhone_Number());
        hmap.put("email", customer.getEmail());
        hmap.put("address", customer.getAddress());
        
        return hmap;
    }

}
