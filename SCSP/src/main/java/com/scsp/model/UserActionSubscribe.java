package com.scsp.model;

public class UserActionSubscribe 
{
  public String getUserEmail() {
    return userEmail;
  }
  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }
 
  public String getUserFirstName() {
    return userFirstName;
  }
  public void setUserFirstName(String userFirstName) {
    this.userFirstName = userFirstName;
  }

  public Boolean getJavaSubscribed() {
    return javaSubscribed;
  }
  public void setJavaSubscribed(Boolean javaSubscribed) {
    this.javaSubscribed = javaSubscribed;
  }
  
  public Boolean getNetSubscribed() {
    return netSubscribed;
  }
  public void setNetSubscribed(Boolean netSubscribed) {
    this.netSubscribed = netSubscribed;
  }
  public String userEmail;
  public String userFirstName;
  public Boolean javaSubscribed; 
  public Boolean netSubscribed;
  public Boolean testSubscribed;

  public Boolean getTestSubscribed() {
    return testSubscribed;
  }
  public void setTestSubscribed(Boolean testSubscribed) {
    this.testSubscribed = testSubscribed;
  }
  public String subscriptionList;
  public String getSubscriptionList() {
    return subscriptionList;
  }
  public void setSubscriptionList(String subscriptionList) {
    this.subscriptionList = subscriptionList;
  }

}
