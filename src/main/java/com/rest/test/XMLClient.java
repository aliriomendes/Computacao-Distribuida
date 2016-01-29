package com.rest.test;

import com.rest.crud.file.FILE;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.ArrayList;

public class XMLClient {

    public static void main(String[] args) {

        // GET 
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(getBaseURI());
        Invocation.Builder invocation =  target.request(MediaType.APPLICATION_XML);
        Response response = invocation.get();    

        if (response.getStatus() != 200) {
           throw new RuntimeException("Failed : HTTP error code : "
            + response.getStatus());
        }

        ArrayList<FILE> output;
        output = response.readEntity(new GenericType<ArrayList<FILE>>(){});//Get the object from the response
        System.out.println("Output xml client .... \n");
        System.out.println(output);

        // If the XML response is needed instead of the actual object 
        //System.out.println(response.readEntity(String.class));
    
    } 

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/rest/xmlServices").build();
    } 
}
