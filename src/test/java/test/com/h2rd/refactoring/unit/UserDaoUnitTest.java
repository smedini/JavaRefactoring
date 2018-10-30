package test.com.h2rd.refactoring.unit;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoUnitTest {

    @InjectMocks
    private UserDao userDao;
    private User user;

    @Before
    public void setUp() {
        user = new User("Fake Name", "fake@email.com", Arrays.asList("admin", "master"));
    }

    @Test
    public void saveUserTest() {
        userDao.saveUser(user);
        User userResult = userDao.findUser(user.getName());
        Assert.assertEquals(userResult.getName(), "Fake Name");
    }

    @Test
    public void deleteUserTest() {
        userDao.deleteUser(user);
    }
}
