package com.zlkj.weixin.entity;

import java.io.Serializable;

/**
 * 微信错误码说明，请阅读： <a href="http://mp.weixin.qq.com/wiki/10/6380dc743053a91c544ffd2b7c959166.html">全局返回码说明</a>
 */
public class WxError implements Serializable {

  private static final long serialVersionUID = 7869786563361406291L;

  private Integer errcode;

  private String errmsg;
  
  private String invalid;

public Integer getErrcode() {
	return errcode;
}

public void setErrcode(Integer errcode) {
	this.errcode = errcode;
}

public String getErrmsg() {
	return errmsg;
}

public void setErrmsg(String errmsg) {
	this.errmsg = errmsg;
}

public String getInvalid() {
	return invalid;
}

public void setInvalid(String invalid) {
	this.invalid = invalid;
}

@Override
public String toString() {
	return "WxError [errcode=" + errcode + ", errmsg=" + errmsg + ", invalid="
			+ invalid + "]";
}
}
