package com.sura2k.samples.springboot.restmvc.conf;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEventListener implements ApplicationListener<AbstractAuthenticationEvent> {


   @Override
   public void onApplicationEvent(AbstractAuthenticationEvent authenticationEvent) {
      if (authenticationEvent instanceof InteractiveAuthenticationSuccessEvent) {
         // ignores to prevent duplicate logging with AuthenticationSuccessEvent
         return;
      }
      Authentication authentication = authenticationEvent.getAuthentication();
      String auditMessage = "Login attempt with username: " + authentication.getName() + "\t\tSuccess: " + authentication.isAuthenticated();
      System.out.println(auditMessage);
   }

}
