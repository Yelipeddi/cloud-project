package com.scsp.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.scsp.dao.UserDao;
import com.scsp.model.Login;
import com.scsp.model.TechnologyListDetail;
import com.scsp.model.User;
import com.scsp.model.UserActionSubscribe;
import com.scsp.model.UserUpdateResponseModel;
import com.scsp.model.UserUpdatesModel;

import twitter4j.JSONArray;
import twitter4j.JSONException;
import twitter4j.JSONObject;

public class UserServiceImpl implements UserService {

  @Autowired
  public UserDao userDao;

  public void register(User user) {
    userDao.register(user);
  }

  public User validateUser(Login login) {
    return userDao.validateUser(login);
  }
  
  public Boolean updateUserSubscriptionList(UserActionSubscribe userActionSubscribe) {
    return userDao.updateUserSubscriptionList(userActionSubscribe);
  }
  
  public String ArrayToCommaList(List<String> listInput){
    
    StringBuilder sb = new StringBuilder();
    for(String item: listInput){
        if(sb.length() > 0){
            sb.append(',');
        }
        sb.append(item);
    }
    String result = sb.toString();
    
    return result;
  }
  
  public String GetOnlyLetters(String alphaNumeric)
  {
   String result=""; 
  
    for(int a=0;a<alphaNumeric.chars().count()-1;a++){
      char c=alphaNumeric.charAt(a);
      if(Character.isLetter(c))
      {
        result+=c;
      }
    }
    return result;
  }
  
