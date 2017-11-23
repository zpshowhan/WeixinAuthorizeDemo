package com.zlkj.weixin.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 用户web层
 */
public class WeiXinUserServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	/**
	 * 请求地址： http://13786147292.tunnel.2bdata.com/WeixinAuthorizeServlet/wxUserServlet?method=loginPage
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void loginPage(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	}
}
