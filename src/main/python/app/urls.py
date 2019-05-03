from django.urls import path
from app import views

logistic_regression_view = views.LogisticRegressionView.as_view()

urlpatterns = [
    path('logistic-regression/', logistic_regression_view),
]
