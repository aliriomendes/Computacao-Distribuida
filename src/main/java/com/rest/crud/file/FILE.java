package com.rest.crud.file;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FILE {

    private String name;
    private String url;
    private String chave;

    public FILE() {

    }

    public FILE(String valor, String name, String chave) {
        super();
        this.url = valor;
        this.chave = chave;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    @Override
    public String toString() {
        return "FILE{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", chave='" + chave + '\'' +
                '}';
    }
}
