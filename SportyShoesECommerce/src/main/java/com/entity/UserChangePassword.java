package com.entity;

public class UserChangePassword {
	private String emailId;
	private String oldPassword;
	private String newPassword;
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	public UserChangePassword(String emailId, String oldPassword, String newPassword) {
		super();
		this.emailId = emailId;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}
	
	public UserChangePassword() {
		super();
	}
}
