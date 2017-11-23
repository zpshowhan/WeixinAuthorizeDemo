package com.zlkj.weixin.http.executor;
import java.io.IOException;

import org.apache.http.Consts;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import static com.zlkj.weixin.base.Base.notEmpty;

import com.zlkj.weixin.exception.WxErrorException;
import com.zlkj.weixin.http.Utf8ResponseHandler;
/**
 * 简单的POST  Http 请求执行器
 */
public class SimplePostRequestExecutor implements RequestExecutor<String, String> {
  @Override
  public String execute(CloseableHttpClient httpclient, HttpHost httpProxy, String url, String postEntity) throws WxErrorException, IOException {
	    HttpPost httpPost = new HttpPost(url);
	    if (notEmpty(httpProxy)) {
	      RequestConfig config = RequestConfig.custom().setProxy(httpProxy).build();
	      httpPost.setConfig(config);
	    }
	   if(notEmpty(postEntity)){
	      StringEntity entity = new StringEntity(postEntity, Consts.UTF_8);
	      httpPost.setEntity(entity);
	      try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
	    	   return Utf8ResponseHandler.INSTANCE.handleResponse(response);
	      } finally {
	    	  httpPost.releaseConnection();
	      }
	    }
   	throw new RuntimeException("请求数据不能为空!");
  }

}
