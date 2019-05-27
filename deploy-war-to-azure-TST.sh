#!/bin/bash


cd target/
rm -rf webapps
rm -f webapps.zip
mkdir webapps
cp *.war webapps
zip -r webapps.zip webapps

az webapp deployment source config-zip --resource-group vacca-app01-tst --name vacca-app01-tst --src webapps.zip 

