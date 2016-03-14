package com.weixin.game.entity;



public enum RoomType {

	// 普通类型
	four(4, 2, 1, 1);

	// 人数
	private int	number;
	// 平民人数
	private int	farmerNum;
	// 警察人数
	private int	policeNum;
	// 杀手人数
	private int	killerNum;

	private RoomType(int number, int farmerNum, int policeNum, int killerNum) {
		this.number = number;
		this.farmerNum = farmerNum;
		this.policeNum = policeNum;
		this.killerNum = killerNum;
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
	
	public static Room getRoom(int num){
		for (RoomType roomType : RoomType.values()) {
			if (roomType.getNumber() == num) {
				Room room = new Room();
				room.setCreateTime(System.currentTimeMillis());
				room.setNumber(roomType.getNumber());
				room.setFarmerNum(roomType.getFarmerNum());
				room.setPoliceNum(roomType.getPoliceNum());
				room.setKillerNum(roomType.getKillerNum());
				return room;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "房间总人数:" + number + " 平民:" + farmerNum + " 警察:" + policeNum + " 杀手" + killerNum;
	}

}
