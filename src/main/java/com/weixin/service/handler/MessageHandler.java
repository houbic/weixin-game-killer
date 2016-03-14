package com.weixin.service.handler;

import org.springframework.stereotype.Component;


/**
 * @author bink
 * @version 创建时间：2015-10-31 下午10:06:50
 * 类说明 ：处理信息接口
 */
@Component
public interface MessageHandler {
	
	public <T> String messageHandler(T message);
	
	public <T> boolean checkParams(T message);
	
}