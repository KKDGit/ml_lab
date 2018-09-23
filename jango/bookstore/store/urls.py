from django.conf.urls import url
from . import views

urlpatterns = [
    # Examples:
    # url(r'^$', 'bookstore.views.home', name='home'),
    # url(r'^blog/', include('blog.urls')),
    url(r'^$', views.store, name='index'),
]
