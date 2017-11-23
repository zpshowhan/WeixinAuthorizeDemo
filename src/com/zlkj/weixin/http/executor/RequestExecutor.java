package com.zlkj.weixin.http.executor;
import org.apache.http.HttpHost;
import org.apache.http.impl.client.CloseableHttpClient;

import com.zlkj.weixin.exception.WxErrorException;

import java.io.IOException;

/**
 * http请求执行器
 * @param <T> 返回值类型
 * @param <E> 请求参数类型
 */
public interface RequestExecutor<T, E> {

  /**
   * @param httpclient 传入的httpClient
   * @param httpProxy  http代理对象，如果没有配置代理则为空  那么久使用无需用户认证的代理服务器 CloseableHttpClient   httpClient = HttpClients.createDefault();
   * @param url        url 
   * @param data       数据 
   * @throws WxErrorException
   * @throws IOException
   */
  T execute(CloseableHttpClient httpclient, HttpHost httpProxy, String url, E data) throws WxErrorException, IOException;
}
