package com.cliente;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.test.CasDao;
import com.rest.crud.file.FILE;
import com.rest.crud.no.NO;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class Client {
    private static boolean loop=true;
    private static final String SERVER = "http://localhost:8080/";


	public static void main(String[] args) {
		System.out.println("Welcome to Alpha Cloud Solutions Client");
        while (loop)
        {
            showMenu();
        }
        System.out.println("Bye Bye");
	}

    private static void showMenu(){
        int opcao;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nMain Menu\n"
                + " 1  - List Resources\n"
                + " 2  - Upload Resource\n"
                + " 3  - Download Resource\n"
                + " 4  - Delete Resource\n"
                + " 5  - Resource Info\n"
                + " 6  - List Nodes\n"
                + " 7  - Download Pro File\n"
                + " 0 – Exit\n"
                + "Option? ");

        try{
          opcao = scanner.nextInt();
            switch (opcao){
                case 0: loop = false; break;
                case 1: listResources(); break;
                case 2: uploadResource(); break;
                case 3: downloadResource(); break;
                case 4: deleteResource(); break;
                case 5: resourceInfo(); break;
                case 6: listNodes(); break;
                case 7: downloadProFile(); break;
                default: break;
            }
        }catch (Exception ex){

        }
    }

    public static void listResources() throws IOException {


        String output = getJsonWebResource(SERVER + "rest/files", "get");
        List<FILE> files = stringToFileList(output);

        System.out.println("\nResource List\n");

        for (FILE file:files) {
            System.out.println(file.getChave());
        }
    }
    public static void uploadResource() throws IOException {
        System.out.println("\nWitch is the path to theresource?\n");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        String url = SERVER + "rest/upload/pdf";

        // "/Users/alirio/Desktop/projeto_IHM_1516.pdf"
        final javax.ws.rs.client.Client client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();

        final FileDataBodyPart filePart = new FileDataBodyPart("file", new File( path ));
        FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
        final FormDataMultiPart multipart = (FormDataMultiPart) formDataMultiPart.field("foo", "bar").bodyPart(filePart);

        final WebTarget target = client.target( url );
        final Response response = target.request().post(Entity.entity(multipart, multipart.getMediaType()));

        //Use response object to verify upload success
        if (response.getStatus() != 200) {
            System.out.println("Something went wrong!!");
            System.out.println("Error code: "+response.getStatus());
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        } else {
            System.out.println("Data uploaded successfully !!\n");
        }

        formDataMultiPart.close();
        multipart.close();

    }
    public static void downloadResource() throws Exception {
        System.out.println("\nWitch is the resource destination?\n");
        Scanner scanner = new Scanner(System.in);
        String chave = scanner.nextLine();
        String url = SERVER + "rest/download/pdf";

        downloadFile( new URL(url), new File("/Users/alirio/Desktop/downloaded.assobio"), true);
    }
    public static void deleteResource() throws IOException {
        System.out.println("\nWitch is the resource key?\n");
        Scanner scanner = new Scanner(System.in);
        String chave = scanner.nextLine();
        String url = SERVER + "rest/file/deleteFile?file="+chave;
        String output = getJsonWebResource( url, "delete" );

        FILE file = stringToFile(output);

        System.out.println(file.getChave() +" "+ file.getName()+ " " +file.getUrl());
    }
    public static void resourceInfo() throws IOException {
        System.out.println("\nWitch is the resource key?\n");
        Scanner scanner = new Scanner(System.in);
        String chave = scanner.nextLine();
        String url = SERVER + "rest/file/getFile?file="+chave;
        String output = getJsonWebResource( url , "get");

        FILE file = stringToFile(output);


        System.out.println(file.getChave() +" "+ file.getName()+ " " +file.getUrl());
    }
    public static void listNodes() throws IOException {
        String output = getJsonWebResource(SERVER + "rest/nos" , "get");
        List<NO> nos = stringToNoList(output);



        System.out.println("\nNode List\n");

        for (NO no: nos) {
            System.out.println(no.getIP() +" -- "+no.getAtivo());
        }
    }

    public static String getJsonWebResource(String url, String method) {
        javax.ws.rs.client.Client client = ClientBuilder.newClient();
        URI uri= UriBuilder.fromUri(url).build();
        WebTarget target = client.target(uri);
        //System.out.println(target);
        Invocation.Builder invocation =  target.request(MediaType.APPLICATION_JSON);
        Response response;
        if(method.equals("delete")){
            response = invocation.delete();
        }else{
            response = invocation.get();
        }

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }

        return  response.readEntity(String.class);
    }
    public static FILE stringToFile(String output)  throws  IOException {
        ObjectMapper objectMapper = new ObjectMapper();
            FILE file = objectMapper.readValue(
                    output,
                    objectMapper.getTypeFactory().constructType(
                            FILE.class));
        return file;
    }

    public static List<FILE> stringToFileList(String output)  throws  IOException{
        ObjectMapper   objectMapper = new ObjectMapper();
        List<FILE>   files = objectMapper.readValue(
                    output,
                    objectMapper.getTypeFactory().constructCollectionType(
                            List.class, FILE.class));
        return files;
    }
    public static List<NO> stringToNoList(String output) throws  IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        List<NO> nos = objectMapper.readValue(
                output,
                objectMapper.getTypeFactory().constructCollectionType(
                        List.class, FILE.class));
        return nos;
    }

    public static void downloadFile(URL from, File to, boolean overwrite) throws Exception {
        if (to.exists()) {
            if (!overwrite)
                throw new Exception("File " + to.getAbsolutePath() + " exists already.");
            if (!to.delete())
                throw new Exception("Cannot delete the file " + to.getAbsolutePath() + ".");
        }

        int lengthTotal = 0;
        try {
            HttpURLConnection content = (HttpURLConnection) from.openConnection();
            lengthTotal = content.getContentLength();
        } catch (Exception e) {
            lengthTotal = -1;
        }

        int lengthSoFar = 0;
        InputStream is = from.openStream();
        FileOutputStream fos = new FileOutputStream(to);

        int lastUpdate = 0;
        int c;
        while ((c = is.read()) != -1) {
            fos.write(c);
        }

        is.close();
        fos.close();
    }

    public static void downloadProFile() throws Exception {
        CasDao casDao = CasDao.instance;
        //casDao.fileToMap();
        casDao.profFileToMap();
    }
}