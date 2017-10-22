package binoo.kit.spring.api.ctr;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import binoo.kit.spring.api.model.BinooAPIVO;

/**
 * @Class Name : BinooAPIManageController
 * @Description : Controller - API redirect current URL.
 * @author botbinoo@naver.com
 * @since 2017.10.22
 * @version test
 *
 *  Copyright (C) by botbinoo's All right reserved.
 */

@RequestMapping("/api")
@RestController
public class BinooAPIManageController {

	private static final Logger logger = LoggerFactory.getLogger(BinooAPIManageController.class);
	
	@RequestMapping(value = "/{sid}/{ssk}/", method = RequestMethod.GET)
	public String api_message(
			Locale locale, Model model,
			@PathVariable("sid") String id,
			@PathVariable("ssk") String secureSerialKey,
			@ModelAttribute("bApiVo") BinooAPIVO bApiVo
			) {
		
		/*
		 * >>>> API 의 처리된 결과를 XML형태로 출력
		 * API 제작 : botbinoo@naver.com
		 * 
		 * */
		model.addAttribute("serverTime", 1 );
		
		return "home";
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/{sid}/{ssk}/{proctype}/", method = RequestMethod.POST)
	public String api_proccessing(
			Locale locale, Model model,
			@PathVariable("sid") String id,
			@PathVariable("ssk") String secureSerialKey,
			@PathVariable("proctype") String procType,
			@ModelAttribute("bApiVo") BinooAPIVO bApiVo
			) {
		
		/*
		 * >>>> 먼저 입력받은 URL 에서 ID 와 SSK 가 맞는 경우에 해당 API로 연결
		 * API 제작 : botbinoo@naver.com
		 * 
		 * */
		HashMap<String, String> resultMap = new HashMap<String, String>();
		
		// id 는 반드시 운영자또는 DB에 저장된 계정의 ID 중 하나여야만 합니다.
		// ssk 는 현재 id 와 정확히 일치하는 값이어야만 합니다. (로그인때는 불필요)
		if(id == null || "".equals(id)){
			resultMap.put("error", "N_API_ID");
			return dummy(resultMap);								// id 가 없는 경우.
		}
		if(procType == null || "".equals(procType)){
			resultMap.put("error", "N_API_PROC");
			return dummy(resultMap);								// 무엇을 처리할지가 없는 경우.
		}
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		resultMap.put("proc_time", formattedDate);					// 처리 요청 시간 저장
		
		if(!"login".equals(procType)){
			if(secureSerialKey == null || "".equals(secureSerialKey)){
				resultMap.put("error", "N_API_SSK");
				return dummy(resultMap);							// SSK 가 없는 경우.
			}
			// VO 설정하고
			// 처리방식에 따라 전달
			switch(procType){
				case " ":
					break;
				default:
					break;
			}
		} else {
			// VO 설정하고
			// 로그인 리다이렉트
			if(true){
				// 로그인 성공시
			} else {
				// 실패시
			}
		}
		
		logger.info("Welcome home! The client locale is {}.", locale);
		

		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("a", 123 );
		
		return "binooFrontLayout:api_result";
	}
	
	private String dummy(HashMap<String, String> paramMap){
		return "";
	}
}