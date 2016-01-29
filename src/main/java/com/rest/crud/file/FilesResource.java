package com.rest.crud.file;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.util.List;

@Path("/files")
public class FilesResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	FileService fileService;

	public FilesResource() {
		fileService = new FileService();
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<FILE> getFiles() {
		return fileService.getFilesAsList();
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	public List<FILE> getFilesAsHtml() {
		return fileService.getFilesAsList();
	}

	// URI: /rest/files/count
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		return String.valueOf(fileService.getFilesCount());
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void createFile(@FormParam("id") String id,
			@FormParam("filename") String name,
			@FormParam("filetype") String type,
			@Context HttpServletResponse servletResponse) throws IOException {
		FILE file = new FILE(id, name, type);
		fileService.createFile(file);
		servletResponse.sendRedirect("./files/");
	}

	@Path("{file}")
	public FileResource getFile(@PathParam("file") String id) {
		return new FileResource(uriInfo, request, id);
	}

}