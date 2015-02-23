package test;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;


@ApplicationPath("/")
public class MyApplication extends ResourceConfig {

    public MyApplication() {
    	super( MultiPartFeature.class);
        System.out.println("MyApplication");
    }
}