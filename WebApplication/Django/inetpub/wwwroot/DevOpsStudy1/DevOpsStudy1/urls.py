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
    url(r'^binooApi/login/(?P<login_id>\d+)/$', views.login, name='login'),
    
    url(r'^binooApi/log(?P<keywords>\d+)/$', views.List, name='list'),
    url(r'^binooApi/log(?P<keywords>\d+)/details/(?P<log_id>\d+)$', views.detail, name='detail'),

    url(r'^binooApi/api/(?P<login_id>\d+)/(?P<ssk>\d+)/(?P<proctype>\d+)/$', views.api, name='api'),
    
    url(r'^admin/', include(admin.site.urls)),

]
