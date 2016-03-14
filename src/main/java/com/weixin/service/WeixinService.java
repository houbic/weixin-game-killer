package com.weixin.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weixin.service.handler.MessageHandlerManage;
import com.weixin.utils.SignUtil;
import com.weixin.utils.SpringContextUtil;

public class WeixinService extends HttpServlet {
	
	private static final long	serialVersionUID	= -7125486482464576473L;

	private static final Logger logger = LoggerFactory.getLogger(WeixinService.class);
	
	public MessageHandlerManage messageHandlerManage = SpringContextUtil.getBean(MessageHandlerManage.class);
	
	/*
	 * 请求校验（确认签名来自服务器）
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException,IOException {
		
		/*response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("get test");
		System.out.println("get test");
		out.flush();
		out.close();*/

		// 微信签名加密
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		logger.info("signature: " + signature + "timestamp:" + 
				timestamp + "nonce" + nonce + "echostr" + echostr);
		PrintWriter out = response.getWriter();
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		} else {
			System.out.println("验证失败");
		}
		out.close();
		out = null;

	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("接受请求");
		Long startTime = System.currentTimeMillis();
		response.setCharacterEncoding("UTF-8");
		String responseString = messageHandlerManage.messageHandler(request);
		PrintWriter out = response.getWriter();
		out.write(responseString);
		out.close();
		out = null;
		Long endTime = System.currentTimeMillis();
		logger.info("处理请求结束, 总耗时{}", (endTime - startTime));
	}
	
}
