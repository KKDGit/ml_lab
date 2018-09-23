from django.conf.urls import include, url
from django.contrib import admin
from . import views
urlpatterns = [
    # Examples:
    # url(r'^$', 'bookstore.views.home', name='home'),
    # url(r'^blog/', include('blog.urls')),
    url(r'^$', views.index, name='main_index'),
    url(r'^store/', include('store.urls'), name='store'),
    url(r'^admin/', include(admin.site.urls)),
]
