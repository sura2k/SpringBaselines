package com.sura2k.examples.servletsec.business;

//Note:
//Could be a @Service if spring is used
//Weld CDI scans every bean by default
public class UserServiceImpl implements UserService {

	@Override
	public User find(String username, String password) {
		//TODO
		//Should be calling a Service which is wrapped by a transaction (Ex: @Transaction if uses spring)
		//OR this class should be wrapped by a transaction
		//OR DAO method has to be invoked with programmatic transaction
		//OR any other way you prefer
		//Retrieve whatever the internal model of User
		//User Id should be used for further identification
		
		//Pull the user entity from DB and instantiate your user model or something similar
		
		if("manager".equals(username)){
			return new User(1, "sura2k", "Manager", UserRole.MANAGER);
		}else{
			return new User(2, "sura2k", "IC", UserRole.IC);
		}
	}

}
