package com.weixin.utils;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonMapper {
	public static String toJson(Object object) {
		return JSON.toJSONString(object, new SerializerFeature[] {
				SerializerFeature.WriteMapNullValue,
				SerializerFeature.DisableCircularReferenceDetect,
				SerializerFeature.WriteNonStringKeyAsString });
	}

	public static String toJsonFormat(Object object) {
		return JSON.toJSONString(object, new SerializerFeature[] {
				SerializerFeature.WriteMapNullValue,
				SerializerFeature.PrettyFormat,
				SerializerFeature.DisableCircularReferenceDetect,
				SerializerFeature.WriteNonStringKeyAsString });
	}

	public static Object toJsonObject(Object obj) {
		return JSON.toJSON(obj);
	}

	public static <T> T fromJson(String jsonString, Class<T> clazz) {
		if (StringUtils.isEmpty(jsonString) || clazz == null) {
			return null;
		}
		return JSON.parseObject(jsonString, clazz);
	}
	
	public static <T> T fromJson(Map<String, String> map, Class<T> clazz) {
		if (map == null || clazz == null) {
			return null;
		}
		return JSON.parseObject(toJson(map), clazz);
	}

}