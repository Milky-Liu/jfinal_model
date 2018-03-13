package com.test.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.ext.plugin.tablebind.AutoTableBindPlugin;
import com.jfinal.ext.plugin.tablebind.SimpleNameStyles;
import com.jfinal.ext.route.AutoBindRoutes;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;

/**
 * 全局配置，插件配置，启动加载 
 * @author zhaorui
 * 
 */

public class TfygMgrConfig extends JFinalConfig {

	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		//me.setEncoding("utf-8");
		PropKit.use("config.txt");
		//me.setUploadedFileSaveDirectory(PropKit.get("upload_path"));
		me.setDevMode(PropKit.getBoolean("devMode", false));

		me.setViewType(ViewType.JSP);	
		
		me.setError404View("/error/404.html");
		me.setError500View("/error/500.html");
		
	}
	
	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		//增加自动注释路由
		me.add(new AutoBindRoutes());	
	}
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		// 配置C3p0数据库连接池插件
		C3p0Plugin c3p0Plugin = new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
		c3p0Plugin.setInitialPoolSize(PropKit.getInt("initialPoolSize"));
		c3p0Plugin.setMinPoolSize(PropKit.getInt("minPoolSize"));
		c3p0Plugin.setMaxPoolSize(PropKit.getInt("maxPoolSize"));
		
		me.add(c3p0Plugin);
		
		//增加ehcache插件
		//EhCachePlugin ecp = new EhCachePlugin();
		//me.add(ecp);
		
		//增加自动注释的表绑定
		AutoTableBindPlugin atbp = new AutoTableBindPlugin(c3p0Plugin,SimpleNameStyles.LOWER_UNDERLINE);
		me.add(atbp);
		
		//任务调度
		//Cron4jPlugin cron = new Cron4jPlugin();
		//cron.config("job.properties");		
		//me.add(cron);
		
	}
	
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		//解决session在freemarker中不能取得的问题 获取方法：${session["user"].username}
		me.add(new SessionInViewInterceptor(true));
		//me.add(new TfygMgrLoginInterceptor());
	}
	
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		//me.add(new ContextPathHandler("ctx"));
	}
	
	public void afterJFinalStart(){
		//短信 初始化
		//SMS78DK.init();
		//微信获取 token,ticket 初始化
		//WxTokenUtil.init();
		//邮件发送 初始化
		//Email78DK.initBillEmail();
		//Email78DK.initServiceEmail();
		
		
		
	/*	try {
			FreeMarkerRender.getConfiguration().setSharedVariable("ctx", JFinal.me().getContextPath());
		} catch (TemplateModelException e) {
			throw new RuntimeException(e);
		}*/
		
		//JFinal.me().getServletContext().setAttribute("url_st",PathAndURL.HTTP_ST_URL);
	};
	

	public void beforeJFinalStop(){
		
		
	};
	
	/**
	 * 建议使用 JFinal 手册推荐的方式启动项目
	 * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 */
	public static void main(String[] args) {
		JFinal.start("WebRoot", 8083, "/", 5);
	}


}
