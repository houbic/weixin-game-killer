package com.weixin.game.entity;


public enum RoleType {

	farmer(1, "平民"), police(2, "警察"), killer(3, "杀手"), judge(4, "法官");

	private int		roleType;

	private String	role;

	public int getRoleType() {
		return roleType;
	}

	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	private RoleType(int roleType, String role) {
		this.roleType = roleType;
		this.role = role;
	}
	
	public static RoleType getRole(Room room){
		boolean flag = true;
		while (flag) {
			RoleType role = getRoleByType(RandomRole());
			if (!roomFull(room, role.getRoleType())) {
				return role;
			}
		}
		return null;
	}
	
	private static boolean roomFull(Room room, int type){
		boolean flag = false;
		switch (type) {
			case 1:
				flag = room.getExistFarmerNum() == room.getFarmerNum();
				break;
			case 2:
				flag = room.getExistPoliceNum() == room.getPoliceNum();
				break;
			case 3:
				flag = room.getExistKillerNum() == room.getKillerNum();
				break;
			default:
				break;
		}
		return flag;
	}
	
	public static RoleType getRoleByType(int type){
		for (RoleType role : RoleType.values()) {
			if (role.getRoleType() == type) {
				return role;
			}
		}
		return null;
	}
	
	private static int RandomRole(){
		int temp = (int) (Math.random() * 3);
		return temp + 1;
	}
	
}
