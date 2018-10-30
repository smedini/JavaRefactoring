package test.com.h2rd.refactoring.integration;

import java.util.ArrayList;

import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDaoApi;
import com.h2rd.refactoring.web.UserResource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application-config.xml"})
@WebAppConfiguration
public class UserIntegrationTest {
	/**
	 * Integration test for end to end flow of an application. Here I am using ContextConfiguration to load the bean definations and SpringJUnit4ClassRunner to run the integration test.
	 */
	@Autowired
	private UserDaoApi userDaoApi;

	@Test
	public void createUserTest() {
		UserResource userResource = new UserResource(userDaoApi);
		User integration = new User("integration", "initial@integration.com", new ArrayList<String>());
		Response response = userResource.addUser(integration.getName(), integration.getEmail(), integration.getRoles());
		Assert.assertEquals(200, response.getStatus());
	}

	@Test
	public void updateUserTest() {
		UserResource userResource = new UserResource(userDaoApi);
		createUserTest();
		User updated = new User("integration", "updated@integration.com", new ArrayList<String>());
		Response response = userResource.updateUser(updated.getName(), updated.getEmail(), updated.getRoles());
		Assert.assertEquals(200, response.getStatus());
	}
}
