package com.scsp.dao;

import com.scsp.model.Login;
import com.scsp.model.User;
import com.scsp.model.UserActionSubscribe;

public interface UserDao {

  void register(User user);

  User validateUser(Login login);
  
  Boolean updateUserSubscriptionList(UserActionSubscribe userActionSubscribe);
  
}
