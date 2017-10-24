<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
/* 
@Class Name : api_result.jsp
@Description : printing view
@author botbinoo@naver.com
@since 2017.10.24
@version test

Copyright (C) by botbinoo's All right reserved.
*/
%>
 
<c:if test="${not empty result}">
	<binoo> 
		<api> 
			<config> 
				<proc name="binooApi">${proccessType}</proc>
				<made>botbinoo@naver.com</made>
				<version>1</version> 
			</config> 
			<request>
				<user-id>${requestUserId} </user-id> 
				<proc>${proccess} </proc> 
				<time>${proccessTime}</time> 
			</request> 
			<response> 
				<success>${result}</success> 
					<c:if test="${not empty resultData}">
						<resultData>
							<c:if test="${proccess eq 'search'}">
								<c:if test="${proccessType eq 'properties'}">
									<server-address>${server-address}</server-address>
									<people>${people}</people>
									<item-drop>${item-drop}</item-drop>
									<gold-grown>${gold-grown}</gold-grown>
									<exp-grown>${exp-grown}</exp-grown>
									<inx>${inx}</inx>
								</c:if>
								<c:if test="${proccessType eq 'log'}">
									<c:forEach var="logItem" items="${resultData}" varStatus="status">
										<log lineNumber="${status.count}">${logItem}</log>
									</c:forEach>
								</c:if>
								<c:if test="${proccessType eq 'chat'}">
									<c:forEach var="chatItem" items="${resultData}" varStatus="status">
										<chat lineNumber="${status.count}">
											<from>${chatItem.fromUserId}</from>
											<to>${chatItem.toUserId}</to>
											<state>${chatItem.state}</state>
											<chatTime>${chatItem.chatTime}</chatTime>
											<chatRead>${chatItem.chatRead}</chatRead>
											<retry>${chatItem.retry}</retry>
											<chatTerm>${chatItem.chatTerm}</chatTerm>
											<chatContent>${chatItem.chatContent}</chatContent>
										</chat>
									</c:forEach>
								</c:if>
								<c:if test="${proccessType eq 'userName'}">
									<c:forEach var="usrItem" items="${resultData}" varStatus="status">
										<chat lineNumber="${status.count}">
											<userId>${usrItem.userId}</userId>
											<passwordIncurrectCnt>${usrItem.passwordIncurrectCnt}</passwordIncurrectCnt>
											<state>${usrItem.state}</state>
											<apiKey>${usrItem.apiKey}</apiKey>
											<job>${usrItem.job}</job>
											<retry>${usrItem.retry}</retry>
											<level>${usrItem.level}</level>
											<gold>${usrItem.gold}</gold>
										</chat>
									</c:forEach>
								</c:if>
							</c:if>
						</resultData>
					</c:if> 
			</response> 
		</api> 
	</binoo>
</c:if>
