package com.rest.util;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1Methods {

	// SHA1 Hash of a String
	public static String strSHA1(String str) throws Exception{
		String out = null;
		MessageDigest mDigest = MessageDigest.getInstance("SHA1");
		byte[] buffer = mDigest.digest(str.getBytes());
		out = new HexBinaryAdapter().marshal(buffer);
		return out.toLowerCase();
	}

	// SHA1 Hash of a file
	public static String fileSHA1(String filePathString) throws Exception  {
		String out = null;
		InputStream is = null;
		File fileObj = new File(filePathString);
		if(fileObj.exists() && !fileObj.isDirectory()) {
			MessageDigest mDigest = MessageDigest.getInstance("SHA1");
			is = new BufferedInputStream(new FileInputStream(fileObj));
			byte[] buffer = new byte[8192];
			for (int read = 0; (read = is.read(buffer)) != -1;) {
				mDigest.update(buffer, 0, read);
			}
			is.close();
			out = new HexBinaryAdapter().marshal(mDigest.digest());
			return out.toLowerCase();
		} else {
			return null;
		}
	}

	// SHA1 Hash of a url content/file
	public static String urlSHA1(String url) throws NoSuchAlgorithmException, IOException {
		String out = null;
		File fileObj = null;
		URL urlObj = new URL(url);
		HttpURLConnection conn = null;
		conn = (HttpURLConnection) urlObj.openConnection();;
		conn.setRequestMethod("GET");
		conn.setAllowUserInteraction(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		try {
			conn.connect();
		} catch (UnknownHostException e) {
			return null;
		}
		if (conn.getResponseCode() == 200) {
			MessageDigest mDigest = MessageDigest.getInstance("SHA1");
			InputStream is = conn.getInputStream();
			DigestInputStream dis = new DigestInputStream(is,mDigest);
			BufferedInputStream bis = new BufferedInputStream(dis);
			byte[] buffer = new byte[8192];
			for (int read = 0; (read = bis.read(buffer)) != -1;);
			is.close();
			bis.close();
			out = new HexBinaryAdapter().marshal(dis.getMessageDigest().digest());
			return out.toLowerCase();
		} else {
			return null;
		}
	}
}