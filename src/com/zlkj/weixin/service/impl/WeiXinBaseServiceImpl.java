package com.zlkj.weixin.service.impl;
import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.zlkj.weixin.exception.WxErrorException;
import com.zlkj.weixin.http.executor.RequestExecutor;
import com.zlkj.weixin.http.executor.SimpleGetRequestExecutor;
import com.zlkj.weixin.http.executor.SimplePostRequestExecutor;
import com.zlkj.weixin.service.WeiXinBaseService;
@SuppressWarnings("unused")
public class WeiXinBaseServiceImpl implements WeiXinBaseService{
	private CloseableHttpClient httpClient;
  	private HttpHost httpProxy;
  	public CloseableHttpClient getHttpClient() {
  	return 	this.httpClient=HttpClients.createDefault();
  	}
	@Override
	public String post(String url, String postData) throws WxErrorException {
		return execute(new SimplePostRequestExecutor(), url, postData);
	}
	@Override
	public String get(String url, String queryParam) throws WxErrorException {
		 	return 	executeHttpClient(new SimpleGetRequestExecutor(), url, queryParam);
	}
	@Override
	public <T, E> T execute(RequestExecutor<T, E> executor, String url, E data)
			throws WxErrorException {
		 	return executeHttpClient(executor, url, data);
	}
	@Override
	public HttpHost getHttpProxy() {
		return this.httpProxy;
	}
	/**
	 * 各种执行的通用入口进行异步控制
	 * @param executor
	 * @param url
	 * @param data
	 * @return
	 * @throws WxErrorException
	 */
	 protected synchronized <T, E> T executeHttpClient(RequestExecutor<T, E> executor, String url, E data) throws WxErrorException{
		 try {
			return executor.execute(getHttpClient(), this.httpProxy, url, data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return null;
	 }
}
