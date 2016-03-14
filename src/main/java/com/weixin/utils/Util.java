package com.weixin.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

/**
 * User: bink
 * Date: 2015/12/23
 * Time: 14:59
 */
public class Util {
	
	/**
     * 通过反射的方式遍历对象的属性和属性值，方便调试
     *
     * @param o 要遍历的对象
     * @throws Exception
     */
    public static void reflect(Object o) throws Exception {
        Class<?> cls = o.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            f.setAccessible(true);
        }
    }
    
    /*public static Map<String, Object> getMapFromObject(Object object) throws IllegalArgumentException, IllegalAccessException{
    	Map<String, Object> map = new HashMap<String, Object>();
    	Class<?> cls = object.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            f.setAccessible(true);
            System.out.println(f.getName() + ":" + f.get(object));
        }
    	return null;
    }*/
    
	/**
	 * 输入流转String
	 *
	 * @param inputStream
	 * @return String
	 * @throws IOException
	 */
    public static String inputStreamToString(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i;
        while ((i = is.read()) != -1) {
            baos.write(i);
        }
        return baos.toString();
    }


    /**
     * 字符串转输入流
     *
     * @param String
     * @return InputStream
     */
    public static InputStream stringToInputStream(String inputString) {
        ByteArrayInputStream tInputStringStream = null;
        if (inputString != null && !inputString.trim().equals("")) {
            tInputStringStream = new ByteArrayInputStream(inputString.getBytes());
        }
        return tInputStringStream;
    }

}

