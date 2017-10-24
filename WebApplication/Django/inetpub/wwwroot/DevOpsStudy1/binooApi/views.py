# -*- coding: utf-8 -*-
from django.shortcuts import render, get_object_or_404
from django.http import HttpResponseRedirect, HttpResponse
from django.core.urlresolvers import reverse
from binooApi.models import USERS, LOG_DATA, CHAT_DATA, ITEM, ITEM_INSTANCE
from django.db.models import Q

import json
import datetime
import os

# Create your views here.

'''
@Py Name : Models.py
@Description : model. in spring, like a vo(model)
@author botbinoo@naver.com
@since 2017.10.22
@last modified 2017.10.24
@version test

Copyright (C) by botbinoo's All right reserved.
'''
# Global value
errorCode = 0
serverpropertyFileName = "E:/Project/Single Project/visual/WindowsFormsApplication1/DevOpsStudy_1/externData/properties/test.properties"
logFileName = "E:/Project/Single Project/visual/WindowsFormsApplication1/DevOpsStudy_1/externData/log/"

'''
@Description : main display
@author botbinoo@naver.com
'''
def main(request):
    return render(request, 'search/main.html', {'a':1})

def loginDefault(request):
    return render(request, 'search/login.html', {'a':1})

def login(request, login_id):
    # TODO : login 처리
    #try:
        errorCode = 1000
        # 만일, 로그인 처리에 실패할 경우 처리코드 1000
        login_pw = request.GET['login_pw']
        print 1
        userData = USERS.objects.get(Q(userid=login_id))
        print 2
        if len(userData) >= 1:
            # 어쨌든 아이디는 있는 경우.
            if login_pw == userData.userpw:
                # 비밀번호가 맞는 경우.
                return HttpResponse({'s':True, 'pcode':'LGNS', 'sm':'운영 로그인 성공'}, content_type="text/html")
            else:
                # 비밀번호는 틀린 경우.
                return HttpResponse("%s." % 'LGNF1', content_type="text/html")
        else:
            # 아이디가 없거나 틀린경우.
            return HttpResponse("%s." % 'LGNF2', content_type="text/html")
    #except:
        return HttpResponse("%s." % 'LGNE1', content_type="text/html")

def List(request, ssk, login_id):
    searchkeyword = keywords
    # TODO : log 항목에서 검색처리
    try:
        errorCode = 2000
        # 만일, 로그 다건조회 처리에 실패할 경우 처리코드 2000
        # id 로 검색
        logList = USERS.objects.filter(Q(userid__icontains=keywords)).distinct().order_by('-pub_date')[:50]
        # 시간으로 검색 (지정시간 ~ 현재까지)
        # 처리성공/실패만 검색

        return render(request, 'search/list.html', {'s':True, 'pcode':'LOGMFS', 'sm':'로그 리스트 조회 성공','logList':logList})
    except:
        return render(request, 'search/list.html', {'s':False, 'pcode':'LOGMFF1', 'sm':'로그 리스트 조회 실패 : 오류'})

def detail(request, ssk, login_id, log_id):
    # TODO : log 항목에서 상세 처리내역 조회
    try:
        errorCode = 2100
        # 만일, 로그 단건조회 처리에 실패할 경우 처리코드 2100
        logData = LogModel.objects.filter(Q(userid=login_id))
        
        if len(logData) >= 1:
            # 어쨌든 데이터가 있는 경우.
            return render(request, 'search/detail.html', {'s':True, 'pcode':'LOGOFS', 'sm':'로그 단건 조회 성공','logData':logData})
        else:
            # 데이터가 없는 경우.
            return render(request, 'search/detail.html', {'s':True, 'pcode':'LOGOFF1', 'sm':'로그 단건 조회 실패 : 조건에 맞는 값이 없습니다.'})
    except:
        # 비밀번호 없이 접근할 경우. 
        return render(request, 'search/detail.html', {'s':False, 'pcode':'LOGOFF2', 'sm':'로그 단건 조회 실패 : 오류'})

