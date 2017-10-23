# -*- coding: utf-8 -*-
from django.shortcuts import render, get_object_or_404
from django.http import HttpResponseRedirect, HttpResponse
from django.core.urlresolvers import reverse
from binooApi.models import LoginModel, LogModel, ApiModel
from django.db.models import Q
import json

# Global value
searchkeyword = ""
errorCode = 0

# 알고리즘만 짜둠. 화면 디자인(템플릿)이랑 DB는 스프링과 함께 작성필요.
# 모든 처리내용 또한 성공 실패를 떠나 저장되도록 해야한다.
# Create your views here.

def main(request):
    # TODO : main 화면 처리
    # return HttpResponseRedirect(reverse('result', args=(p.id,)))
    return render(request, 'search/main.xml', {'a':1})

def loginDefault(request):
    return render(request, 'search/login.html', {'a':1})

def login(request, login_id):
    # TODO : login 처리
    try:
        errorCode = 1000
        # 만일, 로그인 처리에 실패할 경우 처리코드 1000
        login_pw = request.GET['login_pw']
        operatorData = LoginModel.objects.filter(Q(operator_id=login_id))
        
        if len(operatorData) >= 1:
            # 어쨌든 아이디는 있는 경우.
            if login_pw == db_pw[0]:
                # 비밀번호가 맞는 경우.
                return render(request, 'search/login.html',{'s':True, 'pcode':'LGNS', 'sm':'운영 로그인 성공'})
            else:
                # 비밀번호는 틀린 경우.
                return render(request, 'search/login.html',{'s':True, 'pcode':'LGNF1', 'sm':'운영 로그인 실패 : 비밀번호 오류'})
        else:
            # 아이디가 없거나 틀린경우.
            return render(request, 'search/login.html',{'s':True, 'pcode':'LGNF2', 'sm':'운영 로그인 실패 : 찾을 수 없는 아이디'})
    except:
        return render(request, 'search/login.html', {'s':False, 'pcode':'LGNE1', 'sm':'운영 로그인 실패 : 프로그램 오류.'})

def List(request, ssk, login_id):
    searchkeyword = keywords
    # TODO : log 항목에서 검색처리
    try:
        errorCode = 2000
        # 만일, 로그 다건조회 처리에 실패할 경우 처리코드 2000
        # id 로 검색
        logList = LogModel.objects.filter(Q(operator_id__icontains=keywords)).distinct().order_by('-pub_date')[:50]
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
        logData = LogModel.objects.filter(Q(operator_id=login_id))
        
        if len(logData) >= 1:
            # 어쨌든 데이터가 있는 경우.
            return render(request, 'search/detail.html', {'s':True, 'pcode':'LOGOFS', 'sm':'로그 단건 조회 성공','logData':logData})
        else:
            # 데이터가 없는 경우.
            return render(request, 'search/detail.html', {'s':True, 'pcode':'LOGOFF1', 'sm':'로그 단건 조회 실패 : 조건에 맞는 값이 없습니다.'})
    except:
        # 비밀번호 없이 접근할 경우. 
        return render(request, 'search/detail.html', {'s':False, 'pcode':'LOGOFF2', 'sm':'로그 단건 조회 실패 : 오류'})

def api(request, login_id, ssk, proctype):
    # TODO : 요청한 내용을 처리하고 그 결과를 json/xml 출력
    
    ### Input Data : url+params ###
    # login_id -> API 사용자 ID
    # ssk -> api 키 같은 개념.
    # proctype -> 어떤 API 요청인지 구분
    # POST DATA -> 어떤 요청에는 가변적인 입력이 있을 수 있음. : 플레이어 제재(안할수도 있음)
    ##################
    
    ### Output Data : json ###
    # s -> API 요청이 성공했는지.
    # message -> API 요청 결과 설명 (어떻게 됬는지)
    # proctype -> 처리구분
    # Data Area -> 요청에 따른 출력값
    ##########################
    
    # 1. 먼저 입력을 확인한다. ID == ssk 라면 (2)로 아니면 (5)로
    # 2. 처리구분에 따라 입력을 확인한다.
    # 3. 모두 있으면 처리를 한다.
    # 4. 결과와 내용을 묶어 json 출력한다.
    # 5. 하나라도 이상이 있다면 해당 코드와 메시지만 출력한다. (결과 X)
    return render(request, 'api/xxx.html')

def error(request):
    # TODO : 오류 페이지 출력
    # 에러코드를 받으면, 코드에 맞는 메시지를 출력한다.
    return render(request, 'error/error.html')

