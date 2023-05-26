package admin.login;

public class User {
	private int id;
	private String name;
	private String email;
	private String message;
	private String timestamp;

	public User(int id, String name, String email, String message, String timestamp) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.message = message;
		this.timestamp = timestamp;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getMessage() {
		return message;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
