package com.weixin.game;

import com.weixin.cache.KillerGameData;

public class SettingNickName {
	
	public static String settingNickName(String userId, String content){
		String nickName = content.substring(1, content.length() - 1);
		KillerGameData.nickNames.put(userId, nickName);
		return nickName;
	}
	
	public static boolean checkNickName(String userId){
		return KillerGameData.nickNames.containsKey(userId);
	}

}
