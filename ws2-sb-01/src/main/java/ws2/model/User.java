package ws2.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class User {

	@Id
	private String id;

	private String password;

	@Transient
	private String repeatPassword;

	private String role;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", password=");
		builder.append(password);
		builder.append(", repeatPassword=");
		builder.append(repeatPassword);
		builder.append(", role=");
		builder.append(role);
		builder.append("]");
		return builder.toString();
	}

}
