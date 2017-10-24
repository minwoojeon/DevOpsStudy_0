# -*- coding: utf-8 -*-
"""DevOpsStudy1 URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.8/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
from django.conf.urls import include, url
from django.contrib import admin
from binooApi import views

admin.autodiscover()

urlpatterns = [

    # define main display.    
    url(r'^binooApi/$', views.main, name='main'),
    url(r'^binooApi/login/$', views.loginDefault, name='login'),
    url(r'^binooApi/login/(?P<login_id>\w+)/$', views.login, name='login'),
    
    url(r'^binooApi/(?P<login_id>\w+)/log/(?P<ssk>\w+)/$', views.List, name='list'),
    url(r'^binooApi/(?P<login_id>\w+)/log/(?P<ssk>\w+)/details/(?P<log_id>\w+)$', views.detail, name='detail'),

    url(r'^binooApi/api/(?P<reqId>\w+)/(?P<ssk>\w+)/(?P<proctype>\w+)/$', views.api, name='api'),
    
    url(r'^admin/', include(admin.site.urls)),

]

# 404/ 500 핸들링 참고 http://shsong97.blog.me/220851654913
handler404 = views.error
handler500 = views.error
