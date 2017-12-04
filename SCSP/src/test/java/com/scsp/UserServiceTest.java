package com.scsp;
/*package com.scsp;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.scsp.model.Login;
import com.scsp.model.User;
import com.scsp.service.UserService;

@ContextConfiguration(locations = { "classpath:scsp/config/user-beans.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

  @Autowired
  UserService userService;

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void testValidateUser() {

    Login login = new Login();
    login.setEmail("Ravali");
    login.setPassword("N");

    User user = userService.validateUser(login);

    assertEquals("Ravali", user.getFirstname());
  }

}
*/