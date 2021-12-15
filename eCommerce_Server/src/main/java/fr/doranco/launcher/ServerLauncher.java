package fr.doranco.launcher;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class ServerLauncher {

	private static final URI BASE_URI = URI.create("http://localhost:9991/ecommServer/");
	
	public static void main(String[] args) {

		try {
			//using Grizzly HTTP Server :
			ResourceConfig config = new ResourceConfig().packages("fr.doranco.services.rest");
			ServiceLocator locator = ServiceLocatorUtilities.createAndPopulateServiceLocator();
			HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, config, locator);
			server.start();
			
			//using JDK HTTP Server
			//...
			
			//using Simple Server
			//...
			
			//using Jetty HTTP Server
			//..
			
			//using Netty HTTP Server
			//...
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
