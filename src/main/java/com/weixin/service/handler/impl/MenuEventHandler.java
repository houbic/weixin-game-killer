package com.weixin.service.handler.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.weixin.cache.KillerGameData;
import com.weixin.game.entity.Parameter;
import com.weixin.game.entity.Room;
import com.weixin.request.event.MenuEvent;
import com.weixin.response.entity.ResTextMessage;
import com.weixin.service.handler.HandlerType;
import com.weixin.service.handler.MessageHandler;
import com.weixin.utils.Constants;
import com.weixin.utils.JsonMapper;
import com.weixin.utils.ReportUtils;

@Component
public class MenuEventHandler implements MessageHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(MenuEventHandler.class);

	@Override
	public <T> String messageHandler(T message) {
		MenuEvent menuEvent = (MenuEvent) message;
		logger.debug("menuEvent:{}", JsonMapper.toJson(menuEvent));
		ResTextMessage resTextMessage = new ResTextMessage();
		resTextMessage.setToUserName(menuEvent.getFromUserName());
		resTextMessage.setFromUserName(menuEvent.getToUserName());
		resTextMessage.setCreateTime(System.currentTimeMillis());
		resTextMessage.setMsgType(HandlerType.text.getMsgType());
		if (checkParams(message)) {
			resTextMessage.setContent(menuHandler(menuEvent));
		} else {
			resTextMessage.setContent(Constants.ERROR_MSG_TYPE_UNKNOWN);
		}
		return ReportUtils.getResReport(resTextMessage, ReportUtils.TEXT_TEMPLATE);
	}

	@Override
	public <T> boolean checkParams(T message) {
		MenuEvent menuEvent = (MenuEvent) message;
		if (StringUtils.isBlank(menuEvent.getEventKey())) {
			logger.error("此按钮的key为空:{}", JsonMapper.toJson(menuEvent));
			return false;
		}
		return true;
	}
	
	private String menuHandler(MenuEvent menuEvent){
		switch (menuEvent.getEventKey()) {
			case Constants.CREATE_ROOM_KEY:
				return Constants.BEGIN_GAME_MESSAGE;
			case Constants.QUERY_ROOM_KEY:
				return queryRoomInfo(menuEvent);
			case Constants.QUERY_ROLE_KEY:
				return queryRoleInfo(menuEvent);
			default:
				return Constants.ERROR_MSG_TYPE_UNKNOWN;
		}
	}
	
	private String queryRoomInfo(MenuEvent menuEvent){
		String owner = menuEvent.getFromUserName();
		if (!KillerGameData.owners.containsKey(owner)) {
			logger.debug("您还没创建房间");
			return Constants.ERROR_ROOM_NO_EXISTS;
		}
		Integer roomNo = KillerGameData.owners.get(owner);
		Room room = KillerGameData.rooms.get(roomNo);
		if (room == null) {
			logger.debug("房间信息为空");
			return Constants.ERROR_ROOM_NO_EXISTS;
		}
		return room.toString();
	}
	
	private String queryRoleInfo(MenuEvent menuEvent){
		Parameter parameter = KillerGameData.parameters.get(menuEvent.getFromUserName());
		if (parameter == null) {
			return Constants.ERROR_ROLE_NO_EXISTS;
		}
		return parameter.toString();
	}
	
}