def api(request, reqId, ssk, proctype):
    # TODO : 요청한 내용을 처리하고 그 결과를 json/xml 출력
    
    # 요청자 확인
    if (reqId is None) or (len(reqId)==0):
        return # TODO : 에러 추가해야함.
    if (ssk is None) or (len(ssk)==0):
        return # TODO : 에러 추가해야함.
    if (proctype is None) or (len(proctype)==0):
        return # TODO : 에러 추가해야함.
    
    # 로그용 데이터 세팅
    procStartTime = datetime.datetime.now()
    procTime = procStartTime.strftime('%Y/%m/%d-%H:%M:%S')
    processingResult = False;
    
    itemList = { }
    itemList['userId'] = reqId
    itemList['procTime'] = procTime
    
    userData = USERS.objects.get(Q(userid=reqId))

    if userData is None:
        return # TODO : 에러 추가해야함.
    if userData.apikey != ssk:
        return # TODO : 에러 추가해야함.
    if proctype.count('=') != 1:
        return
    proc = proctype.split('=')
    if proc[0] == "search":
        # 프로퍼티 / 로그 / 채팅 / 사용자 검색을 처리합니다.
        if proc[1] == "properties":
            # 외부 파일을 읽어온다.
            f = open(serverpropertyFileName, 'r')
            buf = f.read()
            propertiesContent = buf.split()
            propertData = [ ]
            for line in propertiesContent:
                if line[0] != '#':
                    if line.count('=') == 1:
                        pkey = line.split('=')[0]
                        pvalue = line.split('=')[1]
                        propertData.append({pkey, pvalue}) # Java 에서, HashMap 을 품은 List 형처럼 저장
            # 빠져나오면 딕셔너리에 넣어준다.
            itemList['resultData'] = propertData
        elif proc[1] == "log":
            resultList = [ ]
            searchId = fn_util_postToData(request, 'searchId', '' )
            searchlocaion = fn_util_postToData(request, 'searchlocaion', '' )
            searchkeyword = fn_util_postToData(request, 'searchkeyword', '' )
            # 외부 파일을 읽어온다.
            bufDir = open(logFileName + searchlocaion, 'r')
            for f in os.listdir(bufDir):
                lines = f.readlines()
                for line in lines:
                    if (line.count(searchId) > 0) or (line.count(searchkeyword) > 0):
                        resultList.append(line)
                f.close()
            bufDir.close()
            
            itemList['resultData'] = resultList
        elif proc[1] == "chat":
            # 파라미터를 받아 검색을 한다.
            resultList = [ ]
            isRead = request.POST['isRead']
            searchkeyword = fn_util_postToData(request, 'searchkeyword', '' )
            if len(isRead) > 0:
                if len(searchkeyword) > 0:
                    resultList.extend(CHAT_DATA.objects.filter(Q(userid=isRead, fromuserid__contains=searchkeyword)))
                    resultList.extend(CHAT_DATA.objects.filter(Q(userid=isRead, touserid__contains=searchkeyword)))
                    resultList.extend(CHAT_DATA.objects.filter(Q(userid=isRead, chatcontent__contains=searchkeyword)))
                else:
                    resultList.extend(CHAT_DATA.objects.filter(Q(userid=isRead)))
            else:
                resultList.extend(CHAT_DATA.objects.all())
            itemList['resultData'] = resultList
        elif proc[1] == "userName":
            resultList = [ ]
            searchkeyword = fn_util_postToData(request, 'searchkeyword', '' )
            if len(searchkeyword) > 0:
                resultList.extend(USERS.objects.filter(Q(userid__contains=searchkeyword)))
            else:
                resultList.extend(CHAT_DATA.objects.all())
            itemList['resultData'] = resultList
        else : pass
        processingResult = true
    elif proc[0] == "chat":
        # 메시지를 작성하고 입력합니다.
        if proc[1] == "toUser":
            # 참고 : https://stackoverflow.com/questions/3910165/handling-django-request-get-and-multiple-variables-for-the-same-parameter-name
            # request.GET.getlist('myvar')
            userIds = request.POST.getlist('userIds')
            for user_id in userIds:
                # model 에 담아 인서트
                chatContents = request.POST.getlist('chatContents')
                for chatComment in chatContents:
                    imodel = CHAT_DATA(fromuserid=reqId, touserid=user_id, state=fn_util_postToData(request, 'state', '' ), chattime=fn_util_postToData(request, 'chatTime', '' ), retry=fn_util_postToData(request, 'retry', '' ), chatread=0, chatterm=fn_util_postToData(request, 'chatTerm', '' ), chatcontent=fn_util_postToData(request, 'chatContent', '' ))
                    imodel.save()
        processingResult = true
    elif proc[0] == "account":
        # 계정과 관련된 작업을 합니다.
        if proc[1] == "user":
            userIds = request.POST.getlist('userIds')
            for user_id in userIds:
                if ssk == 'userKey': # 관리자가 아닌 일반 사용자의 경우.
                    if user_id == reqId: # 본인 것만 수정 가능함.
                        todo = fn_util_postToData(request, 'todo', '' )
                        '''
                        if todo == 'delete':
                            # 삭제 처리
                            userData.delete()
                        '''
                        if todo == 'update': # 일반 사용자는 탈퇴 요청(삭제 아님), 가입시에도 해당 페이지에 내장된 Key 로 요청을 진행하도록한다.
                            newId = fn_util_postToData(request, 'userId', userData.userid )
                            newPw = fn_util_postToData(request, 'userPw', userData.userpw )
                            incurrectPw = fn_util_postToData(request, 'passwordIncurrectCnt', userData.passwordincurrectcnt )
                            newState = fn_util_postToData(request, 'state', userData.state )
                            newApiKey = fn_util_postToData(request, 'apiKey', userData.apikey )
                            newJob = fn_util_postToData(request, 'job', userData.job )
                            newLevel = fn_util_postToData(request, 'level', userData.level )
                            newGold = fn_util_postToData(request, 'gold', userData.gold )
                        
                            userData.userid = newId
                            userData.userpw = newPw
                            userData.passwordincurrectcnt = incurrectPw
                            userData.state = newState
                            userData.apikey = newApiKey
                            userData.job = newJob
                            userData.level = newLevel
                            userData.gold = newGold
                            userData.save()
                            # 수정 처리
                        '''
                        if todo == 'insert':
                            newId = request.POST['userId']
                            newPw = request.POST['userPw']
                            newApiKey = 'userKey' # 가입은 모두 유저형태. 나중에 관리자가 신임 관리자를 처리해주는 형태
                            newJob = request.POST['job']
                            newUsers = USERS(userId=newId,userPw=newPw, apiKey=newApiKey, job=newJob)
                            newUsers.save()
                            # 가입 처리
                        '''
                else:
                    todo = fn_util_postToData(request, 'todo', '' )
                    if todo == 'delete':
                        # 삭제 처리
                        userData.delete()
                    if todo == 'update':
                        procUserData = USERS.objects.get(Q(userid=user_id))
                        
                        newId = fn_util_postToData(request, 'userId', procUserData.userid )
                        newPw = fn_util_postToData(request, 'userPw', procUserData.userpw )
                        incurrectPw = fn_util_postToData(request, 'passwordIncurrectCnt', procUserData.passwordincurrectcnt )
                        newState = fn_util_postToData(request, 'state', procUserData.state )
                        newApiKey = fn_util_postToData(request, 'apiKey', procUserData.apikey )
                        newJob = fn_util_postToData(request, 'job', procUserData.job )
                        newLevel = fn_util_postToData(request, 'level', procUserData.level )
                        newGold = fn_util_postToData(request, 'gold', procUserData.gold )
                        
                        procUserData.userid = newId
                        procUserData.userpw = newPw
                        procUserData.passwordincurrectcnt = incurrectPw
                        procUserData.state = newState
                        procUserData.apikey = newApiKey
                        procUserData.job = newJob
                        procUserData.level = newLevel
                        procUserData.gold = newGold
                        procUserData.save()
                        # 수정 처리
                    if todo == 'insert':
                        newId = request.POST['userId']
                        newPw = request.POST['userPw']
                        newApiKey = 'userKey' # 가입은 모두 유저형태. 나중에 관리자가 신임 관리자를 처리해주는 형태
                        newJob = request.POST['job']
                        newUsers = USERS(userid=newId,userpw=newPw, apikey=newApiKey, job=newJob)
                        newUsers.save()
                        # 가입 처리
        processingResult = True
    else : pass
    itemList['proccess'] = proc[0]
    itemList['proccessType'] = proc[1]
    rchar = " N "
    if processingResult == True: rchar = " N "
    line = procTime + rchar + get_client_ip(request) + " " + reqId + " " + itemList
    makelog(logType, line)
    
    return render(request, 'api/xxx.html', itemList)

