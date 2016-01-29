package com.rest.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.crud.file.FILE;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.List;

public class JsonClient {

    public static void main(String[] args) throws IOException {

        /*
        String responseEntity = ClientBuilder.newClient()
            .target(getBaseURI()).path("getEmployee")
                        .request().get(String.class);

        System.out.println(responseEntity);
        */


        // GET
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(getBaseURI());
        System.out.print(target);
        Invocation.Builder invocation =  target.request(MediaType.APPLICATION_JSON);
        Response response = invocation.get();

        if (response.getStatus() != 200) {
           throw new RuntimeException("Failed : HTTP error code : "
            + response.getStatus());
        }

        String output = response.readEntity(String.class);//Get the object from the response


        ObjectMapper objectMapper = new ObjectMapper();

        List<FILE> files = objectMapper.readValue(
                output,
                objectMapper.getTypeFactory().constructCollectionType(
                        List.class, FILE.class));



        System.out.println("Output json client .... \n");
        System.out.println(files);

        // If the JSON response is needed instead of the actual object 
        //System.out.println(response.readEntity(String.class));
        
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/rest/files").build();
    } 
}
