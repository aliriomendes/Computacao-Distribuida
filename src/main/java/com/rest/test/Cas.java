package com.rest.test;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by alirio on 26/01/16.
 */
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
