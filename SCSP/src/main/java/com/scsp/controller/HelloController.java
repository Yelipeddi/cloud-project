package com.scsp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

public class HelloController {

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView getData() {

    ModelAndView model = new ModelAndView("hello");
  
    return model;

  }
}
