package model;

public class Request {
	private int id;
	private String name;
	private String email;
	private String message;
	private int status;
	private String timestamp;
	
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
	
	public int getStatus() {
		return status;
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
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
