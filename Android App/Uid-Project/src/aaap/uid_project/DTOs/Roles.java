package aaap.uid_project.DTOs;

import java.util.ArrayList;

public class Roles {
	private static ArrayList<String> MemberRoles;
	
	public static void setRoles(ArrayList<String> MemberRoles){
		Roles.MemberRoles=MemberRoles;
	}
	public static ArrayList<String> getRoles(){
		return Roles.MemberRoles;
	}
	

}
