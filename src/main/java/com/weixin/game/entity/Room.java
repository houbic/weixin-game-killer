package com.weixin.game.entity;

import java.util.ArrayList;
import java.util.List;

import com.weixin.cache.KillerGameData;

/**
 * @author bink
 * @version 创建时间：2015-11-5 下午10:47:51 类说明 ：房间
 */
public class Room {

	// 房号
	private int				roomNo;
	// 房主
	private String			owner;
	// 人数
	private int				number;
	// 平民人数
	private int				farmerNum;
	// 已加入的平民人数
	private int				existFarmerNum = 0;
	// 警察人数
	private int				policeNum;
	// 已加入的警察人数
	private int				existPoliceNum = 0;
	// 杀手人数
	private int				killerNum;
	// 已加入的警察人数
	private int				existKillerNum = 0;
	// 法官人数（默认1）
	private int				judgeNum	= 1;
	// 创建时间
	private long			createTime;

	private List<Parameter>	parameterList = new ArrayList<Parameter>();

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getFarmerNum() {
		return farmerNum;
	}

	public void setFarmerNum(int farmerNum) {
		this.farmerNum = farmerNum;
	}

	public int getPoliceNum() {
		return policeNum;
	}

	public void setPoliceNum(int policeNum) {
		this.policeNum = policeNum;
	}

	public int getKillerNum() {
		return killerNum;
	}

	public void setKillerNum(int killerNum) {
		this.killerNum = killerNum;
	}

	public int getJudgeNum() {
		return judgeNum;
	}

	public void setJudgeNum(int judgeNum) {
		this.judgeNum = judgeNum;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public List<Parameter> getParameterList() {
		return parameterList;
	}

	public int getExistFarmerNum() {
		return existFarmerNum;
	}

	public void setExistFarmerNum(int existFarmerNum) {
		this.existFarmerNum = existFarmerNum;
	}

	public int getExistPoliceNum() {
		return existPoliceNum;
	}

	public void setExistPoliceNum(int existPoliceNum) {
		this.existPoliceNum = existPoliceNum;
	}

	public int getExistKillerNum() {
		return existKillerNum;
	}

	public void setExistKillerNum(int existKillerNum) {
		this.existKillerNum = existKillerNum;
	}

	public void setParameterList(List<Parameter> parameterList) {
		this.parameterList = parameterList;
	}
	
	@Override
	public String toString(){
		StringBuilder farmerName = new StringBuilder();
		StringBuilder policeName = new StringBuilder();
		StringBuilder killerName = new StringBuilder();
	    for (Parameter parameter : parameterList) {
	    	    int roleType = parameter.getRoleType();
	    	    String nickName = KillerGameData.nickNames.get(parameter.getUserId());
			if (roleType == RoleType.farmer.getRoleType()) {
				farmerName.append(String.format("%s: %s\n", nickName, RoleType.farmer.getRole()));
			}
			if (roleType == RoleType.police.getRoleType()) {
				policeName.append(String.format("%s: %s\n", nickName, RoleType.police.getRole()));
			}
			if (roleType == RoleType.killer.getRoleType()) {
				killerName.append(String.format("%s: %s\n", nickName, RoleType.killer.getRole()));
			}
		}
		return String.format("您创建%s房间人数为:%s\n"
				           + "平民人数:%s, 已加入平民人数:%s\n"
				           + "警察人数:%s, 已加入警察人数:%s\n"
				           + "杀手人数:%s, 已加入杀手人数:%s\n"
				           + "具体情况如下:\n"
				           + "%s%s%s", 
				           roomNo, number, 
				           farmerNum, existFarmerNum,
				           policeNum, existPoliceNum,
				           killerNum, existKillerNum,
				           farmerName.toString(), policeName.toString(), killerName.toString());
	}

}
