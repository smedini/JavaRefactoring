package com.h2rd.refactoring.web;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDaoApi;

//Added @Controller and removed @Repository
@Controller
public class UserResource implements UserResourceApi {
	/**
	 * Instead of creating the UserDao object, we use the bean defination of userdao to load an bean of UserDaoApi using @Autowired annotation. This makes testing easy.
	 * Also we can plug and plug out different implementation of UserDaoApi this way.
	 * I am using constructor based dependency injection.
	 * Also changed in this class is the proper response code for each of these operations.
 	 */


	private UserDaoApi userDaoApi;

	@Autowired
	public UserResource(UserDaoApi userDaoApi) {
		this.userDaoApi = userDaoApi;
	}

	public Response addUser(String name,
													String email,
													List<String> roles) {

		User user = new User(name, email, roles);
		userDaoApi.saveUser(user);
		return Response.ok(201).entity(user).build();
	}

	public Response updateUser(String name,
														 String email,
														 List<String> roles) {

		User user = new User(name, email, roles);

		userDaoApi.updateUser(user);
		return Response.ok(200).entity(user).build();
	}

	public Response deleteUser(String name,
														 String email,
														 List<String> roles) {
		User user = new User(name, email, roles);

		userDaoApi.deleteUser(user);
		return Response.ok(200).entity(user).build();
	}

	public Response getUsers() {
		List<User> users = userDaoApi.getUsers();
		if (users == null) {
			users = new ArrayList<User>();
		}
		GenericEntity<List<User>> usersEntity = new GenericEntity<List<User>>(users) {};
		return Response.status(200).entity(usersEntity).build();
	}

	public Response findUser(String name) {
		User user = userDaoApi.findUser(name);
		return Response.status(200).entity(user).build();
	}
}
