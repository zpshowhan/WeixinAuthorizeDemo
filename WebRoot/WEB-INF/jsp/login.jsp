<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,inital-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>登陆页面</title>
<link href="${pageContext.request.contextPath}/plugins/css/login.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/js/jquery.validate.min.js"></script>
	<script type="text/javascript">
		jQuery(document).ready(function() {
			jQuery("#theForm").validate({
				errorPlacement : function(error, element) {
					element.parent().addClass("bd_red");
					error.appendTo(element.parent().next());
				},
				success : function(label) {
					label.parent().prev().removeClass("bd_red");
				},
				rules : {
					username : {
						required : true
					},
					password : {
						required : true
					},
					code : {
						required : true,
						remote : {
							url : "${pageContext.request.contextPath}/verify_code.htm", //后台处理程序
							type : "post", //数据发送方式
							dataType : "json", //接受数据格式   
							data : { //要传递的数据
								"code" : function() {
									return jQuery("#code").val();
								}
							}
						}
					}
				},
				messages : {
					username : {
						required : "用户名不能为空"
					},
					password : {
						required : "密码不能为空"
					},
					code : {
						required : "验证码不能为空",
						remote : "验证码不正确"
					}
				}
			});
		});
	</script>
</head>
<body>
	<div class="phone_hd">
		<a class="back" href="javascript:history.go(-1);">
			<img src="${pageContext.request.contextPath}/plugins/image/back.png" width="25" height="25">
		</a>登录<a class="menu home"
			href="${pageContext.request.contextPath}/wap/index.htm"><img src="${pageContext.request.contextPath}/plugins/image/home.png" width="25" height="25"></a>
	</div>
	<div class="phone_main">
		<!--登录页-->
		<form action="#"
			method="post" name="theForm" id="theForm" novalidate="novalidate">
			<input name="login_role" type="hidden" id="login_role" value="user">
			<input name="wemall_view_type" type="hidden" id="wemall_view_type" value="wap">
				<div class="phone_login">
					<ul>
						<li class="ip"><input name="username" id="username" type="text" placeholder="请输入用户名/邮箱/已验证手机"></li>
						<li class="yz"></li>
						<li class="ip"><input name="password" id="password" type="password" placeholder="请输入密码"></li>
						<li class="yz"></li>
						<script>
							function refreshCode() {
								jQuery("#code_img").attr("src", "${pageContext.request.contextPath}/verify.htm?d" + new Date().getTime());
							}
						</script>
						<li class="yzm">
							<input name="code" type="text" id="code" placeholder="请输入验证码"> 
								<img id="code_img" src="${pageContext.request.contextPath}/verify.htm" width="73" height="27">
								<a href="javascript:void(0);" onclick="refreshCode();">
								<img src="${pageContext.request.contextPath}/plugins/image/refresh.png"
										width="32" height="32"></a></li>
						<li class="yz"></li>
						<li class="ip_btn"><input type="submit" value="登录"></li>
					</ul>
				</div>
		</form>
	</div>
</body>
</html>