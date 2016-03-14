package com.weixin.service.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.weixin.response.entity.ResTextMessage;
import com.weixin.service.handler.impl.MenuEventHandler;
import com.weixin.service.handler.impl.TextMessageHandler;
import com.weixin.utils.Constants;
import com.weixin.utils.JsonMapper;
import com.weixin.utils.PropertiesUtil;
import com.weixin.utils.ReportUtils;
import com.weixin.utils.Util;
import com.weixin.utils.XmlUtil;

@Component
public class MessageHandlerManage {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageHandlerManage.class);
	
	@Autowired
	private TextMessageHandler textMessageHandler;
	
	@Autowired
	private MenuEventHandler   menuEventHandler;
	
	
	public String messageHandler(HttpServletRequest request){
		try {
			String xmlString = Util.inputStreamToString(request.getInputStream());
			logger.info("请求:{}", xmlString);
			Map<String, String> requestMap = XmlUtil.getMapFromXml(xmlString);
			String responseString = null;
			//微信请求message类型
			String msgType = requestMap.get("MsgType");
			//微信请求event类型, 如果是普通消息即为空
			String eventType = requestMap.get("Event");
			//处理消息并返回
			responseString = handler(requestMap, msgType, eventType);
			logger.info("请求处理返回结果:{}", responseString);
			return responseString;
		} catch (Exception e) {
			logger.error("处理请求异常:{}", e);
		}
		return null;
	}
	
	private String handler(Map<String, String> requestMap, String msgType, String eventType){
		HandlerType handlerType = HandlerType.getHandlerType(msgType, eventType);
		if (handlerType == null) {
			logger.error("请求消息类型异常, 消息:{}", JsonMapper.toJson(requestMap));
			ResTextMessage resTextMessage = new ResTextMessage();
			resTextMessage.setToUserName(requestMap.get("fromUserName"));
			resTextMessage.setFromUserName(requestMap.get("toUserName"));
			resTextMessage.setCreateTime(System.currentTimeMillis());
			resTextMessage.setMsgType(HandlerType.text.getMsgType());
			resTextMessage.setContent(PropertiesUtil.getValue(Constants.ERROR_MSG_TYPE_UNKNOWN));
			return ReportUtils.getResReport(resTextMessage, ReportUtils.TEXT_TEMPLATE);
		}
		MessageHandler handler = getHandler(handlerType.getHandlerType());
		logger.debug("requestMap:{}", JsonMapper.toJson(requestMap));
		return handler.messageHandler(JsonMapper.fromJson(requestMap, handlerType.getMsgClass()));
	}
	
	private MessageHandler getHandler(int handlerType){
		switch (handlerType) {
			case 1:
				return textMessageHandler;
			case 10:
				return menuEventHandler;
			default:
				return null;
		}
	}

}