def fn_util_postToData( request, key, substitute ):
    # post 파라미터중에 해당 값이 없으면 대신 이 값을 삽입한다.
    if request.POST.has_key(key):
        return request.POST[key]
    else:
        return substitute

def makelog(logType, line):
    # 로그를 작성합니다.
    '''
       로그 파일은 다음과 같은 형태로 작성한다고 가정합니다.
       1 2017/10/23-20:32:57.1923 Y 199.10.12.220 binoo [modified properties(item-drop=2, gold-grown=2)]
       2 2017/10/23-20:32:57.2210 N 220.10.28.190 obtain92 [delete users(sanu199, rena2000)]
    '''
    # logFileName logFileNameFormat.strftime('%Y.%m.%d %H') + ".log"
    logFileNameFormat = datetime.datetime.now()
    logFile = logFileName
    
    if len(line) <= 0:
        return;
    if logType == 'chat':
        logFile = logFile + "chat-log/"
    elif logType == 'commend':
        logFile = logFile + "commend-log/"
    elif logType == 'userplay':
        logFile = logFile + "play-log/"
    else:
        return
    logFile = logFile + datetime.datetime.now().strftime('%Y/%m/%d/') + datetime.datetime.now().strftime('%Y.%m.%d %H') + ".log"
    if os.path.isfile(logFile):
        # file 있음.
        f = open(logFile, 'a')
        f.write(line+"\n")
    else:
        # 생성하고 로그 기록하기
        f = open(logFile, 'w')
        f.write(line+"\n")
    
    f.close()

# 참고 - https://stackoverflow.com/questions/4581789/how-do-i-get-user-ip-address-in-django
def get_client_ip(request):
    x_forwarded_for = request.META.get('HTTP_X_FORWARDED_FOR')
    if x_forwarded_for:
        ip = x_forwarded_for.split(',')[0]
    else:
        ip = request.META.get('REMOTE_ADDR')
    return ip

def error(request):
    # TODO : 오류 페이지 출력
    # 에러코드를 받으면, 코드에 맞는 메시지를 출력한다.
    return render(request, 'error/error.html')

