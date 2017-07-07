#!/bin/bash

echo 'Mematikan aplikasi yang sedang berjalan ...'
kill `cat save_pid.txt`

echo 'Menjalankan ulang aplikasi ...'
nohup mvn clean spring-boot:run -Drun.jvmArguments="-Xmx2g" -Drun.profiles=production > my.log 2>&1 & echo $! > save_pid.txt
