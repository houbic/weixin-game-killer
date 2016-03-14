package com.weixin.request.event;

public class MenuEvent extends BaseEvent{
	
	//事件KEY值，与自定义菜单接口中的KEY对应
	private String EventKey;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	
}
