#!/bin/bash
## Author Mine
## Jenkins远程部署脚本
## Date 2019/07/22
#export 确保项目启动之后才不会被Jenkins杀掉。
export BUILD_ID=dontKillMe
#解决中文乱码
export LANG="en_US.UTF-8"

#app包名
app_name=$2
#app包前缀
app_prefix="nookblog"
#启动指定
spring_active="prd"
#启动目录
deploy_path="/usr/local/app/java_app/nookblog"
#java命令目录
java_path="/usr/java/jdk10/bin/java"

#进入工作目录
cd ${deploy_path}
echo 'deploy_path:' ${deploy_path}

#选择按名称倒序排列第一个app包
if [ -z $app_name ];then
    app_name=`ls -r|grep .jar$ |grep $app_prefix | head -n1`
fi
echo "app_name : " ${app_name}

#杀掉旧进程ID
old_pid=`ps -ef |grep java |grep $app_prefix |grep -v grep |awk '{print $2}'`
if [ -z $old_pid ];then
	echo "未找到旧进程..."
else
	echo "停止旧进程PID : $old_pid"
	kill $old_pid
	sleep 2
fi

echo "$app_name 启动中..."
nohup $java_path -Dspring.profiles.active=$spring_active -jar ./$app_name -XX:+UseG1GC -XX:+HeapDumpOnOutOfMemoryError -Xms128M -Xmx512M  > /dev/null 2>&1 &
sleep 2
new_pid_info=`ps -ef |grep java |grep $app_prefix |grep -v grep |awk '{print $2"\t"$10}'`
echo "新进程信息: ${new_pid_info}"

#服务启动检测
check_url="https://yebukong.com/minecms/api/appInfo"
check_attempts=10
check_timeout=6
online=false
echo "检测服务启动状态"
for (( i=1; i<=${check_attempts}; i++ ))
do
  sleep ${check_timeout}
  code=`curl -sL --connect-timeout 20 --max-time 30 -w "%{http_code}\\n" "${check_url}" -o /dev/null`
  echo "服务检测返回结果：$code"
  if [ "${code}" = "200" ]; then
    echo "已检测到服务：${check_url}"
    sleep 10
    online=true
    break
  else
    echo "未检测到服务，等待 ${check_timeout} 秒后重试"
  fi
done

if $online; then
  echo "服务检查结束，服务启动正常"
  # 查找旧app包
  backupApps=`ls |grep -wv $app_name$ |grep .jar$ |grep ^$app_prefix`
  if [ ! -d "backup" ];then
        mkdir backup
  fi
  
  if [ -z $backupApps ];then
   echo "未找到旧app包..."
  else
    #移动到backup目录
    for i in ${backupApps[@]}
    do
	  echo "backup" $i
	  mv $i backup
    done
  fi
  exit 0
else
  echo "服务检查结束，服务启动失败"
  exit 1
fi
