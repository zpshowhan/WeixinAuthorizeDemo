package com.zlkj.weixin.utils;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.zlkj.weixin.base.Base.empty;
/**
 * json工具类
 */
public class JsonUtil {
	private static JsonUtil ju;
	private static JsonFactory jf;
	private static ObjectMapper mapper;
	private JsonUtil(){}
	
	public static JsonUtil getInstance() {
		if(empty(ju)) 
			ju = new JsonUtil();
		return ju;
	}
	public static ObjectMapper getMapper() {
		if(mapper==null) mapper = new ObjectMapper();
		return mapper;
	}
	
	public static JsonFactory getFactory() {
		if(jf==null) jf = new JsonFactory();
		return jf;
	}
	
	/**
	 * 吧Java对象装换为json对象
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String obj2json(Object obj) {
		JsonGenerator jg = null;
		try {
			StringWriter out = new StringWriter();
			getMapper().writeValue(getFactory().createJsonGenerator(out), obj);
			return out.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(jg!=null) jg.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 将对象转换成json字符串格式（默认将转换所有的属性）
	 * 
	 * @param value
	 * @return
	 */
	public static String toJsonStr(Object value) {
		try {
			return getMapper().writeValueAsString(value);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			System.out.println("Json转换失败");
			throw new RuntimeException(e);
		}
	}
	
	/***
	 * 吧Json数据转换为java对象
	 * @param json
	 * @param clz
	 * @return
	 */
	public Object json2obj(String json,Class<?> clz) {
		try {
			mapper = getMapper();
			return mapper.readValue(json,clz);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 解析json属性，放到实体里面去
	 * @Title: readJsonList 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param jsondata
	 * @param @param collectionClass
	 * @param @return    设定文件 
	 * @return List<SpecVO>    返回类型 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public static List<Object> readJsonList(String jsondata,Class<?> collectionClass) {
		try {
			JavaType javaType = getCollectionType(ArrayList.class, collectionClass); 
			return  (List<Object>)getMapper().readValue(jsondata, javaType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * json 转map
	 * @Title: readJsonMap 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param jsondata
	 * @param @return    设定文件 
	 * @return Map<String,Map<String,Object>>    返回类型 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> readJsonToMap(String jsondata) {
	    try {
	        Map<String, String> maps = getMapper().readValue(jsondata, Map.class);
	        return maps;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> readJsonToMap1(String jsondata) {
	    try {
	        return getMapper().readValue(jsondata, Map.class);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	/**
	 * 将对象json格式直接写出到流对象中（默认将转换所有的属性）
	 * 
	 * @param out
	 * @return
	 */
	public static void writeJsonStr(OutputStream out, Object value) {
		try {
			getMapper().writeValue(out, value);
		} catch (Exception e) {
			System.out.println("Json转换失败");
			throw new RuntimeException(e);
		}
	}

	
	/**
	 * 单独解析某一个json的key值
	 * @Title: getjsonvalue 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param jsonText
	 * @param @param key
	 * @param @return    设定文件 
	 * @return JsonNode    返回类型 
	 * @throws
	 */
	public static JsonNode getjsonvalue(String jsonText,String key){
		try {
            JsonNode rootNode = getMapper().readTree(jsonText); 
            return  rootNode.path(key);
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return null;
	}
	/**
	 * 解析字符串中单独key所对应的Value
	 * @param field   json字符串所对应的字段
	 * @param jsonstr json字符串
	 * @return
	 */
	public static String getJsonKeyOrValue(String field,String jsonstr ){
		try {
			return getMapper().readTree(jsonstr).get(field).asText();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {  
		ObjectMapper mapper = new ObjectMapper();  
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);   
	}   
}