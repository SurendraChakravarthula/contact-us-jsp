package admin.login;

interface UserDao {
	boolean isValidUser(String username, String Password);
}

