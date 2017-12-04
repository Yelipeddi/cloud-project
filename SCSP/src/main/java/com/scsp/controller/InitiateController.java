package com.scsp.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.scsp.model.InitiateResponseModel;
import com.scsp.model.TechnologyListDetail;
import com.scsp.model.UserActionSubscribe;
import com.scsp.model.UserUpdatesModel;
import com.scsp.service.AmazonS3Wrapper;
import com.scsp.service.UserService;

import twitter4j.JSONException;
import twitter4j.JSONObject;

@Controller
@EnableWebMvc
public class InitiateController 
{
  
  @Autowired
  UserService userService;
  
  @RequestMapping(value = "/sendAlerts")
  public @ResponseBody InitiateResponseModel sendAlerts(@RequestParam("s3FileUpdated") String s3FileUpdated,Model model) throws IOException, JSONException {
    InitiateResponseModel temp = new InitiateResponseModel();
    System.out.println("s3FileUpdated is \t"+s3FileUpdated);
    
    String techNameHelper=s3FileUpdated.split("\\.")[0];
    String techName=userService.GetOnlyLetters(techNameHelper);
    System.out.println("TechName is \t"+techName);
    List<UserActionSubscribe> usersSubscribed=userService.ListOfUsersSubscribedToTechnology(techName);
    
    System.out.println("List of usersSubscribed size"+usersSubscribed.size());
    
    for(int a=0;a<usersSubscribed.size();a++)
    {
      System.out.println("usersSubscribed is"+usersSubscribed.get(a).getUserEmail());  
      userService.sendTechlogyAlertEmail(usersSubscribed.get(a).getUserEmail(),techName);
      TechnologyListDetail techDetails=userService.getTechListDetailByTechName(techName);
      
      //Also Store the Update in User Update Table.
      UserUpdatesModel updateModel=new UserUpdatesModel();
      updateModel.setEmail(usersSubscribed.get(a).getUserEmail());
      updateModel.setTechname(techName);
      if(techDetails!=null)
      updateModel.setURL(techDetails.getWebsite_url()); //Later which requires changes to techinfo table.
      updateModel.setUpdates("New Version: "+techDetails.getNewversion()+" for "+techName+" is now available.");
      userService.storeUserUpdates(updateModel);
    }
    return temp;
  }
  
  
  
