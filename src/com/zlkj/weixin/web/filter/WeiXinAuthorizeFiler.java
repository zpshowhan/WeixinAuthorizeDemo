package com.zlkj.weixin.web.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.zlkj.weixin.dto.WeiXinAuthorizeDTO;
import com.zlkj.weixin.dto.WeiXinUserInfoDTO;
import com.zlkj.weixin.exception.WxErrorException;
import com.zlkj.weixin.service.WeiXinUserService;
import com.zlkj.weixin.service.impl.WeiXinUserServiceImpl;
import com.zlkj.weixin.utils.WeiXinFinalValue;

import static com.zlkj.weixin.base.Base.empty;
import static com.zlkj.weixin.base.Base.notEmpty;
/**
 * 微信网页授权Filter
 * 测试环境：http://13786147292.tunnel.2bdata.com/WeixinAuthorizeServlet/wxUserServlet?method=loginPage
 */
public class WeiXinAuthorizeFiler implements Filter{
	private WeiXinUserService weiXinUserService=new WeiXinUserServiceImpl();
	@Override
	public void destroy() {
	}
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
			HttpServletRequest request=(HttpServletRequest) arg0;
			HttpServletResponse response=(HttpServletResponse) arg1;
			System.out.println("=====请求路径=========>>>>>"+request.getRequestURL());
			HttpSession httpSession=request.getSession();
			WeiXinUserInfoDTO wxUserDto=(WeiXinUserInfoDTO) httpSession.getAttribute(WeiXinFinalValue.WX_SESSION_USER);
			if(empty(wxUserDto)){
				String agent = request.getHeader("User-Agent");
				if(null!=agent&&agent.toLowerCase().indexOf("micromessenger")>=0) {//如果是微信客户端打开  我们才进行授权处理
					String code=request.getParameter("code");
					String state=request.getParameter("state");
					if(notEmpty(code)&&notEmpty(state)&&state.equals("1")){//通过Code获取openid来进行授权
						try {
							WeiXinAuthorizeDTO 	weiXinAuthorizeDTO=	weiXinUserService.getAuthorizeAccessTokenService(code);
							if(notEmpty(weiXinAuthorizeDTO)){
								WeiXinUserInfoDTO weiXinUserInfoDTO =weiXinUserService.getAuthorizeUserInfo(weiXinAuthorizeDTO);
								httpSession.setAttribute(WeiXinFinalValue.WX_SESSION_USER, weiXinUserInfoDTO);
								System.out.println("【微信用户信息】========>>>>>>>"+weiXinUserInfoDTO);
							}
						} catch (WxErrorException e) {
							e.printStackTrace();
						}
					}else{
						//这里把要访问的地址作为了微信重定向回来的地址。。
						String redirectURL=request.getRequestURL().toString();
						String queryparam=request.getQueryString();
						/**
						 * 用户同意授权后 ，页面将跳转至 redirect_uri/?code=CODE&state=STATE。若用户禁止授权，则重定向后不会带上code参数，
						 * 仅会带上state参数redirect_uri?state=STATE
						 */
						System.out.println("code:"+code+"------>"+"state:"+state);
						if(notEmpty(queryparam)){
							redirectURL=redirectURL+"?"+queryparam;//重定向url
						}
						String url=WeiXinFinalValue.WX_AUTHORIZE;
						url = url.replace("APPID",WeiXinFinalValue.APPID).replace("REDIRECT_URI",
						java.net.URLEncoder.encode(redirectURL, "UTF-8")//重定向url必须经过 URLEncoder编码
						).replace("SCOPE",WeiXinFinalValue.SNSAPI_INFO).replace("STATE", "1");
						/**
						 * 把请求提交给微信  而不是使用 httpClient进行get提交url不然会提示请使用微信客户端打开  
						 * 当我 们吧这个url提交给微信客户端之后 那么微信又会吧这个url再返回给我并且带回code参数  所以第一次访问时code、atate是为null的
						 */
						response.sendRedirect(url);
						System.out.println("【微信授权url】=======>>>>>"+url);
						return ;
					}
				}
			}
			chain.doFilter(request, response);
		}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
