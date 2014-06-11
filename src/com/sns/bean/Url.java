package com.sns.bean;

public class Url {
	//private String url="http://192.168.0.52/MyWebService";
	//private String url="http://192.168.0.200/tomservice";
	//private String url="http://10.10.248.195/zzl";
	private String url="http://10.10.16.192/zzl";
	//private String url ="http://10.10.16.26/qi";
	public Url(){}
	
	public Url(String url){
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
