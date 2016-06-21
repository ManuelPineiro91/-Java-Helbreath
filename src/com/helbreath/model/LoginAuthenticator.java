package com.helbreath.model;

public class LoginAuthenticator {

	private static final LoginAuthenticator INSTANCE = new LoginAuthenticator();
	
	private LoginAuthenticator(){}

	public static LoginAuthenticator getInstance(){
		return INSTANCE;
	}
	
	public LoginInfo login(String account, String password){		
		return this.getLoginInfo(account, password); 		
	}	
	
	private LoginInfo getLoginInfo(String account, String password){
		
		LoginInfo lInfo = new LoginInfo();
		
		return lInfo;
	}
}
