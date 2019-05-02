from django.urls import path
from app import views

item_recommender_view = views.ItemRecommenderView.as_view()

urlpatterns = [
    path('item-recommender/', item_recommender_view),
]
