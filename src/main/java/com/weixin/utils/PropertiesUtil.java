package com.weixin.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author bink
 * @date: 2016年3月10日 上午11:24:08
 * @since 1.0.0
 */
public class PropertiesUtil {

	private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

	private static Properties properties = null;

	//初始化加载资源文件
	static {
		try {
			InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream("conf/weixin.properties");
			BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
			properties = new Properties();
			properties.load(br);
		} catch (IOException e) {
			logger.error("Load weixin.properties faild!", e);
		}
	}

	public static String getValue(String key) {
		return properties.get(key).toString();
	}

	public static Integer getIntegerValue(String key) {
		return Integer.valueOf(getValue(key));
	}

	public static Long getLongValue(String key) {
		return Long.parseLong(getValue(key));
	}
	
}