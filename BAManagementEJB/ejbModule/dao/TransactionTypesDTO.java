package dao;

import java.util.List;
import model.TransactionTypes;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class TransactionTypesDTO
 */
@Stateless
@LocalBean
public class TransactionTypesDTO implements TransactionTypesDTORemote {
	
	@PersistenceContext(unitName="BAManagementEJB")
	EntityManager em;

    /**
     * Default constructor. 
     */
    public TransactionTypesDTO() {
        // TODO Auto-generated constructor stub
    }
    
    public String[] getTransactionTypesName() {
    	// grab the result from database
    	List queryResults = em.createQuery("SELECT t FROM TransactionTypes t")
    			.getResultList();
    	String[] tTypesname = new String[3];
    	TransactionTypes tTypes = new TransactionTypes();
    	for(int i=0; i<queryResults.size(); i++) {
    		tTypes = (TransactionTypes) queryResults.get(i);
    		tTypesname[i] = tTypes.getName();
    	}
		return tTypesname;
    }

}
