package com.baidu.aip;

import com.baidu.aip.ocr.AipOcr;

/**
 * @author wangyunliang
 * @date 2018/1/12 0012 16:34
 */
public class BaiduAipOcr {
	
	//设置APPID/AK/SK
	public static final String APP_ID = "10675641";
	public static final String API_KEY = "YSc6nlyChnqHP7NwDFL0zESG";
	public static final String SECRET_KEY = "2hvg3eI9H6yZL3TI2h01KfEAtYFXmh89";
	public static final Integer CONNECTION_TIMEOUT = 2000;
	public static final Integer TRANSPORT_TIMEOUT = 60000;
	
	private static AipOcr client;
	
	static {
		client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
		// 可选：设置网络连接参数
		client.setConnectionTimeoutInMillis(CONNECTION_TIMEOUT);	// 建立连接的超时时间（单位：毫秒）
		client.setSocketTimeoutInMillis(TRANSPORT_TIMEOUT);	// 通过打开的连接传输数据的超时时间（单位：毫秒）
		
		// 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//		client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//		client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理
	}
	
	public static AipOcr getInstance() {
		return client;
	}
	
	private BaiduAipOcr() {
	}
}