  @SuppressWarnings("restriction")
  public void sendTechlogyAlertEmail(String senderEmailAddress,String techName)
  {
    final String username = "alerts@ravaliswork.com";
    final String password = "Alerts123";
    Properties properties = System.getProperties();  
    Security.addProvider( new com.sun.net.ssl.internal.ssl.Provider());
    properties.setProperty("mail.smtp.host", "smtp.zoho.com");
    final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
    properties.setProperty("mail.smtp.socketFactory.fallback", "false");
    properties.setProperty("mail.smtp.port", "465");
    properties.setProperty("mail.smtp.socketFactory.port", "465");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.auth", "true");
   // properties.put("mail.debug", "true");
    properties.put("mail.store.protocol", "pop3");
    properties.put("mail.transport.protocol", "smtp");
    //properties.put("mail.debug.auth", "true");
    properties.setProperty( "mail.pop3.socketFactory.fallback", "false");
    
    Session session = Session.getInstance(properties,
      new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
      }
      });

    try {

      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress("alerts@ravaliswork.com"));
      message.setRecipients(Message.RecipientType.TO,
        InternetAddress.parse(senderEmailAddress));
      message.setSubject("SCSP Notification Service");
      Resource resource = new ClassPathResource("EmailTemplates/SendEmailAlertTechUpdate.html");
      InputStream resourceInputStream = resource.getInputStream();
      String result = IOUtils.toString(resourceInputStream, StandardCharsets.UTF_8);
      //message.setText(result);
      message.setContent(result, "text/html; charset=utf-8");
      Transport.send(message);

      System.out.println("Done");

    } catch (MessagingException | IOException e) {
      throw new RuntimeException(e);
    }
  
  }
  
  public void sendRegisterEmail(String senderEmailAddress)
  {
    final String username = "alerts@ravaliswork.com";
    final String password = "Alerts123";
    Properties properties = System.getProperties();  
    Security.addProvider( new com.sun.net.ssl.internal.ssl.Provider());
    properties.setProperty("mail.smtp.host", "smtp.zoho.com");
    final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
    properties.setProperty("mail.smtp.socketFactory.fallback", "false");
    properties.setProperty("mail.smtp.port", "465");
    properties.setProperty("mail.smtp.socketFactory.port", "465");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.auth", "true");
   // properties.put("mail.debug", "true");
    properties.put("mail.store.protocol", "pop3");
    properties.put("mail.transport.protocol", "smtp");
    //properties.put("mail.debug.auth", "true");
    properties.setProperty( "mail.pop3.socketFactory.fallback", "false");
    
    Session session = Session.getInstance(properties,
      new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
      }
      });

    try {

      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress("alerts@ravaliswork.com"));
      message.setRecipients(Message.RecipientType.TO,
        InternetAddress.parse(senderEmailAddress));
      message.setSubject("SCSP Notification Service");
      Resource resource = new ClassPathResource("EmailTemplates/SignUpEmail.html");
      InputStream resourceInputStream = resource.getInputStream();
      String result = IOUtils.toString(resourceInputStream, StandardCharsets.UTF_8);
      //message.setText(result);
      message.setContent(result, "text/html; charset=utf-8");
      Transport.send(message);

      System.out.println("Done");

    } catch (MessagingException | IOException e) {
      throw new RuntimeException(e);
    }
  
  }
  
  @SuppressWarnings("restriction")
  public void sendForgotPasswordEmail(String senderEmailAddress)
  {
    final String username = "alerts@ravaliswork.com";
    final String password = "Alerts123";
    Properties properties = System.getProperties();  
    Security.addProvider( new com.sun.net.ssl.internal.ssl.Provider());
    properties.setProperty("mail.smtp.host", "smtp.zoho.com");
    final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
    properties.setProperty("mail.smtp.socketFactory.fallback", "false");
    properties.setProperty("mail.smtp.port", "465");
    properties.setProperty("mail.smtp.socketFactory.port", "465");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.store.protocol", "pop3");
    properties.put("mail.transport.protocol", "smtp");
    properties.setProperty( "mail.pop3.socketFactory.fallback", "false");
    
    Session session = Session.getInstance(properties,
      new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
      }
      });

    try {

      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress("alerts@ravaliswork.com"));
      message.setRecipients(Message.RecipientType.TO,
        InternetAddress.parse(senderEmailAddress));
      message.setSubject("SCSP Notification Service");
      Resource resource = new ClassPathResource("EmailTemplates/ForgotPassword.html");
      InputStream resourceInputStream = resource.getInputStream();
      String result = IOUtils.toString(resourceInputStream, StandardCharsets.UTF_8);
      message.setContent(result, "text/html; charset=utf-8");
      Transport.send(message);
      System.out.println("Done");
    } catch (MessagingException | IOException e) {
      throw new RuntimeException(e);
    }
  
  }
  
  @Autowired
  JdbcTemplate jdbcTemplate;
  @Override
  public void insertTechnologyVersionInfo(TechnologyListDetail technologyListDetail) {
    
    String sql = "insert into techversioninfo values(?,?,?,?,?,?,?)";
    jdbcTemplate.update(sql, new Object[] { technologyListDetail.getTechname(),technologyListDetail.getCurrentversion(),technologyListDetail.getNewversion(),technologyListDetail.getInserttime(),technologyListDetail.getUpdatetime(),technologyListDetail.getS3_cloudfront_url(),technologyListDetail.getWebsite_url()});
  
  }
  
  public Boolean checkIfTechnologyAlreadyExists(String techName)
  {
    
    String sql = "select * from techversioninfo where techname='" + techName + "'";
    List<TechnologyListDetail> techList = jdbcTemplate.query(sql, new TechnologyListDetailMapper());
    
    if(techList.size()==1)
    {
      techDetail.setTechname(techName);
      techDetail.setS3_cloudfront_url(techList.get(0).getS3_cloudfront_url());
      techDetail.setCurrentversion(techList.get(0).getCurrentversion());
     }
    
    return techList.size() ==1 ? true : false;
  }

  public void updateTechnologyVersion(String newVersion,Timestamp updateCheckTime,String htmlPageContentURL, String techname)
  {    
    String sql = "UPDATE techversioninfo SET newversion=?, updatetime=?, s3_cloudfront_url=? where techname=?";
    jdbcTemplate.update(sql, new Object[] { newVersion,updateCheckTime,htmlPageContentURL,techname});
  
  }
  public void insertIntoS3(String bucketName,String fileName,String fileContents,ObjectMetadata metadata)
  {
    AmazonS3Wrapper s3Connector=new AmazonS3Wrapper();
    s3Connector.uploadFile(bucketName, fileName, fileContents,metadata);
  }
  
  public void deleteS3FileByFileName(String bucketName,String fileName)
  {
    AmazonS3Wrapper s3Connector=new AmazonS3Wrapper();
    s3Connector.DeleteObject(bucketName, fileName);
  }

  @Override
  public List<UserActionSubscribe> ListOfUsersSubscribedToTechnology(String techName) 
  {
    
    String sqlQuery = "select email from userDetails where subscriptionList like '%"+techName+"%'";
    List<UserActionSubscribe> userEmailList = jdbcTemplate.query(sqlQuery, new UserActionSubscribeMapper());
    return userEmailList;
  }

  @Override
  public List<String> getUserActiveSubscriptionList(String useremail) 
  {
    List<String> subscriptionList=new ArrayList<String>();
    String sqlQuery = "select subscriptionList from userDetails where email='"+useremail+"'";
    String sqlCountQuery="select count(*) from userDetails where email=?";
    System.out.println(sqlQuery);
    
    Integer cnt = jdbcTemplate.queryForObject(
        sqlCountQuery, Integer.class, useremail);
     if(cnt != null && cnt > 0){
    
    String subScriptionCharacaters= jdbcTemplate.queryForObject(sqlQuery, String.class);
    
    System.out.println(subScriptionCharacaters+"\tis");
    System.out.println(subscriptionList.indexOf(','));
    
   //Replace with new charater.
    if(subScriptionCharacaters.indexOf(',')>0)
    {
      System.out.println("Contains Comma");
      subscriptionList=Arrays.asList(subScriptionCharacaters.split(","));
    }
    else
    {
      System.out.println("No Comma");
      subscriptionList.add(subScriptionCharacaters);
    }
     }
    return subscriptionList;
  }

  @Override
  public User getUserProfileSettings(String useremail) 
  {
    String sql = "select * from users where email='" + useremail + "'";
    List<User> userList = jdbcTemplate.query(sql, new UserDetailMapper());
    return userList.get(0);
  }

  @Override
  public User updateUserProfileSettingsPassword(User user) 
  {
    String sql = "UPDATE users SET password=? where email=?";
    jdbcTemplate.update(sql, new Object[] { user.getPassword(),user.getEmail()});
    return user;
  }

  @Override
  public List<String> getSuggestedTechnologiesForUser(String useremail)
  {
    String sql = "select * from techversioninfo";
    List<TechnologyListDetail> techList = jdbcTemplate.query(sql, new TechnologyListDetailMapper());
    List<String> techListForUser = getUserActiveSubscriptionList(useremail) ;
    List<String> suggestiveList=new ArrayList<String>();
    for(int a=0;a<techList.size();a++)
    {
      if(!techListForUser.contains(techList.get(a).getTechname()))
      {
        suggestiveList.add(techList.get(a).getTechname());
      }
      
    }
    return suggestiveList;
    
  }

  @Override
  public Boolean updateUserSubscriptions(String subscriptionList,String useremail) 
  {
    Boolean operationValue=true;
   
    //Get User FirstName.
    User user=getUserProfileSettings(useremail);
    
    
    //String sql = "UPDATE userDetails SET subscriptionList=? where email=?";
    String sql = "insert into userDetails (firstname,email,subscriptionList) values (?,?,?) on duplicate key update subscriptionList=?";
    jdbcTemplate.update(sql, new Object[] { user.getFirstname(),useremail,subscriptionList,subscriptionList});
    return operationValue;
  }

  @Override
  public JSONObject getUserUpdates(String userEmail) throws JSONException 
  {
    
    JSONObject result=new JSONObject();
    JSONArray newUpdates=new JSONArray();
    JSONObject updateItem=null;
    UserUpdateResponseModel response=new UserUpdateResponseModel();
    String sql = "select * from userUpdates where email='"+userEmail+"'";
    List<UserUpdatesModel> updateListFromDB = jdbcTemplate.query(sql, new UserUpdatesDetailMapper());

    for(int a=0;a<updateListFromDB.size();a++)
    {
      updateItem=new JSONObject();
      updateItem.put("techname", updateListFromDB.get(a).getTechname());
      updateItem.put("update", updateListFromDB.get(a).getUpdates());
      updateItem.put("url", updateListFromDB.get(a).getURL());
      newUpdates.put(updateItem);
    }
  
    result.put("updates", newUpdates);
    return result;    
  }
  
  public void storeUserUpdates(UserUpdatesModel model) throws JSONException 
  {
    String sql = "insert into userUpdates values(?,?,?,?)";
    jdbcTemplate.update(sql, new Object[] { model.getEmail(),model.getUpdates(),model.getURL(),model.getTechname()});
    return;    
  }
  

  @Override
  public void insertProfilePicToS3(String bucketName, String fileName, MultipartFile file) throws AmazonServiceException, SdkClientException, IOException {

    AmazonS3Wrapper s3Connector=new AmazonS3Wrapper();
    s3Connector.uploadFile(bucketName, fileName, file);
    return;
    
  }

  @Override
  public TechnologyListDetail getTechListDetailByTechName(String techName) 
  {
    String sql = "select * from techversioninfo where techname='" + techName + "'";
    List<TechnologyListDetail> techList = jdbcTemplate.query(sql, new TechnologyListDetailMapper());
    if(techList.size()!=0){
     return  techList.get(0);
    }
    return null;
  }



}

