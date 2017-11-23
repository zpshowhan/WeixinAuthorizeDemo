package com.zlkj.weixin.cfg;
import static com.zlkj.weixin.base.Base.empty;

import com.zlkj.weixin.dto.AccessTokenDTO;

/**
 * 微信配置  为单实例Bean  主要用于存储 AccessToken  解决了不需要存储在缓存或者数据库内 或者写在文件中
 */
public class WeiXinConfig {
 private static WeiXinConfig cfg;
	private WeiXinConfig(){}
	private AccessTokenDTO accessTokenDTO;
	public static WeiXinConfig getInstance(){
		if(empty(cfg)) cfg=new WeiXinConfig();
		return cfg;
	}
	public AccessTokenDTO getAccessToken() {
		return accessTokenDTO;
	}
	public void setAccessToken(AccessTokenDTO accessTokenDTO) {
		this.accessTokenDTO = accessTokenDTO;
	}
}
