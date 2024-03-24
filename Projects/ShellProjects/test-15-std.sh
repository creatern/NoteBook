#!/bin/bash

# 将文件描述符3重定向到标准输出（2）
exec 3>&1
exec 1>i_stdout.txt

echo "Hello_1"

# 将标准输入文件（2）重定向到 文件描述符3
exec 1>&3

echo "Hello_2"
