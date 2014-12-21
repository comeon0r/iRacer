#!/bin/bash


if [ $# \< "1" ];then
	echo "Usage: $1 sourcCodeName(Don't include suffix) programPara1 programPara2"
	exit -1
fi

 
make clean
make  NAME=$1

echo "Compilation finished!-----------------------Start run program:"

arg=($@) 
#./$1_final  "${arg[@]:1}"  > output.txt
#cat output.txt 
