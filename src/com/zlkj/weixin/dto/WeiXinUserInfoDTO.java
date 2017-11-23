package com.zlkj.weixin.dto;
import java.io.Serializable;
import java.util.List;
/**
 * 微信网页同意 授权所拉取的用户信息DTO
 */
public class WeiXinUserInfoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String openid;
	private String nickname;
	private String sex;
	private String language;
	private String province;
	private String city;
	private String country;
	private String headimgurl;
	private List<String> privilege;
	private String unionid;
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHeadimgurl() {
		return headimgurl.replace("\\", "").trim();
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public List<String> getPrivilege() {
		return privilege;
	}
	public void setPrivilege(List<String> privilege) {
		this.privilege = privilege;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	@Override
	public String toString() {
		return "WeiXinUserInfoDTO [openid=" + openid + ", nickname=" + nickname
				+ ", sex=" + sex + ", language=" + language + ", province="
				+ province + ", city=" + city + ", country=" + country
				+ ", headimgurl=" + headimgurl + ", privilege=" + privilege
				+ ", unionid=" + unionid + "]";
	}
	
}
