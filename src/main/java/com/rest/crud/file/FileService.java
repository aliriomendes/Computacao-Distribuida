package com.rest.crud.file;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileService {

	FileDao fileDao;

	public FileService() {
		fileDao = FileDao.instance;
	}

	public void createFile(FILE file) {
		fileDao.getFiles().put(file.getChave(), file);
		fileDao.mapToFile();
	}

	public FILE getFile(String chave) {
		return fileDao.getFiles().get(chave);
	}

	public Map<String, FILE> getFiles() {
		return fileDao.getFiles();
	}

	public List<FILE> getFilesAsList() {
		List<FILE> fileList = new ArrayList<FILE>();
		fileList.addAll(fileDao.getFiles().values());
		return fileList;
	}

	public int getFilesCount() {
		return fileDao.getFiles().size();
	}

	public void deleteFile(String id) {
		fileDao.getFiles().remove(id);
		fileDao.mapToFile();
	}

}
