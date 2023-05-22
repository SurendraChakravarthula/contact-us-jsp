package contact_us;

public class ContactUsPOJO {
	private int id;
	private String name;
	private String email;
	private String message;

	public ContactUsPOJO(int id, String name, String email, String message) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.message = message;
	}

	public ContactUsPOJO(String name, String email, String message) {
		this.name = name;
		this.email = email;
		this.message = message;
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
}
