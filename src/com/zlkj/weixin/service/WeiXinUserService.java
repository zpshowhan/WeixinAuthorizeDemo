package com.zlkj.weixin.service;

import com.zlkj.weixin.dto.WeiXinAuthorizeDTO;
import com.zlkj.weixin.dto.WeiXinUserInfoDTO;
import com.zlkj.weixin.exception.WxErrorException;

/**
 * 微信用户接口
 */
public interface WeiXinUserService {
	/**
	 * 根据code获取拉取用户信息的AccessToken
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public WeiXinAuthorizeDTO getAuthorizeAccessTokenService(String code) throws WxErrorException;
	
	/**
	 * 根据accessToken和openId拉取用户信息
	 * @return
	 * @throws Exception
	 */
	public WeiXinUserInfoDTO getAuthorizeUserInfo(WeiXinAuthorizeDTO weiXinAuthorizeDTO )throws WxErrorException;
}
