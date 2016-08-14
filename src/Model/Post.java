package Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "post")
public class Post {
	@Id
	@GeneratedValue
	int id;
	String content;
	
	@ManyToOne(cascade = CascadeType.ALL)
	User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	Community community;
	
	public Post() {
		super();
	}

	public Post(int id, String content, User user, Community community) {
		super();
		this.id = id;
		this.content = content;
		this.user = user;
		this.community = community;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}
	
	
}
