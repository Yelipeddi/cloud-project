package com.scsp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.scsp.model.User;
import com.scsp.service.UserService;

import twitter4j.JSONException;
import twitter4j.JSONObject;

@Controller
public class RegistrationController {
  @Autowired
  public UserService userService;
  
  @RequestMapping(value = "/registerProcess", method = RequestMethod.POST,produces="application/json")
  public @ResponseBody String  addUser(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute("user") User user) 
  {
    
    JSONObject registerResponse=new JSONObject();
    String successMessage="Thank you Signing up.";
    System.out.println("User Details \t"+user.getEmail()+"\t"+user.getFirstname()+"\t"+user.getLastname()+"\t"+user.getPassword());
    
    try 
    {
    userService.register(user);
    //Send SignUp Email to User.
    userService.sendRegisterEmail(user.getEmail());
     //Send Email  
      registerResponse.append("status", successMessage);
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return registerResponse.toString();
      
    }
    return registerResponse.toString();
  }
}
