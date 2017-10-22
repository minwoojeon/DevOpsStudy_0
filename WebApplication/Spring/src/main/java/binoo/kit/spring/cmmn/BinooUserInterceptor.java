package binoo.kit.spring.cmmn;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
/*
	@Autowired
	@Qualifier("menuMapper")
	private MenuDAO binooMenu;*/
	
	private static final Logger logger = LoggerFactory.getLogger(BinooUserInterceptor.class);
	/**
	 * This implementation always returns {@code true}.
	 */
	@SuppressWarnings({ "unchecked" })
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {

		logger.info(" In User Interceptor. Copyright (C) by botbinoo's All right reserved. ");
		// 사용자의 입력을 모두 제어합니다.
		
		try{
			Enumeration<String> keys = request.getParameterNames();
			
			String key = null;
			/*while( (key = keys.nextElement()) != null ){
				String buf = request.getParameter(key).replaceAll("'", "");
				buf = buf.replaceAll("/", "");
				buf = buf.replaceAll("\\\\", "");
				buf = buf.replaceAll("\"", "");
				buf = buf.replaceAll(".", "");
				request.getParameterMap().put( key, buf );
				key = null;
			}*/
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
		// 국문 영문 등 번역을 제어합니다.
		try{
			String viewName = modelAndView.getViewName();
			ModelMap modelMap = modelAndView.getModelMap();
			
			if( viewName.contains("binooFrontLayout:") ){
				viewName = viewName.replace("binooFrontLayout:", "");
				modelMap.addAttribute("pageName", viewName + ".jsp");
				logger.debug("viewNm" + viewName+".jsp");
				viewName = "/cmmn/binooPlugin/templet";
			} else if( viewName.contains("binooMobileLayout:") ) {
				// 아직 구상없음.
			} else if( viewName.contains("binooAdminLayout:") ) {
				// 아직 구상없음.
			}
			/*
			// 메뉴코드를 관리합니다.
			StringBuilder mnCd = new StringBuilder();
			mnCd.append( request.getParameter("mnCd") + "" );
			BinooMenuVO menuVO = new BinooMenuVO();
			menuVO.setMenuCode(mnCd.toString());
			@SuppressWarnings("unchecked")
			ArrayList<BinooMenuVO> menuList = binooMenu.selectItemList(menuVO);
			
			if( mnCd.toString().trim().equals("") && mnCd != null ){
				for( BinooMenuVO item : menuList ){
					if(item.getMenuCode().equals( menuVO.getMenuCode() )){
						menuVO = item;
						menuVO.setActivate(1);
					}
				}
			}
			
			modelMap.addAttribute("menuVO", menuVO);
			modelMap.addAttribute("menuList", menuList);*/
			
			modelAndView.addAllObjects(modelMap);
			modelAndView.setViewName(viewName);
		}catch(Exception e){
			logger.info(" Has Exception : " + e.getMessage());
			logger.info(" Detail : " + e.getCause());
		}
		super.postHandle(request, response, handler, modelAndView);
	}
}