#!/usr/bin/env bash

export JAVA_HOME=/usr/lib/jvm/java-1.8-openjdk
export PATH=$JAVA_HOME/bin:$PATH

LOGS_DIR=/home/stone/logs

DEPLOY_DIR=`pwd`

if test -e ${DEPLOY_DIR}/env ; then
	source ${DEPLOY_DIR}/env
fi

SERVER_NAME=stone-config

jar_name=config.jar
port=8081
profile=prod


function start() {
    if [ ! -d $LOGS_DIR ]; then
        mkdir $LOGS_DIR
    fi


    exec java $JAVA_OPTS -Dlogging.path=$LOGS_DIR/$SERVER_NAME -jar $jar_name --server.port=$port --spring.profiles.active=$profile --management.port=1$port > /dev/null 2>&1

    echo "$jar_name ($profile) at port $port has started!"
}

function stop() {
    pid=`ps -ef | grep $jar_name | grep -v grep | grep -v PPID | awk '{ print $2}'`
    if [ -n "$pid" ];then
        echo "killed $pid of $SERVER_NAME($jar_name)"
        kill -9 $pid
    else
         echo "$SERVER_NAME($jar_name) PID Not Found"
    fi
}

function restart()
{
    stop $@
    sleep 2
    start $@
}

function execute()
{
    command=$1
    case $command in
        start)
            start $2
            ;;

        stop)
            stop
            ;;

        restart)
            restart $2
            ;;

        *)
            echo "unknown command $command"
            exit 0
            ;;
    esac
}

if [ $# -lt 1 ]; then
    echo "ERROR:usage ./control.sh start|stop|restart "
    exit 0
fi

execute $@
