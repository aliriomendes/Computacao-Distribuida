package com.rest.test;


import com.rest.crud.file.FILE;
import com.rest.util.SHA1Methods;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alirio on 26/01/16.
 */
public enum  CasDao {
    instance;

    private Map<String, Cas> files = new HashMap<String, Cas>();

    private final String FICHEIRO = "data.xml";

    public Map<String, Cas> getFiles() {
        return files;
    }

    private  CasDao() {
        //fileToMap();
    }


    public boolean profFileToMap() {
        try{
            // GET

            Client client = ClientBuilder.newClient();

            WebTarget target = client.target(UriBuilder.fromUri("http://192.168.10.127:8080/rest/cass").build());

            Invocation.Builder invocation =  target.request(MediaType.APPLICATION_XML);

            Response response = invocation.get();

            ArrayList<Cas> output = response.readEntity(new GenericType<ArrayList<Cas>>(){});




            for(Cas cas : output){
               // System.out.println(cas);
                String url = cas.getUrl().replace("node-0","192.168.10.127");
                String key = SHA1Methods.urlSHA1(url);
                //System.out.println(key);

                if(key.equals(cas.getId())){
                    System.out.println("O ficheiro "+ cas.getUrl()+" Esta correto");
                }

            }




        }catch (Exception e){
            return false;
        }
        return true;
    }



    /* metodo que carrega conteudo de ficheiro para um hash table */
    public boolean fileToMap() {
        try{
            // GET

            Client client = ClientBuilder.newClient();

            WebTarget target = client.target(UriBuilder.fromUri("http://localhost:8080/rest/xmlServices/getFiles").build());

            Invocation.Builder invocation =  target.request(MediaType.APPLICATION_XML);

            Response response = invocation.get();


            ArrayList<FILE> output = response.readEntity(new GenericType<ArrayList<FILE>>(){});

            System.out.println(output);



        }catch (Exception e){
            return false;
        }
        return true;
    }

}
