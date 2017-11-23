package com.zlkj.weixin.web.servlet;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.zlkj.weixin.base.Base.notEmpty;
import static com.zlkj.weixin.utils.ResponseUtils.renderJson;
import static com.zlkj.weixin.base.Base.empty;
import com.zlkj.weixin.dto.WeiXinConfigSignatureDTO;
import static com.zlkj.weixin.kit.WeiXinEventProcessinKit.weiXinEventProcessin;
import com.zlkj.weixin.kit.WeiXinMassageKit;
import com.zlkj.weixin.kit.WeiXinSecurityKit;
import com.zlkj.weixin.utils.WeiXinFinalValue;
import static com.zlkj.weixin.utils.RequestUtil.toBean;

/**
 * 微信Servlet web层
 */
public class WeiXinConfigurerServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	/**
	 * 微信配置入口
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * http://13786147292.tunnel.2bdata.com/WeixinAuthorizeServlet/wxcfigServlet?method=wxCfgEntrance
	 */
	public void wxCfgEntrance(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			WeiXinConfigSignatureDTO wcfg
			 		=toBean(request.getParameterMap(), WeiXinConfigSignatureDTO.class);
			String[] arrys = {WeiXinFinalValue.TOKEN,wcfg.getTimestamp(), wcfg.getNonce()};
			Arrays.sort(arrys);
			StringBuffer buffer=new StringBuffer();
			for(String arr:arrys){
				buffer.append(arr);
			}
			String shalMsg=WeiXinSecurityKit.shal(buffer.toString());
			if (notEmpty(wcfg.getSignature()) && wcfg.getSignature().equals(shalMsg)&&notEmpty(wcfg.getEchostr())) {
				renderJson(response, wcfg.getEchostr());
				return;
			}
			  String encryptType = empty(wcfg.getEncrypt_type()) ?"raw" :wcfg.getEncrypt_type();
				Map<String, Object> messageMap=null;
				String responseMessage =null;
				    if ("raw".equals(encryptType)) {// 明文传输的消息
				    	System.out.println("【明文传输的消息】------------>>");
				    	 messageMap = WeiXinMassageKit.handlerProcessProclaimedMessage(request);
				    	}else if ("aes".equals(encryptType)) {
				    		System.out.println("【是aes加密传输的消息】------------>>");
				    	 messageMap = WeiXinMassageKit.handlerProcessEncryptionMessage(request,wcfg);
				    }
					 responseMessage =weiXinEventProcessin(messageMap);// 响应消息
					System.out.println("------------->>>>" + responseMessage);
					response.setContentType("application/xml;charset=UTF-8");
					response.setCharacterEncoding("UTF-8");
					if (notEmpty(responseMessage)) 	// 响应给微信公众号
						response.getWriter().write(responseMessage);// 回复微信用户 被动回复
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
