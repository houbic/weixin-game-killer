package com.weixin.utils;

public interface Constants {
	
	// 菜单按钮key
	String	CREATE_ROOM_KEY			= "CREATE_ROOM_KEY";
	String	QUERY_ROOM_KEY			= "QUERY_ROOM_KEY";
	String	QUERY_ROLE_KEY			= "QUERY_ROLE_KEY";

	// weixin.properties配置文件
	String	BEGIN_GAME_MESSAGE		= PropertiesUtil.getValue("BEGIN_GAME_MESSAGE");
	String	CREATE_ROOM_SUCCESS		= PropertiesUtil.getValue("CREATE_ROOM_SUCCESS");
	String  JOIN_ROOM_SUCCESS       = PropertiesUtil.getValue("JOIN_ROOM_SUCCESS");
	String  SET_NICK_NAME_SUCCESS   = PropertiesUtil.getValue("SET_NICK_NAME_SUCCESS");

	String	ERROR_CREATE_ROOM_FAIL	= PropertiesUtil.getValue("ERROR_CREATE_ROOM_FAIL");
	String	ERROR_TEXT_MSG_TYPE		= PropertiesUtil.getValue("ERROR_TEXT_MSG_TYPE");
	String	ERROR_MSG_TYPE_UNKNOWN	= PropertiesUtil.getValue("ERROR_MSG_TYPE_UNKNOWN");
	String	ERROR_ROOM_NO_EXISTS	= PropertiesUtil.getValue("ERROR_ROOM_NO_EXISTS");
	String	ERROR_ROLE_NO_EXISTS	= PropertiesUtil.getValue("ERROR_ROLE_NO_EXISTS");
	String	ERROR_JOIN_FULL_ROOM	= PropertiesUtil.getValue("ERROR_JOIN_FULL_ROOM");
	String  ERROR_HAVEN_JOINED_GAME = PropertiesUtil.getValue("ERROR_HAVEN_JOINED_GAME");
	String  ERROR_NO_SET_NICKNAME   = PropertiesUtil.getValue("ERROR_NO_SET_NICKNAME");
	String  ERROR_OPERA_EXCETION    = PropertiesUtil.getValue("ERROR_OPERA_EXCETION");
	
	// 收回缓存数据时间
	Long    GC_CACHE_TIME           = PropertiesUtil.getLongValue("GC_CACHE_TIME") * 60 * 1000;

}
