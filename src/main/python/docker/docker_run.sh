#!/usr/bin/env bash
docker run -p 8000:8000 \
    -v ~/volumes/smart-tours-ml/trained_models:/usr/src/app/app/trained_models \
    -v ~/volumes/smart-tours-ml/data:/usr/src/app/app/data \
    smart-tours-ml:latest
