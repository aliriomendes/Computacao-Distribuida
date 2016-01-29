package com.rest.service;

import com.rest.crud.file.FILE;
import com.rest.crud.file.FileService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/file")
public class FileJSonSvc {

    @GET
    @Path("/getFile")
    @Produces(MediaType.APPLICATION_JSON)
    public FILE getFile(@QueryParam("file")		String chave) {

        FileService fileService = new FileService();
        FILE file = fileService.getFile(chave);
        System.out.println(file);
        return file;
    }

    @DELETE
    @Path("/deleteFile")
    @Produces(MediaType.APPLICATION_JSON)
    public FILE deleteFile(@QueryParam("file")		String chave) {

        FileService fileService = new FileService();
        FILE file = fileService.getFile(chave);
        fileService.deleteFile(chave);

        System.out.println(file);

        return file;
    }

    @GET
    @Path("/getFiles")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FILE> getFiles() {
        FileService fileService = new FileService();
        System.out.println(fileService.getFilesAsList());
        return fileService.getFilesAsList();
    }

}