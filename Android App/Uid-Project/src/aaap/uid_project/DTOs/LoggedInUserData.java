package aaap.uid_project.DTOs;

public class LoggedInUserData {
	private String USERNAME;
	private String UID;
	private String ROLE;
	private String NAME;
	
	public void setUsername(String USERNAME){
		this.USERNAME=USERNAME;
	}
	public void setUID(String UID){
		this.UID=UID;
	}
	public void setUserRole(String ROLE){
		this.ROLE=ROLE;
	}
	public void setName(String NAME){
		this.NAME=NAME;
	}
	
	public String getUsername(){
		return this.USERNAME;
	}
	public String getUID(){
		return this.UID;
	}
	public String getUserRole(){
		return this.ROLE;
	}
	public String getName(){
		return this.NAME;
	}
	
	

}
