package com.zlkj.weixin.dto;
import java.io.Serializable;
/**
 * 微信配置实体类
 */
public class WeiXinConfigSignatureDTO implements Serializable {
private static final long serialVersionUID = 1L;
private String signature;
private String timestamp;
private String nonce;
private String echostr;
private String encrypt_type;//加密方式
private String msg_signature;//msg_signature是微信给予我们用于的解密签名串，仅仅用于解密，加密不需要msg_signature

public String getMsg_signature() {
	return msg_signature;
}
public void setMsg_signature(String msg_signature) {
	this.msg_signature = msg_signature;
}
public String getSignature() {
	return signature;
}
public void setSignature(String signature) {
	this.signature = signature;
}
public String getTimestamp() {
	return timestamp;
}
public void setTimestamp(String timestamp) {
	this.timestamp = timestamp;
}
public String getNonce() {
	return nonce;
}
public void setNonce(String nonce) {
	this.nonce = nonce;
}
public String getEchostr() {
	return echostr;
}
public void setEchostr(String echostr) {
	this.echostr = echostr;
}
public String getEncrypt_type() {
	return encrypt_type;
}
public void setEncrypt_type(String encrypt_type) {
	this.encrypt_type = encrypt_type;
}
}
