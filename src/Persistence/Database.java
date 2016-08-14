package Persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Model.Community;
import Model.Message;
import Model.User;

public class Database {
	private static Database instance = new Database();

	private EntityManagerFactory factory;
	private EntityManager manager;

	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}

	private Database() {
		factory = Persistence.createEntityManagerFactory("iface");
		manager = factory.createEntityManager();
	}

	// User
	public int createUser(User user) {
		try {
			manager.getTransaction().begin();
			manager.persist(user);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
		return user.getId();
	}

	public int updateUser(User user) {
		try {
			manager.getTransaction().begin();
			manager.persist(user);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
		return user.getId();
	}

	public List<User> readAllUsers() {
		List<User> users = null;
		try {
			manager.getTransaction().begin();
			Query query = manager.createQuery("from User");
			users = query.getResultList();
			manager.getTransaction().commit();

		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
		return users;
	}

	public User readUser(int id) {
		User _user = null;
		try {
			manager.getTransaction().begin();
			_user = manager.find(User.class, id);
			manager.getTransaction().commit();

		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
		return _user;
	}

	public User readUser(String login) {
		List<User> user = null;
		try {
			manager.getTransaction().begin();
			Query query = manager
					.createQuery("from User where Login=:loginAux");
			query.setParameter("loginAux", login);
			user = query.getResultList();
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
		if (user.isEmpty()) {
			return null;
		} else {
			return user.get(0);
		}
	}

	public void deleteUser(User user) {
		try {
			manager.getTransaction().begin();
			manager.remove(user);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
	}

	public boolean validateLogin(User user) {
		List<User> result = null;;
		try {
			manager.getTransaction().begin();
			Query query = manager
					.createQuery("from User where Login=:loginAux");
			query.setParameter("loginAux", user.getLogin());
			result = query.getResultList();
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}

		if (result.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validateUser(String login, String password) {
		List<User> result = null;
		try {
			manager.getTransaction().begin();
			Query query = manager
					.createQuery("from User where Login=:loginAux and password=:passwordAux");
			query.setParameter("loginAux", login);
			query.setParameter("passwordAux", password);
			result = query.getResultList();

			manager.getTransaction().commit();

		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
		if (result.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	// Community
	public int createCommunity(Community community) {
		try {
			manager.getTransaction().begin();
			manager.persist(community);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
		return community.getId();
	}

	public int updateCommunity(Community community) {
		try {
			manager.getTransaction().begin();
			manager.persist(community);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}

		return community.getId();
	}

	public List<Community> readAllCommunities() {
		List<Community> communities = null;
		try {
			manager.getTransaction().begin();
			Query query = manager.createQuery("from Community");
			communities = query.getResultList();
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
		return communities;
	}

	public Community readCommunity(int id) {
		Community _community = null;
		try {
			manager.getTransaction().begin();
			_community = manager.find(Community.class, id);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
		return _community;
	}

	public void deleteCommunity(Community community) {
		try {
			manager.getTransaction().begin();
			manager.remove(community);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
	}

	// Message
	public Message readMessage(int id) {
		Message _message = null;
		try {
			manager.getTransaction().begin();
			_message = manager.find(Message.class, id);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
		return _message;
	}

	public void deleteMessage(Message message) {
		try {
			manager.getTransaction().begin();
			manager.remove(message);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
	}
}
