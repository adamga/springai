To deploy run the following:

# deletion of group
az group delete -n vacca-app01-dev -y

# creation of group
az group create --name vacca-app01-dev --location eastus



# create full environments

#DEV
az group deployment create --resource-group vacca-app01-dev --template-file templates/template.json --parameters parameters/parameters.json --parameters parameters/env-specific/dev/parameters.json

#TEST
az group deployment create --resource-group vacca-app01-tst --template-file templates/template.json --parameters parameters/parameters.json --parameters parameters/env-specific/tst/parameters.json

#PROD
az group deployment create --resource-group vacca-app01-prod --template-file templates/template.json --parameters parameters/parameters.json --parameters parameters/env-specific/prod/parameters.json






# to delete individual resources
az webapp delete -g vacca-group01 -n vacca-web13
az appservice plan delete --yes -g vacca-group01 -n vacca-asp01



Note: In order to wait for the website to be up, explore using some curl command to wait for a HTTP response: 200 OK


** Azure blob upload/download

# upload
az storage blob upload -c temp-data1 -f emptyspringrest-infra.zip -n emptyspringrest-infra


