package com.zlkj.weixin.kit;
import java.util.Map;
import com.zlkj.weixin.exception.WxErrorException;
import com.zlkj.weixin.utils.WeiXinFinalValue;
import static com.zlkj.weixin.kit.WeiXinMassageKit.mapTOxml;
import static com.zlkj.weixin.kit.WeiXinMassageKit.isDuplicateMessage;
/**
 * 微信事件处理
 */
public class WeiXinEventProcessinKit {
	
	/**
	 * 微事件处理
	 * @param messageMap  微信推送过来的xml数据包【已被解析成Map】
	 * @return
	 * @throws WxErrorException
	 */
 public static String weiXinEventProcessin(Map<String, Object> messageMap)throws WxErrorException{
	 	if (isDuplicateMessage(messageMap)) {// 如果是重复消息，那么就不做处理
	        return null;
	      }		
	 		String MsgType = (String) messageMap.get("MsgType");
			if (MsgType.equals(WeiXinFinalValue.MSG_EVENT_TYPE)) {// 如果是事件类型  click类型 或者view类型    view类型 暂时不作处理(直接通过url跳转)
				return eventTypeHandler(messageMap);
			} else if (WeiXinFinalValue.MSG_TEXT_TYPE.equals(MsgType)) {// 文本类型
				return WeiXinMassageKit.textTypeHandler(messageMap);
			} else if (WeiXinFinalValue.MSG_IMAGE_TYPE.equals(MsgType)) {// 如果是圖片消息
				return "";
			}else if(WeiXinFinalValue.MSG_VOICE_TYPE.equals(MsgType)){//语音
				
			}else if(WeiXinFinalValue.MSG_SHORTVIDEO_TYPE.equals(MsgType)){//短视频
				return null;
			}else if(WeiXinFinalValue.MSG_VIDEO_TYPE.equals(MsgType)){
				return null;
			}else if(WeiXinFinalValue.MSG_LOCATION_TYPE.equals(MsgType)){
				return null;
			}
			return null;
 }
 /**
	 * 点击菜单 非（view事件） 事件推送  比如 点击菜单类型为click类型的  
	 *   view类型的系统暂时只是直接 在创建微信菜单时给定跳转url
	 *   解析：微信菜单为click类型 时，返回的xml数据包示列  
	 *	<xml>
	 *	<ToUserName><![CDATA[toUser]]></ToUserName>
	 *	<FromUserName><![CDATA[FromUser]]></FromUserName>
	 *	<CreateTime>123456789</CreateTime>
	 *	<MsgType><![CDATA[event]]></MsgType>
	 *	<Event><![CDATA[CLICK]]></Event>
	 *	<EventKey><![CDATA[EVENTKEY]]></EventKey>
	 *	</xml>
	 *   
	 */
private static String eventTypeHandler(Map<String, Object> messageMap) throws WxErrorException{
		String  evenType=(String) messageMap.get("Event");
		try {
			 if(WeiXinFinalValue.EVT_SUBSCRIBE.equals(evenType)){//关注事件
				 return mapTOxml(WeiXinMassageKit.createTextMsg(messageMap, "欢迎你的关注!OAuth2.0网页授权演示<a href=\""+"http://13786147292.tunnel.2bdata.com/WeixinAuthorizeServlet/wxUserServlet?method=loginPage"+"\">请点击这里进行体验</a>"));
				}else if(WeiXinFinalValue.EVT_UNSUBSCRIBE.equals(evenType)){//取消关注
				return  null;
				}else if(WeiXinFinalValue.EVT_SCAN.equals(evenType)){// 扫描带参数二维码事件之二
				
				}else if(WeiXinFinalValue.EVT_CLICK.equals(evenType)){//点击菜单CLICK事件
					return mapTOxml(WeiXinMassageKit.createTextMsg(messageMap, "点击菜单CLICK事件!"));
				}else if(WeiXinFinalValue.EVT_VIEW.equals(evenType)){//点击菜单View事件
					
				}else if(WeiXinFinalValue.EVT_LOCATION.equals(evenType)){//上报地理位置
					
				}else if(WeiXinFinalValue.EVT_SCANCODE_PUSH.equals(evenType)){//// 扫码推事件
					
				}else if(WeiXinFinalValue.EVT_SCANCODE_WAITMSG.equals(evenType)){// 扫码推事件且弹出“消息接收中”提示框
					
				}else if(WeiXinFinalValue.EVT_PIC_SYSPHOTO.equals(evenType)){//弹出系统拍照发图，这个后台其实收不到该菜单的消息，点击它后，调用的是手机里面的照相机功能，而照相以后再发过来时，就收到的是一个图片消息了
			
				}else if(WeiXinFinalValue.EVT_PIC_PHOTO_OR_ALBUM.equals(evenType)){//弹出拍照或者相册发图
					
				}else if(WeiXinFinalValue.EVT_PIC_WEIXIN.equals(evenType)){//弹出微信相册发图器
				
				}else if(WeiXinFinalValue.EVT_LOCATION_SELECT.equals(evenType)){//弹出地理位置选择器
					
				}else if(WeiXinFinalValue.BUTTON_VIEW_LIMITED.equals(evenType)){//：跳转图文消息URL
					
				}else if(WeiXinFinalValue.EVT_TEMPLATESENDJOBFINISH.equals(evenType)){// 模板消息是否送达成功通知事件
					
				}else if(WeiXinFinalValue.EVT_MASS_SEND_JOB_FINISH.equals(evenType)){//群发任务结束时是否送达成功通知事件
					
				}else if(WeiXinFinalValue.EVT_KF_CREATE_SESSION.equals(evenType)){// 多客服接入会话事件
					
				}else if (WeiXinFinalValue.EVT_KF_CLOSE_SESSION.equals(evenType)){// 多客服关闭会话事件
					
				}else if(WeiXinFinalValue.EVT_KF_SWITCH_SESSION.equals(evenType)){// 多客服转接会话事件
					
				}else if(WeiXinFinalValue.EVT_USER_SHAKE.equals(evenType)){// 微信摇一摇事件
				
				}else if(WeiXinFinalValue.EVT_POI_CHECK_NOTIFY.equals(evenType)){//// 门店在审核事件消息
					
				}else if(WeiXinFinalValue.EVT_QUALIFICATION_VERIFY_FAIL.equals(evenType)||
						WeiXinFinalValue.EVT_NAMING_VERIFY_FAIL.equals(evenType)){// 资质认证失败 || 名称认证失败
					
				}else if(WeiXinFinalValue.EVT_QUALIFICATION_VERIFY_SUCCESS.equals(evenType)||
						WeiXinFinalValue.EVT_NAMING_VERIFY_SUCCESS.equals(evenType)||
						WeiXinFinalValue.EVT_ANNUAL_RENEW.equals(evenType)||
						WeiXinFinalValue.EVT_VERIFY_EXPIRED.equals(evenType)){// 资质认证成功 || 名称认证成功 || 年审通知 || 认证过期失效通知
				}
		} catch (WxErrorException e) {
			e.printStackTrace();
			}
		return null;
	}

}
