package Model;
import javax.persistence.*;

@Entity
@Table(name="message")
public class Message {
	@Id
	@GeneratedValue
	private int id;
	private String content;
	
	@ManyToOne
	private User userSender;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User userReceptor;
	
	public Message() {
		super();
	}

	public Message(int id, String content, User userSender, User userReceptor) {
		super();
		this.id = id;
		this.content = content;
		this.userSender = userSender;
		this.userReceptor = userReceptor;
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

	public User getUserSender() {
		return userSender;
	}

	public void seUsertSender(User userSender) {
		this.userSender = userSender;
	}

	public User getUserReceptor() {
		return userReceptor;
	}

	public void setUserReceptor(User userReceptor) {
		this.userReceptor = userReceptor;
	}
	
	
}
