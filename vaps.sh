#!/bin/bash

checkUser() {
  if [ "`whoami`" == "root" ]; then
   echo "[Pre-Requirement]- You can't root to run this script"
   exit 1
  fi
}
checkUser

if [[ "`git co master`" =~ "Your branch is up to date with 'origin/master'" ]];

then
    echo '1.分支切换成功'
else
    echo 'error:分支切换失败'
    exit
fi

cd /app/repo/distr-backend
git pull

if [[ "`git pull`" == "Already up to date." ]];

then
    echo '2.获取代码成功'
else
    echo 'error:获取代码失败'
    exit
fi

mvn clean install -U -DskipTests