class UserDetailMapper implements RowMapper<User> {

  public User mapRow(ResultSet rs, int arg1) throws SQLException 
  {
    User userDetail = new User();
    userDetail.setEmail(rs.getString("email"));
    userDetail.setFirstname(rs.getString("firstname"));
    userDetail.setLastname(rs.getString("lastname"));
    userDetail.setPassword(rs.getString("password"));
    return userDetail;
  }
}



class UserUpdatesDetailMapper implements RowMapper<UserUpdatesModel> {

  public UserUpdatesModel mapRow(ResultSet rs, int arg1) throws SQLException 
  {
    UserUpdatesModel userUpdate = new UserUpdatesModel();
    userUpdate.setEmail(rs.getString("email"));
    userUpdate.setURL(rs.getString("url"));
    userUpdate.setUpdates(rs.getString("updates"));
    userUpdate.setTechname(rs.getString("techname"));

    return userUpdate;
  }
}

class TechnologyListDetailMapper implements RowMapper<TechnologyListDetail> {

  public TechnologyListDetail mapRow(ResultSet rs, int arg1) throws SQLException 
  {
    TechnologyListDetail technologyListDetail = new TechnologyListDetail();
    technologyListDetail.setTechname(rs.getString("techname"));
    technologyListDetail.setCurrentversion(rs.getString("currentversion"));
    technologyListDetail.setNewversion(rs.getString("newversion"));
    technologyListDetail.setInserttime(rs.getTimestamp("inserttime"));
    technologyListDetail.setUpdatetime(rs.getTimestamp("updatetime"));
    technologyListDetail.setS3_cloudfront_url(rs.getString("s3_cloudfront_url"));
    technologyListDetail.setWebsite_url((rs.getString("website_url")));

    return technologyListDetail;
  }
}


class UserActionSubscribeMapper implements RowMapper<UserActionSubscribe> {

  public UserActionSubscribe mapRow(ResultSet rs, int arg1) throws SQLException 
  {
    UserActionSubscribe userActionSubscribe = new UserActionSubscribe();
    userActionSubscribe.setUserEmail(rs.getString("email"));
    return userActionSubscribe;
  }
}