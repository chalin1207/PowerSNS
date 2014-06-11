package com.sns.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.kobjects.base64.Base64;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;


public class ImageDispose {

	/**  
	     * @param 将图片内容解析成字节数组  
	     * @param inStream  
	     * @return byte[]  
	     * @throws Exception  
	     */    
	   public static byte[] readStream(InputStream inStream) throws Exception {    
	        byte[] buffer = new byte[1024];    
	        int len = -1;    
	        ByteArrayOutputStream outStream = new ByteArrayOutputStream();    
	        while ((len = inStream.read(buffer)) != -1) {    
	            outStream.write(buffer, 0, len);    
	        }    
	        byte[] data = outStream.toByteArray();    
	        outStream.close();    
	        inStream.close();    
	        return data;    
	  
	    }    
	    /**  
	     * @param 将字节数组转换为ImageView可调用的Bitmap对象  
	     * @param bytes  
	     * @param opts  
	     * @return Bitmap  
	    */    
	    public static Bitmap getPicFromBytes(byte[] bytes,    
	           BitmapFactory.Options opts) {    
	        if (bytes != null)    
	            if (opts != null)    
	               return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,    
	                        opts);    
	           else    
	               return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);    
	        return null;    
	    }    
	    /**  
	     * @param 图片缩放  
	    * @param bitmap 对象  
	     * @param w 要缩放的宽度  
	     * @param h 要缩放的高度  
	     * @return newBmp 新 Bitmap对象  
	     */    
	    public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h){    
	        int width = bitmap.getWidth();    
	        int height = bitmap.getHeight();    
	        Matrix matrix = new Matrix();    
	        float scaleWidth = ((float) w / width);    
	        float scaleHeight = ((float) h / height);    
	        matrix.postScale(scaleWidth, scaleHeight);    
	        Bitmap newBmp = Bitmap.createBitmap(bitmap, 0, 0, width, height,    
	                matrix, true);    
	       return newBmp;    
	   }    
	        
	    /**  
	     * 把Bitmap转Byte  
	     */    
	   public static byte[] Bitmap2Bytes(Bitmap bm){    
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();    
	       bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);    
	        return baos.toByteArray();    
	    }    
	    /**  
	     * 把字节数组保存为一个文件  
	     */    
	    public static File getFileFromBytes(byte[] b, String outputFile) {    
	        BufferedOutputStream stream = null;    
	        File file = null;    
	        try {    
	            file = new File(outputFile);    
	            FileOutputStream fstream = new FileOutputStream(file);    
	            stream = new BufferedOutputStream(fstream);    
	            stream.write(b);    
	        } catch (Exception e) {    
	           e.printStackTrace();    
	        } finally {    
	           if (stream != null) {    
	                try {    
	                   stream.close();    
	                } catch (IOException e1) {    
	                    e1.printStackTrace();    
	                }    
	            }    
	        }    
	        return file;    
	    } 
	    
	    //更具URI加载图片
	    public static Bitmap getBitmapFromUri(Activity context,Uri uri)
	    {
	     try
	     {
	      // 读取uri所在的图片
	      Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
	      return bitmap;
	     }
	     catch (Exception e)
	     {
	      Log.e("[Android]", e.getMessage());
	      Log.e("[Android]", "目录为：" + uri);
	      e.printStackTrace();
	      return null;
	     }
	    }
	    
	    //将图片转换为Base64编码的字符串
	    public static String getImageBase64FromBitmap(Bitmap bmp)
	    {
	    	if(bmp==null) return null;
	    	
	        byte[] imageByteBuff= Bitmap2Bytes(bmp);
	    	
	        String imageString=Base64.encode(imageByteBuff);
	        
	    	return imageString;
	    }
	    


}
