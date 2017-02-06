package com.ifoji.common.dal.parsertool;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Json parse Util
 */
public class JsonUtil {

	private static Gson gson = new Gson();

	public static <T> T parseJson(String response, Class<T> clazz) {
		try {
			return gson.fromJson(response, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> T parseJson(String response, Type type) {
		try {
			return gson.fromJson(response, type);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String toJson(Object object) {
		try {
			return gson.toJson(object);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @author I321533
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> jsonToList(String json, Class<T[]> clazz) {
		Gson gson = new Gson();
		T[] array = gson.fromJson(json, clazz);
		return Arrays.asList(array);
	}

	/**
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> ArrayList<T> jsonToArrayList(String json, Class<T> clazz) {
		Type type = new TypeToken<ArrayList<JsonObject>>(){}.getType();
		ArrayList<JsonObject> jsonObjects = new Gson().fromJson(json, type);

		ArrayList<T> arrayList = new ArrayList<>();
		for (JsonObject jsonObject : jsonObjects){
			arrayList.add(new Gson().fromJson(jsonObject, clazz));
		}
		return arrayList;
	}
}