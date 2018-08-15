package io.github.francisfernandez28.libraries.PicasaHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.JsonObject;

import io.github.francisfernandez28.libraries.Json.Parser;

public class Picasa {
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	public String getThumbnail(String userEmail) throws MalformedURLException, IOException {
		InputStream is = null;
		String result = null;
		try {
			String urlFetch = "https://picasaweb.google.com/data/entry/api/user/" + userEmail + "?alt=json";
			is = new URL(urlFetch).openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);

			JsonObject json = Parser.parseToJsonObject(jsonText);

			JsonObject json1 = (JsonObject) json.get("entry");

			JsonObject json2 = (JsonObject) json1.get("gphoto$thumbnail");
			result = String.valueOf(json2.get("$t"));
			result = result.replace("\\", "");
			result = result.replace("\"", "");
			return result;
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "PICASAAPI: Error:" + ex.getMessage());
		} finally {
			if (is != null)
				is.close();
		}
		return result;
	}

	private String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
}
