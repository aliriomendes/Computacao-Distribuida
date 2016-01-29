package com.rest.crud.no;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBElement;

public class NoResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;

	NoService fileService;

	public NoResource(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
		fileService = new NoService();
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public NO getfile() {
		NO file = fileService.getNo(id);
		return file;
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	public NO getfileAsHtml() {
		NO file = fileService.getNo(id);
		return file;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putfile(JAXBElement<NO> fileElement) {
		NO file = fileElement.getValue();
		Response response;
		if (fileService.getNos().containsKey(file.getIP())) {
			response = Response.noContent().build();
		} else {
			response = Response.created(uriInfo.getAbsolutePath()).build();
		}
		fileService.createNo(file);
		return response;
	}

	@DELETE
	public void deletefile() {
		fileService.deleteNo(id);
	}

}