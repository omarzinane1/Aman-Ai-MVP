FROM jupyter/tensorflow-notebook:latest

USER root

# Installation des dépendances depuis le fichier requirements
COPY requirements.txt /tmp/requirements.txt
RUN pip install --no-cache-dir -r /tmp/requirements.txt

USER jovyan
WORKDIR /home/jovyan/work

# Optionnel : on peut copier directement les notebooks au build
# COPY notebooks/ ./notebooks/