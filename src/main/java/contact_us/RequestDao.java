package contact_us;

import java.util.List;

import admin.login.User;

public interface RequestDao {
	boolean saveRequest(Request request);
	List<User> fetchAllRequests(boolean status);
	boolean changeStatus(int id);
}