  @RequestMapping(value = "/initiate",produces="application/json")
  public @ResponseBody String initiateCall(Model model) throws IOException, JSONException 
  {
    
    JSONObject responseModel=new JSONObject();

    //HashMap to Store New and Current Version Strings and URLS.
    HashMap<String,String> newVersions=new HashMap<String,String>();
    HashMap<String,String> websiteURLs=new HashMap<String,String>();
    HashMap<String,Document> documents=new HashMap<String,Document>();

 
    //Get Java JDK latest version
    System.out.println("Get the List of Java JDKs From Oracle Websites");
    websiteURLs.put("Java","http://www.oracle.com/technetwork/java/javase/downloads/index.html");
    Document javadoc = Jsoup.connect(websiteURLs.get("Java")).get();
    documents.put("Java", javadoc);
    Elements link = javadoc.select("h3#javasejdk");
    for (int a = 0; a < link.size() - 1; a++) {
      Element ele = link.get(a);
      Element linkinner = ele.select("a").first();
      System.out.println("Java Version Info Retrieved is \t" + linkinner.html());
      newVersions.put("Java", linkinner.html());
    }

    // Get .Net Latest Version.
    System.out.println("Get the List of Latest .Net FrameWork Version From Microsoft Websites");
    websiteURLs.put("Net","https://docs.microsoft.com/en-us/dotnet/framework/migration-guide/versions-and-dependencies");
    Document netdoc = Jsoup.connect(websiteURLs.get("Net")).get();
    documents.put("Net", netdoc);
    link = netdoc.select("div.primary-holder");
    Element ele = link.get(0);
    Element linkinner = ele.select("td").first();
    newVersions.put("Net", linkinner.html());
    System.out.println("Latest .Net is " + newVersions.get("Net"));

    // Get Python latest version
    System.out.println("Get the List of Latest Python Versions Version From Python Websites");
    websiteURLs.put("Python","https://www.python.org/downloads/");
    Document pythondoc = Jsoup.connect(websiteURLs.get("Python")).get();
    documents.put("Python", pythondoc);
    link = pythondoc.select("span.release-number");
    Element linkinnerPython = link.select("a").first();
    newVersions.put("Python", linkinnerPython.html());
    System.out.println("Latest Python Framework is " + newVersions.get("Python"));

    // Get NetBeans latest version
    System.out.println("Get the List of Latest NetBeans Versions Version From NetBeans Websites");
    websiteURLs.put("NetBeans","https://netbeans.org/community/releases/roadmap.html");
    Document netBeans = Jsoup.connect(websiteURLs.get("NetBeans")).get();
    documents.put("NetBeans", netBeans);
    Elements netBeansTables = netBeans.select("table");
    Element netBeanstable = netBeansTables.get(1);
    Boolean flagSetter = false;
    Elements netBeanstabletrs = netBeanstable.select("tr");
    for (int a = 1; a < netBeanstabletrs.size() - 1; a++) {
      Elements tableTds = netBeanstabletrs.get(a).select("td");
      if (!tableTds.get(2).html().contains("Download")) {
        flagSetter = true;
      } else {
        flagSetter = false;
      }
      if (!flagSetter) {
        newVersions.put("Python", tableTds.get(0).html());
        break;
      }
    }
    System.out.println("Latest Version of Net Beans is \t" + newVersions.get("Python"));

    
    // Get Ruby latest version
    System.out.println("Get the List of Ruby From Ruby Website");
    websiteURLs.put("Ruby","https://www.ruby-lang.org/en/downloads/releases/");
    Document docRuby = Jsoup.connect(websiteURLs.get("Ruby")).get();
    documents.put("Ruby", docRuby);
    Elements linkRuby = docRuby.select("table.release-list");
    newVersions.put("Ruby",linkRuby.select("tr").get(1).select("td").get(0).html());
    System.out.println("Latest Ruby Version is :" + newVersions.get("Ruby"));

    
//    // Get Tomcat latest version
//    System.out.println("Get the List of Tomcat versions From Tomcat Websites");
//    websiteURLs.put("Tomcat","https://tomcat.apache.org/download-80.cgi");
//    Document documentTomcat = Jsoup.connect(websiteURLs.get("Tomcat")).get();
//    Elements linkElements = documentTomcat.select("h3#Quick_Navigation").next().tagName("div");
//    Element latestTomcat = linkElements.select("a").get(1);
//    Element sec_LatestTomcat = linkElements.select("a").get(2);
//    newVersions.put("Tomcat",latestTomcat.html());
//    System.out.println("Latest Tomcat versions:"+newVersions.get("Tomcat"));
   
   
    // Get Scala latest version
    System.out.println("Get the scala latest version versions:");
    websiteURLs.put("Scala","https://www.scala-lang.org/");
    Document documentScala = Jsoup.connect(websiteURLs.get("Scala")).get();
    documents.put("Scala", documentScala);
    Elements scalaVersion=documentScala.select("div.scala-version>span");
    String scaleLatestVersion="";
    for(Element e : scalaVersion) {
          System.out.print(e.text()+"\t");
          newVersions.put("Scala",e.text());
    }
    System.out.println("Scala latest version:"+newVersions.get("Scala"));

    
    // Get Scilab latest version, 
    System.out.println("Get the Scilab latest version versions:");
    websiteURLs.put("Scilab","http://www.scilab.org/en/download/latest");
    Document documentSci = Jsoup.connect(websiteURLs.get("Scilab")).get();
    documents.put("Scilab", documentSci);
    Elements sciVersionList=documentSci.select("ul#leftmenu-firstlevel>li");
    String scilabLatestVersion="";
    for(int i=0;i<2;i++)
    {
      String version= sciVersionList.get(i).getElementsByTag("span").html();
      if(i==0){
        newVersions.put("Scilab",version);
      }
    }
    System.out.println("Latest Version of Scilab is"+newVersions.get("Scilab"));

    
    // Get Perl latest version
    System.out.println("Get the perl latest version:");
    websiteURLs.put("Perl","https://www.perl.org/");
    Document documentPerl = Jsoup.connect(websiteURLs.get("Perl")).get();
    documents.put("Perl", documentPerl);
    newVersions.put("Perl",documentPerl.select("span.version-highlight").html());
    System.out.println("Latest Version of Perl is"+newVersions.get("Perl"));
    
    
    //Get Camel latest version
    System.out.println("Get the latest Camel supported versions");
    websiteURLs.put("Camel","http://camel.apache.org/download.html");
    Document docCamel = Jsoup.connect(websiteURLs.get("Camel")).get();
    documents.put("Camel", docCamel);
    Elements linkCamel = docCamel.select("ul[class=childpages-macro] li");
    String latestCamelVersion="";
    for(int a=1;a<linkCamel.size()-1;a++)
    {
      Element elementCamel=linkCamel.get(a);
      Element linkinnerCamel = elementCamel.select("a").first();
      latestCamelVersion = linkinnerCamel.html();
    }
    newVersions.put("Camel",latestCamelVersion);
    System.out.println("Latest Camel Version is :" + newVersions.get("Camel"));

    // Get Cassandra latest version
    System.out.println("Get the latest Cassandra versions");
    websiteURLs.put("Cassandra","http://cassandra.apache.org/download/");
    Document docCassandra = Jsoup.connect(websiteURLs.get("Cassandra")).get();
    documents.put("Cassandra", docCassandra);
    Elements linkCassandra = docCassandra.select("div[class=container] p");
    System.out.println("Inside Cassandra for loop");
    Element elementCassandra=linkCassandra.get(0);
    Element linkinnerCassandra = elementCassandra.select("a").first();
    newVersions.put("Cassandra",linkinnerCassandra.html()); 
    System.out.println("Latest Cassandra Version is:" + newVersions.get("Cassandra"));
    
    // Get Karaf Container latest version
    System.out.println("Get the latest Karaf versions");
    websiteURLs.put("Karaf","http://karaf.apache.org/download.html");
    Document docKaraf = Jsoup.connect(websiteURLs.get("Karaf")).get();
    documents.put("Karaf", docKaraf);
    Elements linkKaraf = docKaraf.select("div[class=container] div[class=desktop-only] li");
    Element elementKaraf=linkKaraf.get(0);
    Element linkinnerKaraf = elementKaraf.select("a").get(2);
    newVersions.put("Karaf",linkinnerKaraf.html());  
    System.out.println("Latest Karaf Version is:" +  newVersions.get("Karaf"));
    
    // Get ServiceMix latest version
    System.out.println("Get the latest ServiceMix versions");
    websiteURLs.put("ServiceMix","http://servicemix.apache.org/downloads.html");
    Document docServiceMix = Jsoup.connect( websiteURLs.get("ServiceMix")).get();
    documents.put("ServiceMix", docServiceMix);
    Elements linkServiceMix = docServiceMix.select("tr[class=default] td[class=links] p[class=title]");
    Element elementServiceMix=linkServiceMix.get(0);
    Element linkinnerServiceMix = elementServiceMix.select("a").first();
    newVersions.put("ServiceMix", linkinnerServiceMix.html()); 
    System.out.println("Latest ServiceMix Version is:" + newVersions.get("ServiceMix"));
       
    //Test Version WebSite For Demo.
    System.out.println("Get the latest version in test page:");
    websiteURLs.put("Test","http://supreethamg.com.s3-website-us-west-1.amazonaws.com/");
    Document documentTest = Jsoup.connect(websiteURLs.get("Test")).get();
    documents.put("Test", documentTest);
    newVersions.put("Test",documentTest.select("h2#latest").html());
    System.out.println("Latest Version of Test Package is"+newVersions.get("Test"));
    
    
    //Form Response Model
    for(Map.Entry m:newVersions.entrySet())
    {
      responseModel.put(m.getKey().toString(), m.getValue().toString());
      updateOrInsertTechInfo(m.getKey().toString(),documents.get(m.getKey().toString()),m.getValue().toString(),websiteURLs.get(m.getKey().toString()));
      System.out.println(m.getKey()+" "+m.getValue());
     }
   
    
//    updateOrInsertTechInfo("Java",javadoc,latestJDKVersionIs);
//    updateOrInsertTechInfo("Test",javadoc,temp.getTestVersion());
//    updateOrInsertTechInfo("Ruby",docRuby,latestRubyVersion);
//    updateOrInsertTechInfo("Python",pythondoc,latestPythonVersion);
//    updateOrInsertTechInfo("Net",netdoc,latestNetVersion);

    return responseModel.toString();
  }
  
public void updateOrInsertTechInfo  (String techname, Document document, String version,String website_url)
{
  System.out.println("techname is"+techname);
  System.out.println("website_url is"+website_url);

  
  Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("PST"));
  ObjectMetadata metadata = new ObjectMetadata();
  metadata.setContentType("text/plain");
  String filename=techname+calendar.getTimeInMillis()+".html";
  System.out.println("S3 filename is"+filename);

