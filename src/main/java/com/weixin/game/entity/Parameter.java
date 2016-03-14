package com.weixin.game.entity;


public class Parameter {

	// 参赛者微信open id
	private String	userId;

	// 房号
	private int		roomNo;

	// 角色
	private int		roleType;
	
	// 加入时间
	private long    joinTime = System.currentTimeMillis();

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public int getRoleType() {
		return roleType;
	}

	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}
	
	public long getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(long joinTime) {
		this.joinTime = joinTime;
	}

	@Override
	public String toString(){
		return String.format("您所在的%s房间角色是:%s", roomNo, RoleType.getRoleByType(roleType).getRole());
	}

}
