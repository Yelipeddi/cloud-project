package com.scsp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.scsp.model.Login;
import com.scsp.model.ProfilePicModel;
import com.scsp.model.User;
import com.scsp.model.UserActionResponse;
import com.scsp.model.UserActionSubscribe;
import com.scsp.model.UserSubscriptionResponseModel;
import com.scsp.model.UserSuggestedTechListModel;
import com.scsp.model.UserUpdateResponseModel;
import com.scsp.service.AmazonS3CredentialsUtil;
import com.scsp.service.UserService;

import twitter4j.JSONArray;
import twitter4j.JSONException;
import twitter4j.JSONObject;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.BatchGetItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.TableCollection;
import com.amazonaws.services.dynamodbv2.document.TableKeysAndAttributes;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.polly.AmazonPollyClient;
import com.amazonaws.services.polly.model.DescribeVoicesRequest;
import com.amazonaws.services.polly.model.DescribeVoicesResult;
import com.amazonaws.services.polly.model.OutputFormat;
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest;
import com.amazonaws.services.polly.model.SynthesizeSpeechResult;
import com.amazonaws.services.polly.model.Voice;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
@Controller
public class UserActionController
{

  @Autowired
  UserService userService;

  static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
      .withCredentials(AmazonS3CredentialsUtil.getAwsCredentials())
      .withRegion("us-east-1")
      .build();
  static DynamoDB dynamoDB = new DynamoDB(client);
  private AmazonPollyClient polly;
  private Voice voice;
  private static final String SAMPLE = "Your settings have been successfully changed";



  public UserActionController() {
    // To create an polly client.

    polly = new AmazonPollyClient(AmazonS3CredentialsUtil.getAwsCredentials(), new ClientConfiguration());
    //polly.setRegion(Region.getRegion(Regions.US_WEST_1));
    // To Create voice request.
    DescribeVoicesRequest describeVoicesRequest = new DescribeVoicesRequest();

    // To select from available TTS voices.
    DescribeVoicesResult describeVoicesResult = polly.describeVoices(describeVoicesRequest);
    voice = describeVoicesResult.getVoices().get(0);

  }



  //To Navigate to UserHome.
  @RequestMapping(value = "/userhome", method = RequestMethod.GET)
  public ModelAndView showUserHome(HttpServletRequest request, HttpServletResponse response) {
    System.out.println("userhome called");
    ModelAndView mav = new ModelAndView("UserHome");
    return mav;
  }

  //To Navigate to UserSettings.
  @RequestMapping(value = "/usersettings", method = RequestMethod.GET)
  public ModelAndView showUserSettings(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("UserSettings");
    return mav;
  }

  //To Navigate to UserSubscriptions.
  @RequestMapping(value = "/usersubscriptions", method = RequestMethod.GET)
  public ModelAndView showUserSubscriptions(HttpServletRequest request, HttpServletResponse response) {
    System.out.println("usersubscriptions called");
    ModelAndView mav = new ModelAndView("UserSubscription");
    return mav;
  }

  //Get User Profile Details Call: http://localhost:8080/SpringMvcUser/settings?useremail=ravalinrr@hotmail.com
  @RequestMapping(value = "/settings")
  public @ResponseBody User GetUserProfileSettings(@RequestParam("useremail") String userEmail) throws IOException
  {
    System.out.println("In Get User Settings for User \t");
    return userService.getUserProfileSettings(userEmail);
  }

  //Set User Profile Password Information
  @RequestMapping(value = "/settings", method = RequestMethod.POST)
  public @ResponseBody  User SetUserProfileSettings(@RequestParam("useremail") String userEmail,HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute("user") User user)
  {
    System.out.println("In Update User Settings for User \t"+user.getPassword()+"\t"+user.getEmail()+"\t"+user.getFirstname()+"\t"+user.getLastname());
    return userService.updateUserProfileSettingsPassword(user);

  }


  //Get Future Set of Suggested Technologies per user to be used with Search Bar.
  //http://localhost:8080/SpringMvcUser/suggestedlist?useremail=uratravali@gmail.com
  @RequestMapping(value = "/suggestedlist")
  public @ResponseBody UserSuggestedTechListModel GetUserSuggestedList(@RequestParam("useremail") String userEmail) throws IOException
  {
    System.out.println("In Get User Suggetive List for User \t"+userEmail);
    UserSuggestedTechListModel response=new UserSuggestedTechListModel();
     response.setSuggestedTechnologies(userService.getSuggestedTechnologiesForUser(userEmail));
     return response;
  }


