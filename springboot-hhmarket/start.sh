#!/bin/bash
# $1: application.property file
# $2: restapi.jar file 
java -jar -Dspring.config.location="$1" "$2"