#!/bin/bash


cd target/
rm -rf webapps
rm -f webapps.zip
mkdir webapps
cp *.war webapps
zip -r webapps.zip webapps

az webapp deployment source config-zip --resource-group vacca-app01-dev --name vacca-app01-dev --src webapps.zip 

