package dao;

import java.util.Map;

import javax.ejb.Remote;

@Remote
public interface CustomerDTORemote {
	public int checkLogin(String userName, String password);
	public Map<String, String> currentCustomerDetails(int ID);
}
