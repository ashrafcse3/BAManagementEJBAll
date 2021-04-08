package dao;

import javax.ejb.Remote;
import java.util.Arrays; 

@Remote
public interface TransactionTypesDTORemote {
	public String[] getTransactionTypesName();
}
