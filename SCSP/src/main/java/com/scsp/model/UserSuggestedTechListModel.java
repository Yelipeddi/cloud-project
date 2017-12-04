package com.scsp.model;

import java.util.List;

public class UserSuggestedTechListModel 
{
  public String[] suggestedTechnologies;

  public String[] getSuggestedTechnologies() {
    return suggestedTechnologies;
  }

  public void setSuggestedTechnologies(List<String> suggestedTech) {
    this.suggestedTechnologies = suggestedTech.toArray(new String[0]);
  }
  

}
