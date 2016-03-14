package com.weixin.service.handler;

import com.weixin.request.entity.TextMessage;
import com.weixin.request.event.MenuEvent;

public enum HandlerType {
	
	// 普通类型
	text(1, "text", TextMessage.class), 
/*	image(2, "image", ImageMessage.class), 
	link(3, "link", LinkMessage.class), 
	location(4, "location", LocationMessage.class), 
	voice(5, "voice", VoiceMessage.class),*/

	/*// 事件类型
	locationEvent(6, "locationEvent", LocationEvent.class), 
	menuEvent(7, "menuEvent", MenuEvent.class), 
	qRCodeEvent(8, "qRCodeEvent", QRCodeEvent.class), 
	subscribeEvent(9, "subscribeEvent", SubscribeEvent.class),*/

	// 菜单点击事件
	MENU(10, "CLICK", MenuEvent.class);

	private int			handlerType;
	private String		msgType;
	private Class<?>	msgClass;

	private HandlerType(int handlerType, String msgType, Class<?> msgClass) {
		this.handlerType = handlerType;
		this.msgType = msgType;
		this.msgClass = msgClass;
	}

	public int getHandlerType() {
		return handlerType;
	}

	public void setHandlerType(int handlerType) {
		this.handlerType = handlerType;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public Class<?> getMsgClass() {
		return msgClass;
	}

	public void setMsgClass(Class<?> msgClass) {
		this.msgClass = msgClass;
	}

	public static HandlerType getHandlerType(String msgType, String eventType) {
		String type = eventType != null ? eventType : msgType;
		for (HandlerType handlerType : HandlerType.values()) {
			if (handlerType.getMsgType().equals(type)) {
				return handlerType;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "hanlder type : " + handlerType + " messge type : " + msgType;
	}

}
