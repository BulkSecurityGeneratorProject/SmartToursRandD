from app.models import LogisticClassifier
from rest_framework import status
from rest_framework.response import Response
from rest_framework.views import APIView


class LogisticRegressionView(APIView):
    def post(self, request, format=None):
        LogisticClassifier().train()
        return Response(status=status.HTTP_200_OK)

    def get(self, request):
        try:
            distances = request.query_params['distances']
        except:
            return Response(status=status.HTTP_404_NOT_FOUND)
        response = LogisticClassifier().query(distances)
        return Response(response, status=status.HTTP_200_OK)
