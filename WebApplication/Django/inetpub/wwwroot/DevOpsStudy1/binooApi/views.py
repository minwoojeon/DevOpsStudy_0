# -*- coding: utf-8 -*-
from django.shortcuts import render, get_object_or_404
from django.http import HttpResponseRedirect, HttpResponse
from django.core.urlresolvers import reverse
from binooApi.models import LoginModel, LogModel, ApiModel
from django.db.models import Query

# Global value
searchkeyword = ""

# Create your views here.

def main(request):
    # TODO : main 화면 처리
    return render(request, 'search/main.html')

def login(request, login_id):
    # TODO : login 처리
    return render(request, 'search/login.html')

def List(request, keywords):
    searchkeyword = keywords
    # TODO : log 항목에서 검색처리
    return render(request, 'search/list.html', searchkeyword)

def detail(request, log_id):
    # TODO : log 항목에서 상세 처리내역 조회
    return render(request, 'search/detail.html', searchkeyword)

def api(request, log_id, ssk, proctype):
    # TODO : 요청한 내용을 처리하고 그 결과를 json/xml 출력
    return render(request, 'api/xxx.html')
