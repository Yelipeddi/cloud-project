package com.scsp.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class AmazonS3Wrapper
{



  public Boolean DeleteObject(String bucketName,String fileName)
  {
    try
    {

      AmazonS3 s3 = new AmazonS3Client(AmazonS3CredentialsUtil.getAwsCredentials());
      s3.deleteObject(bucketName, fileName);
      return true;
    }
    catch(AmazonServiceException e)
    {
        return false;
    }
    catch(Exception ex)
    {
        return false;
    }

  }

  public Boolean uploadFile(String bukcetName,String fileName, String webpage, ObjectMetadata metadata )
  {

    try
    {

      AmazonS3 s3 = new AmazonS3Client(AmazonS3CredentialsUtil.getAwsCredentials());
      byte[]  contentAsBytes = webpage.getBytes("UTF-8");
        ByteArrayInputStream    contentsAsStream      = new ByteArrayInputStream(contentAsBytes);
        ObjectMetadata          md = new ObjectMetadata();
        md.setContentLength(contentAsBytes.length);
        md.setContentType("text/plain");
        s3.putObject(new PutObjectRequest(bukcetName, fileName, contentsAsStream, md));
        return true;
    }
    catch(AmazonServiceException e)
    {
        return false;
    }
    catch(Exception ex)
    {
        return false;
    }

  }


  public static File convert(MultipartFile file) throws IOException
  {    System.out.println("Convert1");

      File convFile = new File(file.getOriginalFilename());

    try{
    System.out.println("Convert2"+convFile.getAbsolutePath());

      convFile.createNewFile();
    System.out.println("Convert3");

      FileOutputStream fos = new FileOutputStream(convFile);
    System.out.println("Convert4");

      fos.write(file.getBytes());
    System.out.println("Convert5");

      fos.close();
    System.out.println("Convert6");
    }
    catch(Exception e) {
           e.printStackTrace();
        }
      return convFile;
  }



  void uploadFile(String bucketname,String fileName,MultipartFile file) throws AmazonServiceException, SdkClientException, IOException
  {

    AmazonS3 s3client = new AmazonS3Client(AmazonS3CredentialsUtil.getAwsCredentials());
    s3client.putObject(bucketname, fileName, convert(file));
    return ;
  }


}
