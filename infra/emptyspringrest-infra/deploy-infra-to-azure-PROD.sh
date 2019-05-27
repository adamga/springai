#!/bin/bash


# make sure resource group exists
az.cmd group create --name vacca-app01-prod --location eastus

# deploy infra template
az.cmd group deployment create --resource-group vacca-app01-prod --template-file templates/template.json --parameters parameters/parameters.json --parameters parameters/env-specific/prod/parameters.json
