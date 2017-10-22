# -*- coding: utf-8 -*-

from django.contrib import admin
from binooApi.models import LoginModel, LogModel, ApiModel

# Register your models here.

admin.site.register(LoginModel)
admin.site.register(LogModel)
admin.site.register(ApiModel)
