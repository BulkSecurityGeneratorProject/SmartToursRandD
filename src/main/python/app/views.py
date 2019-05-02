
from datetime import datetime
from django.http import HttpResponse
from django.shortcuts import render, redirect
from app.models import ItemRecommender
from django.views.generic import ListView
from rest_framework import status
from rest_framework.parsers import JSONParser
from rest_framework.response import Response
from rest_framework.views import APIView


class ItemRecommenderView(APIView):
    def post(self, request, format=None):
        ItemRecommender().trainModels()
        return Response(status=status.HTTP_200_OK)

    def get(self, request):
        try:
            customerId = request.query_params['customerId']
        except:
            return Response(status=status.HTTP_404_NOT_FOUND)

        recommendations = ItemRecommender().query(customerId)
        return Response(recommendations, status=status.HTTP_200_OK)
