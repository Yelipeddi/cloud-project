package com.scsp.model;

public class UserActionResponse 
{

    private Boolean response;

    public Boolean getResponse() {
      return response;
    }

    public void setResponse(Boolean response) {
      this.response = response;
    }

    public UserActionResponse(Boolean s) { 
       this.response = s;
    }
    public UserActionResponse(){}
  

}
