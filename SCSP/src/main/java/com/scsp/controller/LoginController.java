package com.scsp.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.scsp.model.Login;
import com.scsp.model.User;
import com.scsp.model.UserSubscriptionResponseModel;
import com.scsp.service.UserService;

@Controller
public class LoginController {

  @Autowired
  UserService userService;

  @RequestMapping(value = "/launchpage", method = RequestMethod.GET)
  public ModelAndView showlaunchPage(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("LaunchPage");
    return mav;
  }
  
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView showlaunchPageDefault(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("LaunchPage");
    return mav;
  }
  
  @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
  public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute("login") Login login) {
    ModelAndView mav = null;

    User user = userService.validateUser(login);

    if (null != user) {
      mav = new ModelAndView("UserHome");
      mav.addObject("firstname", user.getFirstname());
      mav.addObject("email", user.getEmail());
    } else {
      mav = new ModelAndView("LaunchPage");
      mav.addObject("message", "Incorrect Username or Password. Please Try Again!!");
    }

    return mav;
  }
  
  @RequestMapping(value = "/testpage", method = RequestMethod.GET)
  public ModelAndView shoTestPage(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("TestTechVersion");
   // mav.addObject("login", new Login());

    return mav;
  }
  
  @RequestMapping(value = "/hello", method = RequestMethod.GET)
  public ModelAndView showHelloPage(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("hello");
    return mav;
  }
  
  
  

  
}
