#!/bin/bash

cd university
startdir=$(pwd)
declare -a arr=("curriculum" "api_gateway" "account" "evaluation" "faculty" "invoice" "learningPlatform" "notification" "registration")

for i in "${arr[@]}"
do
  cd "$i"
  ./mvnw clean
  cd "$startdir"
done

