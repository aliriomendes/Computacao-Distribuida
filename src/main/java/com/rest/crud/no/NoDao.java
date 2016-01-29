package com.rest.crud.no;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public enum NoDao {
	instance;

	private Map<String, NO> files = new HashMap<String, NO>();

	private final String FICHEIRO = "no_conf.xml";

	public Map<String, NO> getNos() {
		return files;
	}

	private  NoDao() {
		fileToMap();
	}

	/* metodo que carrega conteudo de ficheiro para um hash table */
	private boolean fileToMap() {
		FileInputStream inFile;
		XMLDecoder decoder;
		try{
			inFile = new FileInputStream(FICHEIRO);
			decoder = new XMLDecoder(new BufferedInputStream(inFile));
			files = (HashMap<String,NO>)decoder.readObject();

			decoder.close();
		}catch (Exception e){
			return false;
		}
		return true;
	}

	//metodo que escreve conteudo de um hash table para ficheiro
	public boolean mapToFile(){
		FileOutputStream outFile;
		XMLEncoder encoder;
		try{
			outFile = new FileOutputStream(FICHEIRO);
			encoder = new XMLEncoder( new BufferedOutputStream(outFile));
			encoder.writeObject(files);
			encoder.flush();
			encoder.close();
		}catch (Exception exception){
			return false;
		}
		return true;
	}

}
