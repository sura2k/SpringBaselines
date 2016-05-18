package com.sura2k.samples.springboot.restmvc.conf;

import java.io.File;
import java.io.IOException;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

@Configuration
public class WebServerConfig{
	
	public WebServerConfig(){
		System.out.println("---WebServerConfig");
	}
	
	@Value("${app.server.port.http}")
	private int HTTP_PORT;
	
	@Value("${app.server.port.https}")
	private int HTTPS_PORT;
	
    @Value("${app.server.servlet.container.maxThreads}")
    private int MAX_THREADS;
	
	//@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
		    @Override
		    protected void postProcessContext(Context context) {
		        SecurityConstraint securityConstraint = new SecurityConstraint();
		        securityConstraint.setUserConstraint("CONFIDENTIAL");
		        SecurityCollection collection = new SecurityCollection();
		        collection.addPattern("/*");
		        securityConstraint.addCollection(collection);
		        context.addConstraint(securityConstraint);
		    }
		};
		
		tomcat.addConnectorCustomizers(new TomcatConnectorCustomizer() {
			
			@Override
			public void customize(Connector connector) {
				connector.setPort(HTTP_PORT);
			}
		});
		tomcat.addAdditionalTomcatConnectors(createSslConnector());
		return tomcat;
	}

	private Connector createSslConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
		try {
			File keystore = new File(ResourceUtils.getFile("classpath:sura2k.jks").getAbsolutePath());
			File truststore = keystore;
			connector.setScheme("https");
			connector.setSecure(true);
			connector.setPort(HTTPS_PORT);
			
			Object defaultMaxThreads = connector.getAttribute("maxThreads");
            connector.setAttribute("maxThreads", MAX_THREADS);
            System.out.println("Changed Tomcat connector maxThreads from " + defaultMaxThreads + " to " + MAX_THREADS);
            
			protocol.setSSLEnabled(true);
			protocol.setKeystoreFile(keystore.getAbsolutePath());
			protocol.setKeystorePass("sura2k");
			protocol.setTruststoreFile(truststore.getAbsolutePath());
			protocol.setTruststorePass("sura2k");
			protocol.setKeyAlias("restmvc");
			return connector;
		}
		catch (IOException ex) {
			throw new IllegalStateException("cant access keystore: [" + "keystore" + "] or truststore: [" + "keystore" + "]", ex);
		}
	}

}
