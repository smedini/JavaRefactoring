package test.com.h2rd.refactoring.unit;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDaoApi;
import com.h2rd.refactoring.web.UserResource;
import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class UserResourceUnitTest {
    /**
     * I am using Mock annotation to mock UserDaoApi and then check in test if USerDao api is called or not.
     * I have also configured to use @Before to not repeat the same code for every test.
     */
    @Mock
    private UserDaoApi userDao;
    @InjectMocks
    private UserResource userResource;
    private User user;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User("Fake Name", "fake@email.com", Arrays.asList("admin", "master"));
    }

    @Test
    public void getUsersTest() {
        ArrayList<User> userList = new ArrayList<User>();
        userList.add(user);
        when(userDao.getUsers()).thenReturn(userList);
        Response response = userResource.getUsers();
        Assert.assertEquals(200, response.getStatus());
        verify(userDao, times(1)).getUsers();
    }
}