  AmazonS3Wrapper s3Connector=new AmazonS3Wrapper();
  Boolean status= s3Connector.uploadFile("", filename, document.html(),metadata);
  System.out.println("S3 File upload Status is"+status);

  String cloudFrontBaseURL = "http://d3njpsokgy6uhu.cloudfront.net/";
  Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
  
  System.out.println("Cloud Front URL is"+cloudFrontBaseURL);
  System.out.println("File Name is"+filename);

  
  //Get Whether technology is already existing in the existing technology list db.
  if(userService.checkIfTechnologyAlreadyExists(techname))
  {
    
      System.out.println("Technology Current Version:"+userService.techDetail.getCurrentversion()+"\t New Version \t"+version);
      //if the technology is already existing, then check for the version number to see if it matches with new one or else upddate it.  
      if(!version.equals(userService.techDetail.getCurrentversion()))
      {
    
      //Delete Existing S3 File which contains html data.
      String fileNameTobeDeleted=userService.techDetail.getS3_cloudfront_url();
      System.out.println("Update Called: Deleeting S3 Object with FileName"+    fileNameTobeDeleted.substring(cloudFrontBaseURL.length(),fileNameTobeDeleted.length()));
      userService.deleteS3FileByFileName("scsp-project",fileNameTobeDeleted.substring(cloudFrontBaseURL.length(),fileNameTobeDeleted.length()));
    
      //New logic to Insert to S3.
      userService.insertIntoS3("scsp-project",filename,document.html(),metadata);
      
      
      //Now Update the Existing Technology Version and Update TimeStamp Instead of Inserting the Same Again.
      userService.updateTechnologyVersion(version, currentTimeStamp, cloudFrontBaseURL+filename, techname);
      }
      }
  else
  {
  
  //New logic to Insert to S3.
   userService.insertIntoS3("scsp-project",filename,document.html(),metadata);
    
  TechnologyListDetail technologyListDetail = new TechnologyListDetail();
  technologyListDetail.setTechname(techname);
  technologyListDetail.setCurrentversion(version);
  technologyListDetail.setInserttime(currentTimeStamp);
  technologyListDetail.setUpdatetime(currentTimeStamp);
  technologyListDetail.setNewversion(version);
  technologyListDetail.setS3_cloudfront_url(cloudFrontBaseURL+filename);
  technologyListDetail.setWebsite_url(website_url);

  userService.insertTechnologyVersionInfo(technologyListDetail);
  
  }
  
  
}

  
 
}
