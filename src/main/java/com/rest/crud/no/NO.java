package com.rest.crud.no;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NO {

    private String IP;
    private boolean ativo;

    public NO() {

    }

    public NO(String IP, boolean ativo) {
        super();
        this.ativo = ativo;
        this.IP = IP;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    @Override
    public String toString() {
        return "FILE{" +
                "IP='" + IP + '\'' +
                ", ativo='" + ativo + '\'' +
                '}';
    }
}
