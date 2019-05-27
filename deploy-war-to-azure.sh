#!/bin/bash


cd target/
rm -rf webapps
rm -f webapps.zip
mkdir webapps
cp *.war webapps
zip -r webapps.zip webapps

az.cmd webapp deployment source config-zip --resource-group vacca-web06 --name vacca-web06 --src webapps.zip 

