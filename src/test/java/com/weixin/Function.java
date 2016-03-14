package com.weixin;

import java.text.MessageFormat;

public class Function {
	
	
	public static void main(String[] args) {
		String str1 = "大秦 ${user} ${p} ${t}";
		System.out.println(MessageFormat.format(str1, "将军", "梦回", "秦朝"));
	}

}
