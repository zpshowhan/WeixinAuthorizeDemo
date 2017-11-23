package com.zlkj.weixin.service.impl;
import static com.zlkj.weixin.kit.WeiXinCheckKit.checkRequestSucc;

import com.zlkj.weixin.dto.WeiXinAuthorizeDTO;
import com.zlkj.weixin.dto.WeiXinUserInfoDTO;
import com.zlkj.weixin.exception.WxErrorException;
import com.zlkj.weixin.service.WeiXinUserService;
import com.zlkj.weixin.service.WeiXinBaseService;
import com.zlkj.weixin.utils.JsonUtil;
import com.zlkj.weixin.utils.WeiXinFinalValue;

public class WeiXinUserServiceImpl implements WeiXinUserService{
	private WeiXinBaseService weiXinBaseService=new WeiXinBaseServiceImpl();
	@Override
	public WeiXinAuthorizeDTO getAuthorizeAccessTokenService(String code)
			throws WxErrorException {
		String url=WeiXinFinalValue.WX_AUTHORIZE_ACCESSTOKEN;
		url=url.replace("APPID", WeiXinFinalValue.APPID).replace("SECRET",//
				WeiXinFinalValue.APPSECRET).replace("CODE", code);
		String reMsgContent=weiXinBaseService.get(url, null);
		return checkRequestSucc(reMsgContent)?(WeiXinAuthorizeDTO)//
				JsonUtil.getInstance().json2obj(reMsgContent, WeiXinAuthorizeDTO.class):null;
	}

	@Override
	public WeiXinUserInfoDTO getAuthorizeUserInfo(
			WeiXinAuthorizeDTO weiXinAuthorizeDTO) throws WxErrorException {
			String  url=WeiXinFinalValue.WX_AUTHORIZE_INFO;
			url=url.replace("ACCESS_TOKEN", weiXinAuthorizeDTO.getAccess_token())//
					.replace("OPENID", weiXinAuthorizeDTO.getOpenid());
			String reMsgContent=weiXinBaseService.get(url,null);
			return checkRequestSucc(reMsgContent)?(WeiXinUserInfoDTO)//
					JsonUtil.getInstance().json2obj(reMsgContent, WeiXinUserInfoDTO.class):null;
	}
}
