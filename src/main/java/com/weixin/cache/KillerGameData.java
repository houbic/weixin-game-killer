package com.weixin.cache;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.weixin.game.entity.Parameter;
import com.weixin.game.entity.Room;
import com.weixin.utils.Constants;

public class KillerGameData {

	// key-value : 房号-房间
	public static Map<Integer, Room>		rooms		= new ConcurrentHashMap<Integer, Room>();

	// 房主-房号
	public static Map<String, Integer>		owners		= new ConcurrentHashMap<String, Integer>();

	// 参赛者-角色
	public static Map<String, Parameter>	parameters	= new ConcurrentHashMap<String, Parameter>();

	// 参赛者-花名
	public static Map<String, String>		nickNames	= new ConcurrentHashMap<String, String>();

	// 启动计划任务回收游戏数据
	static {
		ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
		ses.scheduleAtFixedRate(new CacheGC(), 0l, 30, TimeUnit.MINUTES);
	}
	
	public static void addRoom(Parameter parameter){
		Integer roomNo = parameter.getRoomNo();
		rooms.get(roomNo).getParameterList().add(parameter);
		switch (parameter.getRoleType()) {
			case 1:
				rooms.get(roomNo).setExistFarmerNum(rooms.get(roomNo).getExistFarmerNum() + 1);
				break;
			case 2:
				rooms.get(roomNo).setExistPoliceNum(rooms.get(roomNo).getExistPoliceNum() + 1);
				break;
			case 3:
				rooms.get(roomNo).setExistKillerNum(rooms.get(roomNo).getExistKillerNum() + 1);
				break;
			default:
				break;
		}
	}
	
	public static void addRole(Parameter parameter){
		parameters.put(parameter.getUserId(), parameter);
	}
	
}
class CacheGC extends Thread {
	
	private static final Logger	logger	= Logger.getLogger(CacheGC.class);
	
	@Override
	public void run() {
		logger.info("开始回收游戏缓存数据======================================");
		for (Entry<Integer, Room> entry : KillerGameData.rooms.entrySet()) {
			Long roomCreateTime = entry.getValue().getCreateTime();
			String owner = entry.getValue().getOwner();
			Integer roomNo = entry.getKey();
			if (System.currentTimeMillis() > (Constants.GC_CACHE_TIME + roomCreateTime)) {
				//移除房间信息
				KillerGameData.rooms.remove(roomNo);
				logger.debug("移除房间:" + roomNo);
				//移除房主信息
				if (KillerGameData.owners.containsKey(owner) && 
						KillerGameData.owners.get(owner).intValue() == roomNo.intValue()) {
					KillerGameData.owners.remove(owner);
					logger.debug("移除房主:" + owner);
				}
			}
		}
		for (Entry<String, Parameter> entry : KillerGameData.parameters.entrySet()) {
			Long joinTime = entry.getValue().getJoinTime();
			String userId = entry.getValue().getUserId();
			if (System.currentTimeMillis() > (Constants.GC_CACHE_TIME + joinTime)) {
				KillerGameData.parameters.remove(userId);
				logger.debug("移除参赛者:" + userId);
			}
		}
		logger.info("回收游戏缓存数据【成功】===================================");
	}
}
