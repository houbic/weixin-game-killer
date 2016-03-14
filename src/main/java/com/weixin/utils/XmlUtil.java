package com.weixin.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author bink
 * @version 创建时间：2015-11-2 下午10:16:20
 * 类说明 ：xml 转换工具
 */
public class XmlUtil {
	
	private static final Logger log = LoggerFactory.getLogger(XmlUtil.class);
	
	/**
	 * xml String转map
	 *
	 * @param xmlString
	 * @return Map
	 */
	public static Map<String,String> getMapFromXml(String xmlString) {
		try {
    		//这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream is =  Util.stringToInputStream(xmlString);
            Document document = builder.parse(is);

            //获取到document里面的全部结点
            NodeList allNodes = document.getFirstChild().getChildNodes();
            Node node;
            Map<String, String> map = new HashMap<String, String>();
            int i=0;
            while (i < allNodes.getLength()) {
                node = allNodes.item(i);
                if(node instanceof Element){
                    map.put(node.getNodeName(), node.getTextContent());
                }
                i++;
            }
            return map;
		} catch (Exception e) {
			log.info("解析xml出错, input xml string:{}, error:{}", xmlString, e);
			return null;
		}
    }
	
}
