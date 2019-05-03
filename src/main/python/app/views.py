from app.models import LogisticClassifier
from rest_framework import status
from rest_framework.response import Response
from rest_framework.views import APIView

from rest_framework.parsers import JSONParser


class LogisticRegressionView(APIView):
    def put(self, request):
        LogisticClassifier().train()
        return Response(status=status.HTTP_200_OK)

    def post(self, request):
        inputData = JSONParser().parse(request)
        try:
            response = LogisticClassifier().query(inputData)
            return Response(response, status=status.HTTP_200_OK)
        except:
            return Response(status=status.HTTP_400_BAD_REQUEST)
