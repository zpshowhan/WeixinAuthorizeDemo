package com.zlkj.weixin.utils;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
 * 响应工具类
 */
public final class ResponseUtils{
  public static void renderText(HttpServletResponse response, String text){
    render(response, "text/plain;charset=UTF-8", text);
  }
  public static void renderJson(HttpServletResponse response, String text){
    render(response, "application/json;charset=UTF-8", text);
  }
  public static void renderObjectValueAjax(HttpServletResponse response, Object obj) throws IOException{
	  response.getWriter().print(obj);
  }
  public static void renderXml(HttpServletResponse response, String text){
    render(response, "text/xml;charset=UTF-8", text);
  }
  
  public static void renderResponseHeader(HttpServletResponse response, String contentType){//清除页面缓存
	  response.setHeader("Pragma", "No-cache");  
      response.setHeader("Cache-Control", "no-cache");  
      response.setDateHeader("Expires", 0);  
      response.setContentType(contentType);  
  }
  public static void renderJson(HttpServletResponse response, String contentType, Object dats){//清除页面缓存
    response.setContentType(contentType);
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0L);
    try {
      response.getWriter().print(dats);
    } catch (IOException e) {
    	e.printStackTrace();
    }
  }
  public static void renderJson(HttpServletResponse response, Object dats){
    renderJson(response, "application/json;charset=UTF-8", dats);
  }
  
  public static void render(HttpServletResponse response, String contentType, String text){
    response.setContentType(contentType);
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0L);
    try {
      response.getWriter().write(text);
    } catch (IOException e) {
    e.printStackTrace();
    }
  }

 @SuppressWarnings("unused")
 private static String beanToJson(Object bean, String arg) {
    if (bean == null) {
      return "";
    }
    PropertyDescriptor[] props = (PropertyDescriptor[])null;
    try {
      props = Introspector.getBeanInfo(bean.getClass(), Object.class)
        .getPropertyDescriptors();
    } catch (IntrospectionException localIntrospectionException) {
    }
    if (props != null) {
      for (int i = 0; i < props.length; ) {
        try {
          String name = props[i].getName();
          if (arg.indexOf(".") > -1) {
            String temparg = arg.substring(0, arg.indexOf("."));
            if (temparg.equals(name)) {
              return beanToJson(
                props[i].getReadMethod().invoke(bean, new Object[0]), 
                arg.substring(arg.indexOf(".") + 1, 
                arg.length()));
            }
          }
          else if (name.equals(arg)) {
            String value = props[i].getReadMethod()
              .invoke(bean, new Object[0]).toString();
            return value;
          }
        }
        catch (Exception localException)
        {
          i++;
        }
      }
    }
    return "";
  }
}