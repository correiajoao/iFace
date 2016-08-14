package Model;

import javax.persistence.*;

@Entity
@Table(name="profile")
public class Profile {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String city;
	private String gender;
	private String status;
	
	public Profile(int id, String name, String city, String gender, String status) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.gender = gender;
		this.status = status;

	}

	public Profile() {
		super();
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
