package io.github.francisfernandez28.libraries.Json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Parser {

	public static <T> String parseToJson(T object) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		return gson.toJson(object);
	}

	public static <T> String parseToJsonPrettyPrintFormat(T object) {
		Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
		return gson.toJson(object);
	}

	public static Class<?> parseFromJson(String json, Class<?> classtype) {
		Gson gson = new Gson();
		return (Class<?>) gson.fromJson(json, classtype);
	}

	public static JsonObject parseToJsonObject(String value) {
		// JsonElement element = new JsonPrimitive(value);
		JsonParser parser = new JsonParser();
		JsonObject obj = parser.parse(value).getAsJsonObject();
		return obj;
	}

}
