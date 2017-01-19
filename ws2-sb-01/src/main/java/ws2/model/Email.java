package ws2.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Email {

	@Id
	private String address;

	private String type;

	public Email() {
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Email [address=");
		builder.append(address);
		builder.append(", type=");
		builder.append(type);
		builder.append("]");
		return builder.toString();
	}

}
