package com.weixin.utils;

import java.io.File;
import java.io.StringWriter;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import com.alibaba.fastjson.JSON;

/** 
 * @author  abing 
 * @date 创建时间：2015年12月8日 下午4:15:52 
 * 作用：
 */
public class ReportUtils {
	
	public static final Template	TEXT_TEMPLATE	= ReportUtils.getVelocityEngine().getTemplate(
			"template" + File.separator + "textMessage.xml");
	
	private static class SingletionVelocityEngine {
		// 初始化并取得Velocity引擎
		private static VelocityEngine ve = new VelocityEngine();
		static {
			ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
			ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
			ve.init();
		}
	}
	
	public static VelocityEngine getVelocityEngine(){
		return SingletionVelocityEngine.ve;
	}

	public static String getReport(Map<String, Object> params, Template template) {
		VelocityContext context = new VelocityContext();
		// 把数据填入上下文
		for (Entry<String, Object> entry : params.entrySet()) {
			context.put(entry.getKey(), entry.getValue());
		}
		// 输出流
		StringWriter writer = new StringWriter();
		// 转换输出
		template.merge(context, writer);
		//return writer.toString().replaceAll("\n", "").replaceAll("\r", "").replaceAll("\t", "");
		return writer.toString();
	}
	
	public static String getResReport(Object object, Template template){
		
		Map<String, Object> params = JSON.parseObject(JsonMapper.toJson(object));
		
		System.out.println(JsonMapper.toJson(params));
		
		return getReport(params, template);
		
	}
	
}
