package dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

@Remote
public interface BankAccountDTORemote {
	public List<Map<String, String>> allAccountDetails(int userID);
}
