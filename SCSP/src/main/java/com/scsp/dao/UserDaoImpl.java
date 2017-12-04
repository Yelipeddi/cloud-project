package com.scsp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.scsp.model.Login;
import com.scsp.model.User;
import com.scsp.model.UserActionSubscribe;

public class UserDaoImpl implements UserDao {

  @Autowired
  DataSource datasource;

  @Autowired
  JdbcTemplate jdbcTemplate;

  public void register(User user) {

    String sql = "insert into users values(?,?,?,?)";

    jdbcTemplate.update(sql, new Object[] { user.getFirstname(),user.getLastname(),user.getEmail(), user.getPassword()});
  }

  public User validateUser(Login login) {

    String sql = "select * from users where email='" + login.getEmail() + "' and password='" + login.getPassword()
        + "'";

    List<User> users = jdbcTemplate.query(sql, new UserMapper());

    return users.size() > 0 ? users.get(0) : null;
  }

  public Boolean updateUserSubscriptionList(UserActionSubscribe userActionSubscribe){
    
    //INSERT INTO `SCSP`.`userDetails` (firstname,email,subscriptionList) VALUES("", "", "") ON DUPLICATE KEY UPDATE    
   // firstname="", subscriptionList=""
    String sql = "insert into `SCSP`.`userDetails` values(?,?,?) ON DUPLICATE KEY UPDATE firstname=?,subscriptionList=?";

    jdbcTemplate.update(sql, new Object[] {userActionSubscribe.getUserFirstName(),userActionSubscribe.getUserEmail(),userActionSubscribe.getSubscriptionList(),userActionSubscribe.getUserFirstName(),userActionSubscribe.getSubscriptionList() });
 
    return true;
  }

  
  
  
  
  
}

class UserMapper implements RowMapper<User> {

  public User mapRow(ResultSet rs, int arg1) throws SQLException {
    User user = new User();

   
    user.setPassword(rs.getString("password"));
    user.setFirstname(rs.getString("firstname"));
    user.setLastname(rs.getString("lastname"));
    user.setEmail(rs.getString("email"));

    return user;
  }
}