# coding=utf-8

import json

from __future__ import unicode_literals

from django.db import models
from django.utils import timezone
from django.utils.encoding import python_2_unicode_compatible

# Create your models here.

# 나중에 한번에 Spring/Ruby/Django 모두 데이터 명 맞추기!
# 일단은 돌아가게끔만 만들기

# unicode -> 한글이 깨짐
@python_2_unicode_compatible
class LoginModel (models.Model):
    operator_id = models.CharField(max_length = 200)
    operator_pw = models.CharField(max_length = 100)
    operator_ssk = models.CharField(max_length = 250)
    regdate = models.DateTimeField(default = timezone.now)
    last_login = models.DateTimeField(default = timezone.now)

    def publish (self):
        # 최근 로그인한 내용 저장. (근무시간과 비교가능)
        self.last_login = timezone.now()
        self.save()
    
    def __str__ (self):
        return self.operator_id


@python_2_unicode_compatible
class LogModel (models.Model):
    proc_time = models.DateTimeField(default = timezone.now)
    operator_id = models.CharField(max_length = 200)
    operator_ssk = models.CharField(max_length = 250)
    result_s = models.CharField(max_length = 1)
    result_message = models.CharField(max_length = 300)
    result_data = models.CharField(max_length = 2000)

    def publish (self):
        # 처리시간과 내용 저장.
        self.proc_time = timezone.now()
        self.save()
    
    # json 참고- https://stackoverflow.com/questions/22340258/django-list-field-in-model   
    def setDataJson(self, arr):
        self.result_data = json.dumps(arr)
    
    def getDataJson(self):
        return json.loads(self.result_data)
    
    def __str__ (self):
        return self.proc_time

@python_2_unicode_compatible
class ApiModel (models.Model):
    proc_time = models.DateTimeField(default = timezone.now)
    operator_id = models.CharField(max_length = 200)
    operator_ssk = models.CharField(max_length = 250)
    result_s = models.CharField(max_length = 1)
    result_message = models.CharField(max_length = 300)
    result_data = models.CharField(max_length = 2000)
    
    def publish (self):
        # 처리시간과 내용 저장.
        self.proc_time = timezone.now()
        self.save()
    
    # json 참고- https://stackoverflow.com/questions/22340258/django-list-field-in-model   
    def setDataJson(self, arr):
        self.result_data = json.dumps(arr)
    
    def getDataJson(self):
        return json.loads(self.result_data)
    
    def __str__ (self):
        return self.result_s


# models.IntegerField(default=0)
