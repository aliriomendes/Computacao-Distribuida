<%@ page import="javax.ws.rs.core.Response" %>
<%@ page import="javax.ws.rs.core.UriBuilder" %>
<%@ page import="java.net.URI" %>
<%@ page import="javax.ws.rs.client.*" %>
<%@ page import="javax.ws.rs.core.MediaType" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="com.rest.crud.file.FILE" %>
<%@ page import="java.util.List" %>
<html xmlns="http://www.w3.org/1999/xhtml\" xml:lang=\"pt\">
<head>
    <meta charset="UTF-8"/>
    <title>Alpha Team</title>
    <style type="text/css" title="jetty">
        @import url(css/jetty.css);
        @import url(css/menu_styles.css);
    </style>
    <script type="text/javascript" src="scripts/script.js"></script>

</head>
<body >


<div id="header">

</div>

<div id="content">
    <div id='cssmenu'>
        <ul>
            <li ><a href='/'>Home</a></li>
            <li><a href="fileUpload.html/">Inserir Recurso</a></li>
            <li class='active' ><a href="#">Listar Recursos</a></li>
            <li><a href="listarNos.jsp">Listar nós</a></li>
        </ul>
    </div>

    <h1>Resource List</h1>
    <%
        URI uri= UriBuilder.fromUri("http://localhost:8080/rest/files").build();

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(uri);
        Invocation.Builder invocation =  target.request(MediaType.APPLICATION_JSON);
        Response my_response = invocation.get();
        List<FILE> files;
        if (my_response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + my_response.getStatus());
        }else{
            String output = my_response.readEntity(String.class);//Get the object from the response


            ObjectMapper objectMapper = new ObjectMapper();

            files = objectMapper.readValue(
                    output,
                    objectMapper.getTypeFactory().constructCollectionType(
                            List.class, FILE.class));
        }
    %>
    <table style="width: 750px;" >

    <tr>
        <td>Chave</td>
        <td>Nome</td>
        <td>URL</td>
    </tr>
    <%for (FILE file:files) {%>
    <tr>
        <td><%=file.getChave()%></td>
        <td><%=file.getName()%></td>
        <td><%=file.getUrl()%></td>
    </tr>
    <%}%>
    </table>
</div>

</body>
</html>
