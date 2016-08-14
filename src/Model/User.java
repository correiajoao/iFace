package Model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue
	private int id;
	private String login;
	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	private Profile profile;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userReceptor")
	private List<Message> receivedMessages;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "members")
	private List<Community> communities;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userAdmin")
	private List<Community> managedCommunities;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "friendship", joinColumns = @JoinColumn(name = "user_one_id"), inverseJoinColumns = @JoinColumn(name = "user_two_id"))
	private List<User> friends;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "friendship_requests", joinColumns = @JoinColumn(name = "user_one_id"), inverseJoinColumns = @JoinColumn(name = "user_two_id"))
	private List<User> friendshipRequests;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Post> posts;
	
	public User() {
		super();
	}

	public User(int id, String login, String password, Profile profile,
			List<Message> receivedMessages, List<Community> communities,
			List<Community> managedCommunities, List<User> friends,
			List<User> friendshipRequests, List<Post> posts) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.profile = profile;
		this.receivedMessages = receivedMessages;
		this.communities = communities;
		this.managedCommunities = managedCommunities;
		this.friends = friends;
		this.friendshipRequests = friendshipRequests;
		this.posts = posts;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public List<Message> getReceivedMessages() {
		return receivedMessages;
	}

	public void setReceivedMessages(List<Message> receivedMessages) {
		this.receivedMessages = receivedMessages;
	}

	public List<Community> getCommunities() {
		return communities;
	}

	public void setCommunities(List<Community> communities) {
		this.communities = communities;
	}

	public List<Community> getManagedCommunities() {
		return managedCommunities;
	}

	public void setManagedCommunities(List<Community> managedCommunities) {
		this.managedCommunities = managedCommunities;
	}

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}


	public List<User> getFriendshipRequests() {
		return friendshipRequests;
	}

	public void setFriendshipRequests(List<User> friendshipRequests) {
		this.friendshipRequests = friendshipRequests;
	}
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public void addFriend(User user) {
		this.friends.add(user);
	}
	
	public void addFriendshipRequests(User user) {
		this.friendshipRequests.add(user);
	}
		
	public void addReceivedMessage(Message message) {
		this.receivedMessages.add(message);
	}

	public void addManagedCommunities(Community community) {
		this.managedCommunities.add(community);
	}

	public void addCommunities(Community community) {
		this.communities.add(community);
	}
	
	public void addPosts(Post post){
		this.posts.add(post);
	}
	
	public void removePosts(Post post){
		this.posts.remove(post);
	}
	
	public void removeCommunities(Community community){
		this.communities.remove(community);
	}
	
	public void removeManagedCommunities(Community community){
		this.managedCommunities.remove(community);
	}
	
	public void removeReceivedMessages(Message message) {
		this.receivedMessages.remove(message);
	}
	
	public void removeFriendshipRequests(User user) {
		this.friendshipRequests.remove(user);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", Login=" + login + ", password=" + password
				+ ", profile=" + profile + ", receivedMessages="
				+ receivedMessages + ", communities=" + communities
				+ ", managedCommunities=" + managedCommunities + ", friends="
				+ friends + ", friendshipRequests=" + friendshipRequests
				+ ", posts=" + posts + "]";
	}
	
}
