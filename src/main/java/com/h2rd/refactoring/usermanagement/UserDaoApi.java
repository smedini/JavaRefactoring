package com.h2rd.refactoring.usermanagement;

import java.util.ArrayList;

/**
 * In order to make our repository extensible and portable I have created this interface which other repository can implement.
 * In this program UserDao is implementing this interface which will store the users in arraylist.
 * In future we may want to use a sql based DAO which save the users in a database.
 */
public interface UserDaoApi {
	void saveUser(User user);
	ArrayList<User> getUsers();
	void deleteUser(User userToDelete);
	void updateUser(User userToUpdate);
	User findUser(String name);
}
