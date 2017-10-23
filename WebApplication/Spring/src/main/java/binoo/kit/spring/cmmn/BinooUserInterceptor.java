package binoo.kit.spring.cmmn;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @Class Name : BinooUserInterceptor
 * @Description : Controlling - Input and Output Params, Attributes.
 * @author botbinoo@naver.com
 * @since 2017.09.05
 * https://github.com/wjsalsdn/homeProject_Maven_1_cloud
 * @version test
 *
 *  Copyright (C) by botbinoo's All right reserved.
 */

@Component
public class BinooUserInterceptor extends HandlerInterceptorAdapter
{
	
	private static final Logger logger = LoggerFactory.getLogger(BinooUserInterceptor.class);
	/**
	 * This implementation always returns {@code true}.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {

		logger.info(" In User Interceptor. Copyright (C) by botbinoo's All right reserved. ");
		
		try{
			// TODO:
		}catch(Exception e){
			logger.info(" Has Exception : " + e.getMessage());
		}
		
		return super.preHandle(request, response, handler);
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {

		logger.info(" Out User Interceptor. Copyright (C) by botbinoo's All right reserved. ");
		try{
			String viewName = modelAndView.getViewName();
			ModelMap modelMap = modelAndView.getModelMap();
			
			if( viewName.contains("binooFrontLayout:") ){
				viewName = viewName.replace("binooFrontLayout:", "");
				modelMap.addAttribute("pageName", viewName + ".jsp");
				logger.debug("viewNm" + viewName+".jsp");
				viewName = "/";
			}
			
			modelAndView.addAllObjects(modelMap);
			modelAndView.setViewName(viewName);
		}catch(Exception e){
			logger.info(" Has Exception : " + e.getMessage());
			logger.info(" Detail : " + e.getCause());
		}
		super.postHandle(request, response, handler, modelAndView);
	}
}