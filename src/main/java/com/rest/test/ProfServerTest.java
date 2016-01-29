package com.rest.test;

import com.rest.util.SHA1Methods;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

/**
 * Created by alirio on 26/01/16.
 */
public class ProfServerTest {

    public static void main(String[] args) {
        fileToMap();
    }

    public static boolean fileToMap() {
        try{
            //URL url = new URL("http://192.168.10.127:8080/rest/cas");
            URL url = new URL("http://localhost:8080/rest/xmlServices/getFiles");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(url.openStream());

            doSomething(doc.getDocumentElement());

        }catch (Exception e){
            return false;
        }
        return true;
    }
    public static void doSomething(Node node) throws IOException, NoSuchAlgorithmException {

        NodeList nodeList = node.getChildNodes();
        for (int j = 0; j < nodeList.getLength(); j++) {
            Node nodefiles = nodeList.item(j);
            NodeList fileList = nodefiles.getChildNodes();
            String url = "";
            String id = "";
            String name = "";
            for (int i = 0; i < fileList.getLength(); i++) {
                Node currentNode = fileList.item(i);
                if (currentNode.getNodeType() == Node.ELEMENT_NODE && currentNode.getNodeName().equals("name")) {
                    name = currentNode.getTextContent();
                } else if (currentNode.getNodeType() == Node.ELEMENT_NODE && currentNode.getNodeName().equals("chave")) {
                    id = currentNode.getTextContent();
                } else if (currentNode.getNodeType() == Node.ELEMENT_NODE && currentNode.getNodeName().equals("url")) {
                    url = currentNode.getTextContent();
                }
            }
            String hash = SHA1Methods.urlSHA1(url);

            if (hash.equals(id) && name.equals("CD-projecto-1-2015.pdf")) {
                System.out.println("Yeah");
            }else{
                System.out.println(":(");
            }
        }
    }

    @XmlRootElement
    public class Cas {

        private String id;
        private String url;
        private String token;

        public Cas() {

        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        @Override
        public String toString() {
            return "Cas{" +
                    "id='" + id + '\'' +
                    ", url='" + url + '\'' +
                    ", token='" + token + '\'' +
                    '}';
        }
    }
}
