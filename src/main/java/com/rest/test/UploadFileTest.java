package com.rest.test;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.File;
import java.io.IOException;
import java.net.URI;

public class UploadFileTest {
	private static final String SERVER = "http://localhost:8080/";
	private static final String SERVER_NODE = "http://node-0:8080/";
	public static void main(String[] args) throws IOException
	{
	    final Client client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
	 
	    final FileDataBodyPart filePart = new FileDataBodyPart("file", new File("/tmp/Sample.pdf"));
	    FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
	    final FormDataMultiPart multipart = (FormDataMultiPart) formDataMultiPart.field("foo", "bar").bodyPart(filePart);
	      
	    final WebTarget target = client.target(getBaseURI());
	    final Response response = target.request().post(Entity.entity(multipart, multipart.getMediaType()));
	     
	    //Use response object to verify upload success
	     if (response.getStatus() != 200) {
           throw new RuntimeException("Failed : HTTP error code : "
            + response.getStatus());
        } else {
			System.out.println("Data uploaded successfully !!\n");
		}	
	    
	    formDataMultiPart.close();
	    multipart.close();
	}
	
	private static URI getBaseURI() {
        return UriBuilder.fromUri(SERVER + "rest/upload/pdf").build();
    } 
}
