package com.zlkj.weixin.http.executor;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import static com.zlkj.weixin.base.Base.notEmpty;

import com.zlkj.weixin.exception.WxErrorException;
import com.zlkj.weixin.http.Utf8ResponseHandler;

import java.io.IOException;
/**
 * 简单的http GET请求执行器
 */
public class SimpleGetRequestExecutor implements RequestExecutor<String, String> {
  @Override
  public String execute(CloseableHttpClient httpclient, HttpHost httpProxy, String url, String queryParam) throws WxErrorException, IOException {
    HttpGet httpGet = new HttpGet(url);
    if (notEmpty(httpProxy)) {
      RequestConfig config = RequestConfig.custom().setProxy(httpProxy).build();
      httpGet.setConfig(config);
    }
    try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
    	return Utf8ResponseHandler.INSTANCE.handleResponse(response);
    } finally {
      httpGet.releaseConnection();
    }
  }
}
