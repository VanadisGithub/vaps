#!/bin/bash

checkUser() {
  if [ "`whoami`" == "root" ]; then
   echo "[Pre-Requirement]- You can't root to run this script"
   exit 1
  fi
}
checkUser

echo `git co master`

cd /app/repo/distr-backend
git pull

if [ "`git pull`" == "Already up to date." ];

then
    echo '2.获取代码成功'
fi

