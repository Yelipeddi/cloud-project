package com.scsp.service;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class AmazonS3FileUploadUtil {

 @SuppressWarnings("deprecation")
 public static void DeleteFile(String bucketName, String keyname) {
  AmazonS3 s3Client = new AmazonS3Client(AmazonS3CredentialsUtil.getAwsCredentials());
  s3Client.deleteObject(new DeleteObjectRequest(bucketName, keyname));
 }

 public static File convert(MultipartFile file) throws IOException {

  File convFile = new File(file.getOriginalFilename());
  try 
  {
   convFile.createNewFile();
   FileOutputStream fos = new FileOutputStream(convFile);
   fos.write(file.getBytes());
   fos.close();
  } catch (Exception e) {
   e.printStackTrace();
  }
  return convFile;
 }
 
 @SuppressWarnings("deprecation")
 public static void putFileAsInputStream(String bucket, String fileName, MultipartFile fileInput) throws IOException {
  AmazonS3 s3Client = new AmazonS3Client(AmazonS3CredentialsUtil.getAwsCredentials());
  File returnedFile = convert(fileInput);
  PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, fileName, returnedFile);
  s3Client.putObject(putObjectRequest);
  returnedFile.delete();
 }

 public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
  File convFile = new File(multipart.getOriginalFilename());
  multipart.transferTo(convFile);
  return convFile;
 }

}