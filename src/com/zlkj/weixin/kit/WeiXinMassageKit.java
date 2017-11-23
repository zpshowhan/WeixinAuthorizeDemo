package com.zlkj.weixin.kit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static com.zlkj.weixin.kit.WeiXinSecurityKit.decrypt;
import static com.zlkj.weixin.base.Base.empty;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

import com.zlkj.weixin.WxMessageDuplicateChecker;
import com.zlkj.weixin.WxMessageInMemoryDuplicateChecker;
import com.zlkj.weixin.dto.WeiXinConfigSignatureDTO;
import com.zlkj.weixin.exception.WxErrorException;
/**
 * 
 *微信消息处理工具类
 */
public class WeiXinMassageKit {
	  private static final WxMessageDuplicateChecker messageDuplicateChecker=new  WxMessageInMemoryDuplicateChecker();;
	private static Map<String, Object> replyMsgs = new HashMap<String, Object>();
	static {
		replyMsgs.put("你好", "你好有什么可以帮助你的吗？");
		replyMsgs.put("OK", "好的 祝你生活愉快!");
		String authcUrl="http://13786147292.tunnel.2bdata.com/WeixinAuthorizeServlet/wxUserServlet?method=loginPage";
		replyMsgs.put("A", "OAuth2.0网页授权演示<a href=\""+authcUrl+"\">请点击这里进行体验</a>");
	}
	/**
	 * dom4j 解析微信推送过来的xml数据包   
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> requestMassagetoMap(
			String xmlmsg) throws Exception {
		try {
			Document document = DocumentHelper.parseText(xmlmsg);
			Element rootElement = document.getRootElement();
			List<Element> elements = rootElement.elements();
			Map<String, Object> maps = new HashMap<String, Object>();
			for (Element element : elements) {
				maps.put(element.getName(), element.getTextTrim());
			}
			return maps;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	

	
	/**
	 * 把map 转换为xml 返回
	 * 
	 * @param map
	 * @return
	 * @throws WxErrorException
	 */
	public static String mapTOxml(Map<String, Object> map) throws WxErrorException {
		Document d = DocumentHelper.createDocument();
		Element root = d.addElement("xml");
		Set<String> keys = map.keySet();
		for(String key:keys) {
			Object o = map.get(key);
			if(o instanceof String) {
				String t = (String)o;
				if(t.indexOf("<a")>=0) {
					root.addElement(key).addCDATA(t); 
				} else {
					root.addElement(key).addText((String)o);
				}
			} else {
			}
		}
		StringWriter sw = new StringWriter();
		XMLWriter xw = new XMLWriter(sw);
		xw.setEscapeText(false);
		try {
			xw.write(d);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sw.toString();
	}
	
	
	
	/**
	 * 处理文本类型的消息
	 * @param messageMap
	 * @return
	 * @throws WxErrorException
	 */
	public static String textTypeHandler(Map<String, Object> msgMap)
			throws WxErrorException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ToUserName", msgMap.get("FromUserName"));
		map.put("FromUserName", msgMap.get("ToUserName"));
		map.put("CreateTime", new Date().getTime() + "");
		map.put("MsgType", "text");
		String replyContent = "你的留言我么已收到  谢谢 !么么哒";
		String con = (String) msgMap.get("Content");
		// 是否包含 replyMsgs中的key 根据key 回复对应的内容
		if(WeiXinCheckKit.isEnglish(con)){
			if (replyMsgs.containsKey(con.toUpperCase())) {
				replyContent = (String) replyMsgs.get(con.toUpperCase());
			}
		}else{
			if (replyMsgs.containsKey(con)) {
				replyContent = (String) replyMsgs.get(con);
			}	
		}
		map.put("Content", replyContent);
		return mapTOxml(map);
	}
	/**
	 * 获取微信事件响应消息数据 （xml形式展现）
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String requestToXml(HttpServletRequest request)
			throws Exception {
		BufferedReader bufferedReader = null;
		StringBuffer stringBuffer = null;
		try {
				//bufferedReader = request.getReader();
			bufferedReader = new BufferedReader(// 字节流额转换为字符流
					new InputStreamReader(request.getInputStream(),Charset.forName("UTF-8")));
			stringBuffer = new StringBuffer();
			String len = null;
			while ((len = bufferedReader.readLine()) != null) {
				stringBuffer.append(len);
			}
			return stringBuffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(bufferedReader!=null)bufferedReader.close();
			}
		return null;
	}
	
	public static Map<String, Object> createTextMsg(Map<String, Object> msgMap,
			String content)throws WxErrorException {
		Map<String,Object > tm = new HashMap<String,Object>();
		tm.put("ToUserName", msgMap.get("FromUserName"));
		tm.put("FromUserName", msgMap.get("ToUserName"));
		tm.put("CreateTime", System.currentTimeMillis()+"");
		tm.put("MsgType", "text");
		tm.put("Content", content);
		return tm;
	}
	 public  static  boolean isDuplicateMessage(Map<String, Object> messageMap) {
		    StringBuffer messageId = new StringBuffer();
		    if (empty(messageMap.get("MsgId"))) {
		      messageId.append(messageMap.get("CreateTime"))
		        .append("-").append(messageMap.get("FromUserName"))
		        .append("-").append(empty(messageMap.get("EventKey"))? "" : messageMap.get("EventKey"))
		        .append("-").append(empty(messageMap.get("Event"))? "" :messageMap.get("Event"))
		      ;
		    } else {
		      messageId.append(messageMap.get("MsgId"));
		    }
		    return messageDuplicateChecker.isDuplicate(messageId.toString());
		  }

	 public static Map<String, Object> handlerProcessEncryptionMessage(
			HttpServletRequest request, WeiXinConfigSignatureDTO wcfg) {
					try {
						return requestMassagetoMap(decrypt(wcfg.getMsg_signature(),wcfg.getTimestamp(),wcfg.getNonce(),requestToXml(request))); 
						} catch (Exception e) {
						e.printStackTrace();
					}
		return null;
	}
	public  static Map<String, Object> handlerProcessProclaimedMessage(HttpServletRequest request) throws Exception {
		return requestMassagetoMap(requestToXml(request));
	}

}
