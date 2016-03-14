package com.weixin.response.entity;

public class BaseMessage {
	
	//接收方账号，收到的openID
	private String toUserName;
	
	//开发者的微信号
	private String FromUserName;
	
	//消息创建时间    整形
	private long CreateTime;
	
	//消息类型（text/image/location/link/voice）
	private String MsgType;
	
	//消息ID, 64位整形
	private long MsgId;

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long CreateTime) {
		this.CreateTime = CreateTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String MsgType) {
		this.MsgType = MsgType;
	}

	public long getMsgId() {
		return MsgId;
	}

	public void setMsgId(long msgId) {
		MsgId = msgId;
	}

}
