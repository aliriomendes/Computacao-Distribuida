package com.rest.crud.file;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

public class FileResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;

	FileService fileService;

	public FileResource(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
		fileService = new FileService();
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public FILE getfile() {
		FILE file = fileService.getFile(id);
		return file;
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	public FILE getfileAsHtml() {
		FILE file = fileService.getFile(id);
		return file;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putfile(JAXBElement<FILE> fileElement) {
		FILE file = fileElement.getValue();
		Response response;
		if (fileService.getFiles().containsKey(file.getChave())) {
			response = Response.noContent().build();
		} else {
			response = Response.created(uriInfo.getAbsolutePath()).build();
		}
		fileService.createFile(file);
		return response;
	}

	@DELETE
	public void deletefile() {
		fileService.deleteFile(id);
	}

}