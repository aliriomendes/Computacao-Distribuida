package com.rest.crud.no;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NoService {

	NoDao noDao;

	public NoService() {
		noDao = noDao.instance;
	}

	public void createNo(NO no) {
		noDao.getNos().put(no.getIP(), no);
		noDao.mapToFile();
	}

	public NO getNo(String chave) {
		return noDao.getNos().get(chave);
	}

	public Map<String, NO> getNos() {
		return noDao.getNos();
	}

	public List<NO> getNosAsList() {
		List<NO> noList = new ArrayList<NO>();
		noList.addAll(noDao.getNos().values());
		return noList;
	}

	public int getNosCount() {
		return noDao.getNos().size();
	}

	public void deleteNo(String id) {
		noDao.getNos().remove(id);
		noDao.mapToFile();
	}

}
