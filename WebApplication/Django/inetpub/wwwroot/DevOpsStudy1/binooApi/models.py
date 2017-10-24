# coding=utf-8

from __future__ import unicode_literals

from django.db import models
from django.utils import timezone
from django.utils.encoding import python_2_unicode_compatible

# Create your models here.

'''
@Py Name : Models.py
@Description : model. in spring, like a vo(model)
@author botbinoo@naver.com
@since 2017.10.22
@last modified 2017.10.24
@version test

Copyright (C) by botbinoo's All right reserved.
'''

# unicode 처리
@python_2_unicode_compatible
class USERS (models.Model):
    userid = models.CharField(max_length = 30)
    userpw = models.CharField(max_length = 50)
    passwordincurrectCnt = models.IntegerField(default=0)
    state = models.IntegerField(default=1)
    apikey = models.CharField(max_length = 100)
    job = models.CharField(max_length = 30)
    level = models.IntegerField(default=0)
    gold = models.IntegerField(default=0)

    def publish (self):
        self.save()
    
    def __str__ (self):
        return self.userId
    
    def __unicode__(self):
        return self.userid

@python_2_unicode_compatible
class LOG_DATA (models.Model):
    procseq = models.IntegerField(default=1)
    proctime = models.CharField(max_length = 50)
    proctype = models.CharField(max_length = 30)
    procresult = models.CharField(max_length = 1)
    procmessage = models.CharField(max_length = 30)
    reqaddress = models.CharField(max_length = 30)
    userid = models.CharField(max_length = 30)

    def publish (self):
        self.save()
    
    def __str__ (self):
        return self.procseq

@python_2_unicode_compatible
class CHAT_DATA (models.Model):
    chatseq = models.IntegerField(default=1)
    fromuserId = models.CharField(max_length = 30)
    touserId = models.CharField(max_length = 30)
    state = models.IntegerField(default=5)    
    chattime = models.CharField(max_length = 50)
    retry = models.IntegerField(default=1)
    chatread = models.IntegerField(default=0)
    chatterm = models.IntegerField(default=0)
    chatcontent = models.CharField(max_length = 1000)

    def publish (self):
        self.save()
    
    def __str__ (self):
        return self.chatseq

@python_2_unicode_compatible
class ITEM (models.Model):
    icode = models.CharField(max_length = 10)
    itype = models.CharField(max_length = 10)
    iname = models.CharField(max_length = 20)
    state_att = models.IntegerField(default=0)
    state_def = models.IntegerField(default=0)
    idescription = models.CharField(max_length = 1000)
    iicon = models.CharField(max_length = 1000)
    iimage = models.CharField(max_length = 1000)
    
    def publish (self):
        self.save()
    
    def __str__ (self):
        return self.icode

@python_2_unicode_compatible
class ITEM_INSTANCE (models.Model):
    inventoryseq = models.IntegerField(default=0)
    userid = models.CharField(max_length = 30)
    icode = models.CharField(max_length = 10)
    inventorynumber = models.IntegerField(default=0)
    inventoryslotNumber = models.IntegerField(default=0)
    
    def publish (self):
        self.save()
    
    def __str__ (self):
        return self.inventoryseq
