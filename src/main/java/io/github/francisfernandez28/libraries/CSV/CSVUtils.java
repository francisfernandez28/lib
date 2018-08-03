package io.github.francisfernandez28.libraries.CSV;

import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CSVUtils {

	private static final char DEFAULT_SEPARATOR = ',';

	public static void writeLine(Writer w, List<String> values) throws IOException {
		writeLine(w, values, DEFAULT_SEPARATOR, ' ');
	}

	public static void writeLine(Writer w, List<String> values, char separators) throws IOException {
		writeLine(w, values, separators, ' ');
	}

	// https://tools.ietf.org/html/rfc4180
	private static String followCVSformat(String value) {

		String result = value;
		if (result.contains("\"")) {
			result = result.replace("\"", "\"\"");
		}
		return result;

	}

	public static void writeLine(Writer w, List<String> values, char separators, char customQuote) throws IOException {

		boolean first = true;

		// default customQuote is empty

		if (separators == ' ') {
			separators = DEFAULT_SEPARATOR;
		}

		StringBuilder sb = new StringBuilder();
		for (String value : values) {
			if (!first) {
				sb.append(separators);
			}
			if (customQuote == ' ') {
				sb.append(followCVSformat(value));
			} else {
				sb.append(customQuote).append(followCVSformat(value)).append(customQuote);
			}

			first = false;
		}
		sb.append("\n");
		w.append(sb.toString());

	}

	public static String generateCSVString(List<String> values, String seperator, String prefix, String suffix) {
		StringJoiner sj = new StringJoiner(seperator, prefix, suffix);
		for (String s : values) {
			sj.add(s);
		}
		String desiredString = sj.toString();
		return desiredString;
	}

	public static String generateCSVString(List<String> values, String seperator) {
		StringJoiner sj = new StringJoiner(seperator);
		for (String s : values) {
			sj.add(s);
		}
		String desiredString = sj.toString();
		return desiredString;
	}

	public static String generateCSVString(Map<String, String> values, String seperator) {
		StringJoiner sjHeader = new StringJoiner(seperator);
		StringJoiner sjValues = new StringJoiner(seperator);
		for (String key : values.keySet()) {

			sjHeader.add(key);
			sjValues.add(values.get(key));

		}
		StringBuilder sB = new StringBuilder();

		String headers = sjHeader.toString();
		String vals = sjValues.toString();

		sB.append(headers);
		sB.append("\n");
		sB.append(vals);
		String desiredString = sB.toString();
		return desiredString;
	}

	public static String generateCSVString(Map<String, String> values, String seperator, String prefix, String suffix) {
		StringJoiner sjHeader = new StringJoiner(seperator, prefix, suffix);
		StringJoiner sjValues = new StringJoiner(seperator, prefix, suffix);
		for (String key : values.keySet()) {

			sjHeader.add(key);
			sjValues.add(values.get(key));

		}
		StringBuilder sB = new StringBuilder();

		String headers = sjHeader.toString();
		String vals = sjValues.toString();

		sB.append(headers);
		sB.append("\n");
		sB.append(vals);
		String desiredString = sB.toString();
		return desiredString;
	}

	public static String generateCSVString(String jsonString, String jsonParam) {

		JSONObject output;
		try {
			output = new JSONObject(jsonString);

			JSONArray docs = output.getJSONArray(jsonParam);
			String csv = CDL.toString(docs);
			return csv;

		} catch (JSONException e) {

		}
		return null;

	}

	public static Map<String, String> generateCSVStringFromJsonArray(String jsonStringArray, String seperator) {

		JSONArray output;
		JSONObject obj;
		Map<String, String> results = new HashMap<String, String>();

		try {
			output = new JSONArray(jsonStringArray);

			for (int x = 0; x < output.length(); x++) {
				StringJoiner sj = new StringJoiner(seperator);
				obj = output.getJSONObject(x);
				Iterator<?> keys = obj.keys();
				while (keys.hasNext()) {
					String key = (String) keys.next();
					sj.add(key);

				}
				results.put(String.valueOf(x), sj.toString());

			}

			return results;

		} catch (JSONException e) {

		}
		return null;

	}
}
