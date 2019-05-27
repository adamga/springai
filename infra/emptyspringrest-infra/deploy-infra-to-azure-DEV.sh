#!/bin/bash


# make sure resource group exists
az group create --name vacca-app01-dev --location eastus

# deploy infra template
az group deployment create --resource-group vacca-app01-dev --template-file templates/template.json --parameters parameters/parameters.json --parameters parameters/env-specific/dev/parameters.json
