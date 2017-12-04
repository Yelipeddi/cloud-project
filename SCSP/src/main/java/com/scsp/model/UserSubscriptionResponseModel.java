package com.scsp.model;

import java.util.List;

public class UserSubscriptionResponseModel 
{
  public String[] getSubscriptionList() {
    return subscriptionList;
  }

  public void setSubscriptionList(List<String> subscriptionList) {
    this.subscriptionList = subscriptionList.toArray(new String[0]);
  }

  private String[] subscriptionList;

}
