package com.zlkj.weixin.kit;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.zlkj.weixin.utils.JsonUtil;

import static com.zlkj.weixin.base.Base.notEmpty;

/**
 * 微信返回码校验工具类
 */
public class WeiXinCheckKit {
	/**
	 * 检查请求是否成功
	 * @return
	 */
	public static boolean checkRequestSucc(String content) {
		try {//取出状态信息进行json数据解析 从而获取请求状态
			if(notEmpty(content)){
				JsonNode jn = JsonUtil.getMapper().readTree(content);
				if(!jn.has("errcode")) return true;
				if(jn.get("errcode").asInt()==0) return true;
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 获取状态码
	 * @param content  公众号平台请求接口所返回的json数据
	 * @return
	 */
	public static int getRequestCode(String content) {
		try {
			JsonNode jn = JsonUtil.getMapper().readTree(content);
			if(jn.has("errcode")) return jn.get("errcode").asInt();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
	/**
	 * 获取状态信息
	 * @param content  公众号平台请求接口所返回的json数据
	 * @return
	 */
	public static String getRequestMsg(String content) {
		try {
			JsonNode jn = JsonUtil.getMapper().readTree(content);
			if(jn.has("errcode")) return jn.get("errmsg").asText();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static boolean isEnglish(String charaString) {
		  return charaString.matches("^[a-zA-Z]*");
		 }
}
