package com.rest.service;

import com.rest.crud.file.FILE;
import com.rest.crud.file.FileService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/xmlServices")
public class FileXMLSvc {


    @GET
    @Path("/getFile")
    @Produces(MediaType.APPLICATION_XML)
    public FILE getFile(@QueryParam("file")		int id) {

        FileService fileService = new FileService();
        FILE file = fileService.getFilesAsList().get(id);
        System.out.println(file);
        return file;
    }

    @DELETE
    @Path("/deleteFile")
    @Produces(MediaType.APPLICATION_XML)
    public FILE deleteFile(@QueryParam("file")		String id) {

        FileService fileService = new FileService();
        FILE file = fileService.getFilesAsList().get(Integer.parseInt(id));
        fileService.deleteFile(file.getChave());
        System.out.println(file);
        return file;
    }

    @GET
    @Path("/getFiles")
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<FILE> getFiles() {
        FileService fileService = new FileService();
        System.out.println(fileService.getFilesAsList());
        ArrayList<FILE> array = new ArrayList<FILE>();
        array.addAll(fileService.getFilesAsList());

        return array;
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<FILE> getFilesRoot() {
        FileService fileService = new FileService();
        System.out.println(fileService.getFilesAsList());
        ArrayList<FILE> array = new ArrayList<FILE>();
        array.addAll(fileService.getFilesAsList());

        return array;
    }
}
