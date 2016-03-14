package com.weixin.game;

import com.weixin.cache.KillerGameData;
import com.weixin.game.entity.Parameter;
import com.weixin.game.entity.RoleType;
import com.weixin.game.entity.Room;
import com.weixin.game.entity.RoomType;
import com.weixin.request.entity.TextMessage;
import com.weixin.utils.Constants;

public class CreateRoom {

	public static synchronized String createRoom(Integer number, TextMessage textMessage) {
		Room room = RoomType.getRoom(number);
		if (room == null) {
			return Constants.ERROR_CREATE_ROOM_FAIL;
		}
		int roomNo = generateRoomNo();
		String owner = textMessage.getFromUserName();
		room.setRoomNo(roomNo);
		room.setOwner(owner);
		Parameter parameter = new Parameter();
		parameter.setRoleType(RoleType.judge.getRoleType());
		parameter.setRoomNo(roomNo);
		parameter.setUserId(owner);
		room.getParameterList().add(parameter);
		KillerGameData.rooms.put(roomNo, room);
		KillerGameData.owners.put(owner, roomNo);
		KillerGameData.parameters.put(owner, parameter);
		return String.format(Constants.CREATE_ROOM_SUCCESS, room.getNumber(), roomNo);
	}

	private static int generateRoomNo() {
		int roomNo = (int) (Math.random() * 9000 + 1000);
		while (isExistRoom(roomNo)) {
			roomNo = (int) (Math.random() * 9000 + 1000);
		}
		return roomNo;
	}

	public static boolean isExistRoom(int roomNo) {
		return KillerGameData.rooms.containsKey(roomNo);
	}

	public static Room getRoom(int roomNo) {
		return KillerGameData.rooms.get(roomNo);
	}

	public static void clearRoom(int roomNo) {
		KillerGameData.rooms.remove(roomNo);
	}
	
	public static boolean isFull(int roomNo){
		Room room = getRoom(roomNo);
		if (room == null) {
			return true;
		}
		if (room.getParameterList().size() == room.getNumber()) {
			return true;
		}
		return false;
	}
	
	public static void joinRoom(String roomNo, Parameter parameter){
		Room room = KillerGameData.rooms.get(roomNo);
		int roleType = parameter.getRoleType();
		switch (roleType) {
			case 1:
				room.setExistFarmerNum((room.getExistFarmerNum()+1));
				break;
			case 2:
				room.setExistPoliceNum((room.getExistPoliceNum()+1));
				break;
			case 3:
				room.setExistKillerNum((room.getExistKillerNum()+1));
				break;
			default:
				break;
		}
		room.getParameterList().add(parameter);
	}
	
}
