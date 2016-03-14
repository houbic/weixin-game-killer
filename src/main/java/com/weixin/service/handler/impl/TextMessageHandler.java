package com.weixin.service.handler.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.weixin.game.CreateRoom;
import com.weixin.game.JoinGame;
import com.weixin.game.SettingNickName;
import com.weixin.request.entity.TextMessage;
import com.weixin.response.entity.ResTextMessage;
import com.weixin.service.handler.HandlerType;
import com.weixin.service.handler.MessageHandler;
import com.weixin.utils.Constants;
import com.weixin.utils.ReportUtils;

@Component
public class TextMessageHandler implements MessageHandler {
	
	@Override
	public <T> String messageHandler(T message) {
		TextMessage textMessage = (TextMessage) message;
        String userId = textMessage.getFromUserName();
		String content = textMessage.getContent();
		ResTextMessage resTextMessage = new ResTextMessage();
		resTextMessage.setToUserName(textMessage.getFromUserName());
		resTextMessage.setFromUserName(textMessage.getToUserName());
		resTextMessage.setCreateTime(System.currentTimeMillis());
		resTextMessage.setMsgType(HandlerType.text.getMsgType());
		if (isSetNickName(content)) { //设置昵称
			String nickName = SettingNickName.settingNickName(userId, content);
			resTextMessage.setContent(nickName);
			return ReportUtils.getResReport(resTextMessage, ReportUtils.TEXT_TEMPLATE);
		}
		Integer number = getNumber(content);
		String resContent = null;
		if (number == null) {
			resContent = Constants.ERROR_TEXT_MSG_TYPE;
		} else if (number > 100) {
			resContent = CreateRoom.createRoom(number, textMessage);
		} else {
			resContent = JoinGame.joinGame(number, textMessage);
		}
		resTextMessage.setContent(resContent);
		return ReportUtils.getResReport(resTextMessage, ReportUtils.TEXT_TEMPLATE);
		
	}

	@Override
	public <T> boolean checkParams(T message) {
		return true;
	}
	
	private Integer getNumber(String content){
		try {
			Integer number = Integer.parseInt(content.replace(" ", ""));
			return number;
		} catch (Exception e) {
			return null;
		}
	}
	
	public boolean isSetNickName(String content){
		if (StringUtils.isBlank(content) || content.length() < 3) {
			return false;
		}
		Pattern pattern = Pattern.compile("#[*]+#");
		Matcher setNickNameCmd = pattern.matcher(content);
		if (setNickNameCmd.matches()) {
			return true;
		}
		return false;
	}
	
}
