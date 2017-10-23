package binoo.kit.spring.api.ctr;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import binoo.kit.spring.api.model.BinooAPIVO;
import binoo.kit.spring.cmmn.BinooCommonDAO;

/**
 * @Class Name : BinooAPIManageController
 * @Description : Controller - API redirect current URL.
 * @author botbinoo@naver.com
 * @since 2017.10.22
 * @version test
 *
 *  Copyright (C) by botbinoo's All right reserved.
 */

@RequestMapping("/spring/binooApi")
@Controller
public class BinooAPIManageController {

	private static final Logger logger = LoggerFactory.getLogger(BinooAPIManageController.class);
	
	@Autowired
	@Qualifier("mainMapper")
	private BinooCommonDAO mainMapper;
	
	/*
	 * main 화면을 출력합니다.
	 * @author botbinoo@naver.com
	 * @last 2017.10.23
	 * */
	@RequestMapping(value = "/")
	public String main() {
		logger.debug(" [debug] in main ");
		return "binooFrontLayout:/board/main";
	}
	
	/*
	 * login 처리를 합니다.
	 * @author botbinoo@naver.com
	 * @last 2017.10.23
	 * */
	@RequestMapping(value = "/login/{operatorId}/", method = RequestMethod.POST)
	public @ResponseBody String login_default(
			HttpServletRequest req,
			BindingResult bind,
			@PathVariable("operatorId") String operatorId,
			@ModelAttribute("bApiVo") BinooAPIVO bApiVo
			) {

		logger.debug(" [debug] in login main ");
		
		if( bind.hasErrors() ){											// bind 에러 뱉고
			return "ERROR:BIND";
		}
		if( req.getSession() != null){
			if( !"".equals(req.getSession().getAttribute("userId")) && req.getSession().getAttribute("userId") != null ){
				return "ERROR:ALREADY LOGIN";							// 이미 로그인 상태
			}
		}
		if( "".equals(bApiVo.getOperatorPw()) || bApiVo.getOperatorPw() == null ){
			return "ERROR:NONE PASSWORD";								// 넘어온 비밀번호가 없으면 에러!
		}
		
		try {
			BinooAPIVO svo = new BinooAPIVO();
			svo.setOperatorId(operatorId);
			BinooAPIVO rvo = (BinooAPIVO) mainMapper.selectItem(svo);	// 가져와서 검사하기
			if(rvo != null){
				if( bApiVo.getOperatorPw().equals(rvo.getOperatorPw()) ){
					return "SUCCESS";									// 비밀번호가 동일할때 성공
				}
			}
		} catch (Exception e) {
			logger.debug(" [error] error login main : " + e.getMessage());
		}
		
		logger.debug(" [debug] out login main ");

		return "FALSE";													// 아니면 실패.
	}

