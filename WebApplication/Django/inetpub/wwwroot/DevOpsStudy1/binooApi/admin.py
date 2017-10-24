# -*- coding: utf-8 -*-

from django.contrib import admin
from binooApi.models import USERS, LOG_DATA, CHAT_DATA, ITEM, ITEM_INSTANCE

# Register your models here.

admin.site.register(USERS)
admin.site.register(LOG_DATA)
admin.site.register(CHAT_DATA)
admin.site.register(ITEM)
admin.site.register(ITEM_INSTANCE)
