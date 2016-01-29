package com.rest.crud.no;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.util.List;

@Path("/nos")
public class NosResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	NoService noService;

	public NosResource() {
		noService = new NoService();
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<NO> getNos() {
		return noService.getNosAsList();
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	public List<NO> getNosAsHtml() {
		return noService.getNosAsList();
	}

	// URI: /rest/nos/count
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		return String.valueOf(noService.getNosCount());
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void createNo(@FormParam("ip") String IP,
			@FormParam("no_ativo") boolean ativo,
			@Context HttpServletResponse servletResponse) throws IOException {
		NO no = new NO(IP, ativo);
		noService.createNo(no);
		servletResponse.sendRedirect("./nos/");
	}

	@Path("{no}")
	public NoResource getNo(@PathParam("no") String IP) {
		return new NoResource(uriInfo, request, IP);
	}

}