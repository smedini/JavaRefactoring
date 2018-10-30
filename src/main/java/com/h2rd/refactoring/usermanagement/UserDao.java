package com.h2rd.refactoring.usermanagement;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

@Repository // added repository since more or less this class act like repository
public class UserDao implements UserDaoApi {
    /**
     * In order to make the class thread safe, I have added syncronize block on users arraylist so that multiple thread don't work on it concurrently.
     * Since this class is bean, we can just instantiate or get a bean object when spring class loader loads, So I have removed the method to create a userdao object.
     * I have also removed all the try and catch in the methods of this class. Its better to check for null conditions before hand and only succeed when the condition becomes true.
     */

    // Changed the arraylist to private and initialized it too.
    private ArrayList<User> users = new ArrayList<User>();

    public void saveUser(User user) {
        synchronized (users) {
            users.add(user);
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void deleteUser(User userToDelete) {
        synchronized (users) {
            if (userToDelete != null) {
                for (User user : users) {
                    if (user.getName() == userToDelete.getName()) {
                        users.remove(user);
                    }
                }
            }
        }
    }

    public void updateUser(User userToUpdate) {
        synchronized (users) {
            if (userToUpdate != null) {
                for (User user : users) {
                    if (user.getName() == userToUpdate.getName()) {
                        user.setEmail(userToUpdate.getEmail());
                        user.setRoles(userToUpdate.getRoles());
                    }
                }
            }
        }
    }

    public User findUser(String name) {
        // Instead of catch null pointer exception we just check if users is null or not
        if(name != null) {
            for (User user : users) {
                if (name  == user.getName()) {
                    return user;
                }
            }
        }
        return null;
    }
}
