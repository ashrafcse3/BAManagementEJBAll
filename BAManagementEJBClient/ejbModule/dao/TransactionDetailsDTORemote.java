package dao;

import javax.ejb.Remote;

@Remote
public interface TransactionDetailsDTORemote {

	public double[] insertTransactionDetails(int transactionTypes, Double amount, int frombankaccountnumber, int tobankaccountnumber);

}
