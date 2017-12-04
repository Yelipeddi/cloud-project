package com.scsp.model;

import org.springframework.web.multipart.MultipartFile;

public class ProfilePicModel 
{
  public String getUseremail() {
    return useremail;
  }
  public void setUseremail(String useremail) {
    this.useremail = useremail;
  }
  public MultipartFile getFile() {
    return file;
  }
  public void setFile(MultipartFile file) {
    this.file = file;
  }
  private String useremail;
  private MultipartFile file;
  

}
