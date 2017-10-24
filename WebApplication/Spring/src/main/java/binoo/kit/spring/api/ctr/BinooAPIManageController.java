package binoo.kit.spring.api.ctr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
	
	private final Map<String, Object> itemMap = new HashMap<String, Object>();
	
	private final String serverpropertyFileName = "E:\\Project\\Single Project\\visual\\WindowsFormsApplication1\\DevOpsStudy_1\\externData\\properties\\test.properties";
	private final String logFileName = "E:\\Project\\Single Project\\visual\\WindowsFormsApplication1\\DevOpsStudy_1\\externData\\log\\";
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
	@RequestMapping(value = "/login/{userId}/", method = RequestMethod.POST)
	public @ResponseBody String login_default(
			HttpServletRequest req,
			BindingResult bind,
			@PathVariable("userId") String userId,
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
		if( "".equals(bApiVo.getUserPw()) || bApiVo.getUserPw() == null ){
			return "ERROR:NONE PASSWORD";								// 넘어온 비밀번호가 없으면 에러!
		}
		
		try {
			BinooAPIVO svo = new BinooAPIVO();
			svo.setUserId(userId);
			BinooAPIVO rvo = (BinooAPIVO) mainMapper.selectItem("users", svo);	// 가져와서 검사하기
			if(rvo != null){
				if( bApiVo.getUserPw().equals(rvo.getUserPw()) ){
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
	 * API : 입력에 따라 사용자계정을 관리/메시지전달(공지)/아이템 전달(보상)/서버프로퍼티관리 를 하고, 그 결과를 출력 합니다.
	 * @author botbinoo@naver.com
	 * @last 2017.10.23
	 * */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/api/{userId}/{ssk}/{proctype}/", method = RequestMethod.POST)
	public String api_main(
			HttpServletRequest req,
			BindingResult bind,
			@PathVariable("userId") String userId,
			@PathVariable("ssk") String ssk,
			@PathVariable("proctype") String proctype,
			@ModelAttribute("bApiVo") BinooAPIVO bApiVo
			) {

		logger.debug(" [debug] in api main ");
		
		if( bind.hasErrors() ){											// bind 에러 뱉고
			return "ERROR:BIND";
		}
		if( "".equals(userId) || userId == null ){
			return "ERROR:MISS API ID";									// API ID가 없으면 에러!
		}
		if( "".equals(ssk) || ssk == null ){
			return "ERROR:MISS API KEY";								// API KEY가 없으면 에러!
		}
		if( "".equals(proctype) || proctype == null ){
			return "ERROR:MISS API TODO";								// API 할 일이 없으면 에러!
		}
		
		/* 로그에 남길 데이터 */
		Date date = new Date();
		DateFormat logTimeFormat = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss.S");	//로그에 남길 시간형식
		String formattedDate = logTimeFormat.format(date);
		boolean processingResult = false;
		String whatToDo = "";
		Map<String, String[]> paramMap = req.getParameterMap();
		
		itemMap.put("userId", userId);
		itemMap.put("procTime", formattedDate);
		
		try {
			BinooAPIVO svo = new BinooAPIVO();
			svo.setUserId(userId);
			BinooAPIVO rvo = (BinooAPIVO) mainMapper.selectItem("users", svo);	// 가져와서 검사하기
			if(rvo == null){
				return "ERROR:MISS API ID";								// API KEY가 없으면 에러!
			}
			if( !bApiVo.getApiKey().equals(rvo.getApiKey()) ){
				return "ERROR:NOT MATCH API KEY";						// API KEY가 안맞으면 에러!
			}
			
			if(!proctype.contains("=")){
				return "ERROR:MISS API TODO";							// API 가 할 일이 문제가 있으면 에러!
			}
			String proc = proctype.split("=")[0];						// 무엇을 처리할지
			String value = proctype.split("=")[1];						// 어떤 값으로 처리를 할지
			
			switch(proc){
				case "search":														// 검색 요청을 할 경우.
					if("properties".equals(value) && !"userKey".equals(ssk)) {		// 사용자가 아닐때 이미 적용된 최신 프로퍼티를 가져옵니다.
						// 서버 프로퍼티 내용 가져오기
						// 처리내용 로그 기록
						HashMap<String, String> propertiesMap = new HashMap<String, String>();
						File propertiesFile = new File( serverpropertyFileName );
						if(propertiesFile.exists()){
							try{
								BufferedReader br = new BufferedReader(new FileReader(propertiesFile));
								String line = null;
								while((line = br.readLine())!= null){				// 읽어온게 null 이 아니면
									if(line.toCharArray().length > 0){				// 그러나 공백은 처리하지 않음.
										if( line.toCharArray()[0] != '#' ){			// 주석은 아니어야함.
											String pkey = line.split("=")[0];
											String pvalue = line.split("=")[1];
											propertiesMap.put(pkey, pvalue);
										}
									}
								}
								itemMap.put("resultData", propertiesMap);
								processingResult = true;
								br.close();
							}catch(Exception e){
								logger.debug(" [error] error api use : " + e.getMessage());
								processingResult = false;
							}
						}
					}
					if("log".equals(value)){
						if("id".equals(value)) {
							svo.setUserId(userId);										// 아이디로 조회
						}
						if("locaion".equals(value)) {
							svo.setProcType(bApiVo.getProcType());						// 조회할 영역 제한
						}
						if("keyword".equals(value)) {
							svo.setSearch_keyword(bApiVo.getSearch_keyword());			// 일정 명령이 포함된 기준으로 조회
						}
						
						// DB 에 있는 경우.
						/*
						 * @SuppressWarnings("unchecked") 
						ArrayList<BinooAPIVO> resultList = (ArrayList<BinooAPIVO>) mainMapper.selectItemList("log", svo);	// 가져와서 검사하기
						resultMap.put("logList", resultList);
						*/
						
						// File 에 있는 경우.
						BufferedReader br = null;
						ArrayList<String> resultList = new ArrayList<String>();
						File logDir = new File( logFileName + svo.getProcType());
						processingResult = true;
						if(logDir.isDirectory()){
							for( File logFile : logDir.listFiles() ){
								if(!logFile.canRead()){				// 있긴 한데 권한이 없음
									processingResult = false;
									logger.debug(" [log > error] search log error : read denided.");
								}
								if(logFile.isFile()){
									br = new BufferedReader(new FileReader(logFile));
									String line = null;
									while((line = br.readLine())!= null){				// 읽어온게 null 이 아니면
										if(line.contains(svo.getUserId()) || line.contains(svo.getSearch_keyword())){ 
											resultList.add(line);
										}
									}
								}
							}
						}
						itemMap.put("resultData", resultList);
					}
					if("chat".equals(value)) {										// 채팅내용을 가져옵니다.
						svo = new BinooAPIVO();
						
						if(!"".equals(bApiVo.getChatRead()) && bApiVo.getChatRead()!=null){
							svo.setChatRead(bApiVo.getChatRead());					// 읽었는지 안읽었는지 검색조건에 넣기
						}
						if(!"".equals(bApiVo.getSearch_keyword()) && bApiVo.getSearch_keyword()!=null){
							svo.setSearch_keyword(bApiVo.getSearch_keyword());		// 키워드 검색을 한 경우.
						}
						@SuppressWarnings("unchecked") 
						ArrayList<BinooAPIVO> resultList = (ArrayList<BinooAPIVO>) mainMapper.selectItemList("chat", svo);
						itemMap.put("resultData", resultList);
						processingResult = true;
					}
					if("userName".equals(value)) {									// 사용자 이름으로 사용자를 조회합니다.
						// 사용자 리스트 가져오기
						svo = new BinooAPIVO();
						if(!"".equals(bApiVo.getSearch_keyword()) && bApiVo.getSearch_keyword()!=null){
							svo.setSearch_keyword(bApiVo.getSearch_keyword());		// 키워드 검색을 한 경우.
						}
						@SuppressWarnings("unchecked") 
						ArrayList<BinooAPIVO> resultList = (ArrayList<BinooAPIVO>) mainMapper.selectItemList("users", svo);
						itemMap.put("resultData", resultList);
						processingResult = true;
					}
					break;
				case "chat":														// 채팅/공지/전달사항 보내기 -> 해당 내용은 DB 적재이므로, 반드시 wifi 같은 것이 연결되어야만 함.
					if("toUser".equals(value)) {									// 1~n명에게 메시지 전달하기.
						// 사용자 개인에게 메시지 전달하기.
						for(String user_id : paramMap.get( "userIds" )){			// 있는 숫자만큼 전송함.
							svo = new BinooAPIVO();
							svo.setUserId(user_id);									// 받을 사람
							svo.setChatRead(0);										// 안읽음 처리
							svo.setChatTerm(bApiVo.getChatTerm());
							svo.setChatTime(formattedDate);
							svo.setRetry(bApiVo.getRetry());
							svo.setState(bApiVo.getState());
							
							for(String content : paramMap.get( "chatContents" )){
								svo.setChatContent(content);
								mainMapper.insertItem("chat", svo);					// 사용자는 입력된 전체 채팅내용중, 읽지 않은 내용을 모두 보는 형태로 구상.
							}
						}
						// 처리내용 로그 기록
						processingResult = true;
					}
					break;
				case "account":														// 계정관리
					if("user".equals(value)) {										// 사용자의 계정 가입/정지/탈퇴 처리.
						// 사용자의 개정 가입/정지/탈퇴 처리.
						// 자신의 데이터처리는 누구나 가능, 그러나 남의 정보는 관리자만 관리가능함.
						String todo = paramMap.get( "process" )[0];
						
						if(paramMap.get( "userIds" ).length > 1 || paramMap.get( "userIds" )[0]!=userId){
							if(!"userKey".equals(ssk)){
								for(String user_id : paramMap.get( "userIds" )){			// 있는 숫자만큼 처리
									svo = new BinooAPIVO();
									svo.setUserId(userId);									// 누구를
									if("delete".equals(todo)){
										mainMapper.deleteItem("users", svo);				// 탈퇴 처리 
									} else if("update".equals(todo)){
										svo = bApiVo;
										mainMapper.updateItem("users",svo);					// 수정 
									} else if("insert".equals(todo)){
										mainMapper.insertItem("users",bApiVo);				// 가입
									}
								}
								processingResult = true;
							}
						} else if(paramMap.get( "userIds" ).length == 1 && paramMap.get( "userIds" )[0]==userId){
							// userId 는 상단에서 미리 apiKey 검사하면서 확인한 내용.
							// 자기자신을 처리하는 경우.
							svo = new BinooAPIVO();
							svo.setUserId(userId);									// 누구를
							if("delete".equals(todo)){
								mainMapper.deleteItem("users",svo);					// 탈퇴 처리 
							} else if("update".equals(todo)){
								svo = bApiVo;
								mainMapper.updateItem("users",svo);					// 수정 
							} else if("insert".equals(todo)){
								mainMapper.insertItem("users",bApiVo);				// 가입
							}
							processingResult = true;
						}
					}
				default:
					break;
			}
			itemMap.put("proccess", proc);
			itemMap.put("proccessType", value);
			makelog( "commend", formattedDate + (processingResult? " Y " : " N ") + req.getRemoteAddr() + " " + userId + " " + whatToDo );
		} catch (Exception e) {
			logger.debug(" [error] error api main : " + e.getMessage());
			makelog( "commend", formattedDate + (processingResult? " Y " : " N ") + req.getRemoteAddr() + " " + userId + " " + whatToDo );
		}
		itemMap.put("result", processingResult);
		
		logger.debug(" [debug] out api main ");
		return "redirect:/spring/binooApi/api/result/";
	}

	/*
	 * mapping : 요청한 처리 결과를 xml 에 뿌려줍니다.
	 * @author botbinoo@naver.com
	 * @last 2017.10.24
	 * */
	@RequestMapping(value = "/api/result/")
	public String api_result( ModelMap model ){
		logger.debug(" [debug] in api_result "); 

		model.addAttribute("result", itemMap.get("result"));
		model.addAttribute("requestUserId", itemMap.get("userId"));
		model.addAttribute("proccessTime", itemMap.get("procTime"));
		model.addAttribute("proccess", itemMap.get("proccess"));
		model.addAttribute("proccessType", itemMap.get("proccessType"));
		model.addAttribute("resultMap", itemMap.get("result"));
		itemMap.clear();
		
		logger.debug(" [debug] out api_result ");
		return "api_result";
	}

	/*
	 * LOG : log 파일을 생성하거나 기록하는 역할을 합니다.
	 * @author botbinoo@naver.com
	 * @last 2017.10.24
	 * */
	private void makelog(String logType, String... lines){
		/*
		로그 포맷!
		줄 번호 + 처리시간 + 성공여부 + 요청IP + 요청ID + 명령(간단하게)
		ex)
		1 2017/10/23-20:32:57.1923 Y 199.10.12.220 binoo [modified properties(item-drop=2, gold-grown=2)]
		2 2017/10/23-20:32:57.2210 N 220.10.28.190 obtain92 [delete users(sanu199, rena2000)]
		 * */
		
		Date date = new Date();
		DateFormat logFileNameFormat = new SimpleDateFormat("yyyy.MM.dd HH");		// 파일명
		String formattedDate = logFileNameFormat.format(date) + ".log";
		
		StringBuilder fileName = new StringBuilder();
		if(lines.length == 0){
			return;													// 저장할 로그 라인이 없으면 저장 안함.
		}
		fileName.append("/externData/log");
		switch(logType){
			case "chat":
				fileName.append("/chat-log/");
				break;
			case "commend":
				fileName.append("/commend-log/");
				break;
			case "userplay":
				fileName.append("/play-log/");
				break;
			default:
				return;												// 저장할 로그분류에 속하지 않으면 저장 안함.
		}
		@SuppressWarnings("deprecation")
		File logFile = new File(fileName.toString()+date.getYear()+"/"+(date.getMonth()+1)+"/"+date.getDate()+"/"+formattedDate, "UTF-8");
		if(!logFile.exists()) logFile.mkdirs(); //없으면 만들기
		if(!logFile.canWrite()){				// 있긴 한데 권한이 없음
			logger.debug(" [log > error] print log error : write denided.");
			return;
		}
		PrintWriter printWriter = null;
		try {
			if(logFile.exists()){
				printWriter = new PrintWriter(logFile, "UTF-8");
			}
			for(String line : lines){
				printWriter.append( line );
			}
		} catch (FileNotFoundException e) {
			logger.debug(" [log > error] print log error : " + e.getMessage());
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			logger.debug(" [log > error] print log error : " + e.getMessage());
			e.printStackTrace();
		} finally {
			if(printWriter != null){ printWriter.close(); }
		}
	}
}