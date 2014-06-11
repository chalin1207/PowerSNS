package com.sns.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Set;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.sns.bean.Url;

public class SOAPUtils {

	public static String NAME_SPACE="http://tempuri.org/";
	
	//这 是一个静态方法,用于与服务器通信
	//URL: http://192.168.0.200/TomService/Service1.asmx
	//method_name:login
	//NAME_SPACE="http://tempuri.org/"
	//
	public static String callWebServiceWithParams(String URL,String method_name,Map<String,String> params)
	{
				
		SoapObject request=new SoapObject(NAME_SPACE,method_name);

		Set<String> sets=params.keySet();
		
		for(String paraName:sets)
		{
System.out.println("Key:"+paraName);
System.out.println("Value:"+params.get(paraName));
			request.addProperty(paraName,params.get(paraName));
		}
	
		SoapSerializationEnvelope envelope=new SoapSerializationEnvelope (SoapEnvelope.VER11);

		envelope.bodyOut=request;
		envelope.dotNet=true;
		envelope.setOutputSoapObject(request);
		HttpTransportSE ht=new HttpTransportSE(URL);

		
		  try {
			  ht.call(NAME_SPACE+method_name,envelope);
			  SoapPrimitive primitive =(SoapPrimitive)envelope.getResponse();
			  
			  String result=primitive.toString();

			  return result;
			  
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}

		return "connect fail";
		
	}
	
	//获取个人信息
	public static SoapObject getSoapObjectMess(String URL,String method_name,Map<String,String> params){
		SoapObject result = null;
		
		SoapObject request=new SoapObject(NAME_SPACE,method_name);

        Set<String> sets=params.keySet();
		
		for(String paraName:sets)
		{
System.out.println("SoapObject-->Key:"+paraName);
System.out.println("SoapObject-->Value:"+params.get(paraName));
			request.addProperty(paraName,params.get(paraName));
		}
		
		SoapSerializationEnvelope envelope=new SoapSerializationEnvelope (SoapEnvelope.VER11);

		envelope.bodyOut=request;
		envelope.dotNet=true;
		envelope.setOutputSoapObject(request);
		HttpTransportSE ht=new HttpTransportSE(URL);
		
		 try {
			ht.call(NAME_SPACE + method_name,envelope);
			result =(SoapObject)envelope.bodyIn;
			  
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
		return result;
	}
	
	
	//图片~下载
	public static Bitmap loadPhoto(String imgUrl) throws IOException{
		Url uu = new Url();
		URL imgURL;
		try {
			imgURL = new URL(imgUrl);
			System.out.println("qwerr----->"+uu.getUrl()+"/"+imgUrl);
			URLConnection conn = imgURL.openConnection();
			conn.connect();
			InputStream IS = conn.getInputStream();
			
			if (IS == null) {
				throw new RuntimeException("stream is null");
			} else {
					byte[] data = readStream(IS);
					if (data != null) {
						Bitmap bitmap = BitmapFactory.decodeByteArray(data,
								0, data.length);
System.out.println("Bitmap-------------->"+bitmap);
						IS.close();
						return bitmap;
					}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static byte[] readStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		outStream.close();
		inStream.close();
		return outStream.toByteArray();
	}
	
	public static String UploadImageWebService(String URL,String method_name,Map<String,String> params,String image_base64)
	{
				
		SoapObject request=new SoapObject(NAME_SPACE,method_name);

		Set<String> sets=params.keySet();
		
		for(String paraName:sets)
		{
			//System.out.println("Key:"+paraName);
			//System.out.println("Value:"+params.get(paraName));
			request.addProperty(paraName,params.get(paraName));
		}
		request.addProperty("image_base64",image_base64);
	
		SoapSerializationEnvelope envelope=new SoapSerializationEnvelope (SoapEnvelope.VER11);

		envelope.bodyOut=request;
		envelope.dotNet=true;
		envelope.setOutputSoapObject(request);
		HttpTransportSE ht=new HttpTransportSE(URL);

		
		  try {
			 ht.call(NAME_SPACE+method_name,envelope);
			  SoapPrimitive primitive =(SoapPrimitive ) envelope.getResponse();
			  
			  String result=primitive.toString();

			  return result;
			  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "connect fail";
		
	}
}
