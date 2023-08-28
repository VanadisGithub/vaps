#!/bin/bash

checkUser() {
  if [ "`whoami`" == "root" ]; then
   echo "You can't root to run this script"
   exit 1
  fi
}
checkUser

#获取当前脚本相对路径
bin_path=`cd $(dirname $0); pwd`


APP_NAME=dingtalk-oa
BASE=$(dirname $bin_path)
conf=${BASE}/conf/application.yml
log=${BASE}/conf/logback.xml
XMS=1024m
XMX=2014m
CLASS='com.xxx.xxx.Application'

export LANG=en_US.UTF-8
export BASE=$BASE

#导入系统变环境量
source /etc/profile

if [ ! -d $BASE/logs ] ; then
    mkdir -p ${BASE/logs}
fi

if [ -z "$JAVA" ] ; then
  JAVA=$(which java)
fi

if [ -z "$JAVA" ]; then
    echo "Cannot find a Java JDK. Please set either set JAVA or put java (>=1.8) in your PATH." 2>&2
    exit 1
fi

if [[ $# -eq 2 ]]; then
    profile=$2
fi

#1.输出进程|过滤除class以外的进程|输出第二字段（pid）
#2.打印pid|去除空格转行|统计输出个数
#3.大于2 杀死进程
# Source function library.
#. /etc/init.d/functions
check_ps(){
  Pid=`ps -ef | grep ${CLASS} | awk -F ' ' '{print $2}'`
  app_wc=`echo ${Pid} |tr ' ' '\n' |wc -l`
  if [ ${app_wc} -gt 2 ];then
    echo ${Pid} | tr ' ' '\n' | xargs kill -HUP
  fi
}

check_ps


java_start(){

  if [ -e ${conf} -a -e ${log} ]
  then
      JAVA_OPTS="-server -Xms${XMS} -Xmx${XMX} -Xloggc:${BASE}/logs/gc.log -verbose:gc -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${BASE}/logs"
      APP_OPTS="--spring.profiles.active=product --spring.config.location=${conf} --logging.config=${log}"
      for i in ${BASE}/lib/*;
          do CLASSPATH=$i:"$CLASSPATH";
      done
      ${JAVA} ${JAVA_OPTS} -classpath .:$CLASSPATH $CLASS $APP_OPTS 1>/dev/null 2>&1 &
      tail -f ${CATALINA_OUT}
      echo $!
  else
      echo "configuration file is not exist!!!"
  fi

}

app_start() {
echo -n "Starting $APP_NAME "
if [ $app_wc -eq 2 ] ; then
    Pid=`ps -ef | grep $CLASS | grep -v grep | awk -F ' ' '{print $2}'`
    echo -e "already running as process" PID=$Pid
    exit 1
elif [ $app_wc -eq 1 ] ; then
  java_start




#  if [ -e $conf -a -e $log ]
#  then
#      JAVA_OPTS="-server -Xms$XMS -Xmx$XMX -Xloggc:$BASE/logs/gc.log -verbose:gc -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=$BASE/logs"
#      APP_OPTS="--spring.profiles.active=product --spring.config.location=$conf --logging.config=$log"
#      for i in $BASE/lib/*;
#          do CLASSPATH=$i:"$CLASSPATH";
#      done
#      $JAVA $JAVA_OPTS -classpath .:$CLASSPATH com.dtstack.plat.springcloud.eureka.Application $APP_OPTS 1>/dev/null 2>&1 &
#      echo $!
#  else
#      echo "configuration file is not exist!!!"
#  fi




elif [ $app_wc -gt 2 ] ; then
    echo "$APP_NAME Error ;please restart $APP_NAME"
fi
}


app_console() {
echo -n "Starting $APP_NAME "
if [ ${app_wc} -eq 2 ];then
    Pid=`ps -ef | grep $CLASS | grep -v grep | awk -F ' ' '{print $2}'`
    echo -e "already running as process" echo $Pid
    exit 1
elif [ $app_wc -gt 2 ];then
    ps -ef | grep $CLASS | grep -v grep | awk -F ' ' '{print $2}'|xargs kill -HUP > /dev/null 2>&1
    java_start
    if [ -e $conf -a -e $log ]
    then
        JAVA_OPTS="-server -Xms$XMS -Xmx$XMX -Xloggc:$BASE/logs/gc.log -verbose:gc -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=$BASE/logs "
        APP_OPTS="--spring.profiles.active=product --spring.config.location=$conf --logging.config=$log"
        for i in $BASE/lib/*;
            do CLASSPATH=$i:"$CLASSPATH";
        done
        $JAVA $JAVA_OPTS -classpath .:$CLASSPATH com.dtstack.plat.springcloud.eureka.Application $APP_OPTS
    else
        echo "configuration file is not exist!!!"
    fi
else
  java_start

#    if [ -e $conf -a -e $log ]
#    then
#        JAVA_OPTS="-server -Xms$XMS -Xmx$XMX -Xloggc:$BASE/logs/gc.log -verbose:gc -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=$BASE/logs "
#        APP_OPTS="--spring.profiles.active=product --spring.config.location=$conf --logging.config=$log"
#        for i in $BASE/lib/*;
#            do CLASSPATH=$i:"$CLASSPATH";
#        done
#        $JAVA $JAVA_OPTS -classpath .:$CLASSPATH com.dtstack.plat.springcloud.eureka.Application $APP_OPTS
#    else
#        echo "configuration file is not exist!!!"
#    fi
fi
}


app_stop() {
if [ $app_wc -lt 2 ];then
  echo "$APP_NAME is not running."
  exit
fi


echo -e "`hostname`: stopping $APP_NAME $pid ... "
kill -HUP $Pid > /dev/null 2>&1

ret=$?
[ $ret -eq 0 ] && echo "stop success" || echo "stop"; echo
}


app_restart() {
if [ $app_wc -ge 2 ];then
  app_stop
  waitting 5
fi
check_ps
app_start
}

app_status() {
if [ $app_wc -eq 2 ];then
  Pid=`ps -ef | grep $CLASS | grep -v grep | awk -F ' ' '{print $2}'`
  echo "$APP_NAME $Pid is running"
else
  ps -ef | grep $CLASS | grep -v grep | awk -F ' ' '{print $2}'|xargs kill -HUP > /dev/null 2>&1
  echo "$APP_NAME  is Stop"
fi
}

waitting() {
  local seconds=$1
  for i in `seq 1 $seconds`; do
    sleep 1
    ((exptime++))
    echo -n -e "\rWaitting: $exptime..."
  done
  echo -n -e "\n"
}

case "$1" in
  start)
    app_start
    ;;

  console)
    app_console
    ;;

  stop)
    app_stop
    ;;

  restart)
    app_restart
    ;;

  status)
    app_status
    ;;

  *)
    echo "Usage: $0 {console|start|stop|restart|status}"
    exit 1
    ;;

esac