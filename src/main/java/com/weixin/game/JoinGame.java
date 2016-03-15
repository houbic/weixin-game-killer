package com.weixin.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.weixin.cache.KillerGameData;
import com.weixin.game.entity.Parameter;
import com.weixin.game.entity.RoleType;
import com.weixin.game.entity.Room;
import com.weixin.request.entity.TextMessage;
import com.weixin.service.WeixinService;
import com.weixin.utils.Constants;
import com.weixin.utils.JsonMapper;

@Component("joinGame")
public class JoinGame {

	private static final Logger				logger					= LoggerFactory.getLogger(WeixinService.class);

	public static synchronized String joinGame(Integer roomNo, TextMessage textMessage) {
		try {
			String userId = textMessage.getFromUserName();
			if (!SettingNickName.checkNickName(userId)) {
				return Constants.ERROR_NO_SET_NICKNAME;
			}
			boolean isRoomExist = CreateRoom.isExistRoom(roomNo);
			if (!isRoomExist) {
				return Constants.ERROR_ROOM_NO_EXISTS;
			}
			if (CreateRoom.isFull(roomNo)) {
				return Constants.ERROR_JOIN_FULL_ROOM;
			}
			Parameter parameter = new Parameter();
			parameter.setUserId(userId);
			parameter.setRoomNo(roomNo);
			Room room = CreateRoom.getRoom(roomNo);
			for (Parameter existParameter : room.getParameterList()) {
				if (existParameter.getUserId().equals(userId)) {
					return String.format(Constants.ERROR_HAVEN_JOINED_GAME, RoleType.getRoleByType(existParameter.getRoleType()).getRole());
				}
			}
			RoleType role = RoleType.getRole(room);
			parameter.setRoleType(role.getRoleType());
			parameter.setRoomNo(roomNo);
			parameter.setUserId(userId);
			KillerGameData.addRole(parameter);
			KillerGameData.addRoom(parameter);
			logger.debug("加入房间后, 房间情况：{}", JsonMapper.toJson(CreateRoom.getRoom(roomNo)));
			return String.format(Constants.JOIN_ROOM_SUCCESS, roomNo, role.getRole());
		} catch (Exception e) {
			logger.error("加入房间错误：{}, error:{}", JsonMapper.toJson(textMessage), e);
			return Constants.ERROR_OPERA_EXCETION;
		}
	}

}
