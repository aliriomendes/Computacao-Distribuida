package com.rest.service;

import com.rest.crud.file.FILE;
import com.rest.crud.file.FileService;
import com.rest.util.SHA1Methods;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.net.URI;

@Path("/upload")
public class UploadService 
{
	@POST
	@Path("/pdf")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	public Response uploadPdfFile(  @FormDataParam("file") InputStream fileInputStream,
	                                @FormDataParam("file") FormDataContentDisposition fileMetaData) throws Exception
	{

        String LOCAL_PATH = "src/main/webapp/resources/";
        String SERVER_PATH = "resources/";
	    try
	    {
	        int read = 0;
	        byte[] bytes = new byte[1024];
	        OutputStream out = new FileOutputStream(new File(LOCAL_PATH + fileMetaData.getFileName()));
			FileService fileService = new FileService();
	        while ((read = fileInputStream.read(bytes)) != -1)
	        {
	            out.write(bytes, 0, read);
	        }
	        out.flush();
	        out.close();


			String hash = SHA1Methods.fileSHA1(SERVER_PATH + fileMetaData.getFileName());

			FILE newFile = new FILE("http://localhost:8080/"+SERVER_PATH + fileMetaData.getFileName(), fileMetaData.getFileName(), hash);

			fileService.createFile(newFile);


			System.out.println(fileService.getFiles());
	    } catch (IOException e)
	    {
	        throw new WebApplicationException("Error while uploading file. Please try again !!");
	    }
	    return Response.seeOther(new URI("/successUpload.html")).build();
	}

}
