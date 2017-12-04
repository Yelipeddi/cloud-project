package com.scsp.service;

import java.io.IOException;
import java.io.InputStream;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.PropertiesCredentials;

public class AmazonS3CredentialsUtil {
 private static AWSCredentialsProvider awsCredentials;
 static {
  init();
 }

 private AmazonS3CredentialsUtil() {}

 private static void init() {
  try {
   Resource resource = new ClassPathResource("credentials");
   InputStream credentialsAsStream = resource.getInputStream();
   AWSCredentials credentials = new PropertiesCredentials(credentialsAsStream);
   awsCredentials = new AWSStaticCredentialsProvider(credentials);
  } catch (IOException e) {
   e.printStackTrace();
  }
 }

 public static AWSCredentialsProvider getAwsCredentials() {
  return awsCredentials;
 }

}