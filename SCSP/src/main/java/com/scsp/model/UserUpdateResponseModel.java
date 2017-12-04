package com.scsp.model;

import java.util.List;

public class UserUpdateResponseModel 
{
  
  
  public String[] news;
  public String[] updates;
  public String[] twitterRecommendations;
  
  public String[] getNews() {
    return news;
  }
  public void setNews(String[] news) {
    this.news = news;
  }
  public String[] getUpdates() {
    return updates;
  }
  
  
  public void setUpdates(List<String> updatesList) {
    this.updates = updatesList.toArray(new String[0]);
  }
  public void setTwitterRecommendations(List<String> setTwitterRecommendationsList) {
    this.twitterRecommendations = setTwitterRecommendationsList.toArray(new String[0]);
  }
   public void setNews(List<String> newsList) {
    this.news = newsList.toArray(new String[0]);
  }
  
  public void setUpdates(String[] updates) {
    this.updates = updates;
  }
  public String[] getTwitterRecommendations() {
    return twitterRecommendations;
  }
  public void setTwitterRecommendations(String[] twitterRecommendations) {
    this.twitterRecommendations = twitterRecommendations;
  }

  


}
