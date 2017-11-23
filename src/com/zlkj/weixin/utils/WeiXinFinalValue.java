package com.zlkj.weixin.utils;

/**
  *微信常量类 
 *   ngrok  下载了吗
 */
public class WeiXinFinalValue {
/*	
public static final String APPID="wx88276c444add3167";

public static final String APPSECRET="e335d3aef875ae157c7f08e4d157b087";
public static final String TOKEN="cbF9bUdUHwNT7scybOoq";
 */
public  static final String AESKEY="cL480Zlo7ZcY07Yqhpjk3chqMcdzonIQQ7ULNZLxu8J";//消息加解密密钥

public static final String WX_SESSION_USER="cbF9bUdUHwNT7scybOoq";

/*public static final String APPID="wx292e1dfb5c3648d4";

public static final String APPSECRET="a1295a46bbb170746aa1363a25bd716d";

public static final String TOKEN="cbF9bUdUHwNT7scybOoq";*/

public static final String APPID="wx0ac2919166428ef0";

public static final String APPSECRET="1d720c4bd3debf080eb9895fb27112f8";

public static final String TOKEN="wechatstudy";


/**
 * 微信消息类型  
 */

public final static String MSG_TEXT_TYPE = "text";//文本消息
public final static String MSG_IMAGE_TYPE = "image";//图片消息
public final static String MSG_VOICE_TYPE = "voice";//语音消息
public final static String MSG_VIDEO_TYPE = "video";//视频
public final static String MSG_SHORTVIDEO_TYPE = "shortvideo";//小视频为shortvideo
public final static String MSG_LOCATION_TYPE = "location";//地理位置
public final static String MSG_EVENT_TYPE = "event";//事件 
public final static String GRAPHIC_TYPE="news";

/**
 * 微信端推送过来的Event事件类型
 */

public static final String EVT_SUBSCRIBE = "subscribe";
public static final String EVT_UNSUBSCRIBE = "unsubscribe";
public static final String EVT_SCAN = "SCAN";
public static final String EVT_LOCATION = "LOCATION";
public static final String EVT_CLICK = "CLICK";
public static final String EVT_VIEW = "VIEW";
public static final String EVT_MASS_SEND_JOB_FINISH = "MASSSENDJOBFINISH";
public static final String EVT_SCANCODE_PUSH = "scancode_push";
public static final String EVT_SCANCODE_WAITMSG = "scancode_waitmsg";
public static final String EVT_PIC_SYSPHOTO = "pic_sysphoto";
public static final String EVT_PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";
public static final String EVT_PIC_WEIXIN = "pic_weixin";
public static final String EVT_LOCATION_SELECT = "location_select";
public static final String EVT_TEMPLATESENDJOBFINISH = "TEMPLATESENDJOBFINISH";
public static final String EVT_ENTER_AGENT = "enter_agent";
public static final String EVT_CARD_PASS_CHECK = "card_pass_check";
public static final String EVT_CARD_NOT_PASS_CHECK = "card_not_pass_check";
public static final String EVT_USER_GET_CARD = "user_get_card";
public static final String EVT_USER_DEL_CARD = "user_del_card";
public static final String EVT_USER_CONSUME_CARD = "user_consume_card";
public static final String EVT_USER_SHAKE="ShakearoundUserShake";
public static final String EVT_USER_PAY_FROM_PAY_CELL = "user_pay_from_pay_cell";
public static final String EVT_USER_VIEW_CARD = "user_view_card";
public static final String EVT_USER_ENTER_SESSION_FROM_CARD = "user_enter_session_from_card";
public static final String EVT_CARD_SKU_REMIND = "card_sku_remind"; // 库存报警
public static final String EVT_KF_CREATE_SESSION = "kf_create_session"; // 客服接入会话
public static final String EVT_KF_CLOSE_SESSION = "kf_close_session"; // 客服关闭会话
public static final String EVT_KF_SWITCH_SESSION = "kf_switch_session"; // 客服转接会话
public static final String EVT_POI_CHECK_NOTIFY = "poi_check_notify"; //门店审核事件推送
public static final String EVT_QUALIFICATION_VERIFY_FAIL="qualification_verify_fail";//// 资质认证失败 
public static final String EVT_NAMING_VERIFY_FAIL="naming_verify_fail";//名称认证失败
public static final String EVT_QUALIFICATION_VERIFY_SUCCESS="qualification_verify_success";// 资质认证成功 
public static final String EVT_NAMING_VERIFY_SUCCESS="naming_verify_success";//名称认证成功 
public static final String EVT_ANNUAL_RENEW="annual_renew";//年审通知 
public static final String EVT_VERIFY_EXPIRED="verify_expired";// 认证过期失效通知
public static final String BUTTON_VIEW_LIMITED = "view_limited";//跳转图文消息URL
/**
 * 网页授权方式 SCOPE为：snsapi_base方式
 */
public static final String SNSAPI_BASE ="snsapi_base";
/**
 * 网页授权方式 SCOPE为：snsapi_userinfo方式
 */
public static final String SNSAPI_INFO="snsapi_userinfo";
/**
 * 微信接口调用的唯一票据  有效时间 7200秒
 * access_token是公众号的全局唯一票据，公众号调用各接口时都需使用access_token 开发者需要进行妥善保存。access_token的存储至少要保留512个字符空间。access_token的有效期目前为2个小时，需定时刷新，重复获取将导致上次获取的access_token失效。 
 */
public static final String GET_ACCESSTOKEN="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
/**
 * 微信网页OAuth2.0鉴权
 */
public static final String WX_AUTHORIZE="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
/**
 * 获取微信网页授权所用的AccessToken
 */
public static final String WX_AUTHORIZE_ACCESSTOKEN="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
/**
 * 根据网页授权的AccessToken以及openId  来获取用户信息
 */
public static final String WX_AUTHORIZE_INFO="https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";


}
