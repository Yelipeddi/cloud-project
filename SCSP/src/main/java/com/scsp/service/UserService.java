package com.scsp.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.scsp.model.Login;
import com.scsp.model.TechnologyListDetail;
import com.scsp.model.User;
import com.scsp.model.UserActionSubscribe;
import com.scsp.model.UserUpdateResponseModel;
import com.scsp.model.UserUpdatesModel;

import twitter4j.JSONException;
import twitter4j.JSONObject;

public interface UserService {

  //User Subscription View Related Methods.
  List<String> getUserActiveSubscriptionList(String useremail);
  List<String> getSuggestedTechnologiesForUser(String useremail);
  Boolean updateUserSubscriptions(String subscriptionList,String useremail);
  
  //User Profile Update Related
  User getUserProfileSettings(String useremail);
  User updateUserProfileSettingsPassword(User user);

  //Send Email Alert Related and Save User Update From Service.
  List<UserActionSubscribe> ListOfUsersSubscribedToTechnology(String techName);
  void sendTechlogyAlertEmail(String senderEmailAddress,String techName);
  void sendRegisterEmail(String senderEmailAddress);
  void sendForgotPasswordEmail(String senderEmailAddress);
  TechnologyListDetail getTechListDetailByTechName(String techName);

  
  //Helper Service Methods.
  String ArrayToCommaList(List<String> listInput);
  String GetOnlyLetters(String alphaNumeric);
 
  
  //User Home Related.
  JSONObject getUserUpdates(String userEmail) throws JSONException ;
  void storeUserUpdates(UserUpdatesModel model) throws JSONException;
  
  //User Subscription Page Related
  Boolean updateUserSubscriptionList(UserActionSubscribe userActionSubscribe);

  
  //Initiate Controller Related.
  public TechnologyListDetail techDetail=new TechnologyListDetail() ;
  Boolean checkIfTechnologyAlreadyExists(String techName);
  void insertTechnologyVersionInfo(TechnologyListDetail technologyListDetail);
  void updateTechnologyVersion(String newVersion,Timestamp updateCheckTime,String htmlPageContentURL,String techname);

  
   
  //User Login and Register Related. 
  void register(User user);
  User validateUser(Login login);
  
 
  //Amazon S3 Related.
  void insertIntoS3(String bucketName,String fileName,String fileContents,ObjectMetadata metadata);
  void deleteS3FileByFileName(String bucketName,String fileName);
  
 //insert profile picture to S3
  void insertProfilePicToS3(String bucketName,String fileName,MultipartFile file) throws AmazonServiceException, SdkClientException, IOException;
}
