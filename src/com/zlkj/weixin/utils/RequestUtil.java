package com.zlkj.weixin.utils;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

/**
 * Request请求处理工具类
 */
public class RequestUtil {
	
	public static  <T> T toBean(@SuppressWarnings("rawtypes") Map map, Class<T> clazz)throws Exception {
		try {
			T bean = clazz.newInstance();
			ConvertUtils.register(new DateConverter(), java.util.Date.class);
			BeanUtils.populate(bean, map);
			return bean;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