  //Set User Subsciption List
  @RequestMapping(value = "/subscriptions", method = RequestMethod.POST)
  public @ResponseBody  UserActionResponse SetUserSubscription(@RequestParam("useremail") String userEmail,@RequestParam("subscriptionlist") String subscriptionList,HttpServletRequest request, HttpServletResponse response) throws JavaLayerException, IOException
  {
    System.out.println("In Update USer Subscription Listor User \t"+userEmail+"\t"+subscriptionList);
    UserActionResponse userActionResponse=new UserActionResponse();
    userActionResponse.setResponse(userService.updateUserSubscriptions(subscriptionList,userEmail));

  //Polly code for reading the text specified.
    // Code to get the audio stream
    InputStream speechStream = this.synthesize(SAMPLE, OutputFormat.Mp3);
    // code for create an MP3 player
    AdvancedPlayer player = new AdvancedPlayer(speechStream,
    javazoom.jl.player.FactoryRegistry.systemRegistry().createAudioDevice());
    player.setPlayBackListener(new PlaybackListener() {
      @Override
      public void playbackStarted(PlaybackEvent evt) {
        System.out.println("Playback started");
        System.out.println(SAMPLE);
      }

      @Override
      public void playbackFinished(PlaybackEvent evt) {
        System.out.println("Playback finished");
      }
      });
      // for playing the voice!
      player.play();


    return userActionResponse;
  }
  public InputStream synthesize(String text, OutputFormat format) throws IOException {
    SynthesizeSpeechRequest synthReq = new SynthesizeSpeechRequest().withText(text).withVoiceId(voice.getId())
        .withOutputFormat(format);
    SynthesizeSpeechResult synthRes = polly.synthesizeSpeech(synthReq);

    return synthRes.getAudioStream();
  }
  //Saving the searched not supported technology to dynamodb for future use.
  @RequestMapping(value = "/search")
  public void search(@RequestParam("useremail") String userEmail, @RequestParam("searchquery") String searchQuery) {
    final String tableName = "SearchTech";
    Table table = dynamoDB.getTable(tableName);

    final boolean exists = userService.checkIfTechnologyAlreadyExists(searchQuery);
    if (exists) {
      return;
    }


    final Item userItem = table.getItem("UserName", userEmail, "TechonologyName", searchQuery);
    System.out.println("DynamoDB table details:\t"+table.getTableName());
    System.out.println("userItem\t"+userItem);
    //checking if user has already searched the technology.If not adding to the db.
    if (userItem == null) {
      try {
        Item item = new Item().withPrimaryKey("UserName", userEmail).withString("TechonologyName", searchQuery);
        System.out.println("Item\t"+item);
        table.putItem(item);
        System.out.println("Created new item.");
      } catch (Exception e) {
        System.err.println("Create items failed.");
        System.err.println(e.getMessage());
      }
    }
  }

  //Example API Call: http://localhost:8080/SpringMvcUser/subscriptions?useremail=ravalinrr@hotmail.com
  @RequestMapping(value = "/subscriptions")
  public @ResponseBody UserSubscriptionResponseModel GetUserCurrentSubscriptionList(@RequestParam("useremail") String userEmail) throws IOException
  {
    System.out.println("In Get User Subscriptions for User \t");
    UserSubscriptionResponseModel response=new UserSubscriptionResponseModel();
    response.setSubscriptionList(userService.getUserActiveSubscriptionList(userEmail));
    return response;
  }



  //User Home Related Controllers.
  @RequestMapping(value = "/userupdates",produces="application/json")
  public @ResponseBody String GetCurrentUserUpdates(@RequestParam("useremail") String userEmail) throws IOException, JSONException
  {
    System.out.println("In Get User UPdates for User \t"+userEmail);
    JSONObject response=new JSONObject();
    response=userService.getUserUpdates(userEmail);
    return response.toString();
  }


//User Home Related Controllers.
  @RequestMapping(value = "/usertwitterrecommendations",produces="application/json")
  public @ResponseBody String GetTwitterRecommendations(@RequestParam("useremail") String userEmail) throws IOException, TwitterException, JSONException
  {
    System.out.println("In Get User Twitter Recommendations for User \t"+userEmail);
    UserUpdateResponseModel response=new UserUpdateResponseModel();

    List<String> userTopics=userService.getUserActiveSubscriptionList(userEmail);
    //response.setSubscriptionList(userService.getUserActiveSubscriptionList(userEmail));
 // The factory instance is re-useable and thread safe.
    JSONObject returnRecommendations=new JSONObject();
    JSONArray recommendationArray=new JSONArray();

    ConfigurationBuilder cb = new ConfigurationBuilder();
    cb.setDebugEnabled(true)
      .setOAuthConsumerKey("")
      .setOAuthConsumerSecret("")
      .setOAuthAccessToken("")
      .setOAuthAccessTokenSecret("");
    TwitterFactory tf = new TwitterFactory(cb.build());
    Twitter twitter = tf.getInstance();
    List<String> tweets=new ArrayList<String>();
    JSONObject recItem=null;

    for(int a=0;a<userTopics.size();a++)
    {
      //recommendationArray

      String searchTopic=userTopics.get(a);
      if(searchTopic.contains("Net"))
      {
        searchTopic=".NET Team";
      }
      ResponseList<twitter4j.User> recommendedUsers=twitter.searchUsers(searchTopic,1);
      for (twitter4j.User user : recommendedUsers)
      {
        System.out.println("Search Item \t"+searchTopic);

          if(user.getFollowersCount()>100000)
          {
            recItem=new JSONObject();
            recItem.put("name", user.getName());
            recItem.put("url", "https://twitter.com/"+user.getScreenName());
            recommendationArray.put(recItem);
          }
      }

      }

    returnRecommendations.put("followers", recommendationArray);
    return returnRecommendations.toString();
  }

  //(@RequestParam("file") MultipartFile file,@RequestParam("useremail") String userEmail,RedirectAttributes redirectAttributes
  @PostMapping(value = "/profilepicture", produces="application/json")
  public String uploadFileData(ProfilePicModel fileModel) throws IllegalStateException, IOException, JSONException
  {
  //public String handleFileUpload(@ModelAttribute("file")) throws AmazonServiceException, SdkClientException, IOException, JSONException


    System.out.println("In Update User Profile Picture for User \t"+fileModel.getUseremail());

    JSONObject jsonObject = new JSONObject();
    ObjectMetadata metadata = new ObjectMetadata();
    metadata.setContentType("text/plain");
    String filename=fileModel.getUseremail()+".png";
    userService.insertProfilePicToS3("scsp-techlist",filename,fileModel.getFile());
    jsonObject.append("status", true);
    return jsonObject.toString();
  }


}
