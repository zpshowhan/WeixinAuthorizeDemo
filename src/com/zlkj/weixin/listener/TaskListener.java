package com.zlkj.weixin.listener;
import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import static com.zlkj.weixin.base.Base.notEmpty;
import static com.zlkj.weixin.kit.WeiXinCheckKit.checkRequestSucc;
import com.zlkj.weixin.cfg.WeiXinConfig;
import com.zlkj.weixin.dto.AccessTokenDTO;
import com.zlkj.weixin.exception.WxErrorException;
import com.zlkj.weixin.service.WeiXinBaseService;
import com.zlkj.weixin.service.impl.WeiXinBaseServiceImpl;
import com.zlkj.weixin.utils.JsonUtil;
import com.zlkj.weixin.utils.WeiXinFinalValue;
/**
 * 定时获取AccessToken 任务
 */
public class TaskListener implements ServletContextListener {
	private WeiXinBaseService weiXinBaseService=new WeiXinBaseServiceImpl();
	private Timer tokenTimer=new Timer();
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		if(notEmpty(this.tokenTimer)) this.tokenTimer.cancel();
	}
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		this.tokenTimer = new Timer();
		/**
		 * public void schedule(TimerTask task,long delay,long period)
		 * task - 所要安排的任务。
		 *delay - 执行任务前的延迟时间，单位是毫秒。
		 *period - 执行各后续任务之间的时间间隔，单位是毫秒。 
		 */
		tokenTimer.schedule(new refreshAccessTokenTask(servletContextEvent), 5, 5000000);
	}
	private class refreshAccessTokenTask extends TimerTask{
		private ServletContextEvent servletContextEvent;
		private refreshAccessTokenTask(ServletContextEvent servletContextEvent){
			this.servletContextEvent=servletContextEvent;
		}
		@Override
		public void run() {
		String resContent;
		try {
				String url=WeiXinFinalValue.GET_ACCESSTOKEN;
				url=url.replace("APPID", WeiXinFinalValue.APPID).replace("APPSECRET", WeiXinFinalValue.APPSECRET);
				resContent=weiXinBaseService.get(url, null);
				if(checkRequestSucc(resContent)){
				AccessTokenDTO accessTokenDTO=(AccessTokenDTO) JsonUtil.getInstance().json2obj(resContent, AccessTokenDTO.class);
				WeiXinConfig.getInstance().setAccessToken(accessTokenDTO);
				System.out.println("【获取到了AccessToken】==========>>"+WeiXinConfig.getInstance().getAccessToken().getAccess_token());
			}else{
				contextInitialized(servletContextEvent);//重新获取AccessToken
			}
			} catch (WxErrorException e) {
			e.printStackTrace();
			}
		}
	}
}

