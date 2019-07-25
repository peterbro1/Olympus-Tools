/*    */ package me.gmx.olympus.util;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FileUtils
/*    */ {
/*    */   public static void copy(InputStream paramInputStream, File paramFile) {
/*    */     try {
/* 18 */       FileOutputStream fileOutputStream = new FileOutputStream(paramFile);
/* 19 */       byte[] arrayOfByte = new byte[1024];
/*    */       int i;
/* 21 */       while ((i = paramInputStream.read(arrayOfByte)) > 0) {
/* 22 */         fileOutputStream.write(arrayOfByte, 0, i);
/*    */       }
/* 24 */       fileOutputStream.close();
/* 25 */       paramInputStream.close();
/* 26 */     } catch (IOException iOException) {
/* 27 */       iOException.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void mkdir(File paramFile) {
/*    */     try {
/* 38 */       paramFile.mkdir();
/* 39 */     } catch (Exception exception) {
/* 40 */       exception.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\peter\Desktop\gangs\GangsPlus-2.2.1-SNAPSHOT.jar!\net\brcdev\gang\\util\FileUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.3
 */