package com.sura2k.examples.springsec.service;

import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ApplicationUserDetailsService implements UserDetailsService {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		
		logger.info("Executing loadUserByUsername() -> username="+username);
		
		//Actual way of doing it
		//
		//UserModel userModel = userDAO.findUser(username);
		//if(userModel==null){
		//		throw new UsernameNotFoundException(username + " not found!");
		//}
		//
		
		//Only for demo
		//
		if("testuser".equals(username)){
			throw new UsernameNotFoundException(username + " not found!");
		}
		
		return new UserDetails() {
			
			private static final long serialVersionUID = 2059202961588104658L;

			@Override
			public boolean isEnabled() {
				return true;
			}
			
			@Override
			public boolean isCredentialsNonExpired() {
				return true;
			}
			
			@Override
			public boolean isAccountNonLocked() {
				return true;
			}
			
			@Override
			public boolean isAccountNonExpired() {
				return true;
			}
			
			@Override
			public String getUsername() {
				return username;
			}
			
			@Override
			public String getPassword() {
				return "password123"; // This will be removed later from Spring Security, so no password will not be there in the UserDetails
									  // This should be in encrypted text
			}
			
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				List<SimpleGrantedAuthority> auths = new java.util.ArrayList<SimpleGrantedAuthority>();
				auths.add(new SimpleGrantedAuthority("ROLE_USER")); //has to fill this from userModel.getRoles(): Set<RoleModel> .... and at least one role should be there
				return auths;
			}
		};
	}

}
