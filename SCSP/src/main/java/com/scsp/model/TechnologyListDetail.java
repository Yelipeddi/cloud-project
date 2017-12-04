package com.scsp.model;

import java.sql.Timestamp;

public class TechnologyListDetail {

  private String techname;
  private String currentversion;
  private String newversion;
  private Timestamp inserttime;
  private Timestamp updatetime;
  private String s3_cloudfront_url;
  private String website_url;

 
  
  public String getWebsite_url() {
    return website_url;
  }
  public void setWebsite_url(String website_url) {
    this.website_url = website_url;
  }
  public String getTechname() {
    return techname;
  }
  public void setTechname(String techname) {
    this.techname = techname;
  }
  public String getCurrentversion() {
    return currentversion;
  }
  public void setCurrentversion(String currentversion) {
    this.currentversion = currentversion;
  }
  public String getNewversion() {
    return newversion;
  }
  public void setNewversion(String newversion) {
    this.newversion = newversion;
  }
  public Timestamp getInserttime() {
    return inserttime;
  }
  public void setInserttime(Timestamp inserttime) {
    this.inserttime = inserttime;
  }
  public Timestamp getUpdatetime() {
    return updatetime;
  }
  public void setUpdatetime(Timestamp updatetime) {
    this.updatetime = updatetime;
  }
  public String getS3_cloudfront_url() {
    return s3_cloudfront_url;
  }
  public void setS3_cloudfront_url(String s3_cloudfront_url) {
    this.s3_cloudfront_url = s3_cloudfront_url;
  }


}
