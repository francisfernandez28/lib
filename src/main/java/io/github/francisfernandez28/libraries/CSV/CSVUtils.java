package io.github.francisfernandez28.libraries.CSV;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.github.francisfernandez28.libraries.Json.Parser;

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

	public static String generateCSVStringFromJsonObject(String jsonObjectString, String seperator)
			throws JSONException {

		StringBuilder builderHeader;
		StringJoiner sjValues;

		JSONObject output;
		try {
			builderHeader = new StringBuilder();
			output = new JSONObject(jsonObjectString);
			sjValues = new StringJoiner(seperator);
			StringJoiner sj = new StringJoiner(seperator);
			Iterator<?> keys = output.keys();
			while (keys.hasNext()) {
				String key = (String) keys.next();

				sj.add(key);
				sjValues.add(String.valueOf(output.get(key)));

			}

			builderHeader.append(sj.toString()).append("\n");
			builderHeader.append(sjValues.toString());

			return builderHeader.toString();

		} catch (JSONException e) {
			throw new JSONException(e.getMessage());
		}

	}

	public static String generateCSVStringFromJsonArray(String jsonStringArray, String seperator) throws JSONException {

		JSONArray output;
		JSONObject obj;

		StringBuilder builderHeader = new StringBuilder();
		StringJoiner sjValues;
		StringBuilder builderValues = new StringBuilder();
		StringBuilder result = new StringBuilder();
		try {
			output = new JSONArray(jsonStringArray);

			for (int x = 0; x < output.length(); x++) {
				sjValues = new StringJoiner(seperator);
				builderHeader = new StringBuilder();
				StringJoiner sj = new StringJoiner(seperator);
				obj = output.getJSONObject(x);
				Iterator<?> keys = obj.keys();
				while (keys.hasNext()) {
					String key = (String) keys.next();
					sj.add(key);
					sjValues.add(String.valueOf(obj.get(key)));

				}
				builderValues.append(sjValues.toString());
				builderValues.append("\r\n");
				builderHeader.append(sj.toString()).append("\r\n");

			}
			result.append(builderHeader.toString()).append(builderValues.toString());
			return result.toString();
		} catch (JSONException e) {
			throw new JSONException(e.getMessage());
		}

	}

	public static String generateCSVStringFromJsonArray(String jsonStringArray, String seperator, String prefix,
			String suffix) throws JSONException {

		JSONArray output;
		JSONObject obj;

		StringBuilder builderHeader = new StringBuilder();
		StringJoiner sjValues;
		StringBuilder builderValues = new StringBuilder();
		StringBuilder result = new StringBuilder();
		try {
			output = new JSONArray(jsonStringArray);

			for (int x = 0; x < output.length(); x++) {
				sjValues = new StringJoiner(seperator, prefix, suffix);
				builderHeader = new StringBuilder();
				StringJoiner sj = new StringJoiner(seperator);
				obj = output.getJSONObject(x);
				Iterator<?> keys = obj.keys();
				while (keys.hasNext()) {
					String key = (String) keys.next();
					sj.add(key);
					sjValues.add(String.valueOf(obj.get(key)));

				}
				builderValues.append(sjValues.toString());
				builderValues.append("\r\n");
				builderHeader.append(sj.toString()).append("\r\n");

			}
			result.append(builderHeader.toString()).append(builderValues.toString());
			return result.toString();
		} catch (JSONException e) {
			throw new JSONException(e.getMessage());
		}

	}

	public static String generateCSVStringFromJsonArray(String jsonStringArray, String seperator,
			String keyOfObjectToFind, List<String> sortedProperties) throws JSONException {

		JSONArray output;
		JSONObject obj;

		StringBuilder builderHeader = new StringBuilder();
		StringJoiner sjValues;
		StringBuilder builderValues = new StringBuilder();
		StringBuilder result = new StringBuilder();
		String dateCreated;
		try {
			output = new JSONArray(jsonStringArray);

			for (int x = 0; x < output.length(); x++) {
				sjValues = new StringJoiner(seperator);
				builderHeader = new StringBuilder();
				StringJoiner sj = new StringJoiner(seperator);
				obj = output.getJSONObject(x);
				sj.add("id");
				sjValues.add(String.valueOf(obj.get("id")));
				dateCreated = String.valueOf(obj.get("dateCreated"));
				obj = obj.getJSONObject(keyOfObjectToFind);
				for (String str : sortedProperties) {
					String key = str;
					sj.add(key);
					sjValues.add(String.valueOf(obj.get(key)));
				}

				sj.add("dateCreated");
				sjValues.add(dateCreated);
				builderValues.append(sjValues.toString());
				builderValues.append("\r\n");
				builderHeader.append(sj.toString()).append("\r\n");

			}
			result.append(builderHeader.toString()).append(builderValues.toString());
			return result.toString();
		} catch (JSONException e) {
			throw new JSONException(e.getMessage());
		}

	}

	public static String generateCSVStringFromJsonArray(String jsonStringArray, String seperator,
			String keyOfJsonObjectToFind, int indexInJsonArray) throws JSONException {

		JSONArray output;
		JSONObject obj;

		StringBuilder builderHeader = new StringBuilder();
		StringJoiner sjValues;
		StringBuilder builderValues = new StringBuilder(32);
		StringBuilder result = new StringBuilder();
		try {
			output = new JSONArray(jsonStringArray);
			output = output.getJSONArray(indexInJsonArray);
			for (int x = 0; x < output.length(); x++) {
				sjValues = new StringJoiner(seperator);
				builderHeader = new StringBuilder();
				StringJoiner sj = new StringJoiner(seperator);
				obj = output.getJSONObject(x);
				obj = obj.getJSONObject(keyOfJsonObjectToFind);
				Iterator<?> keys = obj.keys();
				while (keys.hasNext()) {
					String key = (String) keys.next();
					sj.add(key);
					sjValues.add(String.valueOf(obj.get(key)));

				}

				builderValues.append(sjValues.toString());
				builderValues.append("\r\n");
				builderHeader.append(sj.toString()).append("\r\n");

			}
			result.append(builderHeader.toString()).append(builderValues.toString());
			return result.toString();
		} catch (JSONException e) {
			throw new JSONException(e.getMessage());
		}

	}

	public static String generateCSVStringFromJsonArray(String jsonStringArray, String seperator,
			String keyOfJsonObjectToFind, int indexInJsonArray, List<String> filterPropertySorted)
			throws JSONException {

		JSONArray output;
		JSONObject obj;

		StringBuilder builderHeader = new StringBuilder();
		StringJoiner sjValues;
		StringBuilder builderValues = new StringBuilder(32);
		StringBuilder result = new StringBuilder();

		String dateCreated;
		// List<String> headerUnsorted = new ArrayList<String>();

		try {
			output = new JSONArray(jsonStringArray);
			output = output.getJSONArray(indexInJsonArray);
			for (int x = 0; x < output.length(); x++) {
				sjValues = new StringJoiner(seperator);
				builderHeader = new StringBuilder();
				StringJoiner sj = new StringJoiner(seperator);
				obj = output.getJSONObject(x);

				sj.add("id");
				sjValues.add(String.valueOf(obj.get("id")));

				dateCreated = String.valueOf(obj.get("dateCreated"));
				obj = obj.getJSONObject(keyOfJsonObjectToFind);
				for (String str : filterPropertySorted) {
					String key = str;
					sj.add(key);
					sjValues.add(String.valueOf(obj.get(key)));
				}
				sj.add("dateCreated");
				sjValues.add(dateCreated);
				builderValues.append(sjValues.toString());
				builderValues.append("\r\n");
				builderHeader.append(sj.toString()).append("\r\n");

			}
			result.append(builderHeader.toString()).append(builderValues.toString());
			return result.toString();
		} catch (JSONException e) {
			throw new JSONException(e.getMessage());
		}

	}

	public static <T> String generateCSVStringFromListOfModel(List<T> models, String seperator) throws JSONException {
		String jsonString = Parser.parseToJsonPrettyPrintFormat(models);
		return generateCSVStringFromJsonArray(jsonString, seperator);
	}

	public static <T> String generateCSVStringFromListOfModel(List<T> models, String seperator, String prefix, String suffix)
			throws JSONException {
		String jsonString = Parser.parseToJsonPrettyPrintFormat(models);
		return generateCSVStringFromJsonArray(jsonString, seperator, prefix, suffix);
	}

	public static <T> String generateCSVStringFromListOfModel(List<T> models, String seperator, String keyOfObjectToFind,
			List<String> sortedProperties) throws JSONException {
		String jsonString = Parser.parseToJsonPrettyPrintFormat(models);
		return generateCSVStringFromJsonArray(jsonString, seperator, keyOfObjectToFind, sortedProperties);
	}

}
