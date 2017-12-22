package com.zhuowang.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 监听Session的创建与销毁
 * @author Administrator
 *
 */
@WebListener
public class MyHttpSessionListener  implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("HttpSession 创建");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.out.println("HttpSession 销毁");
	}
}
