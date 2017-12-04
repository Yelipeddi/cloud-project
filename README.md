# Cloud-Project2-Group8

University Name: http://www.sjsu.edu/ 

Course: Cloud Technologies

Professor Sanjay Garje

ISA: Divyankitha Urs

Student: <br>Ravali N</br>
           <br>Supreetha Ganapathi</br>
           <br>Abhinaya Yellipeddi</br>
    
# Software Catalogue Subscription Portal (SCSP)-Introduction
    
    SCSP Portal is for enthusiast developers who are keen on knowing the latest releases on software/technologies and the technology news. Users can subscribe to their interests and get updates periodically on the new release/ news.

# Sample Demo screenshots

<br>SignUp Page</br>
![image_1](https://user-images.githubusercontent.com/31361767/33581076-4f1c5fbe-d904-11e7-8e1a-e288822eb544.jpeg)

<br>Login Page</br>
![image_2](https://user-images.githubusercontent.com/31361767/33581668-9b7281f2-d906-11e7-9b67-c68d462031bc.jpeg)

<br>Dashboard Subscription Page</br>
![image_5](https://user-images.githubusercontent.com/31361767/33581698-afb2d8ba-d906-11e7-9b2d-cbce8760ec89.png)

<br>Twitter Integration Page </br>
![image_3](https://user-images.githubusercontent.com/31361767/33581717-c8f6bc1a-d906-11e7-9c81-73d7cd9c44d7.jpeg)

<br>Techcrunch Integration Page </br>
![image_4](https://user-images.githubusercontent.com/31361767/33581726-daa1ae3e-d906-11e7-9892-7801403254fd.jpeg)

<br>Settings Page </br>
![image_6](https://user-images.githubusercontent.com/31361767/33581783-1a247884-d907-11e7-8b1f-eb112a6c4484.png)

# Pre-requisites Set Up
    
# Configurations to run the code locally 

<br>• IDE (Eclipse for Windows/IntelliJ for Mac) for managing the code. </br>
<br>• JAVA 1.7 or above  </br>
<br> •credentials: file needs to have the required AWS details (secret key) </br>
# Following AWS services need to be configured 
<br>1.    RDS: Setup a mysql database, create the pre-requisite tables based on the attached script: DBScript.sql (part of the attached code)</br>
<br>2.    S3 buckets: S3 buckets are for storing the data related to our application.</br>
<br>3.    CloudFront: To manage the content delivery of web pages, we make use of AWS cloudfront service.</br>
<br>4.    AWS Polly: AWS service that turns text to speech. When user manages his subscription, you will have Polly confirm to the user on their updates with an audio.</br>
<br>5.    AWS Dynamodb: AWS Dynamodb is a no-sql database used to handle the search queries from registered users. When users query new technology, to keep track of such new requirements, is handled via Dynamodb.</br>
<br>6.    AWS Lambda: AWS Lambda helps in server less setup and in this project, we have used it to schedule the cron job or upon changes in AWS S3 bucket.</br>
<br>7.    CloudWatch: CloudWatch is used to monitor the AWS services and trigger alarms when service has any interruption. We use it to monitor the Lambda trigger</br>
<br>8.    SNS: We use this service to trigger email alert for example when there is an update in S3 etc.</br>
<br>9.    Elastic Beanstalk: You can host your application using ELB stack. Alternatively, you can configure hosting in AWS EC2 instance too.</br>
<br>10.    Route53: If you wish to have your custom url, then you can configure a domain name and redirect from this custom url to your app. </br>
<br>11.    AWS Lex: AWS Lex helps us build conversational interface in our application using voice and text. </br>
<br>12.    IAM: You need to have a user with required permissions to manage the above resources.</br>

<br> After setting up the resources as above, run the following commands to run the application:</br>
     <br> mvn clean install</br>
<br>The application will be accessible on the local host at port 80 (when configured locally)</br>
