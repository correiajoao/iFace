package Model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "community")
public class Community {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String description;

	@OneToMany(orphanRemoval = true, mappedBy = "community")
	private List<Post> posts;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_community", joinColumns = @JoinColumn(name = "community_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> members;

	@ManyToOne(cascade = CascadeType.ALL)
	private User userAdmin;

	public Community() {
		super();
	}

	public Community(int id, String name, String description,
			List<User> members, List<Post> posts, User userAdmin) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.members = members;
		this.posts = posts;
		this.userAdmin = userAdmin;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUserAdmin() {
		return userAdmin;
	}

	public void setUserAdmin(User userAdmin) {
		this.userAdmin = userAdmin;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public void addMember(User user) {
		this.members.add(user);
	}
	
	public void addPost(Post post){
		this.posts.add(post);
	}

}
