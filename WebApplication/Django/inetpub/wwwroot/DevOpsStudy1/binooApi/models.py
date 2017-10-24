# coding=utf-8

from __future__ import unicode_literals

import json
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
    userId = models.CharField(max_length = 30)
    userPw = models.CharField(max_length = 50)
    passwordIncurrectCnt = models.IntegerField(default=0)
    state = models.IntegerField(default=1)
    apiKey = models.CharField(max_length = 100)
    job = models.CharField(max_length = 30)
    level = models.IntegerField(default=0)
    gold = models.IntegerField(default=0)

    def publish (self):
        self.save()
    
    def __str__ (self):
        return self.userId

@python_2_unicode_compatible
class LOG_DATA (models.Model):
    procSeq = models.IntegerField(default=1)
    procTime = models.CharField(max_length = 50)
    procType = models.CharField(max_length = 30)
    procResult = models.CharField(max_length = 1)
    procMessage = models.CharField(max_length = 30)
    reqAddress = models.CharField(max_length = 30)
    userId = models.CharField(max_length = 30)

    def publish (self):
        self.save()
    
    def __str__ (self):
        return self.procSeq

@python_2_unicode_compatible
class CHAT_DATA (models.Model):
    chatSeq = models.IntegerField(default=1)
    fromUserId = models.CharField(max_length = 30)
    toUserId = models.CharField(max_length = 30)
    state = models.IntegerField(default=5)    
    chatTime = models.CharField(max_length = 50)
    retry = models.IntegerField(default=1)
    chatRead = models.IntegerField(default=0)
    chatTerm = models.IntegerField(default=0)
    chatContent = models.CharField(max_length = 1000)

    def publish (self):
        self.save()
    
    def __str__ (self):
        return self.chatSeq

@python_2_unicode_compatible
class ITEM (models.Model):
    ICODE = models.CharField(max_length = 10)
    ITYPE = models.CharField(max_length = 10)
    INAME = models.CharField(max_length = 20)
    STATE_ATT = models.IntegerField(default=0)
    STATE_DEF = models.IntegerField(default=0)
    IDESCRIPTION = models.CharField(max_length = 1000)
    IICON = models.CharField(max_length = 1000)
    IIMAGE = models.CharField(max_length = 1000)
    
    def publish (self):
        self.save()
    
    def __str__ (self):
        return self.ICODE

@python_2_unicode_compatible
class ITEM_INSTANCE (models.Model):
    inventorySeq = models.IntegerField(default=0)
    userId = models.CharField(max_length = 30)
    ICODE = models.CharField(max_length = 10)
    inventoryNumber = models.IntegerField(default=0)
    inventorySlotNumber = models.IntegerField(default=0)
    
    def publish (self):
        self.save()
    
    def __str__ (self):
        return self.inventorySeq