	/*
	 * log (여기서는 DB)에서 관리자가 작업한 내용들을 출력 합니다.
	 * @author botbinoo@naver.com
	 * @last 2017.10.23
	 * */
	@RequestMapping(value = "/log/{operatorId}/{ssk}/{proctype}/", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> log(
			BindingResult bind,
			@PathVariable("operatorId") String operatorId,
			@PathVariable("ssk") String ssk,
			@PathVariable("proctype") String proctype,
			@ModelAttribute("bApiVo") BinooAPIVO bApiVo
			) {

		logger.debug(" [debug] in log main ");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if( bind.hasErrors() ){											// bind 에러 뱉고
			resultMap.put("error", "ERROR:BIND");
			return resultMap;
		}
		if( "".equals(operatorId) || operatorId == null ){
			resultMap.put("error", "ERROR:MISS API ID");
			return resultMap;											// API ID가 없으면 에러!
		}
		if( "".equals(ssk) || ssk == null ){
			resultMap.put("error", "ERROR:MISS API KEY");
			return resultMap;											// API KEY가 없으면 에러!
		}
		if( "".equals(proctype) || proctype == null ){
			resultMap.put("error", "ERROR:MISS API TODO");
			return resultMap;											// API 할 일이 없으면 에러!
		}
		
		try {
			if(!proctype.contains("=")){
				resultMap.put("error", "ERROR:MISS API TODO");
				return resultMap;										// API 가 할 일이 문제가 있으면 에러!
			}
			String proc = proctype.split("=")[0];						// 무엇을 처리할지
			String value = proctype.split("=")[1];						// 어떤 값으로 처리를 할지
			BinooAPIVO svo = new BinooAPIVO();
			
			switch(proc){
				case "search":														// 다건 검색을 할 경우. list
					if("id".equals(value)) {
						svo.setOperatorId(operatorId);								// API 아이디로 조회
					}
					if("time".equals(value)) {
						svo.setSearch_sttTime(bApiVo.getSearch_sttTime());			// 시작시간
						svo.setSearch_endTime(bApiVo.getSearch_endTime());			// 종료시간 이내의 일정 기간 으로 조회
					}
					if("todo".equals(value)) {
						svo.setSearch_keyword(bApiVo.getSearch_keyword());			// 일정 명령이 포함된 기준으로 조회
					}
					
					@SuppressWarnings("unchecked") 
					ArrayList<BinooAPIVO> resultList = (ArrayList<BinooAPIVO>) mainMapper.selectItemList(svo);	// 가져와서 검사하기
					resultMap.put("logList", resultList);
					return resultMap;
				case "item":														// 단건 검색을 할 경우. detail
					svo.setProc_time(bApiVo.getProc_time());
					BinooAPIVO resultItem = (BinooAPIVO) mainMapper.selectItem(svo);	// 가져와서 검사하기
					resultMap.put("logItem", resultItem);
					return resultMap;
				default:
					break;
			}
		} catch (Exception e) {
			logger.debug(" [error] error log main : " + e.getMessage());
		}
		
		logger.debug(" [debug] out log main ");
		resultMap.put("error", "ERROR:DIDNT PROCESSING");
		return resultMap;													// 아니면 실패.
	}

	/*
	 * API : 입력에 따라 사용자계정을 관리/메시지전달(공지)/아이템 전달(보상)/서버프로퍼티관리 를 하고, 그 결과를 출력 합니다.
	 * @author botbinoo@naver.com
	 * @last 2017.10.23
	 * */
	@RequestMapping(value = "/api/{operatorId}/{ssk}/{proctype}/", method = RequestMethod.POST)
	public String api_main(
			BindingResult bind,
			@PathVariable("operatorId") String operatorId,
			@PathVariable("ssk") String ssk,
			@PathVariable("proctype") String proctype,
			@ModelAttribute("bApiVo") BinooAPIVO bApiVo
			) {

		logger.debug(" [debug] in api main ");
		
		if( bind.hasErrors() ){											// bind 에러 뱉고
			return "ERROR:BIND";
		}
		if( "".equals(operatorId) || operatorId == null ){
			return "ERROR:MISS API ID";									// API ID가 없으면 에러!
		}
		if( "".equals(ssk) || ssk == null ){
			return "ERROR:MISS API KEY";								// API KEY가 없으면 에러!
		}
		if( "".equals(proctype) || proctype == null ){
			return "ERROR:MISS API TODO";								// API 할 일이 없으면 에러!
		}
		
		try {
			if(!proctype.contains("=")){
				return "ERROR:MISS API TODO";							// API 가 할 일이 문제가 있으면 에러!
			}
			String proc = proctype.split("=")[0];						// 무엇을 처리할지
			String value = proctype.split("=")[1];						// 어떤 값으로 처리를 할지
			BinooAPIVO svo = new BinooAPIVO();
			
			switch(proc){
				case "search":														// 검색 요청을 할 경우.
					if("properties".equals(value)) {								// 이미 적용된 최신 프로퍼티를 가져옵니다.
						// 서버 프로퍼티 내용 가져오기
						// 처리내용 로그 기록
					}
					if("userName".equals(value)) {									// 사용자 이름으로 사용자를 조회합니다.
						// 사용자 리스트 가져오기
						// 처리내용 로그 기록
					}
				case "chat":														// 공지/전달사항 보내기 -> 해당 내용은 DB 적재이므로, 반드시 wifi 같은 것이 연결되어야만 함.
					if("toUser".equals(value)) {									// 1명에게 메시지 전달하기.
						// 사용자 개인에게 메시지 전달하기.
						// 처리내용 로그 기록
					}
					if("toUsers".equals(value)) {									// n명에게 메시지 전달하기.
						// 파라미터로 받아온 리스트를 이용하여 메시지 전달.
						// 처리내용 로그 기록
					}
					if("toServer".equals(value)) {									// 서버 전체에게 메시지 전달하기.
						// 사용자 개인에게 메시지 전달하기.
						// 처리내용 로그 기록
					}

				case "account":														// 계정관리
					if("user".equals(value)) {										// 사용자의 개정 가입/정지/탈퇴 처리.
						// 사용자의 개정 가입/정지/탈퇴 처리.
						// 처리내용 로그 기록
					}
					if("users".equals(value)) {										// 사용자들의 개정 가입/정지/탈퇴 처리.
						// 사용자들의 개정 가입/정지/탈퇴 처리.
						// 처리내용 로그 기록
					}
					if("operator".equals(value)) {									// n명에게 메시지 전달하기.
						// 파라미터로 받아온 리스트를 이용하여 메시지 전달.
						// 처리내용 로그 기록
					}
					if("operators".equals(value)) {									// n명에게 메시지 전달하기.
						// 파라미터로 받아온 리스트를 이용하여 메시지 전달.
						// 처리내용 로그 기록
					}
				default:
					break;
			}
		} catch (Exception e) {
			logger.debug(" [error] error api main : " + e.getMessage());
		}
		
		logger.debug(" [debug] out api main ");
		resultMap.put("error", "ERROR:DIDNT PROCESSING");
		return resultMap;													// 아니면 실패.
	}
}