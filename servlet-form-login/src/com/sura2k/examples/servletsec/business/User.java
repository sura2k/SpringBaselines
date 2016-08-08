package com.sura2k.examples.servletsec.business;

public class User {

	private int userId;
	private String firstName;
	private String lastName;
	private UserRole role;
	private Membership membership;
	
	public User(){}
	
	public User(int userId, String firstName, String lastName) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		
		//TOTO 
		//Default should be PENDING while creating the user, but somebody such as admin has to change the state later 
		//For testing you can check this by manually changing this
		//Registration part is not implemented
		this.membership = Membership.APPROVED; 
		
		//TODO
		//Similar as the membership
		//Either user has to provide the role
		//Otherwise default role has to be defined
		//OR something else
		this.role = UserRole.MANAGER;
	}
	
	//TODO List of roles should be able to provide
	public User(int userId, String firstName, String lastName, UserRole role){
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.membership = Membership.APPROVED; 
		this.role = role;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
	public Membership getMembership() {
		return membership;
	}
	public void setMembership(Membership membership) {
		this.membership = membership;
	}
	
}
