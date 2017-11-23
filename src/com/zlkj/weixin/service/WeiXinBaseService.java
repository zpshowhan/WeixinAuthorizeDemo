package com.zlkj.weixin.service;
import org.apache.http.HttpHost;

import com.zlkj.weixin.exception.WxErrorException;
import com.zlkj.weixin.http.executor.RequestExecutor;

public interface WeiXinBaseService {
	/**
	 * 简单的post请求
	 * @param url
	 * @param postData
	 * @return
	 * @throws WxErrorException
	 */
	String post(String url, String postData) throws WxErrorException;
	 /**
	  * 简单的Get请求
	  * @param url
	  * @param queryParam
	  * @return
	  * @throws WxErrorException
	  */
	String get(String url, String queryParam) throws WxErrorException;
	
		/**
		 * httpclient请求执行器
		 * @param executor
		 * @param uri
		 * @param data
		 * @return
		 * @throws WxErrorException
		 */
	  <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException;

	  /**
	   * 获取代理对象
	   */
	  HttpHost getHttpProxy();


}
